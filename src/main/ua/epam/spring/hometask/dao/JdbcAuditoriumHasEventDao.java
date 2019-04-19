package ua.epam.spring.hometask.dao;

import ua.epam.spring.hometask.entity.Auditorium;
import ua.epam.spring.hometask.entity.AuditoriumHasEvent;
import ua.epam.spring.hometask.entity.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface JdbcAuditoriumHasEventDao extends AbstractDomainObjectDao<AuditoriumHasEvent>{

    public List<Long> getEventVipSeats(Event event, LocalDateTime localDateTime);
}
