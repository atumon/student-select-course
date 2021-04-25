package com.spring.scs.scssystem.controller.admin;


import com.spring.scs.scssystem.dao.Admin.AdminDao;
import com.spring.scs.scssystem.dao.Student.StudentDao;
import com.spring.scs.scssystem.domain.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class StudentManageController {

    @Autowired
    private AdminDao adminDao;
    @RequestMapping(value = "/studentinformation")
    @ResponseBody
    String StudentManagement() {

        System.out.println("运行到这：学生管理_获取信息");
        List<Student> studentList = adminDao.FindAllStudent();
        JSONArray json = new JSONArray();
        for (Student u : studentList) {
            JSONObject jo = new JSONObject();
            jo.put("StudentId", u.getStudentId());
            jo.put("StudentName", u.getStudentName());
            jo.put("StudentDept", u.getStudentDept());
            jo.put("StudentPassword", u.getStudentPassword());
            jo.put("StudentSex", u.getStudentSex());
            jo.put("StudentMajor", u.getStudentMajor());
            jo.put("StudentNational", u.getStudentNational());


            json.add(jo);
        }

        JSONObject jobj = new JSONObject();
        jobj.put("code", 0);
        jobj.put("msg", "success");
        jobj.put("count", studentList.size());
        jobj.put("data", json);
        String result = jobj.toString();

//        System.out.println("返回表格的json内容" + result);
        return result;
    }

    @RequestMapping(value = "/editstudent")
    @ResponseBody
    String editstudent (String oldSid,Student student) {

        System.out.println("原学号"+oldSid);
        System.out.println("修改成"+student.toString());

        Student students = adminDao.FindByStudentId(oldSid);

        students.setOldStudentId(oldSid);
        students.setStudentId(student.getStudentId());
        students.setStudentName(student.getStudentName());
        students.setStudentPassword(student.getStudentPassword());
        students.setStudentDept(student.getStudentDept());
        students.setStudentSex(student.getStudentSex());

        adminDao.UpadteAllBySid(students);
        Student newstudent = adminDao.FindByStudentId(student.getStudentId());
        JSONArray json = JSONArray.fromObject(newstudent);
        String js = json.toString();
        String jso = "{\"code\":\"200\",\"msg\":\"success\",\"count\":"+0+",\"data\":"+js+"}";
        System.out.println(jso);
        return jso;
    }

    @RequestMapping(value = "/delstudent")
    @ResponseBody
    String delstudent (String StudentId) {

        System.out.println("运行到这");

        System.out.println(StudentId);

        adminDao.DelecteStudentByStudentId(StudentId);
//        adminDao.DelecteSelectCourseByStudentId(StudentId);

        String jso = "{\"code\":\"0\",\"msg\":\"success\",\"count\":"+0+",\"data\":\"0\"}";

        return jso;
    }

    @RequestMapping(value = "/insertstudent")
    @ResponseBody
    String insertstudent (Student student) {

        System.out.println("修改成"+student.toString());

        try {
            adminDao.InsertRoleStudent(student);
        }catch (Exception e){
            String jso = "{\"code\":\"0\",\"msg\":\"failed\",\"count\":"+0+",\"data\":\"0\"}";
            return jso;
        }

        Student newstudent = adminDao.FindByStudentId(student.getStudentId());

        JSONArray json = JSONArray.fromObject(newstudent);

        String js = json.toString();

        String jso = "{\"code\":\"1\",\"msg\":\"success\",\"count\":"+0+",\"data\":"+js+"}";
        return jso;

    }
    @RequestMapping(value = "/delstudentsome")
    @ResponseBody
    String delstudentsome (String data) {

            //把json数组解析成集合
            List<Student> studentList = com.alibaba.fastjson.JSON.parseArray(data,Student.class);

            for (Student student : studentList){
                System.out.println(student.getStudentId());
                adminDao.DelecteStudentByStudentId(student.getStudentId());
//                adminDao.DelecteSelectCourseByStudentId(student.getStudentId());
            }


        String jso = "{\"code\":\"0\",\"msg\":\"success\",\"count\":"+0+",\"data\":\"0\"}";
        return jso;
    }

}