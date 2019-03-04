package prog.vladimir;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class SwingInterface extends JFrame {
    private JButton button = new JButton("Посчитать");
    private JTextField input = new JTextField("", 5);
    private JLabel label = new JLabel("Введи выражение:");
    private JLabel answer = new JLabel("");
    private JLabel errorLable = new JLabel("");

    public SwingInterface() {
        super("MyCalculator");
        this.setBounds(200,200,500,150);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridLayout(0, 3, 15, 15));
        label.setHorizontalAlignment(SwingConstants.RIGHT);
        mainPanel.add(label);
        input.addActionListener(new ButtonEventListener());
        mainPanel.add(input);
        mainPanel.add(answer);

        Container outerContainer = this.getContentPane();
        outerContainer.setLayout(new GridLayout(3,0,10,10));
        errorLable.setHorizontalAlignment(SwingConstants.CENTER);
        outerContainer.add(errorLable);
        outerContainer.add(mainPanel);
        button.addActionListener(new ButtonEventListener());
        outerContainer.add(button);
    }
    class ButtonEventListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            try {
                String answerString = Calculator.countExpression(StringParser.parseInput(input.getText())).toString();
                answer.setText(answerString);
            } catch (MyCalculatorExceptinon exeption) {
                errorLable.setText(exeption.getMessage());
            } catch (Exception exeption) {
                answer.setText("ERROR");
                errorLable.setText("неизвестная ошибка =(");
                //clearLable.setText(exept.getMessage());
            }
        }
    }
}
