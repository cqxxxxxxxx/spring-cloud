package com.cqx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.io.Serializable;

/**
 * Created by BG307435 on 2018/2/9.
 */

@Document
public class UserHomeEntity implements Serializable {

    private static final long serialVersionUID = 1L;

    @Id
    private String id;
    private String uid;
    private String home;

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

    public String getHome() {
        return home;
    }

    public void setHome(String home) {
        this.home = home;
    }

    @Override
    public String toString() {
        return "UserHomeEntity{" +
                "id='" + id + '\'' +
                ", uid='" + uid + '\'' +
                ", home='" + home + '\'' +
                '}';
    }

}
