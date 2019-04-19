package ua.epam.spring.hometask.entity;

import java.time.LocalDateTime;
import java.util.Objects;

/**
 * @author Yuriy_Tkach
 */
public class Ticket extends DomainObject{

    private Event event;

    private Order order;

    private LocalDateTime dateTime;

    private long seat;

    public Event getEvent() {
        return event;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public long getSeat() {
        return seat;
    }

    public void setEvent(Event event) {
        this.event = event;
    }

    public void setDateTime(LocalDateTime dateTime) {
        this.dateTime = dateTime;
    }

    public Order getOrder() {
        return order;
    }

    public void setOrder(Order order) {
        this.order = order;
    }

    public void setSeat(long seat) {
        this.seat = seat;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Ticket)) return false;
        Ticket ticket = (Ticket) o;
        return getSeat() == ticket.getSeat() &&
                Objects.equals(getOrder(), ticket.getOrder());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getOrder(), getSeat());
    }

    @Override
    public String toString() {
        return "Ticket{" +
                " id=" + super.getId() +
                " event=" + event +
                ", dateTime=" + dateTime +
                ", seat=" + seat +
                '}';
    }
}
