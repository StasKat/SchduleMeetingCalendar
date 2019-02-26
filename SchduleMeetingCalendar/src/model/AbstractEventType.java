package model;

import java.sql.Date;

import exceptions.OverLappingTimesException;

/**
 * The abstract instantiate of our objects will have these types of methods
 * and fields such as they are subclasses. Instances of meeting/holiday/vacation
 * will implement all these methods are will be changed in the future to help better
 * reduce code.
 * 
 * @author stas katrechko
 * @version 3
 */
public abstract class AbstractEventType implements EventType {
	
	/**Specified amount of workHours can be changed for different employees.*/
	protected static final int WORKDAY_HOURS = 8;

	/**Our event hours that can be filled with.*/
	protected boolean[] myHoursForMeetings; 
		// instead of boolean we make evenType array so we can fill
		//certain hours with our eventType, but this is a place holder for now.
	
	/**The starting time of the event.*/
	private int myEventStartTime;
	
	/**The ending time of the event.*/
	private int myEventEndTime;
	
	/**Will keep track of all the events throughout the day.*/
	private int myAmountOfEventsForTheDay;
	
	/**The Date associated with the event.*/
	private Date myEventDate; //class with month and date/year could use calander to be more precise. 
	
	
	
	
	
	/**
	 * Should not be called, created a constructor so that java wouldn't.
	 */
	private AbstractEventType() {
		throw new IllegalStateException("Abstract constructor was called.");
	}
	
	
	/***
	 * Allows us to initialize the instantiated object's fields, while also setting
	 * the default Event type to last 1 hour long.
	 * 
	 * @param theMeetingDate the date the event is taking place on.
	 * @param theMeetingStartTime the starting time the event will be.
	 */
	AbstractEventType(final Date theMeetingDate, final int theMeetingStartTime) {
		this.myHoursForMeetings = new boolean[WORKDAY_HOURS]; 
		this.myAmountOfEventsForTheDay = 0;
		
		this.myEventStartTime = theMeetingStartTime;
		this.myEventEndTime = theMeetingStartTime + 1;
		
		setEventTimes(theMeetingStartTime, theMeetingStartTime + 1);//default 1 hour
		
	}
	
	

	/**
	 * This will allow our subclasses to have their own hours/times so if events
	 * last longer than one hour, we set our fields and times.
	 * 
	 * @param theEventDate the events date on which its taking place.
	 * @param theEventStartTime the events starting time, IE 8 == 8 am.
	 * @param theEventEndTime the events ending time represented by an int 9 == 9am.
	 */
	AbstractEventType(final Date theEventDate, final int theEventStartTime,
			final int theEventEndTime) {
		this.myHoursForMeetings = new boolean[WORKDAY_HOURS]; 
		this.myAmountOfEventsForTheDay = 0;
		//TODO CHECK TO SEE IF ENDTIME IS VALID INPUT. for future new event types.
		this.myEventStartTime = theEventStartTime;
		this.myEventEndTime = theEventEndTime;
		this.myEventDate = theEventDate;
		
		setEventTimes(theEventStartTime, theEventEndTime);
	}
	
	/**
	 * allows us to add to the date that has an associated event type,
	 * we can fill it's time.
	 */
	public void addToCurrentEventDay(final int theStartTime, final int theEndTime) {

		//TODO CONFIRM THE TIMES are valid inputs,
			//IE if user chooses end time check the times in between.
		
		setEventTimes(theStartTime, theEndTime);

	}
	
	/**
	 * Will set the time on which the event will be taking place, while also
	 * making sure that time isn't filled with another event/time.
	 * 
	 * @param theStartTime the starting time of the event.
	 * @param theEndTime the ending time of the event.
	 */
	private void setEventTimes(final int theStartTime, final int theEndTime) {
		
		//increment meeting for the day here.
		//restrict the user to choose from 8 am through 5 pm therefore 8 possibilities.
		for (int i = theStartTime - WORKDAY_HOURS; i < theEndTime - WORKDAY_HOURS; i=i+2 ) {
			//we check to see if its already initialized meaning a meeting was schduled in that slot.
			if (myHoursForMeetings[i] == true) {
				throw new OverLappingTimesException(" In our Abstract.Class");
			} else {
			//we set our slot to be filled with a meeting
			//IE.for an hour long. IE 8AM to 9AM
			myHoursForMeetings[i] = true;
			myHoursForMeetings[i+1] = true;
			myAmountOfEventsForTheDay++; //amount of meetings for the day.
			}
		}
		
	}
	
	
	//GETTERS
	

	public int getAmountOfEventsForTheDay() {
		return myAmountOfEventsForTheDay;
	}
	
	
	//SQL Date YEAR MM DD.
	public Date getDate() {
		return (Date) myEventDate.clone(); //copy to keep it encapsulated
	}
	
	
	
	//will be used for test cases.
	
	public int getEventStartTime() {
		return myEventStartTime;
	}
	
	public int getEventEndTime() {
		return myEventEndTime;
	}
	
	
	
	
	

}
