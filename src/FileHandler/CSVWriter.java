package FileHandler;

import Main.TimeSlot;

public interface CSVWriter {
    public void writer();
    public String format(TimeSlot toFormat);
}
