import domain.MenuItem;

import java.util.*;

public class BillCalculation {

    public double calculateBill(List<MenuItem> menuItemsList, String promoCode) {
        if(isEmptyOrNull(menuItemsList)) throw new IllegalArgumentException("No Menu Items Found");

        double total = 0;
        for (MenuItem item : menuItemsList) {
            if(!isPositive(item.getPrice())) throw new IllegalStateException("Something went wrong - Try Again");

            if(!isMenuItemInDB(item)) throw new IllegalStateException("Error: Menu Item invalid value(s) - " + item.getName());

            total += item.getPrice();
        }

        double discountedTotal = 0;
        if(promoDiscountRate(promoCode) > 0) discountedTotal = calculateDiscount(promoCode, total);

        //Calculate Tax : Not to sure if this is needed at the menu item would contain tax already
//        double tax = calculateTax(total);

        //Round off
        return total - discountedTotal;
    }

    private boolean isEmptyOrNull(List<MenuItem> menuItemList) {
        return menuItemList == null || menuItemList.isEmpty() ;
    }

    private boolean isPositive(double price) {
        return price > 0;
    }

    //TODO: Check if this is right
    private double calculateDiscount(String promoCode, double total) {
        return total / 100 * promoDiscountRate(promoCode);
    }

    //TODO: Check if this is right
//    private double calculateTax(double subtotal) {
//        return subtotal / 100 * 15;
//    }

    private int promoDiscountRate(String promoCode) {
        Map<String, Integer> promoCodes = Map.of("ONEFORALL20", 20, "ONEFORALL50", 50);
        return promoCodes.getOrDefault(promoCode, 0);
    }

    //Acts as the DB for the Menu Items
    private boolean isMenuItemInDB(MenuItem menuItem) {
        List<MenuItem> menuItemList = Arrays.asList(
                new MenuItem("Burger", 69.99),
                new MenuItem("Milkshake", 22.00)
        );
        return menuItemList.stream().anyMatch(item -> menuItem.getName().toLowerCase().equals(item.getName().toLowerCase()));
    }

}
