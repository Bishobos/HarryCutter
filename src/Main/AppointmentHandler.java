package Main;

import java.util.ArrayList;
import java.time.LocalDate;
import FileHandler.ReadAppointments;

public class AppointmentHandler {
    public ArrayList<TimeSlot> currentMonth;
    public ArrayList<TimeSlot> nextMonth;
    private String currentMonthFilename;
    private String nextMonthFilename;

    public AppointmentHandler(){
        //use ReadAppointments to fill currentMonth and nextMonth
        //case where no file at that date exists should be handled, no timeslots to be added to array may cause errors initially
        //Henter nuværende dato for måned og år
        LocalDate now = LocalDate.now();
        int month = now.getMonthValue();
        int year = now.getYear();

        //Sætter filnavn for værende måned//år
        this.currentMonthFilename = String.format("%02d%d", month, year);
        this.nextMonthFilename = getNextMonth(currentMonthFilename);

        //Initialisere arrays
        //    ArrayList<TimeSlot> currentMonth;
        //    ArrayList<TimeSlot> nextMonth;
        this.currentMonth = new ArrayList<>();
        this.nextMonth = new ArrayList<>();

        //Reader objekt
        ReadAppointments reader = new ReadAppointments();
        //Læser data fra csv appointments filer og gemmer i array
        ArrayList<String[]> currentMonthData = reader.reader(this.currentMonthFilename);
        ArrayList<String[]> nextMonthData = reader.reader(this.nextMonthFilename);

    }

    //getters
    public String getCurrentMonthFilename() {
        return currentMonthFilename;
    }

    public String getNextMonthFilename() {
        return nextMonthFilename;
    }

    public String getNextMonth(String currentMonth){
        String monthValue = currentMonth.substring(0, 2);
        String yearValue = currentMonth.substring(2, 6);

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
            throw new IllegalArgumentException("Num not within range.");
        }

        String _nextMonth;
        if (nextMonth < 10) {
            _nextMonth = "0" + Integer.toString(nextMonth);
        } else {
            _nextMonth = Integer.toString(nextMonth);
        }
        return _nextMonth + Integer.toString(nextYear);

    }

    public void makeTimeslot(){


    }

    public void deleteTimeSlot(){

    }

    private void sortByTime(){

    }
}
