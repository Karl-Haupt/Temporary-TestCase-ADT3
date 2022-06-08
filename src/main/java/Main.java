import GUI.DisplayBill;
import Helper.BillCalculation;
import domain.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BillCalculation c = new BillCalculation();

        List<MenuItem> list = List.of(
                new MenuItem("Burger", 50.00),
                new MenuItem("Burger", 50.00)
        );

        ArrayList<MenuItem> menuItemslist = new ArrayList<>();
        menuItemslist.addAll(list);

        DisplayBill displayBill = new DisplayBill(menuItemslist, c.calculateBill(list, ""));
    }
}
