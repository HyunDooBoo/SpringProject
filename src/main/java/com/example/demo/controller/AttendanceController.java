package com.example.demo.controller;

import com.example.demo.model.Attendance;
import com.example.demo.model.User;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/attendance")
public class AttendanceController {
    @Autowired
    private AttendanceService attendanceService;

    @PostMapping("/increase")
    public void increaseAttendanceCount(@RequestBody User user) {
        attendanceService.increaseAttendanceCount(user);
    }

    @GetMapping("/count/{userId}")
    public int getAttendanceCount(@PathVariable Long userId) {
        User user = new User();
        user.setId(userId);
        return attendanceService.getAttendanceCount(user);
    }
}