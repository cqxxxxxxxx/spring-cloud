package com.cqx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;
import java.util.List;

/**
 * Created by BG307435 on 2018/2/9.
 */
@Document
public class UserFriendEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String uid;
    private List<String> friends;

    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getUid() {
        return uid;
    }

    public void setUid(String uid) {
        this.uid = uid;
    }

    public List<String> getFriends() {
        return friends;
    }

    public void setFriends(List<String> friends) {
        this.friends = friends;
    }

    @Override
    public String toString() {
        return "UserFriendEntity{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", friends=" + friends +
                '}';
    }
}
