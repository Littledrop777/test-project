package by.academy.it.app;

import by.academy.it.dao.PersonDao;
import by.academy.it.pojo.Person;

import java.util.Scanner;

public class ApplicationMenu {

    private static ApplicationMenu instance;
    private final PersonDao dao;
    private final Scanner scanner;

    private ApplicationMenu() {
        this.dao = new PersonDao();
        this.scanner = new Scanner(System.in);
    }

    public static ApplicationMenu getInstance() {
        if (instance == null) {
            instance = new ApplicationMenu();
        }
        return instance;
    }

    public void printAvailableOptions() {
        System.out.println("Menu:");
        System.out.println("1.Save person\n" +
                "2.Get person by id\n" +
                "3.Get all people\n" +
                "4.Delete person by id\n" +
                "0.Exit");

        int input = handleUserInput(0, 4);
        switch (input) {
            case 1:
                savePerson();
                printAvailableOptions();
                break;
            case 2:
                takePerson();
                printAvailableOptions();
                break;
            case 3:
                takePeople();
                printAvailableOptions();
                break;
            case 4:
                deletePerson();
                printAvailableOptions();
                break;
            case 0:
                break;
        }
    }

    private void deletePerson() {
        System.out.println("Enter person id for delete");
        String id = scanner.next();
        dao.deletePerson(id);
    }

    private void takePeople() {
        System.out.println("List of people");
        System.out.println("--------------------------");

        dao.readAllPeople().forEach(person -> System.out.printf(
                "%10s %10s %3d\n",
                person.getName(), person.getSurname(), person.getAge()));
        System.out.println();
    }

    private void takePerson() {
        System.out.println("Enter person id");
        String id = scanner.next();
        Person person = dao.readPerson(id);
        if (person == null) {
            System.out.println("Person does not exist");
        } else {
            System.out.printf("name: %s\nsurname: %s\nage: %d\n\n",
                    person.getName(), person.getSurname(), person.getAge());
        }
    }

    private void savePerson() {
        System.out.println("Enter name");
        String name = scanner.next();
        System.out.println("Enter surname");
        String surname = scanner.next();
        System.out.println("Enter age");
        int age = handleUserInput(0, 120);
        Person person = new Person();
        person.setName(name);
        person.setSurname(surname);
        person.setAge(age);
        String id = dao.savePerson(person);
        System.out.printf("Person was saved with id = %s\n\n", id);
    }

    public int handleUserInput(int minValue, int maxValue) {
        int input;
        while (true) {
            while (!scanner.hasNextInt()) {
                scanner.next();
                System.out.println("Incorrect input. Please, enter a number of option");
            }
            input = scanner.nextInt();
            if (input >= minValue && input <= maxValue) {
                break;
            }
            System.out.println("Incorrect input. Please, try again.");
        }
        return input;
    }
}
