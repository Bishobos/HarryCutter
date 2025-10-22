package FileHandler;

import Main.TimeSlot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class WriteBudget implements CSVWriter {
    double amountPaid;
    String filename = "src/FileHandler/Files/Appointments/budget.csv";
    TimeSlot toWrite;

    public WriteBudget(TimeSlot toWrite, double amountPaid){
        if(toWrite.getIfPaid()) {
            throw new IllegalStateException("Booking already paid.");
        }
        this.toWrite = toWrite;
        this.amountPaid = amountPaid;
    }

    @Override
    public void writer(){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.filename, true))){
        bufferedWriter.write(format(this.toWrite));
        bufferedWriter.newLine();
        }catch (IOException e) {
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
        String paid = Double.toString(this.amountPaid);

        return day + ";" + month + ";" + year + ";" + timestamp + ";" + name + ";" + paid;

    }
    //may need parameters both here and in interface, maybe of type schedule
}
