package io.lectures.lecture.entity;

import io.lectures.entity.BaseEntity;
import io.lectures.user.entity.UserLectureHistory;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

@Entity
@Getter
public class Lecture extends BaseEntity {

    @Column(name = "LECTURE_ID")
    @Id @GeneratedValue
    private Long id;//

    private String lectureTitle;

    @OneToMany(mappedBy = "lecture")
    private List<LectureDate> lectureDates;

    @OneToMany(mappedBy = "lecture")
    private List<UserLectureHistory> userLectureHistories;

    public Lecture() {
    }

    public Lecture(Long id, String lectureTitle) {
        this.id = id;
        this.lectureTitle = lectureTitle;
    }

}
