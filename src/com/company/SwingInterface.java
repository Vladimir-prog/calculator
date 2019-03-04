package com.company;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingInterface extends JFrame {
    private JButton button = new JButton("Посчитать");
    private JTextField input = new JTextField("", 5);
    private JLabel label = new JLabel("Введи выражение:");
    private JLabel answer = new JLabel("answer");
    private JLabel clearLable = new JLabel("");

    public SwingInterface() {
        super("Simple Example");
        this.setBounds(200,200,500,100);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        Container container = this.getContentPane();
        container.setLayout(new GridLayout(0,3,10,10));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        container.add(label);
        container.add(input);
        container.add(answer);
        container.add(clearLable);
        button.addActionListener(new ButtonEventListener());
        container.add(button);

    }

    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String answerString = Calculator.countExpression(Calculator.parseInput(input.getText())).toString();
                answer.setText(answerString);
            } catch (MyCalculatorExceptinon exeption) {
                answer.setText(exeption.getMessage());
                //clearLable.setText(exept.getMessage());
            } catch (Exception exeption) {
                answer.setText("ERROR");
                //clearLable.setText(exept.getMessage());
            }
        }
    }


//    public static void main(String[] args)
//    {
//        SwingInterface w = new SwingInterface();
//        w.setVisible(true);
//    }
}
