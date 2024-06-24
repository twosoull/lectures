package io.lectures.lecture.repository;

import io.lectures.lecture.entity.LectureApplicant;
import org.springframework.stereotype.Repository;

@Repository
public interface LectureApplicantRepository {

    int countByLectureDateIdAndStatus(Long lectureDateId, String status);

    int countByUserIdAndLectureDateId(Long userId, Long lectureDateId);

    LectureApplicant save(LectureApplicant lectureApplicant);


}
