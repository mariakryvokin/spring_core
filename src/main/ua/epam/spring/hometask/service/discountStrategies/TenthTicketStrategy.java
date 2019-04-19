package ua.epam.spring.hometask.service.discountStrategies;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.User;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.UserService;

import java.time.LocalDateTime;

@Component
public class TenthTicketStrategy implements DiscountStrategies {

    private UserService userService;

    @Autowired
    public TenthTicketStrategy(UserService userService) {
        this.userService = userService;
    }

    private long tenthTicket(User user, long emount){
        if(emount<10 && user!=null){
            int count = (int) userService.getAllUserTickets(user).stream().count();
            return (count%10+emount)/10;
        }
        return emount/10;
    }


    @Override
    public double getDiscount(User user, long numberOfTickets, LocalDateTime airday, Event event, double totalPrice) {
        double tenth = tenthTicket(user, numberOfTickets) * (totalPrice/numberOfTickets);
        return tenth*0.5;
    }
}
