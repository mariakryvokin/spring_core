package ua.epam.spring.hometask.aspects;

import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.entity.Order;
import ua.epam.spring.hometask.entity.Ticket;
import ua.epam.spring.hometask.entity.User;
import ua.epam.spring.hometask.service.BookingService;
import ua.epam.spring.hometask.service.OrderService;
import ua.epam.spring.hometask.service.TicketService;
import ua.epam.spring.hometask.service.UserService;

import java.util.Random;
import java.util.Set;
import java.util.stream.Collectors;

@Aspect
@Component
public class LuckyWinnerAspect {
    private  Random random = new Random();

    private OrderService orderService;
    private TicketService ticketService;
    private UserService userService;

    @Autowired
    public LuckyWinnerAspect(OrderService orderService, TicketService ticketService,
                             UserService userService) {
        this.orderService = orderService;
        this.ticketService = ticketService;
        this.userService = userService;
    }

    @Pointcut("execution(* ua.epam.spring.hometask.service.impl.BookingServiceImpl.bookTickets(..)) ")
    public void bookTickets(){}

    @Around("bookTickets() && args(tickets, user)")
    public void luckyWinnerPrice(ProceedingJoinPoint proceedingJoinPoint, Set<Ticket> tickets, User user){
        if (luckyWinner(user)){
            Order order = new Order();
            order.setPrice(0D);
            order.setUser(user);
            final Order o = orderService.save(order);
            tickets.stream().forEach(t ->{t.setOrder(o);ticketService.save(t);} );
            System.out.println("LuckyWinnerAspect work for"+ user);
        }else {
            try {
                proceedingJoinPoint.proceed();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            }
        }

    }

    private boolean luckyWinner(User user){
        if(user != null){
            //return user.getId()==2;
            return userService.getAllUserTickets(user).size() == random.nextInt(Integer.MAX_VALUE);
        }
        return false;
    }

}
