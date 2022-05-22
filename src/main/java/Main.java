import GUI.DisplayBill;
import domain.MenuItem;

import java.util.ArrayList;
import java.util.List;

public class Main {

    public static void main(String[] args) {
        BillCalculation c = new BillCalculation();

        List<MenuItem> list = List.of(
                new MenuItem("Burger", 69.99),
                new MenuItem("Burger", 69.99)
        );

        ArrayList<MenuItem> menuItemslist = new ArrayList<>();
        menuItemslist.addAll(list);

        DisplayBill displayBill = new DisplayBill(menuItemslist, c.calculateBill(list, ""));

        List<MenuItem> listWithDicount = List.of(
                new MenuItem("Burger", 100.00)
        );
        ArrayList<MenuItem> itemArrayList = new ArrayList<>();
        itemArrayList.addAll(listWithDicount);

        DisplayBill displayDiscountedBill = new DisplayBill(itemArrayList, c.calculateBill(itemArrayList, "ONEFORALL20"));
    }
}
