package com.arthur.pojo;

public class Role {
//    public static final long USER = 1;
//    public static final long ADMIN = 2;
    public static final long ADMIN = 1000;
    public static final long USER = 2000;

    private Long id;

    private String name_;

    private String desc_;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName_() {
        return name_;
    }

    public void setName_(String name_) {
        this.name_ = name_ == null ? null : name_.trim();
    }

    public String getDesc_() {
        return desc_;
    }

    public void setDesc_(String desc_) {
        this.desc_ = desc_ == null ? null : desc_.trim();
    }
}