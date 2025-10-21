/**
 * TimeSlot er en... <br>
 * Has methods: <br>
 *
 * Has attributes: <br>
 *
 */

public class TimeSlot implements Comparable<TimeSlot> {
    /**
     * day er en...
     */
    private final int day;
    /**
     * month er en...
     */
    private final int month;
    /**
     * timestamp er en...
     */
    private final int timestamp;
    /**
     * name er en...
     */
    private String name;
    /**
     * paid er en...
     */
    private boolean paid;

    /**
     * Timeslot constructor for new bookings
     * @param day
     * @param month
     * @param timestamp
     * @param name
     */
    public TimeSlot(int day, int month, int timestamp, String name){
        this.day = day;
        this.month = month;
        this.timestamp = timestamp;
        this.name = name;
        this.paid = false;
    }

    /**
     * Timeslot constructor for reading bookings from file
     * @param day
     * @param month
     * @param timestamp
     * @param name
     */
    public TimeSlot(int day, int month, int timestamp, String name, boolean isPaid){
        this.day = day;
        this.month = month;
        this.timestamp = timestamp;
        this.name = name;
        this.paid = isPaid;
    }

    /**
     * getTime is a...
     * @return
     */
    public int getTime(){
        return Integer.parseInt(String.valueOf(this.day)
                + String.valueOf(this.month)
                + String.valueOf(this.timestamp));
    }

    /**
     * getDay..
     * @return
     */
    public int getDay(){return this.day;}

    /**
     * getMonth
     * @return
     */
    public int getMonth(){return this.month;}

    /**
     * getTimestamp
     * @return
     */
    public int getTimestamp(){return this.timestamp;}

    /**
     * getName
     * @return
     */
    public String getName(){return this.name;}

    /**
     * setName
     * @param newName
     */
    public void setName(String newName){this.name = newName;}

    /**
     * toString override
     * @return
     */
    @Override
    public String toString(){
        return this.day + "" + this.month + " " + this.timestamp + "\n" + this.name;
    }

    /**
     * compareTo comparable interface override
     * @param t
     * @return
     */
    @Override
    public int compareTo(TimeSlot t){
        return (this.getTime() - t.getTime());
    }
}
