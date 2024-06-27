package io.lectures.repository;

import io.lectures.entity.LectureDate;

public interface LectureDateRepository {

    LectureDate findById(Long lectureDateId);

}
