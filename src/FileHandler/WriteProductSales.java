package FileHandler;

import Main.TimeSlot;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

/**
 * WriteProductSales bruges til at intrecementere mængde af solgte producter i productsales.csv
 * Has Methods: <br>
 * {@link #WriteProductSales}
 * {@link #sale(String)}
 * {@link #writer()}
 * {@link #format(String[])}
 */
public class WriteProductSales implements CSVWriter {
    String filename = "src/FileHandler/Files/Appointments/productsales.csv";
    ArrayList<String[]> productSales;
    ReadProductSales reader = new ReadProductSales();

    /**
     * constructor
     */
    public WriteProductSales() {
        this.productSales = reader.reader();
    }

    /**
     * registerer et salg af 'product'
     * @param product det product der sælges
     */
    public void sale(String product) {
        for (String[] productSale : productSales) {
            if (productSale[0].equalsIgnoreCase(product)) {
                int newSalesCount = Integer.parseInt(productSale[1]);
                newSalesCount++;
                productSale[1] = Integer.toString(newSalesCount);
            }
        }
    }

    /**
     * writer er hovedfunktionen der skriver til productsales.csv
     */
    @Override
    public void writer() {
        try (BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(this.filename))) {
            for (String[] line : this.productSales) {
                bufferedWriter.write(format(line));
                bufferedWriter.newLine();
            }
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }


    /**
     * format bruges til at formaterer den data der skal skrives
     * @param toFormat String[] af data der skal formatters
     * @return String af formatteret data
     */
    public String format(String[] toFormat) {
        return toFormat[0] + ";" + toFormat[1];
    }

    @Override
    public String format(TimeSlot toFormat) {
        return "formatted export String";
    }

    //may need parameters both here and in interface, maybe of type schedule
}

