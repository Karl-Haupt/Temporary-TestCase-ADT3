package GUI;

import domain.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.util.ArrayList;

public class DisplayBill {
    private JFrame frame;
    private JPanel pnlList, pnlTotal;

    public DisplayBill(ArrayList<MenuItem> items, double total) {
        this.frame = new JFrame("Table #9: Bill Total");
        this.pnlList = new JPanel();
        this.pnlTotal = new JPanel();

        frame.setVisible(true);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);

        setGUI(items, total);
//        frame.addWindowListener(new WindowAdapter() {
//            public void windowClosing(WindowEvent e) {
//                System.exit(0);
//            }
//        });
    }

    private void setGUI(ArrayList<MenuItem> items, double total) {
        pnlList.setBackground(Color.decode("#f59b42"));
        pnlList.setLayout(new BoxLayout(pnlList, BoxLayout.Y_AXIS));
        pnlList.setBorder(BorderFactory.createEmptyBorder(100, 80, 0, 0));

        pnlTotal.setBackground(Color.GREEN);

        int i = 0;
        for (MenuItem item : items) {
            String formattedItem = "#" + i + " " + item.getName() + " \t R " + item.getPrice() + "\n";
            pnlList.add(new JLabel(formattedItem));
            i++;
        }

        pnlTotal.add(new JLabel("Bill Total: " + total));

        frame.add(pnlList, BorderLayout.CENTER);
        frame.add(pnlTotal, BorderLayout.SOUTH);
    }
}
