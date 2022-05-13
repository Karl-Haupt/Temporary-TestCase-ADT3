import domain.MenuItem;

import javax.swing.*;
import java.util.*;

public class BillCalculation {

    public double calculateBill(List<MenuItem> menuItemsList, String promoCode) {
        if(isEmptyOrNull(menuItemsList)) throw new IllegalArgumentException("Error: Invalid Menu Items passed");

        double total = 0;
        for (MenuItem item : menuItemsList) {
            if(!isPositive(item.getPrice())) throw new IllegalStateException("Error: Menu Item invalid value(s) - " + item.getName());

            if(!isMenuItemInDB(item)) throw new IllegalStateException("Error: Menu Item Not Found - " + item.getName());

            total += item.getPrice();
        }

        double discountedTotal = 0;
        if(promoDiscountRate(promoCode) > 0) discountedTotal = calculateDiscount(promoCode, total);
        return total - discountedTotal;
    }

    private boolean isEmptyOrNull(List<MenuItem> menuItemList) {
        return menuItemList == null || menuItemList.isEmpty() ;
    }

    private boolean isPositive(double price) {
        return price > 0;
    }

    private double calculateDiscount(String promoCode, double total) {
        System.out.println(total / 100 * promoDiscountRate(promoCode));
        return total / 100 * promoDiscountRate(promoCode);
    }

    public static double calculateVAT(double total) {
        double VATamount = total - (total / 1.15);
        return VATamount;
    }

    public int promoDiscountRate(String promoCode) {
        Map<String, Integer> promoCodes = Map.of("ONEFORALL20", 20, "ONEFORALL50", 50);
//        if(!promoCodes.containsKey(promoCode)) JOptionPane.showMessageDialog(null, "Error: Invalid Discount Code");
        return promoCodes.getOrDefault(promoCode, 0);
    }

    //Acts as the DB for the Menu Items
    private boolean isMenuItemInDB(MenuItem menuItem) {
        List<MenuItem> menuItemList = Arrays.asList(
                new MenuItem("Burger", 69.99),
                new MenuItem("Milkshake", 22.00),
                new MenuItem("Chips",25.00)
        );
        return menuItemList.stream().anyMatch(item -> menuItem.getName().toLowerCase().equals(item.getName().toLowerCase()));
    }

}
