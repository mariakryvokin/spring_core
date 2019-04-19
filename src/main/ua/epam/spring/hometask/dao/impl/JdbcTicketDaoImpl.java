package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.JdbcTicketDao;
import ua.epam.spring.hometask.dao.rowMappers.TicketRowMapper;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.Ticket;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class JdbcTicketDaoImpl implements JdbcTicketDao {


    private JdbcTemplate jdbcTemplate;
    private TicketRowMapper ticketRowMapper;

    @Autowired
    public JdbcTicketDaoImpl(@Qualifier("mainJdbcTemplate") JdbcTemplate jdbcTemplate, TicketRowMapper ticketRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.ticketRowMapper = ticketRowMapper;
    }


    @Override
    public Ticket save(@Nonnull Ticket object) {
        String sql = "INSERT INTO tickets (seat, date_time, events_id, orders_id) values (?, ?, ?, ?)";
        jdbcTemplate.update(sql,object.getSeat(),String.valueOf(object.getDateTime()),object.getEvent().getId(),object.getOrder().getId());
        return object;
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
        String sql = "SELECT * FROM tickets";
        List<Map<String,Object>> tickets = jdbcTemplate.queryForList(sql);
        return ticketRowMapper.process(tickets);
    }

    @Nonnull
    @Override
    public Set<Ticket> getPurchasedTicketsForEvent(@Nonnull Event event, @Nonnull LocalDateTime dateTime) {
        String sql = "SELECT * FROM tickets WHERE events_id = "+event.getId()+"  AND date_time = '"+dateTime+"'";
        List<Map<String, Object>> map = jdbcTemplate.queryForList(sql);
        return ticketRowMapper.process(map).stream().collect(Collectors.toSet());
    }
}
