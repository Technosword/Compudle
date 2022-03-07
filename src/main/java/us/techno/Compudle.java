package us.techno;

import javax.swing.*;
import java.awt.*;

public class Compudle {
    public static void main(String[] args) {
        createGuiWindow();
    }
    public static void createGuiWindow(){
        //Create the frame
        JFrame frame = new JFrame("Compudle");


        JLabel label = new JLabel("Compudle", SwingConstants.CENTER);
        label.setPreferredSize(new Dimension(300, 200));
        label.setVerticalAlignment(SwingConstants.TOP);
        label.setMinimumSize(new Dimension(50, 25));
        label.setMaximumSize(new Dimension(300, 200));
        label.setFont(new Font("Georgia", Font.PLAIN, 50));
        frame.getContentPane().add(label);

        frame.setLocationRelativeTo(null);
        frame.pack();
        frame.setSize(500,500);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }
}
