package com.spring.scs.scssystem.controller.student;



import com.spring.scs.scssystem.dao.Student.StudentDao;
import com.spring.scs.scssystem.domain.Schedules;
import com.spring.scs.scssystem.domain.Student;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class StudentSchedulesController {


    @Autowired(required = false)
    StudentDao studentDao;


    @RequestMapping("student/schedules")
    public ModelAndView Studentschedules(ModelAndView modelAndView, Map<String, Object> map,
                                        HttpSession session) {
        Student student = (Student) session.getAttribute("student");
        System.out.println(session.getAttribute("student"));

        List<Schedules> schedulesList = new ArrayList<Schedules>();

        String coursename ;
        for (int i = 1; i <= 10; i++) {
            Schedules schedules = new Schedules();
            for (int j = 1; j <=5 ; j++) {
                try {
                    if (studentDao.FindStudentCourseName(student.getStudentId(), j, i) !=null)
                        coursename = String.valueOf(studentDao.FindStudentCourseName(student.getStudentId(), j, i)+"</br>"+studentDao.FindTeacherName(student.getStudentId(), j, i)+"</br>"+studentDao.FindSCourseAddress(student.getStudentId(), j, i));
                    else coursename = "";
                }catch (Exception e){
                    coursename = "";
                }
                switch (j) {
                    case 1:
                        schedules.setMon(coursename);
                        break;
                    case 2:
                        schedules.setTue(coursename);
                        break;
                    case 3:
                        schedules.setWed(coursename);
                        break;
                    case 4:
                        schedules.setThur(coursename);
                        break;
                    case 5:
                        schedules.setFri(coursename);
                        break;
                }
                if (j==5) {
                    if (i <= 4)
                        schedules.setTime("上<br>午<br>");
                    if (i > 4 && i <= 8)
                        schedules.setTime("下<br>午<br>");
                    if (i > 8)
                        schedules.setTime("晚<br>上<br>");
                    schedulesList.add(schedules);
                }
            }
        }
        System.out.println(schedulesList);

        map.put("courselists",schedulesList);

        modelAndView.setViewName("student/student_schedules");

        return modelAndView;
    }

}
