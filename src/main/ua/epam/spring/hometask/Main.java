package ua.epam.spring.hometask;

import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import ua.epam.spring.hometask.commands.AdminCommands;
import ua.epam.spring.hometask.commands.UserCommands;

import java.util.Scanner;

public class Main {
    public static Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {

      /*  ApplicationContext applicationContext = new ClassPathXmlApplicationContext("app.xml");*/
        ApplicationContext applicationContext = new AnnotationConfigApplicationContext("ua.epam.spring.hometask.config");
        UserCommands userCommands = (UserCommands) applicationContext.getBean("userCommands");
        AdminCommands adminCommands = (AdminCommands) applicationContext.getBean("adminCommands");

        System.out.println("Are you admin or user? A/U");
        String enter = scanner.nextLine();

        switch (enter){
            case "A": adminCommands.optionsFroAdmin();
                break;
            case "U": userCommands.optionsForUser();
        }

    }

}
