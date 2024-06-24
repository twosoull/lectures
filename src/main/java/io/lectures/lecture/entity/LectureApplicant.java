package io.lectures.lecture.entity;

import io.lectures.entity.BaseEntity;
import io.lectures.user.entity.Users;
import jakarta.persistence.*;

@Entity
public class LectureApplicant extends BaseEntity {

    @Column(name = "LECTURE_APPLICANT_ID")
    @Id @GeneratedValue
    private Long id;

    private String status;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LECTURE_DATE_ID")
    private LectureDate lectureDate;

    @OneToOne
    @JoinColumn(name = "USER_ID")
    private Users users;

    public LectureApplicant() {
    }

    public LectureApplicant(String status, LectureDate lectureDate, Users users) {
        this.status = status;
        this.lectureDate = lectureDate;
        this.users = users;
    }
}
