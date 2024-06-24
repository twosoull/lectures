package io.lectures.lecture.repository;

import io.lectures.lecture.entity.LectureDate;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureDateRepository {

    LectureDate findById(Long lectureDateId);

}
