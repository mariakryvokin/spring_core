package ua.epam.spring.hometask.dao.rowMappers;

import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.EventRating;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Component
public class EventRowMapper implements RowMapper {
    @Override
    public Object mapRow(ResultSet resultSet, int i) throws SQLException {
        Event event = new Event();
        event.setId(resultSet.getLong("id"));
        event.setName(resultSet.getString("name"));
        event.setBasePrice(resultSet.getDouble("base_price"));
        event.setRating(EventRating.valueOf(resultSet.getString("rating")));
        return event;
    }

    public List<Event> process(List<Map<String,Object>> map ){
        List<Event> events = new ArrayList<>();
        for (Map row : map){
            Event event = new Event();
            event.setId((Long) row.get("id"));
            event.setName(row.get("name").toString());
            event.setBasePrice((Double) row.get("base_price"));
            event.setRating(EventRating.valueOf(row.get("rating").toString()));
            events.add(event);
        }
        return events;
    }
}
