package com.example.demo.handler;

import com.example.demo.model.User;
import com.example.demo.service.AttendanceService;
import com.example.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.AuthenticationSuccessHandler;
import org.springframework.stereotype.Component;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

@Component
public class AttendanceSuccessHandler implements AuthenticationSuccessHandler {

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserService userService;

    @Override
    public void onAuthenticationSuccess(HttpServletRequest request, HttpServletResponse response,
                                        Authentication authentication) throws IOException, ServletException {
        // 로그인 성공 시 출석 횟수 증가 처리
        UserDetails userDetails = (UserDetails) authentication.getPrincipal();
        User user = userService.getUserByUsername(userDetails.getUsername());
        attendanceService.increaseAttendanceCount(user);

        // 원래의 로그인 성공 후 처리 로직 수행 (예: 리다이렉트 등)
        response.sendRedirect("/");
    }
}