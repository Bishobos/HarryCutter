package FileHandler;

import java.util.ArrayList;

public class ReadAppointments implements CSVReader{

    @Override
    public ArrayList<String[]> reader(String filename){
        ArrayList<String[]> result = new ArrayList<String[]>();
        return result;
    } //if file does not exist return empty ArrayList

    @Override
    public String[] format(String test){
        String[] result = {};
        return result;
    }

    //may need parameters both here and in interface
}
