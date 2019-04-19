package ua.epam.spring.hometask.service.discountStrategies;

import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.User;

import java.time.LocalDateTime;

@Component
public class BirthdayStrategy implements DiscountStrategies{

    private boolean birthdayStrategy(User user, LocalDateTime airDay){
        if (user!=null){
            return airDay.getMonth().equals(user.getBirthday().getMonth()) &&
                    (airDay.getDayOfMonth() - 5) <= user.getBirthday().getDayOfMonth() &&
                    (airDay.getDayOfMonth() + 5) >= user.getBirthday().getDayOfMonth();
        }
        return false;
    }

    @Override
    public double getDiscount(User user, long numberOfTickets, LocalDateTime airday, Event event, double totalPrice) {
        if (birthdayStrategy(user,airday)){
            return totalPrice*0.05;
        }
        return 0;
    }
}
