package io.lectures.repository;

import io.lectures.entity.Lecture;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LectureJpaRepository extends JpaRepository<Lecture, Long> {

    List<Lecture> findAll();
}