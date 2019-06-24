/**
 * Shay Yosopov
 * id:324124593
 */


import javax.swing.*;

public class ReminderMain {
    public static void main(String[] args){
        JFrame frame = new JFrame("Reminder");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ReminderPanel p = new ReminderPanel();
        frame.add(p);
        frame.setSize(1000, 400);
        frame.setVisible(true);
    }
}
