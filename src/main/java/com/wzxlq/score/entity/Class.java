package com.wzxlq.score.entity;

import java.io.Serializable;

/**
 * (Class)实体类
 *
 * @author makejava
 * @since 2020-02-28 12:20:37
 */
public class Class implements Serializable {
    private static final long serialVersionUID = 386135900855972044L;
    
    private String id;
    
    private String className;
    
    private Integer status;


    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}