package model;

import java.sql.Date;

import javax.swing.JOptionPane;

/**
 * Subclass of holiday or I can do vice versa depending on the future structure,
 * however I want the client to set their vacation days.
 * 
 * @author stas katrechko
 * @version 3
 */
public class Vacation extends Holiday {

	/**
	 * Constructor that will set the date/time regardless of the input, will fill up the day.
	 * 
	 * @param theMeetingDate the date the event will take place.
	 * @param theMeetingStartTime the meeting start time.
	 */
	public Vacation(Date theMeetingDate, int theMeetingStartTime) {
		super(theMeetingDate, 0,0);//doesnt matter what values i put it will set the date.
	}
	
	
	/**
	 * I want this class to fire a property change event to signify that
	 * the user was trying to add to a holiday, which cannot be done.
	 */
	@Override
	public void addToCurrentEventDay(final int theStartTime, final int theEndTime) {
		
		//Badcode style I'm intertwining two classes, that shouldn't be.
		JOptionPane.showMessageDialog(null, "This day is a vacation!",
				"Vacation!",
				JOptionPane.ERROR_MESSAGE);
		
	}
	
	
	

}
