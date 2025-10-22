package FileHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadAppointments implements CSVReader{

    ArrayList<String[]> formatedData = new ArrayList<>();

    @Override
    public ArrayList<String[]> reader(String filename){
        String formatedFilename = setFilename(filename);
        System.out.println(formatedFilename);
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(formatedFilename))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                formatedData.add(format(line));
            }
        }catch ( FileNotFoundException e){
            System.out.println("No appointments at: " + filename);
        }catch ( IOException e){
            System.out.println("ERROR: IOException");
        }
        return formatedData;
    }

    @Override
    public String[] format(String test){
        String[] result = test.split(";");
        return result;
    }

    private String setFilename(String filename){return "src/FileHandler/Files/Appointments/" + filename + ".csv";}
}
