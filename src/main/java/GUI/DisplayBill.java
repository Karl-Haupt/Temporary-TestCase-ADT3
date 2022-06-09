package GUI;

import domain.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;

public class DisplayBill implements ActionListener {
    private JFrame frame;
    private JPanel pnlList, pnlTotal;

    private JButton btnProcessBill, btnBack;

    public DisplayBill(ArrayList<MenuItem> items, double total) {
        this.frame = new JFrame("Table #9: Bill");
        this.pnlList = new JPanel();
        this.pnlTotal = new JPanel();
        this.btnProcessBill = new JButton("Process Bill");
        this.btnBack = new JButton("Back");

        frame.setVisible(true);
        frame.setSize(300, 300);
        frame.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);

        setGUI(items, total);
    }

    private void setGUI(ArrayList<MenuItem> items, double total) {
        pnlList.setBackground(Color.decode("#808080"));
        pnlList.setLayout(new BoxLayout(pnlList, BoxLayout.Y_AXIS));
        pnlList.setBorder(BorderFactory.createEmptyBorder(100, 80, 0, 0));
        btnProcessBill.setLayout(new GridLayout(2, 1));
        btnProcessBill.setBackground(Color.decode("#808080"));
        btnBack.setBackground(Color.decode("#f59b42"));

        btnProcessBill.addActionListener(this);
        btnBack.addActionListener(this);

        pnlTotal.setBackground(Color.GREEN);

        int i = 0;
        for (MenuItem item : items) {
            String formattedItem = "#" + i + " " + item.getName() + " \t R " + item.getPrice() + "\n";
            pnlList.add(new JLabel(formattedItem));
            i++;
        }

        pnlTotal.add(btnBack);
        pnlTotal.add(new JLabel("Bill Total: R" + total));
        pnlTotal.add(btnProcessBill);

        frame.add(pnlList, BorderLayout.CENTER);
        frame.add(pnlTotal, BorderLayout.SOUTH);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnProcessBill == e.getSource()) {
            DisplayPaymentOptions displayPaymentOptions = new DisplayPaymentOptions();
            frame.dispose();
        }
    }

    public JButton getBtnProcessBill() {
        return btnProcessBill;
    }
}
