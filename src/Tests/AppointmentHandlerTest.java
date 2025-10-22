package Tests;

import Main.AppointmentHandler;

public class AppointmentHandlerTest {
    public static void main(String[] args) {
        AppointmentHandler ah = new AppointmentHandler();

        System.out.println(ah.getNextMonth("102025")); // 112025
        System.out.println(ah.getNextMonth("122025")); // 012026
    }
}
