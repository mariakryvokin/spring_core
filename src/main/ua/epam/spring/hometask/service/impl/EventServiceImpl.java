package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.JdbcEventDao;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.service.EventService;

import javax.annotation.Nonnull;
import javax.annotation.Nullable;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.Optional;
import java.util.Set;

@Service
public class EventServiceImpl implements EventService {

    private JdbcEventDao jdbcEventDao;

    @Autowired
    public EventServiceImpl(JdbcEventDao eventDao) {
        this.jdbcEventDao = eventDao;
    }

    @Nullable
    @Override
    public Optional<Event> getByName(@Nonnull String name) {
        return jdbcEventDao.getByName(name);
    }

    @Nonnull
    @Override
    public Set<Event> getForDateRange(@Nonnull LocalDateTime from, @Nonnull LocalDateTime to) {
        return jdbcEventDao.getForDateRange(from,to);
    }

    @Nonnull
    @Override
    public Set<Event> getNextEvents(@Nonnull LocalDateTime to) {
        return jdbcEventDao.getNextEvents(to);
    }

    @Override
    public Event save(@Nonnull Event object) {
        return jdbcEventDao.save(object);
    }

    @Override
    public void remove(@Nonnull Event object) {
        jdbcEventDao.remove(object);
    }

    @Override
    public Event getById(@Nonnull Long id) {
        return jdbcEventDao.getById(id);
    }

    @Nonnull
    @Override
    public Collection<Event> getAll() {
        return jdbcEventDao.getAll();
    }
}
