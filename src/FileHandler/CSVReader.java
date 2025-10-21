package FileHandler;


import java.util.ArrayList;

public interface CSVReader {
    public ArrayList<String[]> reader(String filename);
    public String[] format(String stringToFormat);


}
