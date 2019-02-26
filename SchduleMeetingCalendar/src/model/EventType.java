package model;

import java.sql.Date;

/**
 * The Interface allows the developer to create subclasses with the abstract class
 * thats provided. It allows the creation of multiple types of "Events" such as
 * Meetings, Holidays, Vacations, to mandatory meetings, Party Events ect...
 * 
 * @author Stas Katrechko
 * @Version 3
 */
public interface EventType {
	
	//Event can be a holiday, a meeting, a group meeting, a vacation or party,
	//or mandatory meetings, ect.. abstractly well call it an "event".
	
	
	/**
	 * If we have a meeting a certain day, this method should allow 
	 * us to add to to fill up that day with multiple meetings.
	 * 
	 * @param theStartTime the beginning time of the event.
	 * @param theEndTime the end time of the event.
	 */
	public void addToCurrentEventDay(int theStartTime , int theEndTime);
		
	/**
	 * Gets amounts of "Events" for the day which can include meetings for the
	 * day.
	 * @return the amount of scheduled "Events" we have for that day.
	 */
	public int getAmountOfEventsForTheDay();
	
	/**
	 * gets the date the "Event" is taking place.
	 * 
	 * @return the SQL date, IE -Year -MM  -DD no time is invovled.
	 */
	public Date getDate();
	
	
	
	
	//These will be implemented as something else other than an int.
	
	/**
	 *  this allows us to get the starting time of the "Event" 
	 *  currently an int representing a certain time IE 8 == 8am  15 == 4pm.
	 *  
	 * @return the representation of what time the "Event" is taking place.
	 */
	public int getEventStartTime();
	
	
	/**
	 * This will get the "Events" end time which can be a maximum of the work-day time.
	 * 
	 * @return an int that represents a certain time on our work-day.
	 */
	public int getEventEndTime();

	
	
	
}
