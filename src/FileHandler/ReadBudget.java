package FileHandler;

import java.util.ArrayList;

public class ReadBudget implements CSVReader{

    @Override
    public ArrayList<String[]> reader(String filename){
        ArrayList<String[]> result = new ArrayList<String[]>();
        return result;
    }

    @Override
    public String[] format(String stringToFormat){
        String[] result = {};
        return result;
    }

    //may need parameters both here and in interface

    public boolean inputPassword(){
        return false;
    }
}
