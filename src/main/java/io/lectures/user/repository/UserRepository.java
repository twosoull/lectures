package io.lectures.user.repository;

import io.lectures.user.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    Users findById(Long userId);
}
