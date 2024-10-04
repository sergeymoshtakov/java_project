package com.example.demo.controller;

import com.example.demo.model.LogData;
import com.example.demo.service.ILogService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * REST controller for managing log entries.
 * Provides endpoints for creating a new log and retrieving existing logs.
 */
@RestController
@RequestMapping("/api/logs")
@RequiredArgsConstructor
public class LogController {

    private final ILogService logService;

    /**
     * Creates a new log entry in the database.
     * Endpoint: POST /api/logs
     *
     * @param logData The log entry data to be created.
     * @return A response containing the ID of the newly created log entry.
     */
    @PostMapping
    public ResponseEntity<?> createLog(@RequestBody LogData logData) {
        LogData savedLog = logService.save(logData);
        return ResponseEntity.ok().body("{\"id\":" + savedLog.getId() + "}");
    }

    /**
     * Retrieves a list of log entries from the database.
     * Endpoint: GET /api/logs
     * If the {@code limit} parameter is provided, it restricts the number of returned logs.
     * If the {@code limit} parameter is not provided, all logs are returned.
     *
     * @param limit Optional parameter to specify the maximum number of logs to retrieve.
     * @return A response containing the list of log entries.
     */
    @GetMapping
    public ResponseEntity<List<LogData>> getLogs(@RequestParam(required = false) Integer limit) {
        List<LogData> logs = logService.findAllLogs(limit);
        return ResponseEntity.ok(logs);
    }
}
