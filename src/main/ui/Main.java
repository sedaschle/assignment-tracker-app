package ui;

import javax.swing.*;

// CITATION: UBC CPSC210 JsonSerializationDemo, Java Swing ListDemo

// creates and shows the GUI
public class Main {
    public static void main(String[] args) {
        javax.swing.SwingUtilities.invokeLater(() -> {
            JFrame frame = new JFrame("Assignment Tracker");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

            JComponent newContentPane = new GUI();
            newContentPane.setOpaque(true);
            frame.setContentPane(newContentPane);

            frame.pack();
            frame.setVisible(true);
        });
    }
}

