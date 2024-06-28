package io.lectures.entity;

import jakarta.persistence.*;
import lombok.Getter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Getter
public class LectureDate extends BaseEntity {

    @Column(name = "LECTURE_DATE_ID")
    @Id @GeneratedValue
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture_Id", foreignKey = @ForeignKey(ConstraintMode.NO_CONSTRAINT))
    private Lecture lecture;

    private LocalDateTime applyStartDate;

    private LocalDateTime applyEndDate;

    private LocalDateTime lectureDay;

    private int maxStudents;

    private int applyCount;

    public LectureDate() {
    }

    public LectureDate(int maxStudents, LocalDateTime lectureDay, LocalDateTime applyEndDate, LocalDateTime applyStartDate, Lecture lecture) {
        this.maxStudents = maxStudents;
        this.lectureDay = lectureDay;
        this.applyEndDate = applyEndDate;
        this.applyStartDate = applyStartDate;
        this.lecture = lecture;
    }

    public LectureDate(Long id, LocalDateTime applyStartDate, LocalDateTime applyEndDate, LocalDateTime lectureDay, int maxStudents) {
        this.id = id;
        this.applyStartDate = applyStartDate;
        this.applyEndDate = applyEndDate;
        this.lectureDay = lectureDay;
        this.maxStudents = maxStudents;
    }

    public LectureDate(Long id,LocalDateTime applyStartDate, LocalDateTime applyEndDate, LocalDateTime lectureDay, int maxStudents, int applyCount) {
        this.id = id;
        this.applyStartDate = applyStartDate;
        this.applyEndDate = applyEndDate;
        this.lectureDay = lectureDay;
        this.maxStudents = maxStudents;
        this.applyCount = applyCount;
    }

    public LocalDate getApplyStartLocalDate() {
        return this.applyStartDate.toLocalDate();
    }

    public LocalDate getApplyEndLocalDate() {
        return this.applyEndDate.toLocalDate();
    }

    public void addLecture(Lecture lecture){
        this.lecture = lecture;
    }

    public void apply(){
        validDateLecture();
        notPossibleIfOverMaxStudentsElseApply();
    }

    //수강 신청 날짜 valid
    public void validDateLecture() {
        LocalDate today = LocalDate.now();
        boolean isBefore = this.getApplyStartLocalDate().isBefore(today)
                || this.getApplyStartLocalDate().isEqual(today);
        boolean isAfter = this.getApplyEndLocalDate().isAfter(today)
                || this.getApplyEndLocalDate().isEqual(today);
        boolean matched = isAfter && isBefore;

        if(!matched){
            throw new RuntimeException("오늘은 강의 신청 날짜가 아닙니다.");
        }
    }

    //정원 초과하는지 확인
    public void notPossibleIfOverMaxStudentsElseApply() {
        if(this.applyCount >= this.maxStudents){
            throw new RuntimeException("정원 초과입니다.");
        }
        applyCount++;
    }
}
