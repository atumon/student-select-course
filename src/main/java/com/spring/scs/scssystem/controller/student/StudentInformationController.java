package com.spring.scs.scssystem.controller.student;

import com.spring.scs.scssystem.dao.Student.StudentDao;
import com.spring.scs.scssystem.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class StudentInformationController {

    @Autowired
    private StudentDao studentDao;

    @RequestMapping("student/information")
    public ModelAndView StudentInformation(ModelAndView modelAndView, Map<String, Object> map,
                                          HttpSession session) {
        Student stu = (Student) session.getAttribute("student");

        Student student = studentDao.FindByStudentId(stu.getStudentId());

        System.out.println(student);

        map.put("student",student);

        modelAndView.setViewName("student/student_information");
        return modelAndView;
    }
}