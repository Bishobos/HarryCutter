package Tests;
import FileHandler.ReadBudget;

import java.util.Arrays;

public class ReadBudgetTest {
    public static void main(String[] args){
        ReadBudget budget = null;
        boolean isPassword = true;
        try{
            budget = new ReadBudget(isPassword);
        } catch (IllegalAccessException e) {
            System.out.println("Password not Validated");
        }

        for (String[] strings : budget.reader()) {
            System.out.println(Arrays.toString(strings));
        }
    }
}
