package com.spring.scs.scssystem.controller.student;

import com.spring.scs.scssystem.dao.Admin.AdminDao;
import com.spring.scs.scssystem.dao.Student.StudentDao;
import com.spring.scs.scssystem.domain.Course;
import com.spring.scs.scssystem.domain.CourseSize;
import com.spring.scs.scssystem.domain.Student;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class SelectCourseController {

    @Autowired(required = false)
    StudentDao studentDao;
    @Autowired(required = false)
    AdminDao adminDao;

    @RequestMapping(value = "Course/information")
    @ResponseBody
    public String rebackinformation(ModelAndView modelAndView, Map<String, Object> map,
                                    HttpSession session) {

        int selctinfo = adminDao.getSelectState();

        Student student = (Student) session.getAttribute("student");
        if (selctinfo == 1) {
            List<Course> courseList = studentDao.FindAllCourse();
            //得到课程时间信息
            String[] weekInfo = new String[courseList.size()];
            for (int i = 0; i < courseList.size(); i++) {
                weekInfo[i] = studentDao.FindWeekInformation(courseList.get(i).getCourseId());
            }
            //得到余量
            int[] leftcourse = new int[courseList.size()];
            for (int i = 0; i < courseList.size(); i++) {
                leftcourse[i] = studentDao.FindCourseRemain(courseList.get(i).getCourseId());
                System.out.println("当前课程余量：" + leftcourse[i]);
            }
            //得到是否已选
            List<CourseSize> courseSizes = new ArrayList<CourseSize>();
            for (int i = 0; i < courseList.size(); i++) {
                String ifselect = "否";
                if (studentDao.FindIfStudentSelect(courseList.get(i).getCourseId(), student.getStudentId()) != null) {
                    ifselect = "是";
                }
                CourseSize courseSize = new CourseSize(courseList.get(i), leftcourse[i], ifselect, weekInfo[i]);
                courseSizes.add(courseSize);
            }


            //将结果转为JSON格式
            JSONArray json = new JSONArray();
            for (CourseSize u : courseSizes) {
                JSONObject jo = new JSONObject();
                jo.put("CourseId", u.getCourse().getCourseId());
                jo.put("CourseName", u.getCourse().getCourseName());
                jo.put("CourseCapacity", u.getCourse().getCourseCapacity());
                jo.put("CoursePoint", u.getCourse().getCoursePoint());
                jo.put("CourseDept", u.getCourse().getCourseDept());
                jo.put("TeacherName", u.getCourse().getTeacherName());
                jo.put("WeekInfo", u.getWeekInformation());
                jo.put("CourseAddress", u.getCourse().getCourseAddress());
                jo.put("leftCourseNumber", u.getLeftCourseNumber());
                jo.put("ifSelect", u.getIfSelect());
                json.add(jo);
            }

            JSONObject jobj = new JSONObject();
            jobj.put("code", 0);
            jobj.put("msg", "success");
            jobj.put("count", courseSizes.size());
            jobj.put("data", json);

            String result = jobj.toString();

//        System.out.println("返回表格的json内容" + result);
            return result;
        }else {
            return String.valueOf(selctinfo);
        }

    }

    @RequestMapping(value = "student/SelectCourse")
    @ResponseBody
    String selectcourse(String CourseId, HttpSession session) {
        System.out.println(CourseId);
        Student student = (Student) session.getAttribute("student");
        System.out.println(student);

        int returnmsg = studentDao.InsertSelectCourse(student.getStudentId(),CourseId);

        if (returnmsg == 0) {
            String params = "{\"channelCode\":\"1\",\"accountNo\":\"200\",\"message\":\"选课成功！\",\"status\":\"1\"}";
            return params;
        }else if (returnmsg == 1){
            String params = "{\"channelCode\":\"0\",\"accountNo\":\"0\",\"message\":\"重复选课！\",\"status\":\"0\"}";
            return params;
        }else if (returnmsg == 2){
            String params = "{\"channelCode\":\"0\",\"accountNo\":\"0\",\"message\":\"学分超载！\",\"status\":\"0\"}";
            return params;
        }else if (returnmsg == 3){
            String params = "{\"channelCode\":\"0\",\"accountNo\":\"0\",\"message\":\"时间冲突！\",\"status\":\"0\"}";
            return params;
        }else {
            String params = "{\"channelCode\":\"0\",\"accountNo\":\"0\",\"message\":\"课程已满！\",\"status\":\"0\"}";
            return params;
        }
    }

    @RequestMapping(value = "student/QuitCourse")
    @ResponseBody
    String quitcourse(String CourseId, HttpSession session) {
        System.out.println(CourseId);
        Student student = (Student) session.getAttribute("student");
        System.out.println(student);


        if (studentDao.FindIfStudentSelect(CourseId,student.getStudentId()) != null) {
            studentDao.DelSelectCourse(CourseId, student.getStudentId());
            String params = "{\"channelCode\":\"1\",\"accountNo\":\"1\",\"message\":\"退选成功！\",\"status\":\"0\"}";
            return params;
        }else {
            String params = "{\"channelCode\":\"0\",\"accountNo\":\"1\",\"message\":\"未选！\",\"status\":\"0\"}";
            return params;
        }

    }
}
