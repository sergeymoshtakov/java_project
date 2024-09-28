package com.example.demo.factories;

import com.example.demo.dao.staffDao.StaffDao;
import com.example.demo.model.Staff;
import com.example.demo.model.StaffSchedule;
import com.example.demo.util.RandomElements;
import org.springframework.stereotype.Service;

import java.sql.Date;
import java.sql.Time;
import java.util.Calendar;
import java.util.List;

@Service
public class StaffScheduleFactory {
    private static final int[] WORK_DAYS = {1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11, 12, 13, 14, 15, 16, 17, 18, 19, 20, 21, 22, 23, 24, 25, 26, 27, 28, 29, 30, 31};
    private static final int[] WORK_HOURS = {8, 9, 10, 11, 12, 13, 14, 15, 16, 17};
    private static final int[] MINUTES = {0, 15, 30, 45};
    private static final int[] TIME_INCREMENT_HOURS = {1, 2, 3};

    public static StaffSchedule createRandomSchedule() {
        StaffDao staffDao = new StaffDao();
        List<Staff> staffList = staffDao.findAll();
        if (staffList.isEmpty()) {
            throw new IllegalStateException("No staff available to create a schedule.");
        }

        Staff staff = RandomElements.getRandomElement(staffList);
        Date workDay = getRandomDate();
        Time startTime = getRandomTime();
        Time endTime = getRandomTimeAfter(startTime);

        return new StaffSchedule(0, staff, workDay, startTime, endTime);
    }

    private static Date getRandomDate() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.YEAR, 2023);
        calendar.set(Calendar.MONTH, RandomElements.getRandomElement(new int[]{0, 1, 2, 3, 4, 5, 6, 7, 8, 9, 10, 11}));
        calendar.set(Calendar.DAY_OF_MONTH, RandomElements.getRandomElement(WORK_DAYS));
        return new java.sql.Date(calendar.getTime().getTime());
    }

    private static Time getRandomTime() {
        Calendar calendar = Calendar.getInstance();
        calendar.set(Calendar.HOUR_OF_DAY, RandomElements.getRandomElement(WORK_HOURS));
        calendar.set(Calendar.MINUTE, RandomElements.getRandomElement(MINUTES));
        return new Time(calendar.getTimeInMillis());
    }

    private static Time getRandomTimeAfter(Time startTime) {
        Calendar calendar = Calendar.getInstance();
        calendar.setTime(startTime);
        calendar.add(Calendar.HOUR, RandomElements.getRandomElement(TIME_INCREMENT_HOURS));
        return new Time(calendar.getTimeInMillis());
    }
}
