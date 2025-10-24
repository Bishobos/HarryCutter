package Main;

import FileHandler.*;
import InputValidater.ValidatePassword;
import InputValidater.ValidateTimestamp;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.Scanner;

public class Menu {
    private Scanner scanner;
    private AppointmentHandler appointmentHandler;
    private boolean running;

    //menu konstruktør starter scanner

    public Menu() {
        this.scanner = new Scanner(System.in);
//laver date input string
        LocalDate now = LocalDate.now();
        String dateInput = String.format("%02d%02d%d", now.getDayOfMonth(), now.getMonthValue(), now.getYear());

        this.appointmentHandler = new AppointmentHandler(dateInput);
        this.running = true;
    }

    //while loop that asks what function to use or ends loop and closes program
    public void mainLoop() {
        while (running) {
            displayMenu();
            int choice = getUserChoice();
            menuChoise(choice);
        }

        WriteAppointments writer = new WriteAppointments(appointmentHandler);
        writer.writer();

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
        System.out.println("6. vis Budget (kræver password)");
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
            return;
        }

        int day = validator.getDay();
        int month = validator.getMonth();
        int year = validator.getYear();
        int timestamp = validator.getTimestamp();

        System.out.print("Indtast kundens navn: ");
        String name = this.scanner.nextLine();

        if (name.trim().isEmpty()) {
            System.out.println("Navn må ikke være tomt. Booking afbrudt.");
            return;
        }

