package io.lectures.repository;

import io.lectures.dto.ResponseLectureApplicantDto;
import io.lectures.entity.LectureApplicant;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LectureApplicantRepository {

    int countByLectureDateIdAndStatus(Long lectureDateId, String status);

    int countByUserIdAndLectureDateId(Long userId, Long lectureDateId);

    LectureApplicant save(LectureApplicant lectureApplicant);

    List<LectureApplicant> findByUserId(Long userId);

}
