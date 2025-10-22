package FileHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ReadAppointments er en klasse der bruges til at læse Appointments filer. Den implementerer {@link CSVReader} interfacet
 * Has Methods:
 * {@link #reader(String)}
 * {@link #format(String)}
 * Has Attributes:
 * {@link #formatedData}
 */
public class ReadAppointments implements CSVReader{
    /**
     * formatedData er en ArrayList&ltString[]&gt af data læst fra filen
     */
    ArrayList<String[]> formatedData = new ArrayList<>();

    /**
     * reader er hovedmetoden der bruges til at læse data fra filer
     * @param filename navnet på filen uden path eller extention {@link #setFilename(String)} tilføjer det
     * @return {@link #formatedData} nu fyldt med læst og formatered data
     */
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

    /**
     * format bruges til at splitte læste linjer med ; som delimiter
     * @param input er den String der skal splittes
     * @return String[] af splittet input
     */
    @Override
    public String[] format(String input){
        String[] result = input.split(";");
        return result;
    }

    /**
     * setFilename formaterer filename med path og extention
     * @param filename uformateret fil navn
     * @return String fil navn med path og exctention
     */
    private String setFilename(String filename){return "src/FileHandler/Files/Appointments/" + filename + ".csv";}
}
