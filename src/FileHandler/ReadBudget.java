package FileHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

/**
 * ReadBudget er en funktion der læser budgetet fra budget.csv
 * Has Methods: <br>
 * {@link #ReadBudget(boolean)}
 * {@link #reader()}
 * {@link #format(String)}
 */
public class ReadBudget implements CSVReader{

    ArrayList<String[]> formatedData = new ArrayList<>();
    String filename = "src/FileHandler/Files/Appointments/budget.csv";

    /**
     * ReadBudget er en constructor
     * @param isPasswordValidated hvor vidt passwordet er valideret
     * @throws IllegalAccessException Thrower en error hvis passwordet ikke er
     */
    public ReadBudget(boolean isPasswordValidated) throws IllegalAccessException {
        if (!isPasswordValidated) {
            throw new IllegalAccessException("Password required!");
            }
    }

    /**
     * reader er hovedfunktionen der der læser budgetet
     * @return ArrayList&ltString[]&gt indeholdene alt data i budgetet
     */
    @Override
    public ArrayList<String[]> reader(){
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(this.filename))){
            String line;
            while((line = bufferedReader.readLine()) != null){
                formatedData.add(format(line));
            }
        }catch ( FileNotFoundException e){
            System.out.println("Budget does not exist.");
        }catch ( IOException e){
            System.out.println("ERROR: IOException");
        }

        return formatedData;
    }

    /**
     * format bruges til at splitte læste linjer med ; som delimiter
     * @param stringToFormat er den String der skal splittes
     * @return String[] af splittet input
     */

    @Override
    public String[] format(String stringToFormat){
        return stringToFormat.split(";");
    }

    //may need parameters both here and in interface

}
