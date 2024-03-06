/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */

/**
 *
 * @author Taysir
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;

import javax.swing.*;

// Controller class to manage the interaction between the model and view
public class QuizController {
    private LoginView loginView;
    private QuizView quizView;
    private StudentModel studentModel;

    public QuizController(LoginView loginView, QuizView quizView, StudentModel studentModel) {
        this.loginView = loginView;
        this.quizView = quizView;
        this.studentModel = studentModel;

        // Add ActionListener for the login button in the login view
        loginView.addLoginListener(new LoginListener());

        // Add ActionListener for the submit button in the quiz view
        quizView.addSubmitListener(new SubmitListener());
    }

    // ActionListener for the login button
    private class LoginListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String username = loginView.getUsername();
            char[] password = loginView.getPassword();

            if (username.equals("std") && String.valueOf(password).equals("std")) {
                // Student login
                loginView.setVisible(false);
                quizView.setVisible(true);
            }
            else if (username.equals("ins") && String.valueOf(password).equals("ins")) {
                // Instructor login
                openStudentScoresFile();}
            else {
                JOptionPane.showMessageDialog(loginView, "Invalid username or password");
            }
        }
    }

    // ActionListener for the submit button
    private class SubmitListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedOption = quizView.getSelectedOption();

            if (selectedOption == 3) {
                // Correct answer (C. Paris)
                int score = 10;

                studentModel.setName(quizView.getName());
                studentModel.setId(quizView.getId());
                studentModel.setScore(score);

                // Save score and ID to a file
                // You can customize the file path and format as needed
                String filePath = "student_scores.txt";
                String content = studentModel.getName() + " (" + studentModel.getId() + "): " + score;

                try (FileWriter writer = new FileWriter(filePath, true)) {
                    writer.write(content);
                    writer.write(System.lineSeparator());
                } catch (IOException ex) {
                    ex.printStackTrace();
                }

                JOptionPane.showMessageDialog(quizView, "Quiz submitted. Your score is " + score);
            } else {
                JOptionPane.showMessageDialog(quizView, "Incorrect. Please try again.");
            }
        }
    }
    private void openStudentScoresFile() {
        JFrame scoresFrame = new JFrame("Student Scores");
        JTextArea scoresTextArea = new JTextArea();

        // Read the content of the "student_scores.txt" file and display it
        try (BufferedReader reader = new BufferedReader(new FileReader("student_scores.txt"))) {
            String line;
            while ((line = reader.readLine()) != null) {
                scoresTextArea.append(line + "\n");
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        scoresFrame.add(new JScrollPane(scoresTextArea));
        scoresFrame.setSize(400, 300);
        scoresFrame.setLocationRelativeTo(null);
        scoresFrame.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        scoresFrame.setVisible(true);
    }

    // Main method to start the application
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create instances of the views and model
                LoginView loginView = new LoginView();
                QuizView quizView = new QuizView();
                StudentModel studentModel = new StudentModel();

                // Create the controller and pass the views and model to it
                QuizController quizController = new QuizController(loginView, quizView, studentModel);

                // Set the initial visibility of views
                loginView.setVisible(true);
                quizView.setVisible(false);
            }
        });
    }
}

