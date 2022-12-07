package com.kinpetstore.restbackend.repository;

import com.kinpetstore.restbackend.base.repository.BaseRepository;
import com.kinpetstore.restbackend.entity.User;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UserRepository extends BaseRepository<User> {
    
    Optional<User> findBySub(String sub);
}
