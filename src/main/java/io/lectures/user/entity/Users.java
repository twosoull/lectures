package io.lectures.user.entity;

import io.lectures.entity.BaseEntity;
import io.lectures.lecture.entity.LectureApplicant;
import jakarta.persistence.*;

import java.time.LocalDateTime;
import java.util.List;

@Entity
public class Users extends BaseEntity {

    @Column(name = "USER_ID")
    @GeneratedValue @Id
    private Long id;

    @Column(name = "USER_NAME")
    private String name;

    @OneToMany(mappedBy = "users")
    private List<UserLectureHistory> userLectureHistories;

    @OneToOne(mappedBy = "users")
    @JoinColumn(name = "USER_ID")
    private LectureApplicant lectureApplicant;

    public Users(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
