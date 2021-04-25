package com.spring.scs.scssystem.controller.teacher;


import com.spring.scs.scssystem.dao.Teacher.TeacherDao;
import com.spring.scs.scssystem.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class TeacherInformationController {

    @Autowired
    private TeacherDao teacherDao;


    @RequestMapping("teacher/information")
    public ModelAndView StudentInfomation(ModelAndView modelAndView, Map<String, Object> map,
                                          HttpSession session) {

        System.out.println(session.getAttributeNames());

        Teacher teacher = (Teacher) session.getAttribute("teacher");

        System.out.println(teacher);

        teacherDao.FindByTeacherId(teacher.getTeacherId());

        map.put("teacher",teacher);

        modelAndView.setViewName("teacher/teacher_information");

        return modelAndView;
    }


}
