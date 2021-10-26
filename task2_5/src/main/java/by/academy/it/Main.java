package by.academy.it;

import by.academy.it.context.Application;

import java.text.ParseException;

public class Main {
    public static void main(String[] args) {
        try {
            Application.start(args);
        } catch (ParseException e) {
            e.printStackTrace();
        }
    }
}
