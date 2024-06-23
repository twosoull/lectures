package io.lectures.user.entity;

import io.lectures.entity.BaseEntity;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Lecture extends BaseEntity {

    @Column(name = "LECTURE_ID")
    @Id @GeneratedValue
    private Long id;

    private String lectureTitle;

    private LocalDateTime ApplyDate;

    @OneToMany(mappedBy = "lecture")
    private List<LectureApplicant> lectureApplicants;

    @OneToMany(mappedBy = "lecture")
    private List<UserLectureHistory> userLectureHistories;

}
