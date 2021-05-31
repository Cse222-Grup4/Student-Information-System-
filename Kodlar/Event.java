public class Event {
    private String eventName;
    private String eventDescription;
    private Date eventDate;

    public Event(String eventName, String eventDescription, Date eventDate)
    {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
    }

    public String getEventName()
    {
        return eventName;
    }

    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    public String getEventDescription()
    {
        return eventDescription;
    }

    public void setEventDescription(String eventDescription)
    {
        this.eventDescription = eventDescription;
    }

    public Date getEventDate()
    {
        return eventDate;
    }

    public void setEventDate(Date eventDate)
    {
        this.eventDate = eventDate;
    }

    public String toString()
    {
        return  "Event Date: " + getEventDate().toString() +
                " Event Name: " + getEventName() +
                " Event Description: " + getEventDescription() + "\n";
    }
}

