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

        DisplayBill bb = new DisplayBill(menuItemslist, c.calculateBill(list, ""));




        c.printBill(list);
        System.out.println();
        System.out.println("Bill Total: R" + c.calculateBill(list, ""));

        System.out.println("--------------------------------------------");

        List<MenuItem> listWithDicount = List.of(
                new MenuItem("Burger", 100.00)
        );

        c.printBill(listWithDicount);
        System.out.println();
        System.out.println("Bill Total: R" + c.calculateBill(listWithDicount, "ONEFORALL20"));
    }
}
