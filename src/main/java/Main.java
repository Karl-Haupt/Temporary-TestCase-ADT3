import domain.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BillCalculation c = new BillCalculation();

        List<MenuItem> list = List.of(new MenuItem("BURGER", 69.99), new MenuItem("Burger", 69.99));
//        List<MenuItem> list = new ArrayList<>();
        System.out.println(c.calculateBill(list, ""));
    }
}