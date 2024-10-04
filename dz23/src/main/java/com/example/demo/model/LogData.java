package com.example.demo.model;

import com.example.demo.enums.LogLevel;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "log_data")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class LogData {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private LogLevel level;

    @Column(nullable = false)
    private String src;

    @Column(nullable = false)
    private String message;
}
