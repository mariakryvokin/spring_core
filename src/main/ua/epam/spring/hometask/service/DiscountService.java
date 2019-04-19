package ua.epam.spring.hometask.service;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Set;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;

import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.Ticket;
import ua.epam.spring.hometask.entity.User;
import ua.epam.spring.hometask.service.discountStrategies.DiscountStrategies;

/**
 * @author Yuriy_Tkach
 */
public interface DiscountService {
    /**
     * Getting discount based on some rules for user that buys some number of
     * tickets for the specific date time of the event
     * 
     * @param user
     *            User that buys tickets. Can be <code>null</code>
     * @param event
     *            Event that tickets are bought for
     * @param airDateTime
     *            The date and time event will be aired
     * @param numberOfTickets
     *            Number of tickets that user buys
     * @return discount value from 0 to 100
     */
    double getDiscount(@Nullable User user, @Nonnull Event event, @Nonnull LocalDateTime airDateTime, long numberOfTickets,double totalPrice);

    List<DiscountStrategies> getDiscountStrategies();
}
