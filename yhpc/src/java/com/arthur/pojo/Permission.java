package com.arthur.pojo;

public class Permission {
    private Long id;

    private String name_;

    private String desc_;

    private String url_;

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

    public String getUrl_() {
        return url_;
    }

    public void setUrl_(String url_) {
        this.url_ = url_ == null ? null : url_.trim();
    }
}