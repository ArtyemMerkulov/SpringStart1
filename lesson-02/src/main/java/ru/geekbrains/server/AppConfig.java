package ru.geekbrains.server;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import ru.geekbrains.server.auth.AuthService;
import ru.geekbrains.server.auth.AuthServiceJdbcImpl;
import ru.geekbrains.server.persistance.UserRepository;

import javax.sql.DataSource;

@Configuration
@ComponentScan("ru.geekbrains.server")
public class AppConfig {

    @Bean(name = "mySQLDataSource")
    public DataSource dataSource() {
        DriverManagerDataSource ds = new DriverManagerDataSource("jdbc:mysql://localhost:3306/network_chat?serverTimezone=UTC",
                "root", "root");
        ds.setDriverClassName("com.mysql.cj.jdbc.Driver");
        return ds;
    }

    @Bean
    public AuthService authService() {
        return new AuthServiceJdbcImpl();
    }

    @Bean
    public ChatServer chatServer() {
        return new ChatServer();
    }
}
