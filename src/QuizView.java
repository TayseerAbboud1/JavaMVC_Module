/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Taysir
 */
import javax.swing.*;
import java.awt.event.ActionListener;

// View class for the quiz window
public class QuizView extends JFrame {
    private JTextField nameField;
    private JTextField idField;
    private JLabel questionLabel;
    private JRadioButton option1;
    private JRadioButton option2;
    private JRadioButton option3;
    private JRadioButton option4;
    private JButton submitButton;

    public QuizView() {
        setTitle("Quiz");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        nameField = new JTextField(15);
        idField = new JTextField(15);
        questionLabel = new JLabel("1. What is the capital of France?");
        option1 = new JRadioButton("A. Berlin");
        option2 = new JRadioButton("B. London");
        option3 = new JRadioButton("C. Paris");
        option4 = new JRadioButton("D. Madrid");
        submitButton = new JButton("Submit");

        ButtonGroup optionGroup = new ButtonGroup();
        optionGroup.add(option1);
        optionGroup.add(option2);
        optionGroup.add(option3);
        optionGroup.add(option4);

        panel.add(new JLabel("Name:"));
        panel.add(nameField);
        panel.add(new JLabel("ID:"));
        panel.add(idField);
        panel.add(questionLabel);
        panel.add(option1);
        panel.add(option2);
        panel.add(option3);
        panel.add(option4);
        panel.add(submitButton);

        add(panel);
    }

    // Getter methods to retrieve user input

    public String getName() {
        return nameField.getText();
    }

    public String getId() {
        return idField.getText();
    }

    public int getSelectedOption() {
        if (option1.isSelected()) {
            return 1;
        } else if (option2.isSelected()) {
            return 2;
        } else if (option3.isSelected()) {
            return 3;
        } else if (option4.isSelected()) {
            return 4;
        } else {
            return 0;
        }
    }

    // Method to add a submit listener to the submit button

    public void addSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
}

