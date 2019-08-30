package com.cqx.service.impl;

import com.cqx.dao.UserFriendRepository;
import com.cqx.dao.UserHomeRepository;
import com.cqx.dao.UserRepository;
import com.cqx.model.UserEntity;
import com.cqx.model.UserFriendEntity;
import com.cqx.model.UserHomeEntity;
import com.cqx.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by BG307435 on 2018/2/9.
 */
@Service
public class UserServiceImpl implements UserService {

//    @Autowired
    UserRepository userRepository;
//    @Autowired
    UserHomeRepository userHomeRepository;
//    @Autowired
    UserFriendRepository userFriendRepository;

    @Override
    public void addUser(UserEntity userEntity) {
        userRepository.save(userEntity);
        UserHomeEntity userHome = new UserHomeEntity();
        userHome.setUid(userEntity.getId());
        userHome.setHome("welcome to " + userEntity.getUsername());
        userHomeRepository.insert(userHome);
        UserFriendEntity userFriend = new UserFriendEntity();
        userFriend.setUid(userEntity.getId());
        userFriendRepository.insert(userFriend);
    }

    @Override
    public void addFriend(String uid, List<String> friendIds) {
        UserFriendEntity userFriend = userFriendRepository.findByUid(uid);
        List<String> friendIdsDB = userFriend.getFriends();
        friendIdsDB.addAll(friendIds);
        List<String> results = friendIdsDB.stream().distinct().collect(Collectors.toList());
        userFriend.setFriends(results);
        userFriendRepository.save(userFriend);
    }

    @Override
    public void saveUserHome(String uid, String home) {
        UserHomeEntity userHome = new UserHomeEntity();
        userHome.setUid(uid);
        userHome.setHome(home);
        userHomeRepository.save(userHome);
    }

    @Override
    public UserHomeEntity findUserHome(String uid) {
//        return userHomeRepository.findOne(uid);
        return null;
    }


    @Override
    public UserEntity findUserDetail(String uid) {
//        UserEntity user = userRepository.findOne(uid);
//        UserHomeEntity userHome = userHomeRepository.findByUid(uid);
//        UserFriendEntity userFriend = userFriendRepository.findByUid(uid);
//        user.setUserHome(userHome);
//        user.setUserFriend(userFriend);
        return null;
    }
}
