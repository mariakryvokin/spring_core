package ua.epam.spring.hometask.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.*;
import org.springframework.context.support.PropertySourcesPlaceholderConfigurer;
import org.springframework.core.env.Environment;
import ua.epam.spring.hometask.entity.Auditorium;

import java.util.*;


@Configuration
@ComponentScan(basePackages = "ua.epam.spring.hometask")
@PropertySource("classpath:auditorium.properties")
@EnableAspectJAutoProxy
public class AppConfig {

    @Autowired
    Environment env;

    /**
     * Property placeholder configurer needed to process @Value annotations
     */
    @Bean
    public static PropertySourcesPlaceholderConfigurer propertyConfigurer() {
        return new PropertySourcesPlaceholderConfigurer();
    }

}
