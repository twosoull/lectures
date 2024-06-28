package io.lectures;

import io.lectures.entity.Lecture;
import io.lectures.entity.LectureDate;
import io.lectures.repository.LectureDateJpaRepository;
import io.lectures.repository.LectureJpaRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

@Configuration
@Transactional
public class DataInitializer {

    @Autowired
    LectureJpaRepository lectureJpaRepository;

    @Autowired
    LectureDateJpaRepository lectureDataJpaRepository;

    @Bean
    public CommandLineRunner initData() {
        return args -> {
            Lecture lecture = new Lecture("향해99 백엔드 5기");

            LocalDateTime applyStartDate = LocalDateTime.of(2024, 6, 23, 14, 30);
            LocalDateTime applyEndDate = LocalDateTime.of(2024, 6, 30, 14, 30);
            LocalDateTime lectureDay = LocalDateTime.of(2023, 6, 30, 14, 30);

            LectureDate lectureDate = new LectureDate(30,lectureDay, applyEndDate, applyStartDate, lecture );
            lecture.addLectureDates(lectureDate);

            lectureJpaRepository.save(lecture);
            lectureDataJpaRepository.save(lectureDate);
        };
    }
}