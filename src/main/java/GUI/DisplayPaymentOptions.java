package GUI;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class DisplayPaymentOptions implements ActionListener {
    private JFrame frame;
    private JPanel pnlOptions, pnlHeading;
    private JLabel lblOptions;

    private JButton btnCash, btnCard, btnSnap;
    private JLabel lblPaymentOptions;

    public DisplayPaymentOptions() {
        this.frame = new JFrame("Table #9: Payment Options");
        this.pnlOptions = new JPanel();
        this.pnlHeading = new JPanel();
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
        pnlOptions.setBackground(Color.decode("#808080"));
        lblOptions.setBackground(Color.decode("#808080"));
        frame.setBackground(Color.decode("#808080"));
        pnlHeading.setBackground(Color.decode("#808080"));
        btnCash.setBackground(Color.GREEN);
        btnCard.setBackground(Color.GREEN);
        btnSnap.setBackground(Color.GREEN);

        btnCash.addActionListener(this);
        btnCard.addActionListener(this);
        btnSnap.addActionListener(this);

        pnlOptions.add(btnCash, BorderLayout.WEST);
        pnlOptions.add(btnCard, BorderLayout.CENTER);
        pnlOptions.add(btnSnap, BorderLayout.EAST);

        pnlHeading.add(lblOptions, BorderLayout.CENTER);

        frame.add(pnlHeading, BorderLayout.NORTH);
        frame.add(pnlOptions, BorderLayout.CENTER);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnCash == e.getSource()) {
            this.frame.dispose();
            DisplayPayment displayPayment = new DisplayPayment("Cash");
        } else if(btnCard == e.getSource()) {
            this.frame.dispose();
            DisplayPayment displayPayment = new DisplayPayment("Card");
        } else if(btnSnap == e.getSource()) {
            this.frame.dispose();
            DisplayPayment displayPayment = new DisplayPayment("SnapScan");
        }
    }
}
