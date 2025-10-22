package FileHandler;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;

public class ReadProductSales implements CSVReader{

    ArrayList<String[]> formatedData = new ArrayList<>();
    String filename = "src/FileHandler/Files/Appointments/productsales.csv";

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
    @Override
    public String[] format(String stringToFormat){
        return stringToFormat.split(";");
    }

    //may need parameters both here and in interface

}
