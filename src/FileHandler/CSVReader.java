package FileHandler;


import java.util.ArrayList;

public interface CSVReader {
    public ArrayList<String[]> reader();
    public String[] format(String stringToFormat);


}
