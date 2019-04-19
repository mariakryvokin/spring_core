package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.JdbcAuditoriumHasEventDao;
import ua.epam.spring.hometask.entity.AuditoriumHasEvent;
import ua.epam.spring.hometask.entity.Event;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

@Repository
public class JdbcAuditoriumHasEventDaoImpl  implements JdbcAuditoriumHasEventDao {

    private JdbcTemplate jdbcTemplate;

    @Autowired
    public JdbcAuditoriumHasEventDaoImpl(@Qualifier("mainJdbcTemplate") JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public AuditoriumHasEvent save(@Nonnull AuditoriumHasEvent object) {
        String sql = "INSERT INTO auditoriums_has_event (auditoriums_name, air_date, events_id) values (?, ?, ?)";
        this.jdbcTemplate.update(sql,object.getAuditorium().getName(),String.valueOf(object.getAirDateTime()),object.getEvent().getId());
        return object;
    }

    @Override
    public void remove(@Nonnull AuditoriumHasEvent object) {

    }

    @Override
    public AuditoriumHasEvent getById(@Nonnull Long id) {
        return null;
    }

    @Nonnull
    @Override
    public Collection getAll() {
        return null;
    }

    @Override
    public List<Long> getEventVipSeats(Event event, LocalDateTime localDateTime) {
        String sql = "SELECT number FROM auditoriums_has_event a, vip_seats v WHERE a.auditoriums_name = v.auditoriums_name AND events_id = "+event.getId() +
                " AND a.air_date = '"+ localDateTime + "'";
        List<Map<String,Object>> maps = jdbcTemplate.queryForList(sql);
        return maps.stream().map(s -> (Long)s.get("number")).collect(Collectors.toList());
    }
}
