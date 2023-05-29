package com.example.demo.controller;

import com.example.demo.model.Attendance;
import com.example.demo.model.AttendanceModel;
import com.example.demo.model.User;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.UserRepository;
import com.example.demo.service.AttendanceService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.security.core.Authentication;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.security.Principal;
import java.util.Optional;

@Controller
@RequestMapping("/")
public class AttendanceController {
    private static final Logger logger = LoggerFactory.getLogger(AttendanceController.class);

    @Autowired
    private AttendanceService attendanceService;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private AttendanceRepository attendanceRepository;

    @PostMapping("/attendance/increase")
    public void increaseAttendanceCount(@RequestBody User user) {
        attendanceService.increaseAttendanceCount(user);
    }

    @GetMapping("/attendance/count")
    public int getAttendanceCount(Principal principal) {
        String username = principal.getName();
        return attendanceService.getAttendanceCountByUsername(username);
    }

    @GetMapping("/")
    public String showHomePage(Model model, Principal principal) {
        if (principal != null) {
            String username = principal.getName();
            int attendanceCount = attendanceService.getAttendanceCountByUsername(username);
            logger.debug("attendanceCount: {}", attendanceCount);
            model.addAttribute("attendanceCount", attendanceCount);
            return "index";
        } else {
            return "logout-home";
        }
    }
}