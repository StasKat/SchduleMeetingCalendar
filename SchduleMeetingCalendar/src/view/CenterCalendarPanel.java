package view;

import java.awt.Color;
import java.awt.Dimension;

import javax.swing.JLabel;
import javax.swing.JPanel;

import controller.MeetingCalendar;

//we can implment more features IE ignoring weekends+Holidays+vacations and so forth.

/**
 * Will draw the calendar that the user can interact and see certain days, 
 * we will also color coat certain days, IE Green for holidays, gray for weekends ect.
 *
 * 
 * @author stas katrechko
 * @version 3
 */
public class CenterCalendarPanel extends JPanel {
	
	MeetingCalendar myDisplayedCalendar;
	
	/**
	 * unfinished calendar panel.
	 */
	public CenterCalendarPanel(){
		//TODO DRAW AND INITLIZE OUR CALANDER TO BE USED TO DRAW THE YEAR.
		myDisplayedCalendar = new MeetingCalendar(); //TODO
		super.setPreferredSize(new Dimension(300,300));
		
		super.add(new JLabel("Place Holder For the Calendar."));
		super.setBackground(Color.LIGHT_GRAY);
	}
	
	
	//DRAW IT and be able to update need to center ect, also color coat each
	//event Green is holiday, light gray weekend, blue meetings ect. 
	
	

}
