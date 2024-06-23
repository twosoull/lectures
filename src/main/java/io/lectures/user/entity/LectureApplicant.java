package io.lectures.user.entity;

import io.lectures.entity.BaseEntity;
import jakarta.persistence.*;

@Entity
public class LectureApplicant extends BaseEntity {

    @Column(name = "LECTURE_APPLICANT_ID")
    @Id @GeneratedValue
    private Long id;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_id")
    private Lecture lecture;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private Users users;
}
