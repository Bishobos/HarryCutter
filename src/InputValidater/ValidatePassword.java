package InputValidater;

import java.util.Scanner;

public class ValidatePassword {
    private final String password = "admin123";

    /**
     * Vi ved at password er admin123 ifølge vores klient. /JS
     * I en videreudvikling / rigtigt scenarie vil vi gerne kunne ændre det. /JS
     * For dette programs formål, er det altid admin123 og kan ikke ændres. /JS
     */

    public boolean validatePassword(){

        String input = "";
        Scanner sc = new Scanner(System.in);
        /**
         * Vi åbner scanner før vores while loop. Så behøver vi ikke åbne den igen senere. /JS
         */

        while(true){

            System.out.println("Indtast dit password: ");
            input = sc.nextLine();

            if(input.equals(password)){
                sc.close();
                System.out.println("Password godkendt!");
                return true;
                /**
                 * Hvis input = password, lukker scanneren sammen med while loopet. /JS
                 * Vi lukker scanneren for ordens skyld. /JS
                 */
            }else{
                System.out.println("Ugyldigt password! Prøv igen.");
                /**
                 * Vi starter forfra i vores loop hvis password er forkert. /JS
                 */
        }}

    }
}
