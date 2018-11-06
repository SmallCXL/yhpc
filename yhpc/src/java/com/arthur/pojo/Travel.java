package com.arthur.pojo;


import java.io.Serializable;
import java.sql.Timestamp;

public class Travel implements Serializable{
    private Long id;

    private Long uid;

    private String type_;

    private String departure_;

    private String arrival_;

    private Timestamp travel_time_;

    private String addition_;

    private String publish_time_;

    private User user;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getUid() {
        return uid;
    }

    public void setUid(Long uid) {
        this.uid = uid;
    }

    public String getType_() {
        return type_;
    }

    public void setType_(String type_) {
        this.type_ = type_;
    }

    public String getDeparture_() {
        return departure_;
    }

    public void setDeparture_(String departure_) {
        this.departure_ = departure_ == null ? null : departure_.trim();
    }

    public String getArrival_() {
        return arrival_;
    }

    public void setArrival_(String arrival_) {
        this.arrival_ = arrival_ == null ? null : arrival_.trim();
    }

    public Timestamp getTravel_time_() {
        return travel_time_;
    }

    public void setTravel_time_(Timestamp travel_time_) {
        this.travel_time_ = travel_time_;
    }

    public String getAddition_() {
        return addition_;
    }

    public void setAddition_(String addition_) {
        this.addition_ = addition_ == null ? null : addition_.trim();
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }
    public String getPublish_time_() {
        return publish_time_;
    }

    public void setPublish_time_(String publish_time_) {
        this.publish_time_ = publish_time_;
    }
}