package Tests;

import Main.AppointmentHandler;


public class AppointmentHandlerTest {
    public static void main(String[] args) {
        AppointmentHandler ah = new AppointmentHandler();
        System.out.println(ah.getNextMonth("102025")); // 112025
        System.out.println(ah.getNextMonth("122025")); // 012026

        //filnavne
        System.out.println("Current month: " + ah.getCurrentMonthFilename());
        System.out.println("Next month: " + ah.getNextMonthFilename());

        //Arraylist
        System.out.println("CurrentMonth virker: " + (ah.currentMonth != null));
        System.out.println("NextMonth virker: " + (ah.nextMonth != null));

        //Tom arraylist?
        assert ah.currentMonth != null;
        System.out.println("CurrentMonth størrelse: " + ah.currentMonth.size());
        assert ah.nextMonth != null;
        System.out.println("NextMonth størrelse: " + ah.nextMonth.size());
        System.out.println(ah.currentMonth.getFirst());

    }
}