        try{
            appointmentHandler.makeTimeslot(day, month, year, timestamp, name, false);
            System.out.println("Booking oprettet for " + name);
        }catch(IllegalArgumentException e) {
            System.out.println("fejl: " + e.getMessage());
        }
    }


    //Delete a Main.TimeSlot without payment and closing it out
    //bruges til at annulere en eksisterende booking uden betaling
    public void cancelBooking() {
        System.out.println("\n Annuller booking.");

        displayBookings();

        ValidateTimestamp validator = new ValidateTimestamp();
        if(!validator.validateTimestamp()){
            System.out.println("ugyldig tid.");
            return;
        }

        int day = validator.getDay();
        int month = validator.getMonth();
        int year = validator.getYear();
        int timestamp = validator.getTimestamp();


        try{
            appointmentHandler.deleteTimeSlot(day, month, year, timestamp);
            System.out.println("Booking annuleret");
        } catch (IllegalArgumentException e) {
            System.out.println("Fejl: " + e.getMessage());
        }
    }

    /**
     * Lukker booking og spørger om der skal tilføjes ekstra produkter. Prisen registreres og gemmes til budgettet
    */

    public void closeBooking() {
        System.out.println("\n Afslut booking");
        //viser alle bookings
        displayBookings();

        System.out.print("Indtast dato og tid for booking der skal afsluttes (dd mm yyyy ttmm): ");
        ValidateTimestamp validator = new ValidateTimestamp();
        if (!validator.validateTimestamp()) {
            System.out.println("ugyldig tid.");
            return;

        }

        int day = validator.getDay();
        int month = validator.getMonth();
        int year = validator.getYear();
        int timestamp = validator.getTimestamp();

        /**
opretter variabel og søger gennem nuværende/næste måned og hvis den ikke finder den så søger den videre, og hvis der ikke er noget der matcher printer
         printer den 'ingen booking fundet'
        */


        try {
            TimeSlot booking = null;

            for(TimeSlot slot : appointmentHandler.getCurrentMonth()){
                if(slot.getDay() == day && slot.getMonth() == month && slot.getYear() == year && slot.getTimestamp() == timestamp){
                    booking = slot;
                    break;
                }
            }

            if (booking==null) {
                for (TimeSlot slot : appointmentHandler.getNextMonth()) {
                    if (slot.getDay() == day && slot.getMonth() == month &&
                            slot.getYear() == year && slot.getTimestamp() == timestamp) {
                        booking = slot;
                        break;
                    }
                }
            }

            if (booking == null){
                System.out.println("ingen booking fundet");
                return;
            }

            /**
             * Denne sprørg om der er tilkøbt ekstra produkter så prisen kan tilføjes. sætter startværdi til 0.0
             *
             */

            System.out.println("Ekstra produkter:");
            displayProducts();

            System.out.print("Er der købt ekstra produkter? (ja/nej): ");
            String hasProducts = this.scanner.nextLine().toLowerCase();

            double totalAmount = 0.0;

            if (hasProducts.equals("ja")) {
                totalAmount = handleProductSales();
            }

            WriteBudget budgetWriter = new WriteBudget(booking, totalAmount);
            budgetWriter.writer();

            System.out.println("booking afsluttet og betaling registreret");

        } catch (IllegalStateException e) {
            System.out.println("fejl: " + e.getMessage());
        }
    }

    private double handleProductSales() {
        boolean addingProducts = true;
        double total = 0.0;
        WriteProductSales salesWriter = new WriteProductSales();

        while(addingProducts){
            System.out.println("Indtast produkt navn (eller 'færdig' for at stoppe): )");
            String productName = this.scanner.nextLine();

            if(productName.equalsIgnoreCase("færdig")) {
                addingProducts = false;
            }else{

                Products foundProduct = null;
                for (Products p : Products.values()) {
                    if (p.getLabel().equalsIgnoreCase(productName)){
                        foundProduct = p;
                        break;
                    }
                }
                if (foundProduct != null){
                    salesWriter.sale(foundProduct.getLabel());
                    total += foundProduct.getPrice();
                    System.out.println("Produkt tilføjet: " + productName + " (" + foundProduct.getPrice() + " kr)");
                }else{
                    System.out.println("ukendt produkt");
                }
            }
        }

        salesWriter.writer();

        return total;
    }



    //printer liste med alle produkter og pris
    private void displayProducts() {
        System.out.println("Tilgængelige produkter:");
        for (Products product : Products.values()) {
            System.out.println("- " + product.getLabel() + ": " + product.getPrice() + " kr");
            //should ask for what products extra products have been bought, then register the payment in budget, set the "paid" boolean to true, and increment productSales
        }
    }

    private double registerProductSales() {
        boolean addingProducts = true;
        double total = 0.0;
        WriteProductSales salesWriter = new WriteProductSales();

        while (addingProducts) {
            System.out.print("Indtast produkt navn (eller 'færdig' for at stoppe): ");
            String productName = this.scanner.nextLine();

            if (productName.equalsIgnoreCase("færdig")) {
                addingProducts = false;
            } else {

                Products foundProduct = null;
                for(Products p : Products.values()){
                    if (p.getLabel().equalsIgnoreCase(productName)){
                        foundProduct = p;
                        break;
                    }
                }

                if (foundProduct != null) {
                    salesWriter.sale(foundProduct.getLabel());
                    total += foundProduct.getPrice();
                    System.out.println("Produkt tilføjet: " + productName + " (" + foundProduct.getPrice() + " kr)");
                }else{
                    System.out.println("kunne ikke finde produktet");
                }
            }
        }
        //gemmer salget
            salesWriter.writer();

        return total;
    }

    //needs password validation, then to use ReadBudget to display the budget
    //password validation call is currently in ReadBudget class for added obfuscation
    //tjekker om koden er tastet korrekt ved at kalde ValidatePassword klassen.
    public void viewBudget() {
        System.out.println("Vis budget");

        ValidatePassword validator = new ValidatePassword();
        if (!validator.validatePassword()) {
            System.out.println("\n Forkert kode, ingen adgang!");
            return;
        }

        try {
            ReadBudget budgetReader = new ReadBudget(true);
            ArrayList<String[]> budgetData = budgetReader.reader();

            if (budgetData.isEmpty()) {
                System.out.println("Ingen Data.");
                return;
            }
        } catch (IllegalAccessException e) {
            throw new RuntimeException(e);
        }
    }


            //needs to read the ProductSales file, and display the read data
            //udskriver listen af produkter og hvor mange der er solgt og vender retur til menu

    public void viewProductSales () {
        System.out.println("\n Statistik for produktsalg");

        ReadProductSales salesReader = new ReadProductSales();
        ArrayList<String[]> salesData = salesReader.reader();

        if (salesData.isEmpty()) {
            System.out.println("ingen data fundet.");
            return;

        }
        for (String[] product : salesData) {
            System.out.println(product[0] + ": " + product[1]);
        }
    }
    //Displays all bookings for a given month and the following month
    //viser bookings for buværende og næste måned ved at kalde det fra appointmenthandler og ellers printe ingen booking hvis  bookingerne er tomme.
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
