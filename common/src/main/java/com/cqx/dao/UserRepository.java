package com.cqx.dao;

import com.cqx.model.UserEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

/**
 * Created by BG307435 on 2018/2/9.
 */
public interface UserRepository extends MongoRepository<UserEntity, String> {

    List<UserEntity> findByUsername(String username);
}
