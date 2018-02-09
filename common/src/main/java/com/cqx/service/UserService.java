package com.cqx.service;

import com.cqx.model.UserEntity;
import com.cqx.model.UserHomeEntity;

import java.util.List;

/**
 * Created by BG307435 on 2018/2/9.
 */
public interface UserService {

    void addUser(UserEntity userEntity);

    void addFriend(String uid, List<String> friendIds);

    void saveUserHome(String uid, String home);

    UserHomeEntity findUserHome(String uid);

    UserEntity findUserDetail(String uid);
}
