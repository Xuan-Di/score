package com.wzxlq.score.entity;

import java.io.Serializable;

/**
 * (Major)实体类
 *
 * @author makejava
 * @since 2020-02-28 12:23:47
 */
public class Major implements Serializable {
    private static final long serialVersionUID = -55225857684765726L;
    
    private Integer id;
    
    private String majorName;
    
    private String grade;
    
    private String courseName;
    
    private Integer status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMajorName() {
        return majorName;
    }

    public void setMajorName(String majorName) {
        this.majorName = majorName;
    }

    public String getGrade() {
        return grade;
    }

    public void setGrade(String grade) {
        this.grade = grade;
    }

    public String getCourseName() {
        return courseName;
    }

    public void setCourseName(String courseName) {
        this.courseName = courseName;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

}