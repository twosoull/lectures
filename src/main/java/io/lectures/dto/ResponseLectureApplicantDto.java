package io.lectures.dto;

import io.lectures.entity.Lecture;
import io.lectures.entity.LectureApplicant;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ResponseLectureApplicantDto {

    private Long id;
    private String state;

    public static ResponseLectureApplicantDto from(LectureApplicant lectureApplicant){
        ResponseLectureApplicantDto dto = new ResponseLectureApplicantDto();
        dto.setId(lectureApplicant.getId());
        dto.setState(lectureApplicant.getState());
        return dto;
    }

}
