package io.lectures.lecture.repository;

import io.lectures.lecture.entity.Lecture;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureRepository {

    Lecture findByLecture(Long lectureId);

}
