package GUI;

import Helper.BillCalculation;
import Helper.Validator;
import com.sun.tools.javac.Main;
import domain.MenuItem;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;

//WaiterName, TotalAmount, PaymentOption, CVC number, Card Number, PIN
public class DisplayPayment implements ActionListener {
    private JFrame frame;
    private JPanel pnlBillDetails, pnlCustomerDetails, pnlProcessPayment;
    private JLabel lblWaiterName, lblTotalAmount, lblPaymentOption, lblTip, lblCVC, lblCardNum,lblPIN, lblTotal, lblCashRecieved, lblExpDate;
    private JTextField txtWaiterName, txtTotalAmount, txtPaymentOption, txtTip, txtCVC, txtCardNum, txtCashRecieved, txtExpDate;
    private JPasswordField txtPIN;
    private JButton btnProcess;

    private double total = 100;
    private String paymentOption;

    private List<domain.MenuItem> list = List.of(
            new domain.MenuItem("Burger", 50.00),
            new domain.MenuItem("Burger", 50.00)
    );

    private ArrayList<MenuItem> menuItemslist = new ArrayList<>();
    public DisplayPayment(String paymentOption) {
        this.paymentOption = paymentOption;
        menuItemslist.addAll(list);

        this.frame = new JFrame("Table #9: Payment");
        this.pnlBillDetails = new JPanel();
        this.pnlCustomerDetails = new JPanel();
        this.pnlProcessPayment = new JPanel();

        lblWaiterName = new JLabel("Waiter: Jenny");
        lblTotalAmount = new JLabel("Total Amount(R): R100");
        lblPaymentOption = new JLabel("Payment Options: " + paymentOption);
        lblTip = new JLabel("Tip Amount(R): ");
        lblCVC = new JLabel("CVC Number: ");
        lblCardNum = new JLabel("Card Number: ");
        lblPIN = new JLabel("PIN : ");
        lblTotal = new JLabel("Total Amount(R): " + total);
        lblCashRecieved = new JLabel("Cash Received: ");
        lblExpDate = new JLabel("Exp Date: ");

        txtTip = new JTextField("0");
        txtCVC = new JTextField();
        txtCardNum = new JTextField();
        txtPIN = new JPasswordField();
        txtCashRecieved = new JTextField("0");
        txtExpDate = new JTextField("");

        btnProcess = new JButton("Process Payment");

        frame.setVisible(true);
        frame.setSize(450, 200);
        frame.setDefaultCloseOperation(JInternalFrame.EXIT_ON_CLOSE);

        setOptions();
        setGUI();
    }

    public void setGUI() {
        pnlBillDetails.setBackground(Color.decode("#808080"));
        pnlCustomerDetails.setBackground(Color.decode("#808080"));
        pnlProcessPayment.setBackground(Color.decode("#808080"));

        pnlBillDetails.setLayout(new GridLayout(3, 3));
        pnlCustomerDetails.setLayout(new GridLayout(2, 3));

        btnProcess.setBackground(Color.GREEN);
        btnProcess.addActionListener(this);

        pnlBillDetails.add(lblWaiterName);
        pnlBillDetails.add(lblTotalAmount);
        pnlBillDetails.add(lblPaymentOption);
        pnlBillDetails.add(lblTip);
        pnlBillDetails.add(txtTip);
        pnlBillDetails.add(new JLabel());
        pnlBillDetails.add(lblCashRecieved);
        pnlBillDetails.add(txtCashRecieved);

        pnlCustomerDetails.add(lblCVC);
        pnlCustomerDetails.add(txtCVC);
        pnlCustomerDetails.add(lblCardNum);
        pnlCustomerDetails.add(txtCardNum);
        pnlCustomerDetails.add(lblPIN);
        pnlCustomerDetails.add(txtPIN);
        pnlCustomerDetails.add(lblExpDate);
        pnlCustomerDetails.add(txtExpDate);

        pnlProcessPayment.add(lblTotal);
        pnlProcessPayment.add(btnProcess);

        frame.add(pnlBillDetails, BorderLayout.NORTH);
        frame.add(pnlCustomerDetails);
        frame.add(pnlProcessPayment, BorderLayout.SOUTH);
    }

    private void setOptions() {
        if(this.paymentOption.equals("Cash")) {
            txtCVC.setText("NOT APPLICABLE");
            txtCardNum.setText("NOT APPLICABLE");
            txtExpDate.setText("NOT APPLICABLE");
            txtCVC.setEnabled(false);
            txtCardNum.setEnabled(false);
            txtPIN.setEnabled(false);
            txtExpDate.setEnabled(false);
        } else {
            txtCashRecieved.setText("NOT APPLICABLE");
            txtCashRecieved.setEnabled(false);
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(btnProcess == e.getSource() && !this.paymentOption.equals("Cash")) {
            total += Double.parseDouble(txtTip.getText());
            if(validCard() && Validator.isValidTip(txtTip.getText())) {
                lblTotal.setText("Total Amount(R): " + total);
                this.frame.dispose();
                new JOptionPane().showMessageDialog(null, "Thank you, Payment Successful \n Bill Total: " + total);
                var db =  new DisplayBill(new ArrayList<>(), new BillCalculation().calculateBill(new ArrayList<>(), ""));
                db.getBtnProcessBill().setEnabled(false);
            }
            else new JOptionPane().showMessageDialog(null, "Error: Payment unsuccessful");
        } else if(btnProcess == e.getSource()) {
            if(Validator.isValidTip(txtTip.getText())) {
//                Double cashRecieved = Double.parseDouble(txtCashRecieved.getText());
                double totalChange = Double.parseDouble(txtCashRecieved.getText())  - (total + Double.parseDouble(txtTip.getText()));
                total += Double.parseDouble(txtTip.getText());
                lblTotal.setText("Total Amount(R): " + total);
                new JOptionPane().showMessageDialog(null, "Thank you, Payment Successful " +
                        "\n Bill Total: " + total +
                        "\n Total Change: " + totalChange);
                var db = new DisplayBill(new ArrayList<>(), new BillCalculation().calculateBill(new ArrayList<>(), ""));
                db.getBtnProcessBill().setEnabled(false);
                this.frame.dispose();
            }
        }
    }

    private boolean validCard() {
        if(!Validator.isValidCVVNumber(txtCVC.getText()) || !Validator.isValidCardNumber(txtCardNum.getText()) || !Validator.isValidPIN(txtPIN.getText()))
            return false;
        return true;
    }

}
