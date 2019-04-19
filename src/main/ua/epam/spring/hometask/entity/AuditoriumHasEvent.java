package ua.epam.spring.hometask.entity;

import java.time.LocalDateTime;
import java.util.Objects;

public class AuditoriumHasEvent {

    private Auditorium auditorium;

    private Event event;

    private LocalDateTime airDateTime;

    public Auditorium getAuditorium() {
        return auditorium;
    }

    public void setAuditorium(Auditorium auditorium) {
        this.auditorium = auditorium;
    }

    public Event getEvent() {
        return event;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public LocalDateTime getAirDateTime() {
        return airDateTime;
    }

    public void setAirDateTime(LocalDateTime airDateTime) {
        this.airDateTime = airDateTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof AuditoriumHasEvent)) return false;
        AuditoriumHasEvent that = (AuditoriumHasEvent) o;
        return Objects.equals(getAuditorium(), that.getAuditorium()) &&
                Objects.equals(getEvent(), that.getEvent()) &&
                Objects.equals(getAirDateTime(), that.getAirDateTime());
    }

    @Override
    public int hashCode() {

        return Objects.hash(getAuditorium(), getEvent(), getAirDateTime());
    }
}
