package io.lectures.lecture.dto;

import io.lectures.lecture.entity.Lecture;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDateTime;

@Getter
@Setter
public class RequestLectureDateDto {
    private Long lectureDateId;
    private Long userId;
}
