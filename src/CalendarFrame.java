import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Objects;

class MyLabel extends JLabel {
    MyLabel(String content, int style, int size) {
        setText(content);
        setHorizontalAlignment(JLabel.CENTER);
        setVerticalAlignment(JLabel.CENTER);
        setFont(new Font("Cascadia Code", style, size));
        setBorder(BorderFactory.createLineBorder(Color.black, 1));
    }
}

public class CalendarFrame implements ActionListener{
    JFrame frame;
    JComboBox<String> month;
    JComboBox<Integer> year;
    JButton confirm, next, prev;
    JPanel panel;
    MyLabel[] dates = new MyLabel[42];
    Font myFont = new Font("Cascadia Code", Font.PLAIN, 16);

    CalendarCalculator dmyObj = new CalendarCalculator("JAN", 2024);

    CalendarFrame() {
        frame = new JFrame();
        frame.setTitle("Calendar");
        frame.setSize(500, 650);
        frame.setResizable(false);
        frame.setLayout(null);

        String[] monthNames =
                {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        month = new JComboBox<>(monthNames);
        month.setFont(myFont);
        month.setFocusable(false);
        month.setBounds(120, 30, 100, 35);

        year = new JComboBox<>();
        for(int i=1900; i<2100; i++)
            year.addItem(i);
        year.setFont(myFont);
        year.setSelectedItem(2024);
        year.setFocusable(false);
        year.setBounds(260, 30, 100, 35);

        confirm = new JButton("Confirm");
        confirm.setFont(myFont);
        confirm.setFocusable(false);
        confirm.setBounds(180, 90, 120, 35);
        confirm.addActionListener(this);

        panel = new JPanel(new GridLayout(0, 7));
        String[] days = {"SUN", "MON", "TUE", "WED", "THU", "FRI", "SAT"};
        for(String day : days)
            panel.add(new MyLabel(day, Font.BOLD, 20));
        for(int i=0; i<42; i++) {
            dates[i] = new MyLabel("", Font.PLAIN, 16);
            panel.add(dates[i]);
        }
        printDates();
        panel.setBorder(BorderFactory.createLineBorder(Color.BLACK, 3));
        panel.setBounds(35, 150, 420, 360);

        prev = new JButton("Prev");
        prev.setFont(myFont);
        prev.setFocusable(false);
        prev.setBounds(35, 540, 100, 40);
        prev.addActionListener(this);

        next = new JButton("Next");
        next.setFont(myFont);
        next.setFocusable(false);
        next.setBounds(355, 540, 100, 40);
        next.addActionListener(this);

        frame.add(month);
        frame.add(year);
        frame.add(confirm);
        frame.add(panel);
        frame.add(prev);
        frame.add(next);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setVisible(true);
    }

    void printDates() {
        int count = 0;
        for(int a=0; a<dmyObj.startFromDay(); a++, count++) // a represents the index
            dates[a].setText("-");
        for(int b=1; b<=dmyObj.daysInMonth(); b++, count++) // b represents the repetitions
            dates[count].setText(Integer.toString(b));
        for(int c=count; c<42; c++) // c represents the index
            dates[c].setText("-");
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        if(e.getSource() == confirm) {
            dmyObj.month = Objects.requireNonNull(month.getSelectedItem()).toString();
            dmyObj.year = Integer.parseInt(Objects.requireNonNull(year.getSelectedItem()).toString());
            printDates();
        } else if(e.getSource() == prev) {
            dmyObj.adjMonth('p');
            month.setSelectedItem(dmyObj.month);
            year.setSelectedItem(dmyObj.year);
            printDates();
        } else if(e.getSource() == next) {
            dmyObj.adjMonth('n');
            month.setSelectedItem(dmyObj.month);
            year.setSelectedItem(dmyObj.year);
            printDates();
        }
    }
}
