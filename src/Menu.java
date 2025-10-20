public class Menu {

    public void mainLoop(){
        //while loop that asks what function to use or ends loop and closes program
    }

    public void bookTimeslot(){
        //this should be several different functions for bookings of different lengths
        //gets user inputs, validates them, and uses Schedule.makeTimeSlot with those inputs
    }

    public void cancelBooking(){
        //Delete a TimeSlot without payment and closing it out
    }

    public void closeBooking(){
        //should ask for what products extra products have been bought, then register the payment in budget, set the "paid" boolean to true, and increment productSales
    }

    public void viewBudget(){
        //needs password validation, then to use ReadBudget to display the budget
        //password validation call is currently in ReadBudget class for added obfuscation
    }

    public void viewProductSales(){
        //needs to read the ProductSales file, and display the read data
    }
    public void displayBookings(){
        //Displays all bookings for a given month and the following month
    }
}
