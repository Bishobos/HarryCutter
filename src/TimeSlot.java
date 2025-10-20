public class TimeSlot implements Comparable<TimeSlot> {
    int day;
    int month;
    int timestamp;
    String name;
    boolean paid;

    public TimeSlot(int day, int month, int timestamp, String name){
        this.day = day;
        this.month = month;
        this.timestamp = timestamp;
        this.name = name;
        this.paid = false;
    }

    //Setters and getters

    @Override
    public String toString(){
        return "";
    }

    @Override
    public int compareTo(TimeSlot t){
        return 0;
    }
}
