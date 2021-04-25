package com.spring.scs.scssystem.controller.admin;

import com.spring.scs.scssystem.dao.Admin.AdminDao;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

@Controller
public class StartSelectController {

    @Autowired(required = false)
    private AdminDao adminDao;

    @RequestMapping("Select/Controller")
    public ModelAndView Information(ModelAndView modelAndView, Map<String,Object> map){

        int selctinfo = adminDao.getSelectState();
        int inputinfo = adminDao.getInutState();

        if (selctinfo == 1) {
            map.put("SelectInfo","正在选课中");
        }else{
            map.put("SelectInfo","停止选课中");
        }

        if (inputinfo == 1) {
            map.put("InputInfo","正在录入成绩中");
        }else{
            map.put("InputInfo","停止录入成绩中");
        }

        modelAndView.setViewName("admin/course_startselect");
        return modelAndView;
    }


    @RequestMapping(value = "/Select/Admin/StartSelect")
    @ResponseBody
    String StartSelect(){

        System.out.println("1231");

        try {
            adminDao.StartSelect();
        }catch (Exception e){
            String jso = "{\"code\":\"0\",\"msg\":\"failed\",\"count\":"+0+",\"data\":\"0\"}";
            return jso;
        }
        String jso = "{\"code\":\"1\",\"msg\":\"success\",\"count\":"+0+",\"data\":\"success\"}";
        return jso;
    }

    @RequestMapping("/Select/Admin/EndSelect")
    @ResponseBody
    public String EndSelect(){

        try {
            adminDao.EndSelect();
        }catch (Exception e){
            String jso = "{\"code\":\"0\",\"msg\":\"failed\",\"count\":"+0+",\"data\":\"0\"}";
            return jso;
        }

        String jso = "{\"code\":\"1\",\"msg\":\"success\",\"count\":"+0+",\"data\":\"success\"}";
        return jso;
    }

    @RequestMapping("/Select/Admin/StartInput")
    @ResponseBody
    public String StartInput(){

        try {
            adminDao.StartInput();
        }catch (Exception e){
            String jso = "{\"code\":\"0\",\"msg\":\"failed\",\"count\":"+0+",\"data\":\"0\"}";
            return jso;
        }
        String jso = "{\"code\":\"1\",\"msg\":\"success\",\"count\":"+0+",\"data\":\"success\"}";
        return jso;
    }

    @RequestMapping("/Select/Admin/EndInput")
    @ResponseBody
    public String EndInput(){
        try {
            adminDao.EndInput();
        }catch (Exception e){
            String jso = "{\"code\":\"0\",\"msg\":\"failed\",\"count\":"+0+",\"data\":\"0\"}";
            return jso;
        }
        String jso = "{\"code\":\"1\",\"msg\":\"success\",\"count\":"+0+",\"data\":\"success\"}";
        return jso;
    }

}
