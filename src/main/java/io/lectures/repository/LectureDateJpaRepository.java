package io.lectures.repository;

import io.lectures.entity.LectureDate;
import jakarta.persistence.LockModeType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Lock;

import java.util.Optional;

public interface LectureDateJpaRepository extends JpaRepository<LectureDate, Long> {

    @Lock(LockModeType.PESSIMISTIC_READ)
    Optional<LectureDate> findById(Long lectureDateId);
}
