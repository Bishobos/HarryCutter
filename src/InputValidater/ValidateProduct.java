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
            /**
             * Vi tjekker alle vores enums i listen (tjek længere nede).
             * Hvis produktet ikke er null - så er den godkendt.
             */
            if (product != null) {
                System.out.println("Produkt er godkendt:  " + product.getLabel());
                return product;
            } else {
                System.out.println("Ugyldigt produktnavn. Prøv igen");
            }

        }
    }
    /**
     * Her tjekker vi faktisk om det findes i vores enums.
     * Java gennemgår alle produkter i Products og kalder dem p.
     * Derefter tjekker vi om inputtet svarer til et produkt i vores liste - stor/små bogstaver ignoreres.
     * Hvis det gør, afslutter den metoden og returnerer p.
     */
    private Products validateToEnum(String input) {
        for (Products p : Products.values()) {
            if (p.getLabel().equalsIgnoreCase(input)) {
                return p;
            }
        }
        return null; // not found

    }
}