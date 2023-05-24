package com.example.demo.repository;

import com.example.demo.model.Attendance;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Attendance findByUser(User user);
}