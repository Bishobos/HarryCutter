package InputValidater;

import java.util.Scanner;

public class ValidateTimestamp {

    public boolean validateTimestamp() {
        String input = "";
        Scanner sc = new Scanner(System.in);
        while (true) {
            System.out.println("Indtast en gyldig dato og klokkeslæt. (dd mm yyyy ttmm)");
            String timestampInput = sc.nextLine();
            String[] parts = timestampInput.split(" ");

            if (parts.length != 4) {
                System.out.println("Ugyldigt format! Prøv igen.");
                continue;
            }

            String hourString = parts[3].substring(0, 2);
            String minuteString = parts[3].substring(2, 4);

            int day, month, year, hour, minute;
            try {
                day = Integer.parseInt(parts[0]);
                month = Integer.parseInt(parts[1]);
                year = Integer.parseInt(parts[2]);
                hour = Integer.parseInt(hourString);
                minute = Integer.parseInt(minuteString);
            } catch (NumberFormatException e) {
                System.out.println("Input skal være tal i formatet: dd mm yyyy ttmm");
                continue;
            }

            if (year < 2020 || year > 2050) {
                System.out.println("Årstal mellem 2020-2050.");
                return false;
            }
            if (month < 1 || month > 12) {
                System.out.println("Måned mellem 1-12");
                return false;
            }

            int maxDays = switch (month) {
                case 1, 3, 5, 7, 8, 10, 12 -> 31;
                case 4, 6, 9, 11 -> 30;
                case 2 -> isLeapYear(year) ? 29 : 28;
                default -> 0;
            };

            if (day < 1 || day > maxDays) {
                System.out.println("Ugyldig dag for denne måned.");
                return false;
            }


            if (hour < 0 || hour > 23 || minute < 0 || minute > 59) {
                System.out.println("Ugyldigt klokkeslæt.");
                return false;
            }

            return true;
        }


    }
    private boolean isLeapYear(int year) {
        return (year % 4 == 0 && year % 100 != 0) || (year % 400 == 0);
    }
}


