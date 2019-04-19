package ua.epam.spring.hometask.config;


import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

@Configuration
@PropertySource("classpath:auditorium.properties")
public class DiscountConfig {

}
