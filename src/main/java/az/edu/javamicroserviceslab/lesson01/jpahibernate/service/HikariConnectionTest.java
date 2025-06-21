package az.edu.javamicroserviceslab.lesson01.jpahibernate.service;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class HikariConnectionTest {

    public static void main(String[] args) {
        // 1. Hikari config
        HikariConfig config = new HikariConfig();
        config.setJdbcUrl("jdbc:postgresql://localhost:5432/studentdb");
        config.setUsername("postgres");
        config.setPassword("postgres");
        config.setMaximumPoolSize(20);     // Max 20 connection
        config.setMinimumIdle(5);          // Minimum 5 idle
        config.setIdleTimeout(30000);      // 30 saniyə boşda qalsa bağlanacaq
        config.setConnectionTimeout(10000); // 10 saniyə gözləmə vaxtı

        HikariDataSource dataSource = new HikariDataSource(config);

        // 2. Thread hovuzu (20 paralel sorğu üçün)
        ExecutorService executor = Executors.newFixedThreadPool(20);

        for (int i = 1; i <= 25; i++) {
            final int taskId = i;
            executor.submit(() -> {
                try (Connection ignored = dataSource.getConnection()) {
                    System.out.println("Thread #" + taskId + " - Connection acquired.");
                    // Simulate some DB usage
                    Thread.sleep(15000);
                    System.out.println("Thread #" + taskId + " - Connection released.");
                } catch (SQLException | InterruptedException e) {
                    System.err.println("Thread #" + taskId + " - ERROR: " + e.getMessage());
                }
            });
        }

        executor.shutdown();
    }
}
