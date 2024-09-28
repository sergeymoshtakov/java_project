package com.example.demo.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;

import java.sql.Time;
import java.util.Date;

@Data
@NoArgsConstructor
@AllArgsConstructor

public class StaffSchedule {
    private int id;
    private Staff staff;
    private Date workDay;
    private Time startTime;
    private Time endTime;
}