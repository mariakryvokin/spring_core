package ua.epam.spring.hometask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.core.env.Environment;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@PropertySource("classpath:repository.properties")
public class RepositoryConfig {

    @Autowired
    private Environment env;

    @Bean(name = "mainDataSource")
    public DataSource mysqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName(env.getProperty("mysql.driver"));
        dataSource.setUrl(env.getProperty("mysql.jdbcUrl"));
        dataSource.setUsername(env.getProperty("mysql.username"));
        dataSource.setPassword(env.getProperty("mysql.password"));
        return dataSource;
    }

    @Bean(name = "mainJdbcTemplate")
    @Autowired
    public JdbcTemplate jdbcTemplate(@Qualifier("mainDataSource") DataSource dataSource){
        return new JdbcTemplate(dataSource);
    }
}
