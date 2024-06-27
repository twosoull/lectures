package io.lectures.repository;

import io.lectures.entity.Users;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository {

    Users findById(Long userId);
}
