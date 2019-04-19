package ua.epam.spring.hometask.entity;

public class VipSeats {

    private Auditorium auditorium;

    private long number;

    public VipSeats(Auditorium auditorium, long number) {
        this.auditorium = auditorium;
        this.number = number;
    }
}
