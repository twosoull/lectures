package io.lectures.repository.impl;

import io.lectures.entity.LectureDate;
import io.lectures.repository.LectureDateJpaRepository;
import io.lectures.repository.LectureDateRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.NoSuchElementException;

@Repository
@RequiredArgsConstructor
public class LectureDateRepositoryImpl implements LectureDateRepository {

    private final LectureDateJpaRepository lectureDateJpaRepository;

    @Override
    public LectureDate findById(Long lectureDateId) {
        return lectureDateJpaRepository.findById(lectureDateId).orElseThrow(
                () -> new NoSuchElementException());
    }
}
