package io.lectures.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
public class LectureApplicant extends BaseEntity {

    @Column(name = "LECTURE_APPLICANT_ID")
    @Id @GeneratedValue
    private Long id;

    private String state;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "LECTURE_DATE_ID" , foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private LectureDate lectureDate;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "USER_ID" , foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Users users;

    public LectureApplicant() {
    }

    public LectureApplicant(String state, LectureDate lectureDate, Users users) {
        this.state = state;
        this.lectureDate = lectureDate;
        this.users = users;
    }

    public LectureApplicant(Long id, String state, LectureDate lectureDate, Users users) {
        this.id = id;
        this.state = state;
        this.lectureDate = lectureDate;
        this.users = users;
    }
}
