package com.spring.scs.scssystem.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.servlet.config.annotation.*;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;

@Configuration
public class MyMvcConfig implements WebMvcConfigurer {

    @Bean
    public SecurityInterceptor getSecurityInterceptor() {
        return  new SecurityInterceptor();
    }

    private class SecurityInterceptor extends HandlerInterceptorAdapter {
        @Override
        public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler) throws IOException, ServletException {
            HttpSession session = request.getSession();
            //判断是否已有该用户登录的session

            if(session.getAttribute("student") !=null||session.getAttribute("teacher") !=null||session.getAttribute("admin") !=null){
                return true;
            }else {
                request.setAttribute("msg","没有权限请先登陆");
//                request.getRequestDispatcher("index.html").forward(request,response);
                response.sendRedirect("index.html");
                return false;
            }
        }
    }

    @Override
    public void addInterceptors(InterceptorRegistry registry){
        InterceptorRegistration addInterceptor = registry.addInterceptor(getSecurityInterceptor());

        //排除配置
        addInterceptor.excludePathPatterns("/user/login","/webjars/**","/asserts/**","/index.html","/","/resources/**","/layui/**");
        //拦截配置
        addInterceptor.addPathPatterns("/**");
    }

    @Override
    public void addViewControllers(ViewControllerRegistry registry) {
        registry.addViewController("/").setViewName("login");
        registry.addViewController("/index.html").setViewName("login");
        registry.addViewController("/studentindex.html").setViewName("student/student_index");
        registry.addViewController("/teacherindex.html").setViewName("teacher/teacher_index");
        registry.addViewController("/adminindex.html").setViewName("admin/admin_index");
        registry.addViewController("/selectcourse.html").setViewName("student/course_select");
        registry.addViewController("/managerstudent.html").setViewName("admin/student_management");
        registry.addViewController("/managerteacher.html").setViewName("admin/teacher_management");
        registry.addViewController("/managercourse.html").setViewName("admin/course_management");
        registry.addViewController("/startselect.html").setViewName("admin/course_startselect");
        registry.addViewController("/gradeinput.html").setViewName("teacher/teacher_gradeinput");
    }


}

