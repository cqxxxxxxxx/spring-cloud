package com.cqx.dao;

import com.cqx.model.UserFriendEntity;
import org.springframework.data.mongodb.repository.MongoRepository;

/**
 * Created by BG307435 on 2018/2/9.
 */
public interface UserFriendRepository extends MongoRepository<UserFriendEntity, String> {

    /**
     * 根据用户id查询 好友关系
     *
     * @param uid
     * @return
     */
    UserFriendEntity findByUid(String uid);

}
