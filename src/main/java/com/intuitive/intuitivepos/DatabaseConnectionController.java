package com.intuitive.intuitivepos;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class DatabaseConnectionController {

    private final JdbcTemplate jdbcTemplate;

    public DatabaseConnectionController(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    // ✅ Manual API to test DB connection
    @GetMapping("/test-connection")
    public String testConnection() {
        try {
            jdbcTemplate.execute("SELECT 1");
            return "✅ Database connection SUCCESSFUL!";
        } catch (Exception e) {
            e.printStackTrace();
            return "❌ Database connection FAILED: " + e.getMessage();
        }
    }
}