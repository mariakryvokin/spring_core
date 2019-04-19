package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.Ticket;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Set;

public interface JdbcTicketDao extends AbstractDomainObjectDao<Ticket>{

    /**
     * Getting all purchased tickets for event on specific air date and time
     *
     * @param event
     *            Event to get tickets for
     * @param dateTime
     *            Date and time of airing of event
     * @return set of all purchased tickets
     */
    public @Nonnull
    Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime);
}
