package FileHandler;

import Main.TimeSlot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

public class WriteProductSales implements CSVWriter{
    String filename = "src/FileHandler/Files/Appointments/productsales.csv";
    ArrayList<String[]> productSales = new ArrayList<>();
    ReadProductSales reader = new ReadProductSales();

    public WriteProductSales(){this.productSales = reader.reader();}

    public void sale(String product){
        for (String[] productSale : productSales) {
            if (productSale[0].equalsIgnoreCase(product)) {
                int newSalesCount = Integer.parseInt(productSale[1]);
                newSalesCount++;
                productSale[1] = Integer.toString(newSalesCount);
            }
        }
    }

    @Override
    public void writer(){
        try(BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.filename))){
            for (String[] line : this.productSales){
                bufferedWriter.write(format(line));
                bufferedWriter.newLine();
            }
        }catch (IOException e) {
                throw new RuntimeException(e);
        }
    }


    public String format(String[] toFormat){
        return toFormat[0] + ";" + toFormat[1];
    }
    @Override
    public String format(TimeSlot toFormat){
        return "formatted export String";
    }

    //may need parameters both here and in interface, maybe of type schedule
}

