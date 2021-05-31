import java.util.Comparator;

public class Date {
    private int day;
    private int month;
    private int year;

    public Date()
    {
        this(1, 1, 1970);
    }

    public Date(int day, int month, int year)
    {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    public int getDay()
    {
        return day;
    }

    public void setDay(int day)
    {
        this.day = day;
    }

    public int getMonth()
    {
        return month;
    }

    public void setMonth(int month)
    {
        this.month = month;
    }

    public int getYear()
    {
        return year;
    }

    public void setYear(int year)
    {
        this.year = year;
    }

    public String toString()
    {
        return String.format("%d/%d/%d%n", day, month, year);
    }

    public static class EventComparator implements Comparator<Date> {

        @Override
        public int compare(Date d1, Date d2) {
            if(d1.getYear() < d2.getYear())
                return -1;
            else if(d1.getYear() > d2.getYear())
                return 1;
            if(d1.getMonth() < d2.getMonth())
                return -1;
            else if(d1.getMonth() > d2.getMonth())
                return 1;
            if(d1.getDay() < d2.getDay())
                return -1;
            else if(d1.getDay() > d2.getDay())
                return 1;

            return 0;
        }
    }

}



