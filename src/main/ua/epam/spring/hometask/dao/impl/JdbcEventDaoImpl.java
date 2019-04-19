package ua.epam.spring.hometask.dao.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.PreparedStatementCreator;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;
import ua.epam.spring.hometask.dao.JdbcEventDao;
import ua.epam.spring.hometask.dao.rowMappers.EventRowMapper;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.entity.EventRating;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.sql.*;
import java.sql.Date;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;
import java.util.stream.Collectors;

@Repository
public class JdbcEventDaoImpl implements JdbcEventDao {

    private JdbcTemplate jdbcTemplate;
    private EventRowMapper eventRowMapper;

    @Autowired
    public JdbcEventDaoImpl(@Qualifier("mainJdbcTemplate") JdbcTemplate jdbcTemplate, EventRowMapper eventRowMapper) {
        this.jdbcTemplate = jdbcTemplate;
        this.eventRowMapper = eventRowMapper;
    }

    @Nullable
    @Override
    public Optional<Event> getByName(@Nonnull String name) {
        return null;
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDateTime from, @Nonnull LocalDateTime to) {
        String sql = "SELECT * FROM events e, auditoriums_has_event a WHERE e.id = a.events_id AND a.air_date BETWEEN '"+ from+"' AND '"+to+"'";
        Set<Event> events = new HashSet<>();
        List<Map<String,Object>> map  = jdbcTemplate.queryForList(sql);
        return eventRowMapper.process(map).stream().collect(Collectors.toSet());
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        String sql = "SELECT * FROM events e, auditoriums_has_event a WHERE e.id = a.events_id AND a.air_date < '"+to+"'";
        List<Map<String,Object>> map  = jdbcTemplate.queryForList(sql);
        return eventRowMapper.process(map).stream().collect(Collectors.toSet());
    }

    @Override
    public Event save(@Nonnull Event object) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "INSERT INTO events (`name`, base_price, rating) values (?, ?, ?)";
        this.jdbcTemplate.update(new PreparedStatementCreator() {
            @Override
            public PreparedStatement createPreparedStatement(Connection connection) throws SQLException {
                PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
                ps.setString(1,object.getName());
                ps.setDouble(2,object.getBasePrice());
                ps.setString(3,object.getRating().toString());
                return ps;
            }
        },keyHolder);
        object.setId((Long) keyHolder.getKey());
        return object;
    }

    @Override
    public void remove(@Nonnull Event object){
    }

    @Override
    public Event getById(@Nonnull Long id) {
        String sql = "SELECT * FROM events WHERE id = ?";
        return  (Event) jdbcTemplate.queryForObject(sql, new Object[] {id}, new EventRowMapper());
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        String sql = "SELECT * FROM events";
        Collection<Event> events = new ArrayList<>();
        List<Map<String,Object>> map  = jdbcTemplate.queryForList(sql);
        eventRowMapper.process(map).stream().forEach(event -> events.add(event));
        return events;
    }
}
