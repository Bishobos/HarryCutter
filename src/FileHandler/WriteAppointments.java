package FileHandler;

import Main.AppointmentHandler;
import Main.TimeSlot;

public class WriteAppointments implements CSVWriter{
    String filename;
    AppointmentHandler appointments;

    public WriteAppointments(AppointmentHandler appointments){this.appointments = appointments; this.filename = appointments.getCurrentMonthFilename();}

    @Override
    public void writer(){
        System.out.println(format(appointments.currentMonth.getFirst()));
    }

    @Override
    public String format(TimeSlot toFormat){
        String day = Integer.toString(toFormat.getDay());
        String month = Integer.toString(toFormat.getMonth());
        String year = Integer.toString(toFormat.getYear());
        String timestamp = Integer.toString(toFormat.getTimestamp());
        String name = toFormat.getName();
        String paid = Boolean.toString(toFormat.getIfPaid());

        return day + ";" + month + ";" + year + ";" + timestamp + ";" + name + ";" + paid;
    }

    public void makeFile(){
        //check if file with specified timestamp exists, if not make it
    }

    //may need parameters both here and in interface, maybe of type schedule
}
