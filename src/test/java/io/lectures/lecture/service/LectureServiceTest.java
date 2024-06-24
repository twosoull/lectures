package io.lectures.lecture.service;

import io.lectures.lecture.dto.RequestLectureDateDto;
import io.lectures.lecture.entity.LectureApplicant;
import io.lectures.lecture.entity.LectureDate;
import io.lectures.lecture.repository.LectureApplicantRepository;
import io.lectures.lecture.repository.LectureDateRepository;
import io.lectures.lecture.repository.LectureRepository;
import io.lectures.user.entity.Users;
import io.lectures.user.repository.UserRepository;
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


        String status = "등록";
        int count = 29;
        doReturn(count).when(lectureApplicantRepository)
                .countByLectureDateIdAndStatus(lectureDateId, status);


        String name = "이영훈";
        Users users = new Users(userId, name);
        doReturn(users).when(userRepository)
                .findById(userId);


        LectureApplicant lectureApplicant = new LectureApplicant(
                status,lectureDate,users
        );
        doReturn(lectureApplicant).when(lectureApplicantRepository)
                .save(any(LectureApplicant.class));
        //when
        RequestLectureDateDto requestLectureDateDto = new RequestLectureDateDto();
        requestLectureDateDto.setLectureDateId(lectureDateId);
        requestLectureDateDto.setUserId(userId);

        boolean result = lectureService.applyLecture(requestLectureDateDto);

        //then
        Assertions.assertEquals(true, result);
    }

    @Test
    void validDateLecture_test(){
        //given
        LocalDateTime applyStartDate = LocalDateTime.of(2023, 6, 23, 14, 30);
        LocalDateTime applyEndDate = LocalDateTime.of(2023, 6, 24, 14, 30);
        //when
        boolean result = lectureService.validDateLecture(applyStartDate.toLocalDate()
                                                        ,applyEndDate.toLocalDate());

        //then
        Assertions.assertEquals(false,result);
    }

    @Test
    void alreadyApply_test() {
        //given
        Long userId = 1L;
        int count = 1;
        Long lectureDateId = 1L;
        doReturn(count).when(lectureApplicantRepository).countByUserIdAndLectureDateId(userId, lectureDateId);

        //when
        boolean result = lectureService.alreadyApply(userId, lectureDateId);

        //then
        Assertions.assertEquals(false,result);
    }

    @Test
    void NotPossibleIfOver30(){
        //given
        Long lectureDateId = 1L;
        String status = "등록";
        int count = 30;
        doReturn(count).when(lectureApplicantRepository)
                .countByLectureDateIdAndStatus(lectureDateId, status);

        int maxStudent = 30;
        //when
        boolean result = lectureService.notPossibleIfOverMaxStudents(lectureDateId, maxStudent);

        //then
        Assertions.assertEquals(false,result);
    }
}