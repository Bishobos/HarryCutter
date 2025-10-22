package Tests;

import FileHandler.ReadProductSales;

import java.util.Arrays;

public class ReadProductSalesTest {
    public static void main(String[] args){
        ReadProductSales sales = new ReadProductSales();
        for (String[] strings : sales.reader()) {
            System.out.println(Arrays.toString(strings));
        }
    }
}
