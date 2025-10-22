package Tests;

import Main.AppointmentHandler;

public class AppointmentHandlerTest {
    public static void main(String[] args) {
        AppointmentHandler ah = new AppointmentHandler();

        System.out.println(ah.getNextMonth("22102025")); // 102025
        System.out.println(ah.getNextMonth("22122025")); // 012026
    }
}
