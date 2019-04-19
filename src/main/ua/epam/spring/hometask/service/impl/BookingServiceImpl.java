package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.entity.*;
import ua.epam.spring.hometask.service.*;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Set;
import java.util.stream.Collectors;

@Service
public class BookingServiceImpl implements BookingService {

    private AuditoriumHasEventService auditoriumHasEventService;
    private TicketService ticketService;
    private OrderService orderService;
    private DiscountService discountService;

    @Autowired
    public BookingServiceImpl(AuditoriumHasEventService auditoriumHasEventService, DiscountService discountService,
                              TicketService ticketService, OrderService orderService) {
        this.auditoriumHasEventService = auditoriumHasEventService;
        this.discountService = discountService;
        this.ticketService = ticketService;
        this.orderService = orderService;
    }

    @Override
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user, @Nonnull Set<Long> seats) {
        int countVipSeatsInOrder =
                (int) auditoriumHasEventService.getEventVipSeats(event,dateTime).stream().filter(c -> seats.contains(c)).count();
        double priceForVipInOrder = highPriceForVipSeats(countVipSeatsInOrder, event.getBasePrice());
        double totalPrice = ((seats.size() - countVipSeatsInOrder) * event.getBasePrice() + priceForVipInOrder);
        if (event.getRating().equals(EventRating.MID)) {
            totalPrice *= 1.1;
        } else if (event.getRating().equals(EventRating.HIGH)) {
            totalPrice *= 1.2;
        }
        return totalPrice - discountService.getDiscount(user, event, dateTime, seats.size(), totalPrice);
    }

    private double highPriceForVipSeats(int numberOfVipSeats, double eventBasePriceVip) {
        return numberOfVipSeats * eventBasePriceVip * 2;
    }

    @Override
    public void bookTickets(@Nonnull Set<Ticket> tickets, @Nullable User user) {
        Ticket ticket = tickets.stream().findFirst().get();
        Order order = new Order();
        order.setPrice(getTicketsPrice(ticket.getEvent(),ticket.getDateTime(),user,tickets.stream().map(t -> t.getSeat()).collect(Collectors.toSet())));
        order.setUser(user);
        final Order o = orderService.save(order);
        tickets.stream().forEach(t ->{t.setOrder(o);ticketService.save(t);} );
    }


/*

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return purchasedTickets.stream().flatMap(order -> order.getTicketSet().stream()).collect(Collectors.toSet());
        // return purchasedTickets.stream().filter(ticket -> ticket.getEvent().equals(event) && ticket.getDateTime().equals(dateTime)).collect(Collectors.toSet());
    }

*/
}
