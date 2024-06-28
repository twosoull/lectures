package io.lectures.entity;

import io.lectures.service.LectureService;
import jakarta.persistence.*;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@Entity
public class Users extends BaseEntity {

    @Column(name = "USER_ID")
    @GeneratedValue @Id
    private Long id;

    @Column(name = "USER_NAME")
    private String name;

    @OneToMany(mappedBy = "users")
    private List<LectureApplicant> lectureApplicants;

    public Users() {
    }

    public Users(Long id, String name) {
        this.id = id;
        this.name = name;
    }
}
