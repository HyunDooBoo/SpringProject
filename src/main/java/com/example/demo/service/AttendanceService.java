package com.example.demo.service;

import com.example.demo.model.Attendance;
import com.example.demo.model.AttendanceModel;
import com.example.demo.model.User;
import com.example.demo.repository.AttendanceRepository;
import com.example.demo.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

@Service
public class AttendanceService {

    @Autowired
    private AttendanceRepository attendanceRepository;

    @Autowired
    private UserRepository userRepository;

    @Transactional
    public void increaseAttendanceCount(User user) {
        Attendance attendance = attendanceRepository.findByUser(user);
        if (attendance == null) {
            // 새로운 사용자의 경우 출석 정보 생성
            attendance = new Attendance();
            attendance.setUser(user);
            attendance.setCount(1);
            attendance.setLastAttendanceDate(LocalDate.now());
            attendanceRepository.save(attendance);
        } else {
            LocalDate lastAttendanceDate = attendance.getLastAttendanceDate();
            if (lastAttendanceDate == null || lastAttendanceDate.isBefore(LocalDate.now())) {
                attendance.setCount(attendance.getCount() + 1);
                attendance.setLastAttendanceDate(LocalDate.now());
                attendanceRepository.save(attendance);
            }
        }
    }

    public int getAttendanceCountByUsername(String username) {
        User user = userRepository.findByUsername(username);
        Attendance attendance = attendanceRepository.findByUser(user);
        return attendance != null ? attendance.getCount() : 0;
    }
    public AttendanceModel getAttendanceByUsername(String username) {
        User user = userRepository.findByUsername(username);
        if (user != null) {
            Attendance attendance = attendanceRepository.findByUser(user);
            if (attendance != null) {
                return new AttendanceModel(attendance.getCount());
            }
        }
        // Return an empty AttendanceModel or handle the case when attendance is null
        return new AttendanceModel(0);
    }
}