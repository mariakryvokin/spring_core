package ua.epam.spring.hometask.service.discountStrategies;

import org.junit.Assert;
import org.junit.Test;
import org.springframework.jdbc.core.JdbcTemplate;
import ua.epam.spring.hometask.dao.impl.JdbcUserDaoImpl;
import ua.epam.spring.hometask.entity.User;
import ua.epam.spring.hometask.service.UserService;
import ua.epam.spring.hometask.service.impl.UserServiceImpl;

import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class TenthTicketStrategyTest {

    UserService userService = new UserServiceImpl(new JdbcUserDaoImpl(new JdbcTemplate()));
    TenthTicketStrategy tenthTicketStrategy = new TenthTicketStrategy(userService);

    @Test
    public void getDiscount() {
        Assert.assertEquals(5d,tenthTicketStrategy.getDiscount(null, 15, LocalDateTime.now(), null, 150d),0.01);
    }
}