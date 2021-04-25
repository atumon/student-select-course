package com.spring.scs.scssystem.controller.teacher;


import com.spring.scs.scssystem.dao.Teacher.TeacherDao;
import com.spring.scs.scssystem.domain.Teacher;
import com.spring.scs.scssystem.domain.TeacherNamed;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.List;
import java.util.Map;

@Controller
public class GradeInputController {


    @Autowired
    TeacherDao teacherDao;

    @RequestMapping("gradeinput/information")
    @ResponseBody
    String GradeInput(HttpSession session){

        Teacher teacher = (Teacher) session.getAttribute("teacher");

        List<TeacherNamed> teacherNamedList = teacherDao.FindTeacherCourseName(teacher.getTeacherId());

        System.out.println(teacherNamedList);

        JSONArray json = new JSONArray();

        for (TeacherNamed u : teacherNamedList) {
            JSONObject jo = new JSONObject();
            jo.put("StudentId", u.getStudentId());
            jo.put("StudentName", u.getStudentName());
            jo.put("CourseId", u.getCourseId());
            jo.put("CourseName", u.getCourseName());
            jo.put("StudentSex", u.getStudentSex());
            jo.put("Score", u.getScore());
            json.add(jo);
        }

        JSONObject jobj = new JSONObject();
        jobj.put("code", 0);
        jobj.put("msg", "success");
        jobj.put("count", teacherNamedList.size());
        jobj.put("data", json);
        String result = jobj.toString();

        System.out.println("返回表格的json内容" + result);
        return result;
    }

    @RequestMapping("teacher/inputgrade")
    public ModelAndView InputGrade(ModelAndView modelAndView,Map<String,Object> map,
                                   HttpSession session){

        Teacher teacher = (Teacher) session.getAttribute("teacher");

        List<TeacherNamed> teacherNamedList = teacherDao.FindTeacherCourseName(teacher.getTeacherId());

        System.out.println(teacherNamedList);

        map.put("teacherNamedLists",teacherNamedList);

        modelAndView.setViewName("teacher/gradeinputindex");
        return modelAndView;
    }


    @RequestMapping(value = "teacher/GradeInput")
    @ResponseBody
    String selectcourse(String CourseId, String StudentId, int Score, HttpSession session) {


        System.out.println(CourseId);

        System.out.println(StudentId);

        System.out.println(Score);


        teacherDao.UpdateStudentGrade(StudentId,CourseId,Score);
            String params = "{\"channelCode\":\"0\",\"accountNo\":\"200\",\"message\":\"插入成绩成功！\",\"status\":\"1\"}";
            return params;
    }

}
