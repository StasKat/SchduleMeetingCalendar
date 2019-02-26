package model;

import java.sql.Date;

import javax.swing.JOptionPane;

//will allow the boss/developer to create new holidays, however i need to store.

/**
 * A class that will outline what a holiday will entail, we want to fill every position
 * of a date so that the user cannot fill it, we will send a warning message via GUI
 * to indicate that day is a holiday.
 * 
 * 
 * @author stas katrechko
 * @version 3
 */
public class Holiday extends AbstractEventType {

	//private String myHolidayName;implement at later time will also keep track of meeting type
	

	
	
	/**
	 * We can later implement different types of holidays for use, but for now
	 * we fill up all the time slots for our client so that they cannot set a meeting 
	 * on that day.
	 * 
	 * @param theEventDate the date the event will take place.
	 * @param theEventStartTime the start time for the event.
	 * @param theEventEndTime the end time for the event.
	 */
	public Holiday(Date theEventDate, final int theEventStartTime, final int theEventEndTime) {
		// we are filling up all the slots within the workday so we cant accidently add to the event.
		super(theEventDate, AbstractEventType.WORKDAY_HOURS, 
				AbstractEventType.WORKDAY_HOURS * 2);
	}

	/**
	 * I want this class to fire a property change event to signify that
	 * the user was trying to add to a holiday, which cannot be done.
	 */
	@Override
	public void addToCurrentEventDay(final int theStartTime, final int theEndTime) {
		
		JOptionPane.showMessageDialog(null, "This Date is a holiday!",
				"Holiday!",
				JOptionPane.ERROR_MESSAGE);
		
		//TODO
		//cannot add to holiday, fire property change event ->You cant set a meeting on this day.
		//we can create a Event time by overriding certain times in the future. half days ect.
	}

	
	
	
	
	
	@Override
	public String toString() {
		return "It's a holiday today!"; //will be implmented in future + Holiday name.
	}

}
