package controller;

import java.sql.Date;
import java.util.HashMap;
import java.util.Map;

import javax.swing.JOptionPane;

import exceptions.OverLappingTimesException;
import model.EventType;
import model.Holiday;
import model.Meeting;
import model.Vacation;

//WILL BE USED TO INTERACT WITH MEETING HOLIDAY CLASSES WILL BE THE MODEL/BRIDGE
//of our view and controller. 

public class CalendarSchduler {
	

	/**
	 * The Date will only contain M/D/Y and will be a place holder for an event type IE holiday,
	 * Vacation,Meeting and so forth.
	 */
	private final Map<Date, EventType> myDaysFilled;
	
	/**Every Single meeting that our client has schedulded, very general.*/
	private int totalMeetingCount;
	
	
	//TODO find a better way to grab/store information/event types.
	/**
	 * Constructor that initalizes our "Storing" method, need to find a better way to store
	 * and get from.
	 */
	public CalendarSchduler() {
		totalMeetingCount = 0;
		myDaysFilled = new HashMap<Date, EventType>();
		
		//Add holidays 
		
	}

	

	/**
	 * All 3 addEvent types are a placeHolder for now, I want to find a good way
	 * to store and upload these events from something, other than a text file, so some
	 * research will be needed. 
	 * 
	 * @param theDate the date the event will take place.
	 */
	public void addVacation(final Date theDate) {
		
		try {
			EventType newEventDate = new Vacation(theDate, 0);//TODO
			//currently i've not implemented my GUI to handle create the the type of Event.
			myDaysFilled.put(theDate, newEventDate);
			
			
		} catch (OverLappingTimesException theException) {
			System.out.print("Failed to place holiday");
		}
		
	}
	
	
	//Currently a temp holder to handle creating the holidays, will allow GUI and web Archive
	//to create every holiday date or possible dates
	public void addHolidays(final Date theDate) {
		
		try {
			EventType newEventDate = new Holiday(theDate, 0, 0);//TODO
			//currently i've not implemented my GUI to handle create the the type of Event.
			myDaysFilled.put(theDate, newEventDate);
			
			
		} catch (OverLappingTimesException theException) {
			System.out.print("Failed to place holiday");
		}
		
	}


	/**
	 * Currently a place holder i want to reduce this redundant code with my GUI,
	 * but for now we are using this add meeting to instaniate a new event type that
	 * is a meeting.
	 * 
	 * @param theDate the date's event that will be taken place.
	 * @param theStartingTime the starting time the event will take place.
	 */
	public void addMeeting(final Date theDate, final int theStartingTime) {		
		
		//if a meeting on this day exists we will see if we can add another to this.
		if (myDaysFilled.containsKey(theDate)) { 
			addToExistingMeeting(theDate, theStartingTime);
			
		} else {
			totalMeetingCount++;
			EventType newEventDate = new Meeting(theDate, theStartingTime);//TODO
			//currently i've not implemented my GUI to handle create the the type of Event.
			myDaysFilled.put(theDate, newEventDate);
			
		}
		
		
	}
	
	
	
	
	
	/**
	 * If there is an event already in place of a certain day, we check to see if
	 * we're allowed to fill a certain time slot with a meeting, if not we throw 
	 * an overlapping exception which shows that, the time slot is filled.
	 * 
	 * @param theDate the date thats going to be used to checked to see if we can
	 * fill its open time slot. 
	 * @param theStartingTime the time of which we are trying to fill the slot, IE 
	 * 8 == 8am and so forth.
	 */
	private void addToExistingMeeting(final Date theDate, final int theStartingTime) {
		try {

			//if the time isnt already booked, we fill that time with another meeting.
			myDaysFilled.get(theDate).addToCurrentEventDay(theStartingTime,theStartingTime + 1); //TODO DEFAULT OF 1 hour long meetings.
			totalMeetingCount++; //after the exception
			
			
		} catch (OverLappingTimesException theException) {
			
			
			//System.out.println("temp println for holder.");
			//TODO
			//should fire a Property change event so the classes aren't interwined,
			//however its quicker for me to just twine them to meet the 6 hour coding deadline.
			JOptionPane.showMessageDialog(null, "Set Fill a time thats already filled.",
					"OverLapping Times",
					JOptionPane.ERROR_MESSAGE);
		}
	}
	
	
	/**
	 * Allows us to fill a certain event's workday and count the number of events
	 * if its of type meeting, otherwise we ignore the event type. 
	 * 
	 * @param theStartDate the starting date from the users first launch, grabs local
	 * information.
	 * @param theEndDate the end time of which the user has specified Via text.
	 * @return returns the amount of meetings to a certain date.
	 */
	public int getAmountOfMeetingsToThisDate(final Date theStartDate, final Date theEndDate) {
		int amountOfMeetings = 0;
		//important to note both are SQL dates in both classes.
		for (Date aDay : myDaysFilled.keySet()) {
			EventType theEvent = myDaysFilled.get(aDay);
			
			//if the day we are comparing to find all the meetings to a certain locaiton
			//is not less then the start day, or greater then the end date we account for it.
			if (aDay.compareTo(theStartDate) != -1 && aDay.compareTo(theEndDate) != 1) {
				
				//TODO check to see the instance if the event is of instance Meeting add.
				//or for holiday and vacation dont increment the event.
				if (theEvent instanceof Meeting) {
					amountOfMeetings += myDaysFilled.get(aDay).getAmountOfEventsForTheDay();
				}
			}
			//else do nothing we care only about days that fit our criteria.
		}		
		return amountOfMeetings;
	}
	
	
	
	//GETTERS
	/**
	 * gets the total meeting/event count, no specifications.Will implement a way to
	 * check vacation days/holiday days and so forth.
	 * 
	 * @return amount of events throughout the project that was added, doesn't matter 
	 * what they are, can be used to see how busy.
	 */
	public int getTotalMeetingCount() {
		return totalMeetingCount;
	}
	

}
