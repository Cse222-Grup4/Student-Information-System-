public class Event implements Comparable<Event>{
    private String eventName;
    private String eventDescription;
    private Date eventDate;
    private boolean situation;
    private boolean waitSituation;
    
    /**
     * I record the information about for event details.
     * @param eventName
     * @param eventDescription
     * @param eventDate
     */
    public Event(String eventName, String eventDescription, Date eventDate)
    {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
    }

    /**
     * I record the information about for event details.
     * @param eventName
     * @param eventDescription
     * @param eventDate
     * @param situation
     * @param waitSituation
     */
    public Event(String eventName,String eventDescription, Date eventDate,boolean situation,boolean waitSituation)
    {
        this.eventName = eventName;
        this.eventDescription = eventDescription;
        this.eventDate = eventDate;
        this.situation = situation;
        this.waitSituation = waitSituation;
    }   


    /**
     * Determines whether the event added by the student should be canceled or not.
     * @param waitSituation
     */
    public void setWaitSituation(boolean waitSituation) {
        this.waitSituation = waitSituation;
    }
    /**
     * Getter function for wait situation.
     * @return
     */
    public boolean getwaitSituation() {
        return waitSituation;
    }
    /**
     * Setter function for situation.
     * @param situation
     */
    public void setSituation(boolean situation) {
        this.situation = situation;
    }
    /**
     * Getter function for situation.
     * @return
     */
    public boolean getSituation(){
        return situation;
    }

    /**
     * return the event name.
     * @return
     */
    public String getEventName()
    {
        return eventName;
    }
    /**
     * Setter function for event name.
     * @param eventName
     */
    public void setEventName(String eventName)
    {
        this.eventName = eventName;
    }
    /**
     * Getter function for event description.
     * @return
     */
    public String getEventDescription()
    {
        return eventDescription;
    }

    /**
     * It defines the event date.
     * @param eventDescription
     */
    public void setEventDescription(String eventDescription)
    {
        this.eventDescription = eventDescription;
    }

    /**
     * It defines the event date.
     * @return
     */
    public Date getEventDate()
    {
        return eventDate;
    }

    public void setEventDate(Date eventDate)
    {
        this.eventDate = eventDate;
    }

    /**
     * toStirng for event specify
     */
    public String toString()
    {
        return  "Event Date: " + getEventDate().toString() +
                " Event Name: " + getEventName() +
                " Event Description: " + getEventDescription() + 
                "Event Situation: " + getSituation() +
                "Event Wait Situation" + getwaitSituation();
    }
	@Override
	/**
	 ** Compare to function for Event class according to dates.
	 *@return a negative integer, zero, or a positive integer as this object is less than, equal to, or greater than the specified object.
	 */
	public int compareTo(Event o) {
        if(getEventDate().getYear() < o.getEventDate().getYear())
            return -1;
        else if(getEventDate().getYear() > o.getEventDate().getYear())
            return 1;
        if(getEventDate().getMonth() < o.getEventDate().getMonth())
            return -1;
        else if(getEventDate().getMonth() > o.getEventDate().getMonth())
            return 1;
        if(getEventDate().getDay() < o.getEventDate().getDay())
            return -1;
        else if(getEventDate().getDay() > o.getEventDate().getDay())
            return 1;

        return 0;		
	}
}
