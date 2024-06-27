package io.lectures.service;

import io.lectures.dto.RequestLectureDateDto;
import io.lectures.dto.ResponseLectureApplicantDto;
import io.lectures.entity.LectureApplicant;
import io.lectures.entity.LectureDate;
import io.lectures.repository.LectureApplicantRepository;
import io.lectures.repository.LectureDateRepository;
import io.lectures.repository.LectureRepository;
import io.lectures.entity.Users;
import io.lectures.repository.UserRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.time.LocalDateTime;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.doReturn;

@ExtendWith(MockitoExtension.class)
class LectureServiceTest {

    @InjectMocks
    LectureService lectureService;

    @Mock
    LectureRepository lectureRepository;

    @Mock
    LectureApplicantRepository lectureApplicantRepository;

    @Mock
    LectureDateRepository lectureDateRepository;

    @Mock
    UserRepository userRepository;

    //1) 특정 userId 로 선착순으로 제공되는 특강을 신청하는 API 를 작성합니다.
    //2) 동일한 신청자는 한 번의 수강 신청만 성공할 수 있습니다.
    //3) 특강은 `4월 20일 토요일 1시` 에 열리며, 선착순 30명만 신청 가능합니다.
    //4) 이미 신청자가 30명이 초과되면 이후 신청자는 요청을 실패합니다.
    //5)어떤 유저가 특강을 신청했는지 히스토리를 저장해야한다.
    @Test
    void applyLecture_test(){

        //given
        Long lectureDateId = 1L;
        LocalDateTime applyStartDate = LocalDateTime.of(2024, 6, 23, 14, 30);
        LocalDateTime applyEndDate = LocalDateTime.of(2024, 6, 30, 14, 30);
        LocalDateTime lectureDay = LocalDateTime.of(2023, 6, 30, 14, 30);
        int maxStudents = 30;

        LectureDate lectureDate = new LectureDate(lectureDateId
                , applyStartDate
                , applyEndDate
                , lectureDay
                , maxStudents);

        doReturn(lectureDate).when(lectureDateRepository)
                .findById(lectureDateId);

        Long userId = 1L;
        int alreadyCount = 0;
        doReturn(alreadyCount).when(lectureApplicantRepository).countByUserIdAndLectureDateId(userId, lectureDateId);

        Users user = new Users(userId,"홍길동");
        String status = "등록";
        Long lectureApplicantId = 1L;
        LectureApplicant lectureApplicant = new LectureApplicant(
                lectureApplicantId, status,lectureDate,user
        );
        doReturn(lectureApplicant).when(lectureApplicantRepository).save(any(LectureApplicant.class));

        //when
        RequestLectureDateDto requestLectureDateDto = new RequestLectureDateDto();
        requestLectureDateDto.setLectureDateId(lectureDateId);
        requestLectureDateDto.setUserId(userId);

        ResponseLectureApplicantDto result = lectureService.applyLecture(requestLectureDateDto);

        //then
        Assertions.assertEquals(result.getId(), lectureApplicantId);
    }

    @Test
    void alreadyApply_test() {
        //given
        Long userId = 1L;
        int count = 1;
        Long lectureDateId = 1L;
        doReturn(count).when(lectureApplicantRepository).countByUserIdAndLectureDateId(userId, lectureDateId);

        //when
        Assertions.assertThrows(RuntimeException.class,
                ()->{lectureService.alreadyApplyValid(userId, lectureDateId);
        });

    }

}