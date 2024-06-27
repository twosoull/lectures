package io.lectures.entity;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class LectureDateTest {

    @Test
    void validDateLecture_test(){
        //given
        Long lectureDateId = 1L;
        LocalDateTime applyStartDate = LocalDateTime.of(2024, 6, 23, 14, 30);
        LocalDateTime applyEndDate = LocalDateTime.of(2024, 6, 24, 14, 30);
        LocalDateTime lectureDay = LocalDateTime.of(2023, 6, 30, 14, 30);
        int maxStudents = 30;
        int applyCount = 30;

        LectureDate lectureDate = new LectureDate(lectureDateId
                , applyStartDate
                , applyEndDate
                , lectureDay
                , maxStudents
                , applyCount);
        //when && then
        Assertions.assertThrows(RuntimeException.class,
                () -> {lectureDate.validDateLecture();}
        );
    }

    @Test
    void NotPossibleIfOver30(){
        //given
        Long lectureDateId = 1L;
        LocalDateTime applyStartDate = LocalDateTime.of(2024, 6, 23, 14, 30);
        LocalDateTime applyEndDate = LocalDateTime.of(2024, 6, 30, 14, 30);
        LocalDateTime lectureDay = LocalDateTime.of(2023, 6, 30, 14, 30);
        int maxStudents = 30;
        int applyCount = 30;

        LectureDate lectureDate = new LectureDate(lectureDateId
                , applyStartDate
                , applyEndDate
                , lectureDay
                , maxStudents
                , applyCount);

        //when
        Assertions.assertThrows(RuntimeException.class,
                () -> { lectureDate.notPossibleIfOverMaxStudentsElseApply();
                });
    }

    @Test
    void notPossibleIfOverMaxStudentsElseApply() {
    }
}