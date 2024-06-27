package io.lectures.dto;

import io.lectures.entity.Lecture;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseLectureDto {
    private Long id;//

    private String lectureTitle;

    public static ResponseLectureDto from(Lecture lecture){
        ResponseLectureDto dto = new ResponseLectureDto();
        dto.setId(lecture.getId());
        dto.setLectureTitle(lecture.getLectureTitle());
        return dto;
    }
}
