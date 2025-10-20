package com.intuitive.intuitivepos;


import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseConnectionChecker {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @PostConstruct
    public void checkConnection() {
        try {
            // Simple query to test DB connection
            jdbcTemplate.execute("SELECT 1");
            System.out.println("✅ Database connection SUCCESSFUL!");
        } catch (Exception e) {
            System.err.println("❌ Database connection FAILED!");
            e.printStackTrace();
        }
    }
}