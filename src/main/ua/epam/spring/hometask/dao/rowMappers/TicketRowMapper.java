package ua.epam.spring.hometask.dao.rowMappers;


import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.entity.Order;
import ua.epam.spring.hometask.entity.Ticket;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class TicketRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Ticket ticket = new Ticket();
        ticket.setId(resultSet.getLong("id"));
        ticket.setSeat(resultSet.getLong("seat"));
        ticket.setDateTime(LocalDateTime.parse(resultSet.getString("date_time")));
        return ticket;
    }

    public List<Ticket> process(List<Map<String,Object>> map){
        List<Ticket> res = new ArrayList<>();
        for(Map row : map){
            Ticket ticket = new Ticket();
            Order order = new Order();
            ticket.setId((Long) row.get("id"));
            ticket.setSeat((Long) row.get("seat"));
            ticket.setDateTime(LocalDateTime.parse(row.get("date_time").toString().replace(" ","T")));
            order.setId((Long) row.get("orders_id"));
            ticket.setOrder(order);
            res.add(ticket);
        }
        return res;
    }
}
