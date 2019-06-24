/**
 * Shay Yosopov
 * id:324124593
 */


import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Scanner;

public class ReminderPanel extends JPanel {
    private Reminder<Date> reminder;
    private JComboBox<String> day, month, year;
    private JTextArea text;
    private JButton cmdSaveInFile, cmdSaveInReminders, cmdView, cmdLoadFromFile;
    private String[] days, months, years; //string arrays for the options of the days months and years

    public ReminderPanel() {

        //setting up the options for the days months and years.
        days = new String[31];
        months = new String[12];
        years = new String[125];
        int i = 1;
        while (i <= 31) {
            days[i - 1] = i + "";
            i++;
        }
        i = 1;
        while (i <= 12) {
            months[i - 1] = "" + i;
            i++;
        }
        i = 1900;
        while (i <= 2024) {
            years[i - 1900] = i + "";
            i++;
        }


        //setting up the controls
        reminder = new Reminder<Date>();
        cmdSaveInFile = new JButton("Save In File");
        cmdSaveInReminders = new JButton("Save In Reminders");
        cmdView = new JButton("View");
        cmdLoadFromFile = new JButton("Load From File");
        text = new JTextArea();
        day = new JComboBox<String>(days);
        month = new JComboBox<String>(months);
        year = new JComboBox<String>(years);

        day.setEditable(true);
        month.setEditable(true);
        year.setEditable(true);

        //setting up the controls panel
        JPanel controls = new JPanel();
        controls.setLayout(new GridLayout(1, 7, 10, 10));
        controls.add(day);
        controls.add(month);
        controls.add(year);
        controls.add(cmdSaveInFile);
        controls.add(cmdSaveInReminders);
        controls.add(cmdLoadFromFile);
        controls.add(cmdView);

        this.setLayout(new BorderLayout());
        this.add(controls, BorderLayout.SOUTH);
        this.add(text, BorderLayout.CENTER);

        cmdView.addActionListener(new ButtonListener());
        cmdSaveInReminders.addActionListener(new ButtonListener());
        cmdSaveInFile.addActionListener(new ButtonListener());
        cmdLoadFromFile.addActionListener(new ButtonListener());


    }


    public void paintComponent(Graphics g) {
        super.paintComponent(g);
    }


    private class ButtonListener implements ActionListener {
        public void actionPerformed(ActionEvent e) {
            if (e.getSource() == cmdSaveInFile) {



                JFileChooser fileChooser = new JFileChooser();
                int returnValue = fileChooser.showOpenDialog(null);
                if (returnValue == JFileChooser.APPROVE_OPTION) {
                    File remindersFile = fileChooser.getSelectedFile();
                    try {
                        if (!remindersFile.exists()) {
                            remindersFile.createNewFile();
                        }


                        ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(remindersFile));

                        out.writeChars(reminder.toString());
                        out.close();
                    } catch (Exception exc) {
                        JOptionPane.showMessageDialog(null, "Failed to save to file");
                    }
                }

                }
            else if (e.getSource() == cmdLoadFromFile) {
                    text.setText("");
                    JFileChooser fileChooser = new JFileChooser();
                    int returnValue = fileChooser.showOpenDialog(null);
                    if (returnValue == JFileChooser.APPROVE_OPTION) {
                        File remindersFile = fileChooser.getSelectedFile();
                        try {


                            ObjectInputStream in = new ObjectInputStream(new FileInputStream(remindersFile));

                                while (true) //reads the chars in the file and prints them until it gets to the end of the file
                                {
                                    text.append(in.readChar()+"");
                                }
                        }
                        catch (EOFException eof){} //reached the end of the file
                        catch (Exception exc) {
                            JOptionPane.showMessageDialog(null, "Failed to save to file");
                        }
                    }
                }
                else if (e.getSource()==cmdView)
                {
                    int d=Integer.parseInt((String)day.getSelectedItem());
                    int m=Integer.parseInt((String)month.getSelectedItem());
                    int y=Integer.parseInt((String)year.getSelectedItem());
                    try{
                        text.setText(reminder.getReminder(new Date(d,m,y)));
                    }
                    catch (DateNotExistsException dExc){ //if the date does not exist prints an error massage
                        JOptionPane.showMessageDialog(null, "Date does not exist in reminder");
                    }
                }
                else if(e.getSource() == cmdSaveInReminders)
                {
                    //creates a new date and saves it with the text which is written
                    String msg=text.getText();
                    int d=Integer.parseInt((String)day.getSelectedItem());
                    int m=Integer.parseInt((String)month.getSelectedItem());
                    int y=Integer.parseInt((String)year.getSelectedItem());
                    reminder.addReminder(new Date(d,m,y) , msg );
                }



        }

    }
}
