package io.lectures.repository.impl;

import io.lectures.entity.Lecture;
import io.lectures.repository.LectureJpaRepository;
import io.lectures.repository.LectureRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
@RequiredArgsConstructor
public class LectureRepositoryImpl implements LectureRepository {

    private final LectureJpaRepository lectureJpaRepository;

    @Override
    public Lecture findByLecture(Long lectureId) {
        return null;
    }

    @Override
    public List<Lecture> findAll() {
        return lectureJpaRepository.findAll();
    }
}
