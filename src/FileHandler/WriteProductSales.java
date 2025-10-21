package FileHandler;

public class WriteProductSales implements CSVWriter{

    @Override
    public void writer(){

    }

    @Override
    public String format(){
        return "formatted export String";
    }

    public void makeFile(){
        //check if file with specified timestamp exists, if not make it
    }

    //may need parameters both here and in interface, maybe of type schedule
}

