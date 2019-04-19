package ua.epam.spring.hometask.commands;


import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import ua.epam.spring.hometask.entity.*;
import ua.epam.spring.hometask.service.*;


import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.format.DateTimeParseException;
import java.util.LinkedHashSet;
import java.util.List;
import java.util.Scanner;
import java.util.Set;
import java.util.stream.Collectors;

@Controller
public class UserCommands {

    private Scanner scanner = new Scanner(System.in);
    private UserService userService;
    private EventService eventService;
    private BookingService bookingService;
    private TicketService ticketService;
    private String inputOfWrongData = "Please enter ring data in right format!";

    @Autowired
    public UserCommands(UserService userService, EventService eventService,
                        BookingService bookingService, TicketService ticketService) {
        this.userService = userService;
        this.eventService = eventService;
        this.bookingService = bookingService;
        this.ticketService = ticketService;
    }

    public  void optionsForUser() {
        System.out.println("What are you going to do: R - register,V - view events, P - get tickets price, B - buy ticket ?");
        String thingToDo = scanner.nextLine();

        switch (thingToDo){
            case "R": register();
                break;
            case "V": viewEvents();
                break;
            case "P":{
                System.out.println("Price: "+getTicketsPrice());
                optionsForUser();
            }
            break;
            case "B": bookTicket();
            break;
        }
    }
    private void bookTicket() {
        Set<Ticket> tickets = new LinkedHashSet<>();
        try {
            System.out.println("Please enter your id (or -1), event id, event air date (in format 2007-12-03T10:15:30, total number of seats.");
            String user_id = scanner.nextLine();
            User user = null;
            if(!user_id.equals("-1")){
                user = userService.getById(Long.parseLong(user_id));
            }
            Event event = eventService.getById(Long.parseLong(scanner.nextLine()));
            LocalDateTime airDateTime = LocalDateTime.parse(scanner.nextLine());
            List<Long> seatsList = enterSeats(Integer.valueOf(scanner.nextLine())).stream().collect(Collectors.toList());
            for(int i = 0; i < seatsList.size(); i++){
                Ticket ticket = new Ticket();
                ticket.setSeat(seatsList.get(i));
                ticket.setEvent(event);
                ticket.setDateTime(airDateTime);
                if (ticketService.getAll().contains(ticket)){
                    System.err.println("Ticket " + ticket + "already booked");
                    break;
                }
                tickets.add(ticket);
            }

            if (tickets.size()>0){
                bookingService.bookTickets(tickets,user);
            }

        }catch (NumberFormatException | DateTimeParseException e){
            System.err.println(inputOfWrongData);
            optionsForUser();
        }catch (EmptyResultDataAccessException exception){
            System.err.println("NO user ore event under this id!");
            optionsForUser();
        }
        optionsForUser();
    }

    private double getTicketsPrice() {
        try {
            System.out.println("Please enter such parameters: id of event, air date (in format 2007-12-03T10:15:30), your id or -1, total number of seats. Each parameter must start from new line");
            String eventId = scanner.nextLine();
            String airDate = scanner.nextLine();
            String userId = scanner.nextLine();
            Set<Long> longSet = enterSeats(Integer.valueOf(scanner.nextLine()));
            Event event = eventService.getById(Long.valueOf(eventId));
            User user = null;
            if(!userId.equals("-1")){
                user = userService.getById(Long.valueOf(userId));
            }
            return bookingService.getTicketsPrice(event,LocalDateTime.parse(airDate), user, longSet );
        }catch (NumberFormatException | DateTimeParseException e){
            System.err.println(inputOfWrongData);
            optionsForUser();
        }
       return 0;
    }

    private Set<Long> enterSeats(Integer integer) {
        Set<Long> longSet = new LinkedHashSet<>();
        System.out.println("Please enter numbers of seats");
        try {
            for(int i = 0; i < integer; i++){
                longSet.add(Long.valueOf(scanner.nextLine()));
            }
        }catch (NumberFormatException | DateTimeParseException e){
            System.err.println(inputOfWrongData);
        }
        return longSet;
    }

    private void viewEvents() {
        System.out.println("Press: 1 - watch events to date; 2 - events in diapason; 3 - all events ");
        try {
            String thingToDo = scanner.nextLine();
            switch (thingToDo){
                case "1":{
                    System.out.println("events from now to(enter date in format 2007-12-03T10:15:30): ");
                    eventService.getNextEvents(LocalDateTime.parse(scanner.nextLine())).stream().forEach(System.out::println);;
                    optionsForUser();
                }
                break;
                case "2": {
                    System.out.println("enter two dates (from and to) in format 2007-12-03T10:15:30 each from new line: ");
                    eventService.getForDateRange(LocalDateTime.parse( scanner.nextLine()), LocalDateTime.parse(scanner.nextLine())).stream().forEach(System.out::println);
                    optionsForUser();
                }
                break;
                case "3":{
                    eventService.getAll().stream().forEach(System.out::println);
                    optionsForUser();
                }
                    break;
            }
        }catch (NumberFormatException | DateTimeParseException e){
            System.err.println(inputOfWrongData);
        }
    }

    public   void register() {
        System.out.println("Enter please your name, last name, email, birthday (in format 2007-12-03) each from NEW line : ");
        try {
            String name = scanner.nextLine();
            String lastName = scanner.nextLine();
            String email = scanner.nextLine();
            LocalDate birthday = LocalDate.parse(scanner.nextLine());
            User user = new User();
            user.setLastName(lastName);
            user.setFirstName(name);
            user.setEmail(email);
            user.setBirthday(birthday);
            userService.save(user);
        }catch (NumberFormatException | DateTimeParseException e){
            System.err.println(inputOfWrongData);
        }
        userService.getAll().stream().forEach(System.out::println);
        optionsForUser();
    }





}
