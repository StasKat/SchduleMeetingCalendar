package model;

import java.sql.Date;
import java.time.LocalTime;
import exceptions.OverLappingTimesException;

//Date from SQL package only represent only Date part. to be precise Date contains year, month and day
//date from util contains time as well. 
//therfore i should approach this prolbem using SQL date instead. we can ignore time.


/**
 * Allows the developer to create instances of meetings to fill certain locations/times
 * throughout a time frame. 
 * 
 * @author stas katrechko
 * @version 3
 */
public class Meeting extends AbstractEventType {
	
	/**
	 * Default meeting type of 1 hour long, it will not worry about the end time.
	 * 
	 * @param theEventDate the date the event will be taking place, MM/DD/YEAR.
	 * @param TheEventStartTime the event start time using an int to represent
	 * a certain time IE 8 = 8am.
	 */
	public Meeting(Date theEventDate, int TheEventStartTime){
		super(theEventDate, TheEventStartTime, TheEventStartTime + 1);
	}

	/**
	 * 
	 * @param theEventDate the event date time, mm/dd/year.
	 * @param theEventStartTime the event start time Based on int 8 = 8am and so forth.
	 * @param theEventEndTime the event end time based on int 9 = 9 am and so forth.
	 */
	public Meeting(Date theEventDate, int theEventStartTime, int theEventEndTime) {
		super(theEventDate, theEventStartTime, theEventEndTime);
		
	}

	/**
	 * Fills certain time frames throughout the workday for the meeting.
	 * 
	 * @param theTimeEvent the event start time thats represened on a scale from
	 * 8-16 for a work week 8 == 8 am.
	 */
	public void addToCurrentEventDay(final int theTimeEvent) {
		super.addToCurrentEventDay(theTimeEvent, theTimeEvent + 1);
	}

	
	
	@Override
	public String toString() {
		StringBuilder meetingSchedule = new StringBuilder();
		LocalTime timeTemp = LocalTime.now();
		meetingSchedule.append('\n');
		
		for (int i = 0; i < AbstractEventType.WORKDAY_HOURS ; i++ ) {
			//line below converts to an AM PM system/string. 
			meetingSchedule.append(timeTemp.withHour(i + AbstractEventType.WORKDAY_HOURS).getHour());
			//we check to see values to be true, as a slot for a meeting to take place.
			if (super.myHoursForMeetings[i] == true) {
				meetingSchedule.append("In Meeting"); // TODO can create meeting/holi/vac type.
				meetingSchedule.append('\n');
			} else {
				meetingSchedule.append("Empty");
				meetingSchedule.append('\n');
			}
		}

		return meetingSchedule.toString();
	}

}
