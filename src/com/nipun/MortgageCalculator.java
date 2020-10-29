package com.nipun;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.NumberFormat;

public class MortgageCalculator implements ActionListener {

    private static JFrame frame;
    private static JPanel panel;
    private static JLabel principalLabel, interestLabel, durationLabel, repaymentLabel;
    private static JTextField principalText, interestText, durationText;
    private static JButton calculateButton;

    public static void main(String[] args) {

        frame = new JFrame("Mortgage Calculator");
        panel = new JPanel();

        frame.setSize(350, 200);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setResizable(false);
        frame.getContentPane().setBackground(Color.BLUE);
        frame.add(panel);
        //panel.setBackground(Color.ORANGE);
        panel.setLayout(null);

        principalLabel = new JLabel("Principal (£)");
        principalLabel.setBounds(20, 10, 100, 25);
        panel.add(principalLabel);
        principalText = new JTextField();
        principalText.setBounds(130, 10, 100,25);
        panel.add(principalText);

        interestLabel = new JLabel("Interest Rate (%)");
        interestLabel.setBounds(20, 40, 100, 25);
        panel.add(interestLabel);
        interestText = new JTextField();
        interestText.setBounds(130, 40, 100, 25);
        panel.add(interestText);

        durationLabel = new JLabel("Duration (years)");
        durationLabel.setBounds(20, 70, 100, 25);
        panel.add(durationLabel);
        durationText = new JTextField();
        durationText.setBounds(130, 70, 100, 25);
        panel.add(durationText);

        calculateButton = new JButton("Calculate");
        calculateButton.setBounds(130, 100, 100, 25);
        //calculateButton.setBackground(Color.RED);
        //calculateButton.setForeground(Color.WHITE);
        calculateButton.addActionListener(new MortgageCalculator());
        panel.add(calculateButton);

        repaymentLabel = new JLabel("");
        repaymentLabel.setBounds(10, 130, 400, 25);
        panel.add(repaymentLabel);
        frame.setVisible(true);

    }

    @Override
    public void actionPerformed(ActionEvent event){

        final int monthsInAYear = 12;
        final int percentage = 100;
        int principal;
        float interestRate;
        float duration;

        principal = Integer.parseInt(principalText.getText().trim());

        interestRate = Float.parseFloat(interestText.getText().trim());
        float monthlyInterestRate = interestRate / percentage / monthsInAYear;

        duration = Float.parseFloat(durationText.getText().trim());
        float monthlyDuration = (Math.round(duration)) * monthsInAYear;

        if(principal < 1_000 || principal > 1_000_000){

            repaymentLabel.setText("Principal value must be between £1000 and £1000000");

        } else if(interestRate < 1 || interestRate > 35) {

            repaymentLabel.setText("Interest value must be between 1% and 35%");

        } else if(duration < 5 || duration > 30) {

            repaymentLabel.setText("Duration value must be between 5 and 30 years");

        } else {

            double monthlyPayments = principal * (monthlyInterestRate * Math.pow(1 + monthlyInterestRate, monthlyDuration) /
                    (Math.pow(1 + monthlyInterestRate, monthlyDuration) - 1));

            NumberFormat currency = NumberFormat.getCurrencyInstance();
            String monthlyRepayment = currency.format(monthlyPayments);

            repaymentLabel.setText("Monthly repayments of:  " + monthlyRepayment);

        }
        //TODO: If text field is empty
        //TODO: If text field does not contain numbers but has letters
    }
}
