package com.spring.scs.scssystem.controller.admin;

import com.alibaba.fastjson.JSON;
import com.spring.scs.scssystem.dao.Admin.AdminDao;
import com.spring.scs.scssystem.domain.Teacher;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
@RequestMapping(value = "/admin")
public class TeacherManageController {

    @Autowired
    AdminDao adminDao;

    @RequestMapping(value = "/teacherinformation")
    @ResponseBody
    String TeacherManagement() {

        List<Teacher> teacherList = adminDao.FindAllTeacher();
        JSONArray json = new JSONArray();
        for (Teacher u : teacherList) {
            JSONObject jo = new JSONObject();
            jo.put("TeacherId", u.getTeacherId());
            jo.put("TeacherName", u.getTeacherName());
            jo.put("TeacherDept", u.getTeacherDept());
            jo.put("TeacherPassword", u.getTeacherPassword());
            jo.put("TeacherSex", u.getTeacherSex());
            jo.put("TeacherOffice", u.getTeacherOffice());
            jo.put("TeacherPhone", u.getTeacherPhone());
            json.add(jo);
        }

        JSONObject jobj = new JSONObject();
        jobj.put("code", 0);
        jobj.put("msg", "success");
        jobj.put("count", teacherList.size());
        jobj.put("data", json);
        String result = jobj.toString();

        System.out.println("返回表格的json内容" + result);
        return result;
    }

    @RequestMapping(value = "/editteacher")
    @ResponseBody
    String editstudent (String oldTid,Teacher teacher) {

        System.out.println("原教工号"+oldTid);
        System.out.println("修改成"+teacher.toString());

        Teacher teachers = adminDao.FindByTeacherId(oldTid);

        teachers.setOldTeacherId(oldTid);
        teachers.setTeacherId(teacher.getTeacherId());
        teachers.setTeacherName(teacher.getTeacherName());
        teachers.setTeacherPassword(teacher.getTeacherPassword());
        teachers.setTeacherDept(teacher.getTeacherDept());
        teachers.setTeacherSex(teacher.getTeacherSex());

        adminDao.UpdateAllByTid(teachers);

        Teacher newteacher = adminDao.FindByTeacherId(teacher.getTeacherId());

        JSONArray json = JSONArray.fromObject(newteacher);
        String js = json.toString();
        String jso = "{\"code\":\"200\",\"msg\":\"success\",\"count\":"+0+",\"data\":"+js+"}";
        System.out.println(jso);
        return jso;
    }

    @RequestMapping(value = "/delteacher")
    @ResponseBody
    String delstudent (String TeacherId) {

        System.out.println("运行到这");

        System.out.println(TeacherId);

        try {
            adminDao.DelectTeacherByTeacherId(TeacherId);
        }catch (Exception e){
            String jso = "{\"code\":\"0\",\"msg\":\"请重新安排课程\",\"count\":"+0+",\"data\":\"0\"}";
            return jso;
        }

        String jso = "{\"code\":\"1\",\"msg\":\"success\",\"count\":"+0+",\"data\":\"0\"}";
        return jso;

    }
    @RequestMapping(value = "/insertteacher")
    @ResponseBody
    String insertstudent (Teacher teacher) {


        System.out.println("添加"+teacher.toString());
        try {
            adminDao.InsertRoleTeacher(teacher);
        }catch (Exception e){
            String jso = "{\"code\":\"0\",\"msg\":\"请检查输入\",\"count\":"+0+",\"data\":\"0\"}";
            return jso;
        }
        Teacher newteacher = adminDao.FindByTeacherId(teacher.getTeacherId());

        JSONArray json = JSONArray.fromObject(newteacher);

        String js = json.toString();

        String jso = "{\"code\":\"0\",\"msg\":\"success\",\"count\":"+0+",\"data\":"+js+"}";
        System.out.println(jso);
        return jso;

    }
    @RequestMapping(value = "/delteachersome")
    @ResponseBody
    String delstudentsome (String data) {

        System.out.println("运行到这");

            //把json数组解析成集合
            List<Teacher> teacherList = JSON.parseArray(data,Teacher.class);

            for (Teacher teacher : teacherList){
                System.out.println(teacher.getTeacherId());
                adminDao.DelectTeacherByTeacherId(teacher.getTeacherId());
//                adminDao.DeleteTeachCourseByTeacherId(teacher.getTeacherId());
            }


        String jso = "{\"code\":\"0\",\"msg\":\"success\",\"count\":"+0+",\"data\":}";
        return jso;
    }

}