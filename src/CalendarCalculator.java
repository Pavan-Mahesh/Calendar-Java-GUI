import java.util.HashMap;

public class CalendarCalculator {
    String month;
    int year;
    CalendarCalculator(String month, int year) {
        this.month = month;
        this.year = year;
    }

    private boolean leapYear() {
        if(year%100 == 0 && year%400 == 0)
            return true;
        else
            return year % 4 == 0;
    }

    private int yearCode() {
        return (year < 2000) ? 0 : 6;
    }

    public int daysInMonth() {
        if(month.equals("FEB"))
            return leapYear() ? 29 : 28;
        boolean days30 = (month.equals("APR")) || (month.equals("JUN")) ||
                (month.equals("SEP")) || (month.equals("NOV"));
        return days30 ? 30 : 31;
    }

    public void adjMonth(char decision) {
        String[] months =
                {"JAN", "FEB", "MAR", "APR", "MAY", "JUN", "JUL", "AUG", "SEP", "OCT", "NOV", "DEC"};
        int i;
        for(i=0; i<12; i++)
            if(this.month.equals(months[i]))
                break;
        if(decision == 'n' && ++i == 12) {
            i = 0;
            year++;
        } else if(decision == 'p' && --i == -1) {
            i = 11;
            year--;
        }
        this.month = months[i];
    }

    public int startFromDay() {
        HashMap<String, Integer> monthCode = new HashMap<>();
        monthCode.put("JAN", 0); monthCode.put("FEB", 3); monthCode.put("MAR", 3);
        monthCode.put("APR", 6); monthCode.put("MAY", 1); monthCode.put("JUN", 4);
        monthCode.put("JUL", 6); monthCode.put("AUG", 2); monthCode.put("SEP", 5);
        monthCode.put("OCT", 0); monthCode.put("NOV", 3); monthCode.put("DEC", 5);

        int sum = 1 + monthCode.get(month) + yearCode() + year%100 + (year%100)/4;
        int weekCode = sum % 7;
        if(((month.equals("JAN") || month.equals("FEB"))) && leapYear())
            weekCode -= 1;
        return weekCode;
    }
}
