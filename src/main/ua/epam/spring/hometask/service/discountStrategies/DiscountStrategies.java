package ua.epam.spring.hometask.service.discountStrategies;

import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.User;

import java.io.Serializable;
import java.time.LocalDateTime;

public interface DiscountStrategies extends Serializable {

    double getDiscount(User user, long numberOfTickets, LocalDateTime airday, Event event,double totalPrice);
}
