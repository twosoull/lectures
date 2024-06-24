package io.lectures.lecture.entity;

import io.lectures.entity.BaseEntity;
import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class LectureDate extends BaseEntity {

    @Column(name = "LECTURE_DATE_ID")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_Id")
    private Lecture lecture;

    private LocalDateTime applyStartDate;

    private LocalDateTime applyEndDate;

    private LocalDateTime lectureDay;

    private int maxStudents;

    public LectureDate() {
    }

    public LectureDate(Long id, LocalDateTime applyStartDate, LocalDateTime applyEndDate, LocalDateTime lectureDay, int maxStudents) {
        this.id = id;
        this.applyStartDate = applyStartDate;
        this.applyEndDate = applyEndDate;
        this.lectureDay = lectureDay;
        this.maxStudents = maxStudents;
    }

    public LocalDate getApplyStartLocalDate() {
        return this.applyStartDate.toLocalDate();
    }

    public LocalDate getApplyEndLocalDate() {
        return this.applyEndDate.toLocalDate();
    }
}
