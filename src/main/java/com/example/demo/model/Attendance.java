package com.example.demo.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Data;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Data
public class Attendance {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @OneToOne
    @JoinColumn(name = "user_id")
    private User user;

    private int count;

    @Column(name = "last_attendance_date")
    private LocalDate lastAttendanceDate;

    // Getter and Setter for lastAttendanceDate

    // Constructor
    public Attendance() {
        // Initialize lastAttendanceDate to null or a default value if needed
    }
}
