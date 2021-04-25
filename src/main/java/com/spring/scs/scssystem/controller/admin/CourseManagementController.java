package com.spring.scs.scssystem.controller.admin;

import com.spring.scs.scssystem.dao.Admin.AdminDao;
import com.spring.scs.scssystem.domain.Course;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class CourseManagementController {


    @Autowired
    private AdminDao adminDao;

    @RequestMapping(value = "/courseinformation")
    @ResponseBody
    String CoursetManagement() {

        List<Course> courseList = adminDao.FindAllCourse();
        System.out.println(courseList);
        JSONArray json = new JSONArray();
        for (Course u : courseList) {
            JSONObject jo = new JSONObject();
            jo.put("CourseId", u.getCourseId());
            jo.put("CourseName", u.getCourseName());
            jo.put("CourseCapacity", u.getCourseCapacity());
            jo.put("CourseAddress", u.getCourseAddress());
            jo.put("CoursePoint", u.getCoursePoint());
            jo.put("TeacherId", u.getTeacherId());
            jo.put("TeacherName", u.getTeacherName());
            jo.put("CourseDept", u.getCourseDept());
            jo.put("CourseRemain", u.getCourseRemain());
            jo.put("CourseAmount",u.getCourseCapacity()-u.getCourseRemain());
            json.add(jo);
        }
        JSONObject jobj = new JSONObject();
        jobj.put("code", 0);
        jobj.put("msg", "success");
        jobj.put("count", courseList.size());
        jobj.put("data", json);
        String result = jobj.toString();

        System.out.println("返回表格的json内容" + result);
        return result;
    }

    @RequestMapping(value = "/editcourse")
    @ResponseBody
    String editstudent (String oldCourseId,String oldTeacherId,Course course) {

        System.out.println("原课程号"+oldCourseId);
        System.out.println("修改"+oldCourseId+"                 "+ oldTeacherId);

        System.out.println("OldCourseId:"+course.getOldCourseId());
        System.out.println("OldCourseId:"+course.getOldTeacherId());

        System.out.println("修改成"+course.toString());

        Course courses = adminDao.FindOldCourseInfo(oldCourseId);

        courses.setOldCourseId(oldCourseId);
        courses.setOldTeacherId(oldTeacherId);
        courses.setCourseId(course.getCourseId());
        courses.setCourseName(course.getCourseName());
        courses.setCourseDept(course.getCourseDept());
        courses.setCourseAddress(course.getCourseAddress());
        courses.setTeacherId(course.getTeacherId());
        courses.setCourseCapacity(course.getCourseCapacity());
//        courses.setCoursePoint(course.getCoursePoint());

        System.out.println("修改成了"+courses.toString());

        adminDao.UpdateAllByOldCid(courses);

        Course newcourse = adminDao.FindByCourseId(course.getCourseId());

        System.out.println(newcourse);

        JSONArray json = JSONArray.fromObject(newcourse);
        String js = json.toString();
        String jso = "{\"code\":\"200\",\"msg\":\"success\",\"count\":"+0+",\"data\":"+js+"}";
        System.out.println(jso);
        return jso;
    }

    @RequestMapping(value = "/delcourse")
    @ResponseBody
    String delstudent (String CourseId,String TeacherId) {

        System.out.println("运行到这");

        System.out.println(CourseId);
        System.out.println(TeacherId);

        adminDao.DeleteCourse(CourseId);

        String jso = "{\"code\":\"0\",\"msg\":\"success\",\"count\":"+0+",\"data\":\"success\"}";
        return jso;
    }
    @RequestMapping(value = "/insertcourse")
    @ResponseBody
    String insertstudent (Course course) {

        System.out.println("修改成"+course.toString());

//        students.setSid(student.getSid());
//        students.setStudentName(student.getStudentName());
//        students.setPassword(student.getPassword());
//        students.setMajorNum(student.getMajorNum());
//        students.setSex(student.getSex());

        adminDao.InsertCourse(course);


        Course newcourse = adminDao.FindByCourseId(course.getCourseId());

        JSONArray json = JSONArray.fromObject(newcourse);

        String js = json.toString();

        String jso = "{\"code\":\"0\",\"msg\":\"success\",\"count\":"+0+",\"data\":"+js+"}";
        System.out.println(jso);
        return jso;

    }
    @RequestMapping(value = "/delcoursesome")
    @ResponseBody
    String delstudentsome (String data) {

        System.out.println("运行到这");

        //把json数组解析成集合
        List<Course> courseList = com.alibaba.fastjson.JSON.parseArray(data,Course.class);

        for (Course course : courseList){
            System.out.println(course.getCourseId());
            adminDao.DeleteCourse(course.getCourseId());
        }

        String jso = "{\"code\":\"0\",\"msg\":\"success\",\"count\":"+0+",\"data\":\"success\"}";
        return jso;
    }

}