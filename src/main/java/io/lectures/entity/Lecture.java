package io.lectures.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Entity
@Getter
public class Lecture extends BaseEntity {

    @Column(name = "LECTURE_ID")
    @Id @GeneratedValue
    private Long id;//

    private String lectureTitle;

    @OneToMany(mappedBy = "lecture")
    private List<LectureDate> lectureDates = new ArrayList<>();

    public Lecture() {
    }

    public Lecture(String lectureTitle) {
        this.lectureTitle = lectureTitle;
    }

    public Lecture(Long id, String lectureTitle) {
        this.id = id;
        this.lectureTitle = lectureTitle;
    }

    public void addLectureDates(LectureDate lectureDate) {
        this.lectureDates.add(lectureDate);
        lectureDate.addLecture(this);
    }
}
