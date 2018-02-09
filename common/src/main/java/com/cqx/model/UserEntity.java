package com.cqx.model;


import org.springframework.data.annotation.Id;
import org.springframework.data.annotation.Transient;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * @Entity 是javax.persistence提供的注解
 * @Document 是spring提供的注解 @Document for Spring Data MongoDB/Spring Data Elasticsearch.
 * 使用了@Document就表明该实体类用于mongoDB数据库，
 * 在多数据库的时候可以用@Document跟@Entity区分开来Repository适用什么数据库
 * Created by BG307435 on 2017/8/4.
 */
@Document
public class UserEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id //指定作为MongoDB 的 objectId
    private String id;
    private String username;
    private String password;

    @Transient
    private UserHomeEntity userHome;
    @Transient
    private UserFriendEntity userFriend;


    public static long getSerialVersionUID() {
        return serialVersionUID;
    }

    public UserHomeEntity getUserHome() {
        return userHome;
    }

    public void setUserHome(UserHomeEntity userHome) {
        this.userHome = userHome;
    }

    public UserFriendEntity getUserFriend() {
        return userFriend;
    }

    public void setUserFriend(UserFriendEntity userFriend) {
        this.userFriend = userFriend;
    }

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "UserEntity{" +
                "id='" + id + '\'' +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                ", userHome=" + userHome +
                ", userFriend=" + userFriend +
                '}';
    }
}
