package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.JdbcTicketDao;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.Ticket;
import ua.epam.spring.hometask.service.TicketService;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Set;

@Service
    public class TicketServiceImpl implements TicketService {

        private JdbcTicketDao jdbcTicketDao;

    @Autowired
    public TicketServiceImpl(JdbcTicketDao jdbcTicketDao) {
        this.jdbcTicketDao = jdbcTicketDao;
    }


    @Override
    public Ticket save(@Nonnull Ticket object) {
        return jdbcTicketDao.save(object);
    }

    @Override
    public void remove(@Nonnull Ticket object) {

    }

    @Override
    public Ticket getById(@Nonnull Long id) {
        return null;
    }

    @Nonnull
    @Override
    public Collection getAll() {
        return jdbcTicketDao.getAll();
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        return jdbcTicketDao.getPurchasedTicketsForEvent(event, dateTime);
    }
}
