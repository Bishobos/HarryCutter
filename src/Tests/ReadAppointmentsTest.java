package Tests;

import FileHandler.ReadAppointments;

import java.util.ArrayList;
import java.util.Arrays;

public class ReadAppointmentsTest {
    public static void main(String[] args){
        ReadAppointments testReader = new ReadAppointments();
        ArrayList<String[]> result = testReader.reader("102025");
        for (String[] strings : result) {
            System.out.println(Arrays.toString(strings));

        }
    }
}
