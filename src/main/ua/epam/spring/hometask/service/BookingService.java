package ua.epam.spring.hometask.service;

import java.time.LocalDateTime;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.Order;
import ua.epam.spring.hometask.entity.Ticket;
import ua.epam.spring.hometask.entity.User;

/**
 * @author Yuriy_Tkach
 */
public interface BookingService {

    /**
     * Getting price when buying all supplied seats for particular event
     * 
     * @param event
     *            Event to get base ticket price, vip seats and other
     *            information
     * @param dateTime
     *            Date and time of event air
     * @param user
     *            User that buys ticket could be needed to calculate discount.
     *            Can be <code>null</code>
     * @param seats
     *            Set of seat numbers that user wants to buy
     * @return total price
     */
    public double getTicketsPrice(@Nonnull Event event, @Nonnull LocalDateTime dateTime, @Nullable User user,
            @Nonnull Set<Long> seats);

    /**
     * Books tickets in internal system. If user is not
     * <code>null</code> in a ticket then booked tickets are saved with it
     * 
     * @param tickets
     *            Set of tickets
     */
    public void bookTickets(@Nonnull Set<Ticket> tickets,@Nullable User user);



}
