package com.wzxlq.score.controller;


import com.wzxlq.score.entity.Major;
import com.wzxlq.score.service.MajorService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("query")
public class MajorController {
    @Resource
    private MajorService majorService;

    /**
     * 根据专业名称和年级查询课程名称
     * @param majorName
     * @param grade
     * @return
     */
    @GetMapping("courseName")
    public List<Major> selectCourseName(String majorName, String grade) {
        return this.majorService.queryAllByMajorAndGrade(majorName, grade);
    }
}
