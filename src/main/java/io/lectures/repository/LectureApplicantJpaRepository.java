package io.lectures.repository;

import io.lectures.entity.LectureApplicant;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface LectureApplicantJpaRepository extends JpaRepository<LectureApplicant, Long> {

    @Query("select count(l) from LectureApplicant l where l.users.id = :userId and l.lectureDate.id = :lectureDateId")
    int countByUserIdAndLectureDateId(@Param("userId") Long userId,
                                      @Param("lectureDateId") Long lectureDateId);

    List<LectureApplicant> findByUsers_Id(@Param("userId") Long userId);

    @Query("select count(l) from LectureApplicant l where l.lectureDate.id = :lectureDateId and l.state = :status")
    int countByLectureDateIdAndStatus(@Param("lectureDateId")Long lectureDateId, @Param("status") String status);

    @Lock(LockModeType.PESSIMISTIC_WRITE)
    LectureApplicant save(LectureApplicant lectureApplicant);
}

