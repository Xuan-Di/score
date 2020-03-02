package com.wzxlq.score.controller;

import com.wzxlq.score.entity.CourseGrade;
import com.wzxlq.score.entity.Excel;
import com.wzxlq.score.entity.Student;
import com.wzxlq.score.service.CourseGradeService;
import com.wzxlq.score.service.StudentService;
import com.wzxlq.score.tools.ReadExcel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 王照轩
 * @date 2020/3/2 - 14:26
 */
@RestController
@RequestMapping("excel")
public class ExcelController {

    private static String xls2003 = "F:\\mycourse.xls";
    @Autowired
    StudentService studentService;
    @Autowired
    CourseGradeService courseGradeService;
    /**
     * 将上传的excel导入mysql
     */
    @GetMapping("insertExcel")
    public void insertExcel(String className, String term) {
        List<Excel> excelList = ReadExcel.readFromXLS2003(xls2003, className, term);
        List<Student> studentList = excelList.stream().map(excel -> new Student(excel.getStudentNum(), excel.getStudentName(),
                excel.getClassName(), excel.getTerm(), excel.getCourseNum(), excel.getFlunkNum(), excel.getAvgScore(),
                excel.getAllScore(), excel.getAllCreditScore(), excel.getAvgCredit(), excel.getAvgCreditPoint(), excel.getMajorRank(),
                1
        )).limit( 2).collect(Collectors.toList());
        studentService.insertAllStudent(studentList);
        Excel lastexcel = excelList.get(excelList.size() - 1);
        ArrayList<String> courseNameList = lastexcel.getCourseName();
        List<ArrayList<String>> collect = excelList.stream().limit(2).map(excel -> excel.getScores()).collect(Collectors.toList());
        List<CourseGrade> courseGradeList = new ArrayList<>();
        for (int j = 0; j < collect.size(); j++) {
            ArrayList<String> temp = collect.get(j);
            for (int i = 0; i < temp.size(); i++) {
                if (temp.get(i).equals("优")) {
                    courseGradeList.add(new CourseGrade(studentList.get(j).getStudentNum(), studentList.get(j).getStudentName(),
                            studentList.get(j).getTerm(), courseNameList.get(i), temp.get(i), 5));
                } else if (temp.get(i).equals("良")) {
                    courseGradeList.add(new CourseGrade(studentList.get(j).getStudentNum(), studentList.get(j).getStudentName(),
                            studentList.get(j).getTerm(), courseNameList.get(i), temp.get(i), 4));
                } else if (temp.get(i).equals("中")) {
                    courseGradeList.add(new CourseGrade(studentList.get(j).getStudentNum(), studentList.get(j).getStudentName(),
                            studentList.get(j).getTerm(), courseNameList.get(i), temp.get(i), 3));
                } else if (temp.get(i).equals("及格")) {
                    courseGradeList.add(new CourseGrade(studentList.get(j).getStudentNum(), studentList.get(j).getStudentName(),
                            studentList.get(j).getTerm(), courseNameList.get(i), temp.get(i), 2));
                } else if (temp.get(i).equals("不及格")) {
                    courseGradeList.add(new CourseGrade(studentList.get(j).getStudentNum(), studentList.get(j).getStudentName(),
                            studentList.get(j).getTerm(), courseNameList.get(i), temp.get(i), 1));
                } else {
                    courseGradeList.add(new CourseGrade(studentList.get(j).getStudentNum(), studentList.get(j).getStudentName(),
                            studentList.get(j).getTerm(), courseNameList.get(i), null, Integer.parseInt(temp.get(i))));
                }
            }
        }
        courseGradeService.insertAllGrades(courseGradeList);
    }
}
