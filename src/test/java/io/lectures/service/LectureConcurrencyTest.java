package io.lectures.service;

import io.lectures.dto.RequestLectureDateDto;
import io.lectures.entity.LectureDate;
import io.lectures.entity.Users;
import io.lectures.repository.LectureApplicantRepository;
import io.lectures.repository.impl.LectureDateRepositoryImpl;
import jakarta.annotation.PostConstruct;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.annotation.Rollback;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@SpringBootTest
public class LectureConcurrencyTest {

    @Autowired
    private LectureService lectureService;

    @Autowired
    private LectureDateRepositoryImpl lectureDateRepository;

    @Autowired
    private LectureApplicantRepository lectureApplicantRepository;

    @PostConstruct
    void init() {

    }

    //1. 멀티쓰레드를 구현해주는 ExecutorService
    //code -> ExecutorService executorService = Executors.newFixedThreadPool(int Threads);
    //(FixedThreadPool은 고정된 개수를 가진 쓰레드풀이다)
    //2. 다른 스래드에서 수행중인 작업을 완료될 때까지 대기하도록 도와주는 CountDownLatch
    //CountDownLatch latch = new CountDownLatch(threadCount);
    @Test
    @Rollback(false)
    void applyLecture_test() throws InterruptedException {
        //given
        int threadCount = 36;
        ExecutorService executorService = Executors.newFixedThreadPool(threadCount);
        CountDownLatch latch = new CountDownLatch(threadCount);

        Long lectureDateId = 1L;

        //when
        for(Long i = 0L; i < threadCount; i++){

            Long finalI = i + 1;
            executorService.submit(()->{

                try {
                    RequestLectureDateDto requestLectureDateDto = new RequestLectureDateDto(lectureDateId, finalI);
                    lectureService.applyLecture(requestLectureDateDto);
                } finally {
                       latch.countDown();
                }
            });
        }

        latch.await();
        executorService.shutdown();
        LectureDate lectureDate = lectureDateRepository.findById(lectureDateId);
        int applicantCount = lectureApplicantRepository.countByLectureDateIdAndStatus(lectureDateId, "등록");
        int applyCount = lectureDate.getApplyCount();
        Assertions.assertEquals(30,applicantCount);
        Assertions.assertEquals(30,applyCount);

    }

}
