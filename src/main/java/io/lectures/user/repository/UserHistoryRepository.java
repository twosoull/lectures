package io.lectures.user.repository;

import io.lectures.user.entity.UserLectureHistory;
import org.springframework.stereotype.Repository;

@Repository
public interface UserHistoryRepository {

    UserLectureHistory save(UserLectureHistory userLectureHistory);
}
