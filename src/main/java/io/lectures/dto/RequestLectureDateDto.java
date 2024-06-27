package io.lectures.dto;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Data
public class RequestLectureDateDto {
    private Long lectureDateId;
    private Long userId;

    public RequestLectureDateDto() {
    }

    public RequestLectureDateDto(Long lectureDateId, Long userId) {
        this.lectureDateId = lectureDateId;
        this.userId = userId;
    }
}
