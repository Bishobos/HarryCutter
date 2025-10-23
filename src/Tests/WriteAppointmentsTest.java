package Tests;

import FileHandler.WriteAppointments;
import Main.AppointmentHandler;

public class WriteAppointmentsTest {
    public static void main(String[] args){
        AppointmentHandler handler = new AppointmentHandler("222025");
        WriteAppointments writer = new WriteAppointments(handler);
        writer.writer();
    }
}
