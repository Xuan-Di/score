package com.wzxlq.score.controller;

import com.wzxlq.score.entity.Teacher;
import com.wzxlq.score.service.TeacherService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.List;

@RestController
@RequestMapping("identity")
public class TeacherController {
    @Resource
    private TeacherService teacherService;

    /**
     * 根据workNum查询是否有该工号的信息
     * @param workNum
     * @return
     */
    @GetMapping("verify")
    public List<Teacher> verify(String workNum) {
        return this.teacherService.queryAllByWorkNum(workNum);
    }
}
