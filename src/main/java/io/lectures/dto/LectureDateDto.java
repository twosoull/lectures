package io.lectures.dto;

import io.lectures.entity.LectureDate;
import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Data
public class LectureDateDto {

    private Long id;
    private LocalDateTime applyStartDate;
    private LocalDateTime applyEndDate;
    private LocalDateTime lectureDay;
    private int maxStudents;

    public static LectureDateDto from(LectureDate ld) {
        LectureDateDto lectureDateDto = new LectureDateDto();
        lectureDateDto.setId(ld.getId());
        lectureDateDto.setApplyStartDate(ld.getApplyStartDate());
        lectureDateDto.setApplyEndDate(ld.getApplyEndDate());
        lectureDateDto.setLectureDay(ld.getLectureDay());
        lectureDateDto.setMaxStudents(ld.getMaxStudents());

        return lectureDateDto;
    }


    public LocalDate getApplyStartLocalDate() {
        return this.applyStartDate.toLocalDate();
    }

    public LocalDate getApplyEndLocalDate() {
        return this.applyEndDate.toLocalDate();
    }
}
