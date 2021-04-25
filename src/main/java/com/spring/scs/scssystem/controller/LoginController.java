package com.spring.scs.scssystem.controller;


import com.spring.scs.scssystem.dao.Admin.AdminDao;
import com.spring.scs.scssystem.dao.Student.StudentDao;
import com.spring.scs.scssystem.dao.Teacher.TeacherDao;
import com.spring.scs.scssystem.domain.Admin;
import com.spring.scs.scssystem.domain.Student;
import com.spring.scs.scssystem.domain.Teacher;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import javax.servlet.http.HttpSession;
import java.util.Map;

@Controller
public class LoginController {

    @Autowired(required = false)
    private AdminDao adminDao;
    @Autowired(required = false)
    private StudentDao studentDao;
    @Autowired(required = false)
    private TeacherDao teacherDao;



    @RequestMapping(value = "user/login", method = RequestMethod.POST)
    public String login(@RequestParam("id") String id,
                        @RequestParam("password") String password,
                        @RequestParam("check") int check,
                        Map<String, Object> map, HttpSession session) {
        System.out.println(id + password);
        System.out.println(check);

        int selctinfo = adminDao.getSelectState();

        Student student = new Student();
        Teacher teacher = new Teacher();
        Admin admin = new Admin();
        if (check == 1) {
            student = studentDao.FindIfStuExit(id, password);
            System.out.println(student);
            if (student != null) {
                session.setAttribute("student", student);
                session.setAttribute("SelectState",1);
                return "redirect:/studentindex.html";
            } else {
                map.put("msg", "用户名密码错误");
                return "login";
            }
        } else if(check == 2){
            teacher = teacherDao.FindIfTeacherExit(id,password);
            System.out.println(teacher);
            if ((teacher != null)){
                session.setAttribute("teacher",teacher);
                return "redirect:/teacherindex.html";
            }else {
                map.put("msg","用户名或密码错误");
                return "login";
            }
        } else {
            admin = adminDao.FindIfAdminExit(id, password);
            System.out.println(admin);
            if (admin != null) {
                session.setAttribute("admin", admin);
                return "redirect:/adminindex.html";
            } else {
                map.put("msg", "用户名密码错误");
                return "login";
            }
        }
    }
}



