package io.lectures.repository.impl;

import io.lectures.entity.Users;
import io.lectures.repository.UserRepository;
import org.springframework.stereotype.Repository;

@Repository
public class UserRepositoryImpl implements UserRepository {


    @Override
    public Users findById(Long userId) {
        return null;
    }
}
