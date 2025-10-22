package FileHandler;

import Main.TimeSlot;

public class WriteBudget implements CSVWriter {

    @Override
    public void writer(){

    }

    @Override
    public String format(TimeSlot toFormat){
        return "formatted export String";
    }
    //may need parameters both here and in interface, maybe of type schedule
}
