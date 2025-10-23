package InputValidater;

import Main.Products;

import java.util.Scanner;

public class ValidateProduct {

    public Products productValidated() {
        String input = "";

        Scanner productScanner = new Scanner(System.in);
        while (true) {
            System.out.println("Indtast produkt navn (Shampoo, Conditioner, Wax, Men's haircut, Women's haircut, curls):");
            String productInput = productScanner.nextLine().trim(); // vi trimmer for at tage højde for trykfejl

            Products product = validateToEnum(productInput);
            if (product == null) {
                System.out.println("Produkt er godkendt:  " + product.getLabel());
                return product;
            } else {
                System.out.println("Ugyldigt produktnavn. Prøv igen");
            }

        }
    }

    private Products validateToEnum(String input) {
        for (Products p : Products.values()) {
            if (p.getLabel().equalsIgnoreCase(input)) {
                return p;
            }
        }
        return null; // not found
    }
}