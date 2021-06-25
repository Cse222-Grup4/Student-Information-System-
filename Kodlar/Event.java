/**
 * Event class for represent the events of school.
 */
public class Event {
    /**
     * Name of event.
     */
    private String eventName;

    /**
     * Description of event.
     */
    private String eventDescription;

    /**
     * Date of event.
     */
    private Date eventDate;

    /**
     * Constructor with 3 parameters.
     * @param eventName Name of event.
     * @param eventDescription Description of event.
     * @param eventDate Date of event.
     */
    public Event(String eventName, String eventDescription, Date eventDate)
    {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
    }

    /**
     * Get name of event.
     * @return Name of event.
     */
    public String getEventName()
    {
        return eventName;
    }

    /**
     * Set name of event.
     * @param eventName Name of event
     */
    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }

    /**
     * Get description of event.
     * @return Description of event.
     */
    public String getEventDescription()
    {
        return eventDescription;
    }

    /**
     * Set description of event.
     * @param eventDescription Description of event.
     */
    public void setEventDescription(String eventDescription)
    {
        this.eventDescription = eventDescription;
    }

    /**
     * Get date of event.
     * @return Date of event.
     */
    public Date getEventDate()
    {
        return eventDate;
    }

    /**
     * Set date of event.
     * @param eventDate Date of event.
     */
    public void setEventDate(Date eventDate)
    {
        this.eventDate = eventDate;
    }

    /**
     * toString method of Event class.
     * @return String of Event.
     */
    public String toString()
    {
        return  "Event Date: " + getEventDate().toString() +
                " Event Name: " + getEventName() +
                " Event Description: " + getEventDescription() + "\n";
    }
}

