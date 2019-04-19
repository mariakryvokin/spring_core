package ua.epam.spring.hometask.service.discountStrategies;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.User;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.junit.Assert.*;

public class BirthdayStrategyTest {

    BirthdayStrategy birthdayStrategy = new BirthdayStrategy();
    User user;
    LocalDateTime airDate;

    @Before
    public void init(){
       user = new User();
       user.setBirthday(LocalDate.of(2000,10,10));
       airDate = LocalDateTime.of(2019,10,8,22,10);
    }

    @Test
    public void getDiscount() {
        Event event = new Event();
        Assert.assertEquals(10d,birthdayStrategy.getDiscount(user, 7, airDate, event, 200d),0.01);
    }
}