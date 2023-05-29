package com.example.demo.repository;

import com.example.demo.model.Attendance;
import com.example.demo.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface AttendanceRepository extends JpaRepository<Attendance, Long> {
    Attendance findByUser(User user);
    @Query("SELECT a.count FROM Attendance a WHERE a.user = :user")
    Integer findCountByUser(@Param("user") User user);
}