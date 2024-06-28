package io.lectures.user.entity;

import io.lectures.entity.BaseEntity;
import io.lectures.lecture.entity.Lecture;
import io.lectures.lecture.entity.LectureDate;
import jakarta.persistence.*;

@Entity
public class UserLectureHistory extends BaseEntity {

    @Column(name = "USER_HISTORY_ID")
    @GeneratedValue
    @Id
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID")
    private Users users;

}