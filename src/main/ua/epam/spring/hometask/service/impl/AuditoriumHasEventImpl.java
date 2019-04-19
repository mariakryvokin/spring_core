package ua.epam.spring.hometask.service.impl;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.epam.spring.hometask.dao.JdbcAuditoriumHasEventDao;
import ua.epam.spring.hometask.entity.AuditoriumHasEvent;
import ua.epam.spring.hometask.entity.Event;
import ua.epam.spring.hometask.service.AuditoriumHasEventService;

import javax.annotation.Nonnull;
import java.time.LocalDateTime;
import java.util.Collection;
import java.util.List;


@Service
public class AuditoriumHasEventImpl implements AuditoriumHasEventService {

    private JdbcAuditoriumHasEventDao auditoriumHasEventDao;

    @Autowired
    public AuditoriumHasEventImpl(JdbcAuditoriumHasEventDao auditoriumHasEventDao) {
        this.auditoriumHasEventDao = auditoriumHasEventDao;
    }

    @Override
    public AuditoriumHasEvent save(@Nonnull AuditoriumHasEvent object) {
        return auditoriumHasEventDao.save(object);
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
    public Collection<AuditoriumHasEvent> getAll() {
        return null;
    }

    @Override
    public List<Long> getEventVipSeats(Event event, LocalDateTime localDateTime) {
        return auditoriumHasEventDao.getEventVipSeats(event,localDateTime);
    }
}
