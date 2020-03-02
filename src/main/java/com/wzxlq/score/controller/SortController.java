package com.wzxlq.score.controller;

import com.wzxlq.score.tools.PageSort;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("page")
public class SortController {

    /**
     * 页面排序的接口
     * @param className 班级名称
     * @param term 学期(单科成绩页面此参数可为null)
     * @param courseName 课程名称(只有单科成绩页面是需要)
     * @param page 表示为哪个页面的排序,1:总体成绩页面 2:不及格页面 3:单科成绩页面
     * @param sort 1:按学号升序 2:按总分或单科成绩降序 3:按平均学分绩点降序 4:按课程名称
     * @return
     */
    @GetMapping("sort")
    public List<Object> sort(String className, String term, String courseName, Integer page, Integer sort) {
        if (page == 1) {
            PageSort.sortAllGradePage(new ArrayList<>(), sort);
        } else if (page == 2) {
            PageSort.sortFlunkPage(new ArrayList<>(), sort);
        } else  {
            PageSort.sortOneGrade(new ArrayList<>(), sort);
        }
        return null;
    }
}
