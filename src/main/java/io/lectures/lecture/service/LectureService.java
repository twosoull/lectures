package io.lectures.lecture.service;

import io.lectures.lecture.dto.LectureDateDto;
import io.lectures.lecture.dto.RequestLectureDateDto;
import io.lectures.lecture.entity.LectureApplicant;
import io.lectures.lecture.entity.LectureDate;
import io.lectures.lecture.repository.LectureApplicantRepository;
import io.lectures.lecture.repository.LectureDateRepository;
import io.lectures.lecture.repository.LectureRepository;
import io.lectures.user.entity.Users;
import io.lectures.user.repository.UserHistoryRepository;
import io.lectures.user.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import org.springframework.util.ObjectUtils;

import java.time.LocalDate;

@Service
@RequiredArgsConstructor
@Slf4j
public class LectureService {

    private final LectureRepository lectureRepository;
    private final LectureApplicantRepository lectureApplicantRepository;
    private final LectureDateRepository lectureDateRepository;
    private final UserRepository userRepository;
    private final UserHistoryRepository userHistoryRepository;

    public boolean applyLecture(RequestLectureDateDto requestLectureDateDto){
        LectureDate lectureDate = lectureDateRepository.findById(requestLectureDateDto.getLectureDateId());

        if(ObjectUtils.isEmpty(lectureDate)){
            return false;
        }

        LectureDateDto lectureDateDto = LectureDateDto.from(lectureDate);

        //* 뒤로 미루기 특강은 4월20일에 열림
        boolean result = validDateLecture(lectureDateDto.getApplyStartLocalDate()
                                        ,lectureDateDto.getApplyEndLocalDate());

        if(result){
            //신청한 적이 있는지 조회
            result = alreadyApply(requestLectureDateDto.getUserId()
                                    ,lectureDateDto.getId());
        }

        if(result){
            //신청자가 30명이 초과 되었는지 확인
            result = notPossibleIfOverMaxStudents(lectureDateDto.getId(),lectureDateDto.getMaxStudents());
        }

        if(result) {
            //위 사항이 다 맞다면 저장
            Users users = userRepository.findById(requestLectureDateDto.getUserId());
            String status = "등록";
            LectureApplicant lectureApplicant = new LectureApplicant(
                    status,lectureDate,users
            );
            LectureApplicant save = lectureApplicantRepository.save(lectureApplicant);
            /*성공 실패값 확인해서 마지막 result 처리*/
        }

        //히스토리 저장
        //User로 이동해야할까?
        //userHistoryRepository.save(userId,lectureId,status);

        //완료메세지 전달

        return result;
    }

    public boolean validDateLecture(LocalDate applyStartLocalDate,
                                    LocalDate applyEndLocalDate) {

        LocalDate today = LocalDate.now();
        boolean isBefore = applyStartLocalDate.isBefore(today)
                || applyStartLocalDate.isEqual(today);
        boolean isAfter = applyEndLocalDate.isAfter(today)
                || applyEndLocalDate.isEqual(today);
        boolean matched = isAfter && isBefore;

        if(!matched){
            return false;
        }
        return true;
    }

    public boolean alreadyApply(Long userId, Long lectureDateId) {

        int count = lectureApplicantRepository.countByUserIdAndLectureDateId(userId, lectureDateId);

        if(count > 0){
            return false;
        }
        return true;
    }

    public boolean notPossibleIfOverMaxStudents(Long lectureDateId,int maxStudent) {
        String status = "등록";
        int count = lectureApplicantRepository
               .countByLectureDateIdAndStatus(lectureDateId, status);

        if(count >= maxStudent){
            return false;
        }
        return true;
    }
}
