package io.lectures.repository;

import io.lectures.entity.Lecture;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureRepository {

    Lecture findByLecture(Long lectureId);

    List<Lecture> findAll();
}
