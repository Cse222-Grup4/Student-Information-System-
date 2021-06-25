import java.util.Comparator;

/**
 * Date class for respresent the date of events.
 */
public class Date {
    /**
     * Day of date.
     */
    private int day;

    /**
     * Month of date.
     */
    private int month;

    /**
     * Year of date.
     */
    private int year;

    /**
     * Default ctor.
     */
    public Date()
    {
        this(1, 1, 1970);
    }

    /**
     * Constructor with 3 parameters.
     * @param day Day of date.
     * @param month Month of date.
     * @param year Year of date.
     */
    public Date(int day, int month, int year)
    {
        this.day = day;
        this.month = month;
        this.year = year;
    }

    /**
     * Get day of date.
     * @return Day of date.
     */
    public int getDay()
    {
        return day;
    }

    /**
     * Set day of date.
     * @param day Day of date.
     */
    public void setDay(int day)
    {
        this.day = day;
    }

    /**
     * Get month of date.
     * @return Month of date.
     */
    public int getMonth()
    {
        return month;
    }

    /**
     * Set month of date.
     * @param month Month of date.
     */
    public void setMonth(int month)
    {
        this.month = month;
    }

    /**
     * Get year of date.
     * @return Year of date.
     */
    public int getYear()
    {
        return year;
    }

    /**
     * Set year of date.
     * @param year Year of date.
     */
    public void setYear(int year)
    {
        this.year = year;
    }

    /**
     * toString method of Date.
     * @return String of date.
     */
    public String toString()
    {
        return String.format("%d/%d/%d%n", day, month, year);
    }

    /**
     * Inner class for Date class.
     */
    public static class EventComparator implements Comparator<Date> {

        /**
         * Compare method with 2 date objects.
         * @param d1 First date object.
         * @param d2 Second date object.
         * @return Compare result.
         */
        @Override
        public int compare(Date d1, Date d2) {
            if (d1.getYear() < d2.getYear())
                return -1;
            else if (d1.getYear() > d2.getYear())
                return 1;
            if (d1.getMonth() < d2.getMonth())
                return -1;
            else if (d1.getMonth() > d2.getMonth())
                return 1;
            if (d1.getDay() < d2.getDay())
                return -1;
            else if (d1.getDay() > d2.getDay())
                return 1;

            return 0;
        }
    }
}
