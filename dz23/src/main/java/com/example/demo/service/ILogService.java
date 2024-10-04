package com.example.demo.service;

import com.example.demo.model.LogData;

import java.util.List;

public interface ILogService {
    LogData save(LogData logData);
    int[] saveLogsDataList(List<LogData> logsData);
    void update(LogData logData);
    void delete(LogData logData);
    List<LogData> findAll();
    void deleteAll();

    List<LogData> findAllLogs(Integer limit);
}
