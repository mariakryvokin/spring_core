package ua.epam.spring.hometask.commands;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Controller;
import ua.epam.spring.hometask.aspects.Counters;
import ua.epam.spring.hometask.entity.*;
import ua.epam.spring.hometask.service.*;

import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

@Controller
public class AdminCommands {

    private Scanner scanner = new Scanner(System.in);
    private AuditoriumService auditoriumService;
    private EventService eventService;
    private TicketService ticketService;
    private StatisticsService statisticsService;
    private UserService userService;
    private AuditoriumHasEventService auditoriumHasEventService;
    private String inputOfWrongData = "Please enter ring data in right format!";

    @Autowired
    public AdminCommands(AuditoriumService auditoriumService, EventService eventService, AuditoriumHasEventService auditoriumHasEventService,
                         TicketService ticketService, StatisticsService statisticsService, UserService userService) {
        this.auditoriumService = auditoriumService;
        this.eventService = eventService;
        this.auditoriumHasEventService = auditoriumHasEventService;
        this.ticketService = ticketService;
        this.statisticsService = statisticsService;
        this.userService = userService;
    }

    public   void optionsFroAdmin() {
        System.out.println("What are you going to do: E - enter events,V - view purchased tickets, S - show statistics ?");
        String thingToDo = scanner.nextLine();
        switch (thingToDo){
            case "E": enterEvent();
                break;
            case "V": {viewPurchasedTickets(); optionsFroAdmin();}
                break;
            case "S": { showStatisticOfMethodCallsForEvent();
            showStatisticsForDiscounts();
            optionsFroAdmin();
            }
            break;
        }
    }

    private void showStatisticsForDiscounts() {
        try {
            System.out.println("Please enter user id");
            Long user_id = Long.valueOf(scanner.nextLine());
            List<String> list = new ArrayList<>();
            list.add(Counters.BIRTHDAY_STRATEGY.toString());
            list.add(Counters.TENTH_STRATEGY.toString());
            System.out.println("discounts for user"+statisticsService.countEachGivenDiscountAndGivenDiscountForUser(list, userService.getById(user_id)));
            System.out.println("discounts: "+statisticsService.countEachGivenDiscountAndGivenDiscountForUser(list, null));
        }catch (NumberFormatException | DateTimeParseException e) {
            System.err.println(inputOfWrongData);
        }catch (EmptyResultDataAccessException ex){
            System.out.println("No such user exists");
        }
    }

    private void showStatisticOfMethodCallsForEvent() {
        try {
            System.out.println("Please enter event id");
            Long event_id = Long.valueOf(scanner.nextLine());
            System.out.println("call event by name: "+ statisticsService.countMethodsCallForEvent(eventService.getById(event_id), Counters.EVENT_GET_BY_NAME_CALL_COUNT));
            System.out.println("number of booked tickets method call: " + statisticsService.countMethodsCallForEvent(eventService.getById(event_id), Counters.BOOK_TICKETS_CALL_COUNT));
            System.out.println("number of get order price call " + statisticsService.countMethodsCallForEvent(eventService.getById(event_id), Counters.GET_TICKETS_PRICE_CALL_COUNT));
        } catch (NumberFormatException | DateTimeParseException e) {
            System.err.println(inputOfWrongData);
        }catch (EmptyResultDataAccessException ex){
            System.out.println("No such event exists");
        }
    }

    private void viewPurchasedTickets() {
        try {
            System.out.println("Enter please event id and air time (in format 2019-04-09T22:10) each in new line");
            System.out.println(ticketService.getPurchasedTicketsForEvent(eventService.getById(Long.valueOf(scanner.nextLine())),LocalDateTime.parse(scanner.nextLine())));
        }catch (NumberFormatException | DateTimeParseException e) {
            System.err.println(inputOfWrongData);
        }
    }

    public void enterEvent() {
        System.out.println("Enter please event name, basePrice, rating(LOW,MID,HIGH) each from NEW line : ");
        try {
            Event event = new Event();
            String name = scanner.nextLine();
            Double basePrice = Double.valueOf(scanner.nextLine());
            String eventRating =scanner.nextLine();
            event.setName(name);
            event.setRating(EventRating.valueOf(eventRating));
            event.setBasePrice(basePrice);
            event = eventService.save(event);
            System.out.println("Enter number of demonstration:");
            enterDateTImeAndAuditorium(event,Integer.parseInt(scanner.nextLine()));
        }catch (NumberFormatException | DateTimeParseException e){
            System.err.println(inputOfWrongData);
            optionsFroAdmin();
        }
        catch (IllegalArgumentException e){
            System.err.println("Enter exactly LOW, HIGH or MID");
            optionsFroAdmin();
        }catch (org.springframework.dao.DuplicateKeyException e){
            System.err.println("Auditorium has been already booked on this hours!");
            optionsFroAdmin();
        }

    }


    private void enterDateTImeAndAuditorium(Event event, int count) {
        for(int i = 0; i < count; i++){
            System.out.println("You can enter LocalDateTime in format 2007-12-03T10:15:30" +
            " and auditorium(Green,Yellow) each in new line and sequential");
            LocalDateTime dateTime = LocalDateTime.parse(scanner.nextLine());
            Auditorium auditorium = auditoriumService.getByName(scanner.nextLine());
            AuditoriumHasEvent auditoriumHasEvent = new AuditoriumHasEvent();
            auditoriumHasEvent.setEvent(event);
            auditoriumHasEvent.setAuditorium(auditorium);
            auditoriumHasEvent.setAirDateTime(dateTime);
            auditoriumHasEventService.save(auditoriumHasEvent);
            System.out.println("Event created!");
            }
        optionsFroAdmin();
    }
}
