package io.lectures.repository.impl;

import io.lectures.entity.LectureApplicant;
import io.lectures.repository.LectureApplicantJpaRepository;
import io.lectures.repository.LectureApplicantRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureApplicantRepositoryImpl implements LectureApplicantRepository {

    private final LectureApplicantJpaRepository lectureApplicantJpaRepository;

    @Override
    public int countByLectureDateIdAndStatus(Long lectureDateId, String status) {
        return lectureApplicantJpaRepository.countByLectureDateIdAndStatus(lectureDateId,status);
    }

    @Override
    public int countByUserIdAndLectureDateId(Long userId, Long lectureDateId) {
        return lectureApplicantJpaRepository.countByUserIdAndLectureDateId(userId,lectureDateId);
    }

    @Override
    public LectureApplicant save(LectureApplicant lectureApplicant) {
        return lectureApplicantJpaRepository.save(lectureApplicant);
    }

    @Override
    public List<LectureApplicant> findByUserId(Long userId) {
        return lectureApplicantJpaRepository.findByUsers_Id(userId);
    }
}
