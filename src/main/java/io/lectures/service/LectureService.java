package io.lectures.service;

import io.lectures.dto.RequestLectureDateDto;
import io.lectures.dto.ResponseLectureApplicantDto;
import io.lectures.dto.ResponseLectureDto;
import io.lectures.entity.Lecture;
import io.lectures.entity.LectureApplicant;
import io.lectures.entity.LectureDate;
import io.lectures.repository.LectureApplicantRepository;
import io.lectures.repository.LectureDateRepository;
import io.lectures.repository.LectureRepository;
import io.lectures.entity.Users;
import io.lectures.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.ObjectUtils;
import java.util.List;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureApplicantRepository lectureApplicantRepository;
    private final LectureDateRepository lectureDateRepository;
    private final UserRepository userRepository;

    @Transactional
    public ResponseLectureApplicantDto applyLecture(RequestLectureDateDto requestLectureDateDto){
        LectureDate lectureDate = lectureDateRepository.findById(requestLectureDateDto.getLectureDateId());
        if (ObjectUtils.isEmpty(lectureDate)) throw new RuntimeException("강의가 없습니다.");

        lectureDate.apply();

        //이미 저장된 유저인지 확인
        alreadyApplyValid(requestLectureDateDto.getUserId(), requestLectureDateDto.getLectureDateId());

        //현재 유저 저장기능이 없음
        //Users users = userRepository.findById(requestLectureDateDto.getUserId());
        Users user = new Users(requestLectureDateDto.getUserId(),"홍길동");
        String status = "등록";
        LectureApplicant lectureApplicant = new LectureApplicant(
                status,lectureDate,user
        );

        LectureApplicant saveLectureApplicant = lectureApplicantRepository.save(lectureApplicant);

        return ResponseLectureApplicantDto.from(saveLectureApplicant);
    }

    public List<ResponseLectureDto> lectures(){
        List<Lecture> lectures = lectureRepository.findAll();
        if (lectures.size() == 0) {
            //예외처리
        }
        return lectures.stream().map(
               l -> ResponseLectureDto.from(l)).collect(Collectors.toList());
    }

    public List<ResponseLectureApplicantDto> application(Long userId){
        List<LectureApplicant> lectureApplicants = lectureApplicantRepository.findByUserId(userId);
        if (lectureApplicants.size() == 0) {throw new RuntimeException("신청 기록이 없습니다.");};
        return lectureApplicants.stream().map(
                lectureApplicant -> ResponseLectureApplicantDto.from(lectureApplicant)
        ).toList();
    }

    public void alreadyApplyValid(Long userId, Long lectureDateId) {
        int count = lectureApplicantRepository.countByUserIdAndLectureDateId(userId, lectureDateId);

        if (count > 0) {
            throw new RuntimeException("이미 신청한 강의입니다.");
        }
    }
}
