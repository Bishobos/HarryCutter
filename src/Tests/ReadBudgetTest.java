package Tests;
import FileHandler.ReadBudget;

import java.util.Arrays;

public class ReadBudgetTest {
    public static void main(String[] args){
        ReadBudget budget = new ReadBudget(false);
        for (String[] strings : budget.reader()) {
            System.out.println(Arrays.toString(strings));
        }
    }
}
