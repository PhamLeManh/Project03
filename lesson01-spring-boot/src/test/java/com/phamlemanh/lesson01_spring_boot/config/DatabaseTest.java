package com.phamlemanh.lesson01_spring_boot.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Component;

@Component
public class DatabaseTest implements CommandLineRunner {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void run(String... args) throws Exception {
        try {
            // Test với H2 database
            String database = jdbcTemplate.queryForObject("SELECT H2VERSION()", String.class);
            System.out.println("✅ Kết nối H2 Database thành công! a hi hi");
            System.out.println("H2 Database Version: " + database);

            // Test truy vấn users table
            try {
                Integer userCount = jdbcTemplate.queryForObject("SELECT COUNT(*) FROM users", Integer.class);
                System.out.println("Số lượng users trong database: " + userCount);
            } catch (Exception e) {
                System.out.println("Table 'users' chưa có dữ liệu: " + e.getMessage());
            }

        } catch (Exception e) {
            System.out.println("❌ Lỗi kết nối database: " + e.getMessage());
        }
    }
}