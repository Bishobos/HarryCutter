package Main;

import FileHandler.ReadAppointments;
import FileHandler.ReadBudget;
import FileHandler.ReadProductSales;
import InputValidater.ValidatePassword;
import InputValidater.ValidateTimestamp;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private AppointmentHandler appointmentHandler;
    private boolean running;

    //menu konstruktør starter scanner
    public Menu() {
        this.scanner = new Scanner(System.in);
        this.appointmentHandler = new AppointmentHandler();
        this.running = true;
    }

    //while loop that asks what function to use or ends loop and closes program
    public void mainLoop() {
        while (running) {
            displayMenu();
            int choice = getUserChoice();
            menuChoise(choice);
        }
        scanner.close();
        System.out.println("Afslutter programmet.");
    }

    private void displayMenu() {
        System.out.println("\n Harry Cotters Salon");
        System.out.println("1. Book tid");
        System.out.println("2. Annuler booking");
        System.out.println("3. Afslut booking(registrer betaling)");
        System.out.println("4. Vis Bookinger.");
        System.out.println("5. Vis Produkter ");
        System.out.println("6. vis Budget (kræver pass");
        System.out.println("0. Afslut program");
        System.out.println("Vælg funktion");
    }

    //Læser brugerinput og validere om det er et tal der er tastet ind
    private int getUserChoice() {
        try {
            int choice = Integer.parseInt(scanner.nextLine());
            return choice;
        } catch (NumberFormatException e) {
            System.out.println("indtast venligst et tal.");
            return -1;
        }

    }

    //kalder den relevante metode ud fra brugerinput
    private void menuChoise(int choise) {
        switch (choise) {
            case 1 -> bookTimeslot();
            case 2 -> cancelBooking();
            case 3 -> closeBooking();
            case 4 -> displayBookings();
            case 5 -> viewProductSales();
            case 6 -> viewBudget();
            case 0 -> {
                running = false;
            }
            default -> System.out.println("Vælg mellem 0-6");

        }
    }
//this should be several different functions for bookings of different lengths
    //gets user inputs, validates them, and uses Main.Schedule.makeTimeSlot with those inputs
//bruges til at booke nye tider og validere tidspunkt med ValidateTimestamp

    public void bookTimeslot() {

        System.out.println("\n Book ny tid");

        ValidateTimestamp validator = new ValidateTimestamp();
        if (!validator.validateTimestamp()) {
            System.out.println("ugyldig tid. Booking afbrudt");
        }
        System.out.print("Indtast kundens navn: ");
        String name = this.scanner.nextLine();

        if (name.trim().isEmpty()) {
            System.out.println("Navn må ikke være tomt. Booking afbrudt.");
            return;
        }
        System.out.println("Booking oprettet for " + name);
    }


    //Delete a Main.TimeSlot without payment and closing it out
    //bruges til at annulere en eksisterende booking uden betaling
    public void cancelBooking() {
        System.out.println("\n Annuller booking.");

        displayBookings();

        System.out.print("Indtast dato og tid for booking der skal annulleres (dd mm yyyy ttmm): ");
        String input = this.scanner.nextLine();

        System.out.println("Booking annuleret");

    }

    public void closeBooking() {
        System.out.println("\n Afslut booking");

        displayBookings();

        System.out.print("Indtast dato og tid for booking der skal afsluttes (dd mm yyyy ttmm): ");
        String input = this.scanner.nextLine();

        System.out.println("Ekstra produkter:");
        displayProducts();

        System.out.print("Er der købt ekstra produkter? (ja/nej): ");
        String hasProducts = this.scanner.nextLine().toLowerCase();

        if (hasProducts.equals("ja")) {
            handleProductSales();
        }
        //  booking bliver betalt og data gemt
        System.out.println("Booking afsluttet og betaling registreret.");

    }

    //printer liste med alle produkter og pris
    private void displayProducts() {
        System.out.println("Tilgængelige produkter:");
        for (Products product : Products.values()) {
            System.out.println("- " + product.getLabel() + ": " + product.getPrice() + " kr");
            //should ask for what products extra products have been bought, then register the payment in budget, set the "paid" boolean to true, and increment productSales
        }
    }

    private void handleProductSales() {
        boolean addingProducts = true;

        while (addingProducts) {
            System.out.print("Indtast produkt navn (eller 'færdig' for at stoppe): ");
            String productName = this.scanner.nextLine();

            if (productName.equalsIgnoreCase("færdig")) {
                addingProducts = false;
            } else {
                System.out.println("Produkt tilføjet: " + productName);
            }
        }
    }

    //needs password validation, then to use ReadBudget to display the budget
    //password validation call is currently in ReadBudget class for added obfuscation
    public void viewBudget() {
        System.out.println("Vis budget");

        ValidatePassword validator = new ValidatePassword();
        if (!validator.validatePassword()) {
            System.out.println("\n Forkert kode, ingen adgang!");
            return;
        }

        try {
            ReadBudget budgetReader = new ReadBudget(true);
            ArrayList<String[]> budgetData = new budgetReader.reader();

            if (budgetData.isEmpty()) {
                System.out.println("Ingen Data.");
                return;
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


            //needs to read the ProductSales file, and display the read data

    public void viewProductSales () {
        System.out.println("\n Statistik for produktsalg");

        ReadProductSales salesReader = new ReadProductSales();
        ArrayList<String[]> salesData = salesReader.reader();

        if (salesData.isEmpty()) {
            System.out.println("ingen data fundet.");
            return;

        }
    }
    //Displays all bookings for a given month and the following month

    public void displayBookings () {
        System.out.println("\n Denne måneds bookings");
        if (appointmentHandler.getCurrentMonth().isEmpty()) {
            System.out.println("Ingen bookinger");
        } else {
            for (TimeSlot booking : appointmentHandler.getCurrentMonth()) {
                System.out.println(booking);
            }
        }

        System.out.println("\n Næste måneds bookings");
        if (appointmentHandler.getNextMonth().isEmpty()) {
            System.out.println("Ingen bookinger");
        } else {
            for (TimeSlot booking : appointmentHandler.getNextMonth()) {
                System.out.println(booking);
            }
        }
    }
}
