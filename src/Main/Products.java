package Main;

/**
 * Enums med labels til navn der er mere læseligt, samt pris på produkterne i 'doubles' så der tages højde for kommatal osv
 */

public enum Products {
    SHAMPOO("Shampoo", 49.99),
    CONDITIONER("Conditioner", 60),
    WAX("Wax", 30),
    HAIR_SPRAY("Hair Spray", 50),
    MENS_HAIRCUT("Mens Haircut", 150),
    WOMAN_HAIRCUT("Womans Haircut", 300),
    CURLS("Curls", 200);


    public final String label;
    public final double price;

     Products(String label, double price) {
        this.label = label;
        this.price = price;
    }
    public String getLabel(){
         return label;
    }
    public double getPrice(){
         return price;
    }
}
