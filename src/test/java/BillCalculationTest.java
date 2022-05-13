import domain.MenuItem;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class BillCalculationTest {

    @Test
    public void testBillCalculationForInvalidMenuItem() {
        List<MenuItem> list = Arrays.asList(new MenuItem(null, -45.00));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            var bill = new BillCalculation().calculateBill(list, "");
        });

        String expectedMessage = "Error: Menu Item invalid value(s) - " + list.get(0).getName();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testBillCalculationForMenuItemNotInDB() {
        List<MenuItem> list = Arrays.asList(new MenuItem("Sushi", 45.00));

        Exception exception = assertThrows(IllegalStateException.class, () -> {
            var bill = new BillCalculation().calculateBill(list, "");
        });

        String expectedMessage = "Error: Menu Item Not Found - " + list.get(0).getName();
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

    @Test
    public void testBillCalculationForNullOrEmpty() {
        Exception exception = assertThrows(IllegalArgumentException.class, () -> {
            var bill = new BillCalculation().calculateBill(null, "");
        });

        String expectedMessage = "Error: Invalid Menu Items passed";
        String actualMessage = exception.getMessage();

        assertTrue(actualMessage.contains(expectedMessage));
    }

//    @Test
//    public void testInvalidPromoCode() {
//        List<MenuItem> list = Arrays.asList(new MenuItem("Burger", 69.99));
//        var bill = new BillCalculation().calculateBill(list, "NONEFORALL20");
//        assertEquals(69.99, bill);
//    }

    @Test
    void testValidateCoupon() {
        String discoCode = "ONEFORALL20";
        int actualDisco = 20;
        BillCalculation bill = new BillCalculation();
        int assumedDisco = bill.promoDiscountRate(discoCode);
        assertEquals(assumedDisco,actualDisco);
    }

    @Test
    void testCalculateSubtotal() {
        BillCalculation bill = new BillCalculation();
        double actualSubtotal = 80.00;
        List<MenuItem> menuItemList = Arrays.asList(
                new MenuItem("Burger", 40.00),
                new MenuItem("Burger", 40.00)
        );
        double assumedSubtotal = bill.calculateBill(menuItemList,"");
        assertEquals(assumedSubtotal,actualSubtotal);
    }
    @Test
    public void vatTest() {
        List<MenuItem> menuItemList = Arrays.asList(
                new MenuItem("Burger", 40.00),
                new MenuItem("Burger", 40.00)
        );
        var bill = new BillCalculation().calculateBill(menuItemList, "");
        double VATrecieved = BillCalculation.calculateVAT(bill);
        assertEquals(10.434782608695642,VATrecieved);
    }

    @Test
    public void discountAppliedTest() {
        List<MenuItem> list = Arrays.asList( new MenuItem("Burger", 100.00));
        var bill = new BillCalculation().calculateBill(list, "ONEFORALL20");
        assertEquals(80, bill);
    }

    @Test
    public void testBillAmount() {
        List<MenuItem> menuItemList = Arrays.asList(
                new MenuItem("Burger", 69.99),
                new MenuItem("Milkshake", 22.00),
                new MenuItem("Chips",25.00)
        );
        var bill = new BillCalculation().calculateBill(menuItemList, "");
        assertEquals(116.99,bill);
    }
}