package io.lectures;

import io.lectures.dto.RequestLectureDateDto;
import io.lectures.dto.ResponseLectureApplicantDto;
import io.lectures.dto.ResponseLectureDto;
import io.lectures.entity.LectureApplicant;
import io.lectures.service.LectureService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class LectureController {

    private final LectureService lectureService;

    //특강 신청 API
    @PostMapping("/lectures/apply")
    public ResponseEntity<ResponseLectureApplicantDto> apply(@RequestBody RequestLectureDateDto requestLectureDateDto){
        return new ResponseEntity<>(lectureService.applyLecture(requestLectureDateDto)
                                    ,HttpStatus.OK);
    }
    //특강 목록 API
    @GetMapping("/lectures")
    public ResponseEntity<List<ResponseLectureDto>> lectures(){
        return new ResponseEntity<>(lectureService.lectures(),HttpStatus.OK);
    }
    //특강 신청 완료 여부 조회 API GET /lectures/application/{userId}
    @GetMapping("/lectures/application/{userId}")
    public ResponseEntity<List<ResponseLectureApplicantDto>> application(@PathVariable("userId") Long userId){
        return new ResponseEntity<>(lectureService.application(userId), HttpStatus.OK);
    }

}



