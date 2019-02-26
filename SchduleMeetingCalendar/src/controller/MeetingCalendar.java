package controller;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;


//we use utilDate for information and easier understanding of the date.

/**
 * A class that started off doing a majority of the work, now im trying to get rid of, mainly
 * used to just validate and get the certain date the user has passed, want to make
 * my own class to be more specific for my application.
 * 
 * @author staskatrechko
 * @version 3
 */
public class MeetingCalendar {
	
	/**A calendar that allows us to reassign the date, to get its values.*/
	private Calendar myMeetingCalendarStartDay;
	
	/**The date we are trying to assign the event to.*/
	private Date myDate;
	
	
	/**
	 * This constructor will initialize a calendar with todays date, want to replace
	 * and put into my drawing panel, and can use fire property change events to get
	 * certain values.
	 */
	public MeetingCalendar() {
		 
		myMeetingCalendarStartDay = Calendar.getInstance();// gets current day/time i can create
		//days for appointments such as
		myDate = myMeetingCalendarStartDay.getTime();
		
//		test
//		Calendar day1 = new GregorianCalendar(2018, 11, 1); 
//		day1.set(2019, 1, 11); // use the set to set a date to get its long value to pass
//		Date day4 = new Date(day1.getTimeInMillis());
//		System.out.println(day4.toString());
		
	}
	
	/**
	 *Checks to see if the string the user has provided is a valid format.
	 *however it does not check to see if the month, day or year are valid inputs.
	 *IE. month can be 22 and this method will pass true. 
	 *
	 * @param theString the date represented in a string the user typed out.
	 * @return returns true if the date is of valid format.
	 * @throws ParseException will throw, if the user has not written the date in the
	 * given format.
	 */
	public boolean isValidDate(final String theString) throws ParseException {
		DateFormat temp = new SimpleDateFormat("MM/dd/yyyy");
		Date day1 = temp.parse(theString);
		
		//TODO provide a check style for mm dd and year, the year cant be any less then current
		
		
		myDate = day1;
		//have many ways to determine if the string is valid, different formats.
		//or make drop down box to choose between the dates so i wont need to do this.
		
		
		return true;
	}
	
	/**
	 * Allows us to manipulate the date into a long number for easier trasnfer
	 * of date values to other Dates.
	 * 
	 * @return returns an int value representing the date the event is taking place.
	 */
	public long getDateValue() {
		return myDate.getTime();
	}
	
	/**
	 * allows us to manipulate the date if we so need, but its a until date therefore
	 * it will contain time aswell.
	 * 
	 * @return a copy of our date to keep this class encapsulated.
	 */
	public Date copyOfDate() {
		return (Date) myDate.clone();
	}
	
	
	
	
	//Depricated however its a bit quicker to handle with these method calls.
	//i want to replace these with a class that handles all the year/mm/dd info.
//	public int getYear() {
//		return myDate.getYear();
//	}
//	
//	public int getMonth() {
//		return myDate.getMonth();
//	}
//	
//	public int getDay() {
//		return myDate.getDay();
//	}
//	
	
	
	
	

}
