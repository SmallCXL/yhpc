package com.arthur.pojo;

import java.io.Serializable;
import java.util.List;

public class User implements Serializable{
    private Long id;

    private String phone_;

    private String name_;

    private String sex_;

    private String password_;

    private String salt_;

    private List<Travel> travelList;

    private String validate_code_;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getPhone_() {
        return phone_;
    }

    public void setPhone_(String phone_) {
        this.phone_ = phone_ == null ? null : phone_.trim();
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_ == null ? null : name_.trim();
    }

    public String getSex_() {
        return sex_;
    }

    public void setSex_(String sex_) {
        this.sex_ = sex_ == null ? null : sex_.trim();
    }

    public String getPassword_() {
        return password_;
    }

    public void setPassword_(String password_) {
        this.password_ = password_ == null ? null : password_.trim();
    }

    public String getSalt_() {
        return salt_;
    }

    public void setSalt_(String salt_) {
        this.salt_ = salt_ == null ? null : salt_.trim();
    }

    public List<Travel> getTravelList() {
        return travelList;
    }

    public void setTravelList(List<Travel> travelList) {
        this.travelList = travelList;
    }

    public String getValidate_code_() {
        return validate_code_;
    }

    public void setValidate_code_(String validate_code_) {
        this.validate_code_ = validate_code_;
    }
}