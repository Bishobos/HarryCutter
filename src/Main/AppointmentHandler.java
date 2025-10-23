package Main;

import java.util.ArrayList;
import FileHandler.ReadAppointments;

public class AppointmentHandler {
    public ArrayList<TimeSlot> currentMonth = new ArrayList<>();
    public ArrayList<TimeSlot> nextMonth = new ArrayList<>();
    private String currentMonthFilename;
    private String nextMonthFilename;

    public AppointmentHandler(String dateInput){
        //use ReadAppointments to fill currentMonth and nextMonth
        //case where no file at that date exists should be handled, no timeslots to be added to array may cause errors initially

        // DD(MM)(YYYY)
        String month = dateInput.substring(2, 4);
        String year = dateInput.substring(4, 8);

        //Sætter filnavn for værende måned//år
        this.currentMonthFilename = month + year;
        this.nextMonthFilename = getNextMonth(currentMonthFilename);

        //Reader objekt
        ReadAppointments currentReader = new ReadAppointments(this.currentMonthFilename);
        //Læser data fra csv appointments filer og gemmer i array
        ArrayList<String[]> currentMonthString = currentReader.reader();
        makeTimeSlots(currentMonthString, this.currentMonth);
        ReadAppointments nextReader = new ReadAppointments(this.nextMonthFilename);

        ArrayList<String[]> nextMonthString = nextReader.reader();
        makeTimeSlots(nextMonthString, this.nextMonth);

    }

    private void makeTimeSlots(ArrayList<String[]> input, ArrayList<TimeSlot> Month) {
        for (String[] strings : input) {
            int day = Integer.parseInt(strings[0]);
            int month = Integer.parseInt(strings[1]);
            int year = Integer.parseInt(strings[2]);
            int timestamp = Integer.parseInt(strings[3]);
            String name = strings[4];
            boolean paid = Boolean.parseBoolean(strings[5]);
            TimeSlot time = new TimeSlot(day, month, year, timestamp, name, paid);
            Month.add(time);
        }
    }

    //getters
    public String getCurrentMonthFilename() {
        return currentMonthFilename;
    }

    public String getNextMonthFilename() {
        return nextMonthFilename;
    }
    public ArrayList<TimeSlot> getCurrentMonth(){
        return this.currentMonth;
    }
    public ArrayList<TimeSlot> getNextMonth() {
        return this.nextMonth;
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
            throw new IllegalArgumentException("Nummer ikke mellem 1-12.");
        }

        String _nextMonth;
        if (nextMonth < 10) {
            _nextMonth = "0" + Integer.toString(nextMonth);
        } else {
            _nextMonth = Integer.toString(nextMonth);
        }
        return _nextMonth + Integer.toString(nextYear);

    }

    public void makeTimeslot(int day, int month, int year, int timestamp, String name, boolean paid){
        TimeSlot newSlot = new TimeSlot(day, month, year, timestamp, name, paid);

        String slotMonth = String.format("%02d%d", month, year);

        if (slotMonth.equals(this.currentMonthFilename)) {
            currentMonth.add(newSlot);
            //sorter også her
            sortByTime(currentMonth);
            //^^
        } else if (slotMonth.equals(this.nextMonthFilename)) {
            nextMonth.add(newSlot);
            //sorter også her
            sortByTime(nextMonth);
            //^^
        } else {
            throw new IllegalArgumentException("Dato ikke i nuværende eller næste måned");
        }
    }

    public void deleteTimeSlot(int day, int month, int year, int timestamp) {
        String specificMonth = String.format("%02d%d", month, year);

        if (specificMonth.equals(currentMonthFilename)) {
            //fjern fra nuværende måneds array
            for (int i = 0; i < currentMonth.size(); i++) {
                TimeSlot slot = currentMonth.get(i);
                if
                    //hvis alle attributes er det samme så det den korrekte og bliver slettet.
                (slot.getDay() == day &&
                        slot.getMonth() == month &&
                        slot.getYear() == year &&
                        slot.getTimestamp() == timestamp) {
                    currentMonth.remove(i);
                    //har fjernet fra array
                    return;
                }
            }
        } else if (specificMonth.equals(nextMonthFilename)) {
            //fjern fra næste måneds array
            for (int i = 0; i < nextMonth.size(); i++) {
                TimeSlot slot = nextMonth.get(i);
                if
                    //hvis alle attributes er det samme så det den korrekte og bliver slettet.
                (slot.getDay() == day &&
                        slot.getMonth() == month &&
                        slot.getYear() == year &&
                        slot.getTimestamp() == timestamp) {
                    nextMonth.remove(i);
                    //har fjernet fra array behøver ik return noget andet. i think
                    return;
                } else {
                    throw new IllegalArgumentException("Dato ikke i nuværende eller næste måned");
                }
            }
        }
    }

    private void sortByTime(ArrayList<TimeSlot> list){
        list.sort(TimeSlot::compareTo);
    }
}
