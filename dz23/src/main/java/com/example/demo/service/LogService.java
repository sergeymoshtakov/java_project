package com.example.demo.service;

import com.example.demo.dao.LogRepository;
import com.example.demo.model.LogData;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * Service class for managing log entries.
 * Provides methods for performing CRUD operations and fetching logs with an optional limit.
 */
@Service
public class LogService implements ILogService {

    private final LogRepository logRepository;

    /**
     * Constructor for the LogService class.
     *
     * @param logRepository Repository for accessing log data.
     */
    @Autowired
    public LogService(LogRepository logRepository) {
        this.logRepository = logRepository;
    }

    /**
     * Saves a new log entry to the database.
     *
     * @param logData Log entry data to be saved.
     * @return The saved log entry with an assigned ID.
     */
    @Override
    public LogData save(LogData logData) {
        return logRepository.save(logData);
    }

    /**
     * Saves a list of log entries to the database.
     *
     * @param logsData List of log entries to be saved.
     * @return An array of integers representing the count of saved entries (each entry represented by 1).
     */
    @Override
    public int[] saveLogsDataList(List<LogData> logsData) {
        logRepository.saveAll(logsData);
        return logsData.stream().mapToInt(log -> 1).toArray();
    }

    /**
     * Updates an existing log entry in the database.
     * Saves the log entry only if an entry with the given ID exists.
     *
     * @param logData Log entry data to be updated.
     */
    @Override
    public void update(LogData logData) {
        if (logRepository.existsById(logData.getId())) {
            logRepository.save(logData);
        }
    }

    /**
     * Deletes the specified log entry from the database.
     *
     * @param logData Log entry data to be deleted.
     */
    @Override
    public void delete(LogData logData) {
        logRepository.delete(logData);
    }

    /**
     * Retrieves all log entries from the database.
     *
     * @return A list of all log entries stored in the database.
     */
    @Override
    public List<LogData> findAll() {
        return logRepository.findAll();
    }

    /**
     * Deletes all log entries from the database.
     */
    @Override
    public void deleteAll() {
        logRepository.deleteAll();
    }

    /**
     * Retrieves a list of log entries with an optional limit on the number of entries.
     * If the {@code limit} parameter is specified, returns the given number of log entries.
     * If the {@code limit} parameter is not specified (null), returns all log entries.
     *
     * @param limit The maximum number of log entries to be returned. Can be null.
     * @return A list of log entries according to the specified limit.
     */
    @Override
    public List<LogData> findAllLogs(Integer limit) {
        List<LogData> logs = logRepository.findAll();
        if (limit != null) {
            return logs.stream().limit(limit).toList();
        }
        return logs;
    }
}
