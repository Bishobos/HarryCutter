package Tests;

import FileHandler.WriteProductSales;

public class WriteProductSalesTest {
    public static void main(String[] args){
        WriteProductSales sales = new WriteProductSales();
        sales.sale("test");
        sales.sale("test");
        sales.writer();
    }
}
