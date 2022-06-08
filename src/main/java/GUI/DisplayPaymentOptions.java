package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayPaymentOptions implements ActionListener {
    private JFrame frame;
    private JPanel pnlOptions;
    private JLabel lblOptions;

    private JButton btnCash, btnCard, btnSnap;
    private JLabel lblPaymentOptions;

    public DisplayPaymentOptions() {
        this.frame = new JFrame("Table #9: Payment Options");
        this.pnlOptions = new JPanel();
        this.lblOptions = new JLabel("Choose Payment Option: ");

        this.btnCash = new JButton("Cash");
        this.btnCard = new JButton("Card");
        this.btnSnap = new JButton("SnapScan");

        frame.setVisible(true);
        frame.setSize(300, 200);
        frame.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);

        setGUI();
    }

    private void setGUI() {
        pnlOptions.setBackground(Color.decode("#f59b42"));
        lblOptions.setBackground(Color.decode("#f59b42"));
        frame.setBackground(Color.decode("#f59b42"));
        btnCash.setBackground(Color.GREEN);
        btnCard.setBackground(Color.GREEN);
        btnSnap.setBackground(Color.GREEN);

        btnCash.addActionListener(this);
        btnCard.addActionListener(this);
        btnSnap.addActionListener(this);

        pnlOptions.add(btnCash, BorderLayout.WEST);
        pnlOptions.add(btnCard, BorderLayout.CENTER);
        pnlOptions.add(btnSnap, BorderLayout.EAST);

        frame.add(lblOptions, BorderLayout.NORTH);
        frame.add(pnlOptions, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnCash == e.getSource()) {
            DisplayPayment displayPayment = new DisplayPayment("Cash");
        } else if(btnCard == e.getSource()) {
            DisplayPayment displayPayment = new DisplayPayment("Card");
        } else if(btnSnap == e.getSource()) {
            DisplayPayment displayPayment = new DisplayPayment("SnapScan");
        }
    }

    public JButton getBtnCash() {
        return btnCash;
    }

    public JButton getBtnCard() {
        return btnCard;
    }

    public JButton getBtnSnap() {
        return btnSnap;
    }
}
