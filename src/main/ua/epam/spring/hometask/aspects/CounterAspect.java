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
import ua.epam.spring.hometask.service.StatisticsService;

import java.time.LocalDateTime;
import java.util.Set;

@Aspect
@Component
public class CounterAspect {

    @Autowired
    private StatisticsService statisticsService;

    @Pointcut("execution(* ua.epam.spring.hometask.service.EventService.getByName(String))")
    public void eventGetByNameCallCount(){}

    @Pointcut("execution(* ua.epam.spring.hometask.service.impl.BookingServiceImpl.bookTickets(..))")
    public void eventBookTicketCallCount(){}

    @Pointcut("execution(* ua.epam.spring.hometask.service.impl.BookingServiceImpl.getTicketsPrice(..))")
    public void eventTicketPriceCallCount(){}

    @AfterReturning(pointcut = "eventGetByNameCallCount()",returning = "retVal")
    public void eventGetByName(Event retVal){
        Statistics statistics = new Statistics();
        statistics.setAction(Counters.EVENT_GET_BY_NAME_CALL_COUNT.toString());
        statistics.setAmount(1L);
        statistics.setEvent(retVal);
        statisticsService.save(statistics);
        System.out.println("ASPECT eventGetByNameCallCount  work");
    }

    @AfterReturning(pointcut = "eventTicketPriceCallCount() && args(event,dateTime,user,..)")
    public void eventGetTicketPrice(Event event, LocalDateTime dateTime, User user){
        Statistics statistics = new Statistics();
        statistics.setAction(Counters.GET_TICKETS_PRICE_CALL_COUNT.toString());
        statistics.setAmount(1L);
        statistics.setEvent(event);
        statistics.setUser(user);
        statisticsService.save(statistics);
        System.out.println("ASPECT eventTicketPriceCallCount work! ");
    }

    @AfterReturning( pointcut = "eventBookTicketCallCount() && args(tickets, user)")
    public void eventBookingCallTicketCount(Set<Ticket> tickets, User user){
        Statistics statistics = new Statistics();
        statistics.setAction(Counters.BOOK_TICKETS_CALL_COUNT.toString());
        statistics.setAmount((long) tickets.size());
        statistics.setEvent(tickets.stream().findFirst().get().getEvent());
        statistics.setUser(user);
        statisticsService.save(statistics);
        System.out.println("ASPECT eventBookedTicketCount work!");
    }

}
