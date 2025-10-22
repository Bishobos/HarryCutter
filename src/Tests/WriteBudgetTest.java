package Tests;

import FileHandler.WriteBudget;
import Main.TimeSlot;

public class WriteBudgetTest {
    public static void main(String[] args){
        TimeSlot booking = new TimeSlot(22, 10, 2025, 1100, "Test");
        WriteBudget writeBudget = new WriteBudget(booking, 0.00);
        writeBudget.writer();
    }
}
