package FileHandler;

import Main.AppointmentHandler;
import Main.TimeSlot;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;

public class WriteAppointments implements CSVWriter{
    String filename;
    AppointmentHandler appointments;

    public WriteAppointments(AppointmentHandler appointments){
        this.appointments = appointments;
        this.filename = "src/FileHandler/Files/Appointments/" + appointments.getCurrentMonthFilename() + ".csv";}

    @Override
    public void writer(){
        makeFile();
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.filename))){
            for (TimeSlot current: appointments.getCurrentMonth()){
                bufferedWriter.write(format(current));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
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

    private void makeFile(){
        try{
            File file = new File(this.filename);
            if (file.createNewFile()){
                System.out.println("New appointments file created");
            }
        }catch (IOException e){
            System.out.println("IOException");
        //check if file with specified timestamp exists, if not make it
    }}

    //may need parameters both here and in interface, maybe of type schedule
}
