package ua.epam.spring.hometask.service;

import ua.epam.spring.hometask.entity.AuditoriumHasEvent;
import ua.epam.spring.hometask.entity.Event;

import java.time.LocalDateTime;
import java.util.List;

public interface AuditoriumHasEventService extends AbstractDomainObjectService<AuditoriumHasEvent> {

    public List<Long> getEventVipSeats(Event event, LocalDateTime localDateTime);

}
