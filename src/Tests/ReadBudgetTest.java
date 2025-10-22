package Tests;
import FileHandler.ReadBudget;

public class ReadBudgetTest {
    public static void main(String[] args){
        ReadBudget budget = new ReadBudget();
        System.out.println(budget.reader().getLast()[5]);
    }
}
