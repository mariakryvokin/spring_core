package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.annotation.AfterReturning;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.Statistics;
import ua.epam.spring.hometask.entity.Ticket;
import ua.epam.spring.hometask.entity.User;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.DiscountService;
import ua.epam.spring.hometask.service.StatisticsService;
import ua.epam.spring.hometask.service.UserService;
import ua.epam.spring.hometask.service.discountStrategies.BirthdayStrategy;
import ua.epam.spring.hometask.service.discountStrategies.DiscountStrategies;
import ua.epam.spring.hometask.service.discountStrategies.TenthTicketStrategy;
/*import ua.epam.spring.hometask.service.discountStrategies.DiscountStrategies;*/

import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Aspect
@Component
public class DiscountAspect {

    @Autowired
    private DiscountService discountService;
    @Autowired
    private BookingService bookingService;
    @Autowired
    private StatisticsService statisticsService;
    @Autowired
    private UserService userService;

    @Pointcut("execution (* ua.epam.spring.hometask.service.BookingService.bookTickets(..))")
    public void getNameOfGivenDiscount(){}


    @AfterReturning(pointcut = "getNameOfGivenDiscount() && args(tickets,user)")
    public void countDiscountCall(Set<Ticket> tickets, User user){
        Event event = tickets.stream().findFirst().get().getEvent();
        LocalDateTime dateTime = tickets.stream().findFirst().get().getDateTime();
        Set<Long> seats = tickets.stream().map(value -> value.getSeat()).collect(Collectors.toSet());
        double totalPrice = bookingService.getTicketsPrice(event, dateTime, user, seats);

        double discount = discountService.getDiscountStrategies().stream()
                .map(d->d.getDiscount(user,tickets.size(),dateTime,event,totalPrice)).max(Double::compareTo).get();
        String discountClass = null;
        if(discount != 0){
            for (DiscountStrategies discountStrategies : discountService.getDiscountStrategies().stream().collect(Collectors.toList()) ){
                if(discountStrategies.getDiscount(user,tickets.size(),dateTime,event,totalPrice) == discount){
                    discountClass = discountStrategies.getClass().getName();
                    break;
                }
            }
            Statistics statistics = new Statistics();
            statistics.setUser(user);
            statistics.setEvent(event);

            if(discountClass.equals(BirthdayStrategy.class.getName())){
                discountClass = Counters.BIRTHDAY_STRATEGY.toString();
                statistics.setAmount(1L);
            }
            else if(discountClass.equals(TenthTicketStrategy.class.getName())){
                discountClass = Counters.TENTH_STRATEGY.toString();
                statistics.setAmount( user != null ? (userService.getAllUserTickets(user).stream().count()%10 + tickets.size()) /10 : tickets.size() /10 );
            }
            statistics.setAction(discountClass);
            statisticsService.save(statistics);
        }

        System.out.println("DISCOUNT ASPECT WORK!");

    }

}
