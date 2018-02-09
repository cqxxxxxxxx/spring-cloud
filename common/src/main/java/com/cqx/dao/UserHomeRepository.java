package com.cqx.dao;

import com.cqx.model.UserHomeEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by BG307435 on 2018/2/9.
 */
public interface UserHomeRepository extends MongoRepository<UserHomeEntity, String> {

    UserHomeEntity findByUid(String uid);
}
