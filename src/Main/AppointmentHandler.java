package Main;

import java.util.ArrayList;

public class AppointmentHandler {
    ArrayList<TimeSlot> currentMonth;
    ArrayList<TimeSlot> nextMonth;
    private String currentMonthFilename;
    private String nextMonthFilename;

    public AppointmentHandler(){
        //use ReadAppointments to fill currentMonth and nextMonth
        //case where no file at that date exists should be handled, no timeslots to be added to array may cause errors initially
    }

    public String getCurrentMonthFilename() {
        return currentMonthFilename;
    }

    public String getNextMonthFilename() {
        return nextMonthFilename;
    }

    public String getNextMonth(String currentMonth){
        String input = currentMonth;
        String monthValue = input.substring(2, 4);
        String yearValue = input.substring(4, 8);

        int monthInt = Integer.parseInt(monthValue);
        int yearInt = Integer.parseInt(yearValue);

        int nextMonth;
        int nextYear;

        if (monthInt < 12) {
            nextMonth = (monthInt + 1);
            nextYear = yearInt;
        }
        else if (monthInt==12) {
            nextMonth = 1;
            nextYear = (yearInt + 1);
        }
        else {
            System.out.println("Error: 404");
            nextMonth = 1;
            nextYear = 1900;
        }

        if (nextMonth < 10) {
            nextMonth = Integer.parseInt("0" + nextMonth);
        } else {
            System.out.println("Error: 404");
        }
        return String.valueOf(nextMonth + nextYear);

    }

    public void makeTimeslot(){


    }

    public void deleteTimeSlot(){

    }

    private void sortByTime(){

    }
}
