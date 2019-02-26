package view;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.sql.Date;
import java.text.ParseException;

import javax.swing.AbstractAction;
import javax.swing.Action;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JComponent;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

import controller.CalendarSchduler;
import controller.MeetingCalendar;

/**
 * This is filled with a lot of stuff in each method which is not good practice however
 * its easier to deal with than using property change events, also sets up the frame.
 * 
 * @author stas katrechko
 * @version 3
 */
public class ViewSetup {
	
    /**ToolKit allows us to get screen info. */
    private static final Toolkit KIT = Toolkit.getDefaultToolkit();
    
    /**The Dimensions of the screen for other information. */
    private static final Dimension SCREEN_SIZE = KIT.getScreenSize();
    
    /**The width of the screen. */
    private static final int SCREEN_WIDTH = SCREEN_SIZE.width / 2;

    /**The height of the screen. */
    private static final int SCREEN_HEIGHT = SCREEN_SIZE.height / 2;
    
    /**These will be the associated hours of work time.*/
    private static final  String[] myMeetingHours = {"8:00 AM", "9:00 AM", "10:00 AM", "11:00 AM",
    		"12:00 PM", "1:00 PM", "2:00 PM", "2:00 PM", "3:00 PM"};
    	//could use an enum to deal with this, instead time sensitive, so I did this.
    
    
    /**This will handle the textfield to the north.*/
    private static JTextField myUserInputNorthTextField;
    	//however I could create one instance and use that instance 
    	//for both, but its easier to handle if i seperate them for now.
    
    /**This will handle the users specified date to check the end date of meetings.*/
    private static JTextField myUserInputSouthTextField;
	
	/**Our Master Frame that displays the GUI.*/
    private static JFrame myMasterFrame;
    
    
    /**Our controller that connects the View to the model/Classes specified.*/
    private final CalendarSchduler myMeetingsSchduler;
    
    /**a place holder to keep track of the time.*/
    private int myTimeOfMeeting;
    
   
    
    /**Currently a placeholder, grabs information from the calendar to pass on.*/
    MeetingCalendar s ;//used to validate date since it contains util.DATE instead.
	
	
	
    /**
     * Our constructor for our class that will initialize some GUI Functionality.
     */
    public ViewSetup() {
    	myMeetingsSchduler = new CalendarSchduler();
    	
   	 	s = new MeetingCalendar();//TODO need to incorporate Calendar to handle dates instead.
 	
   	 	setupFrame();
   		setupSouthButtons();
   		setupNorthTextField();
   		setupCenterCalendarPanel();
   		
   		
   		
   		
   		//find a better solution for holiday,
   		//also allow vacations to be added.
   		try {
   			example();//TODO remove only used for example purposes.
   		} catch(final ParseException theException) {
   			//Only doing this to show how the functionality will work.
    		System.out.println("invalid holiday/VacationAdd.");
    		
    	}

    }
    
    //Will be removed and replaced with an approriate handle for vacations/holidays.
    private void example() throws ParseException {
    	
		//S is just a holder going ot make a diferent class to handle this.
		s.isValidDate("12/25/2019");
		
		//validate month year ect. Date is Deprecated but is a placeholder for now.
		Date tempDate = new Date(s.getDateValue());
		
		//adds the meeting to the storing method.
		myMeetingsSchduler.addHolidays(tempDate);
		
		s.isValidDate("12/26/2019");
		tempDate = new Date(s.getDateValue());
		myMeetingsSchduler.addVacation(tempDate);
    	
    }
    
    /**
     * This method will start up our program.
     */
    public void start() {
    	
    	myMasterFrame.pack();
    	myMasterFrame.setVisible(true); // so no lag looks
    }
    
    /**
     * This method will draw and initialize our calendar.
     */
    private void setupCenterCalendarPanel() {
    	final CenterCalendarPanel centerCalendar = new CenterCalendarPanel();
    	myMasterFrame.add(BorderLayout.CENTER, centerCalendar);
    	
    }
    
    
    
    /**
     * Creates our frame to allow us to add and modify the contents inside.
     */
    private void setupFrame() {
    	myMasterFrame = new JFrame("Schedule Meeting Subsystem.");
    	myMasterFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        myMasterFrame.setSize(SCREEN_WIDTH, SCREEN_HEIGHT);
        //divide by 3 to better center the application.
        myMasterFrame.setLocation(SCREEN_SIZE.width / 3, SCREEN_SIZE.height / 3);
    	
    }
    
    
    /**
     * Creates and sets up the southern side of our frame.
     */
    private void setupSouthButtons() { 

    	final JComponent southButtonContainer = new JPanel();
    	southButtonContainer.setLayout(new FlowLayout());

    	//Should be in its own method to break up this method.
    	
    	final JButton getEventCount = setupCheckAmountOfMeetings(); 
    	final JLabel userSouthInputLabel = 
    			new JLabel("Choose a date to see amount of meetings! mm/dd/year");
    	
    	userSouthInputLabel.setLabelFor(myUserInputSouthTextField);
    	
    	southButtonContainer.add(userSouthInputLabel);
    	southButtonContainer.add(myUserInputSouthTextField);
    	southButtonContainer.add(getEventCount);
    	
    	myMasterFrame.add(BorderLayout.SOUTH, southButtonContainer);
    	
    }
    
    //realizing now that i could have just used one method call but create instances
    //of the south and north side.
    
    /**
     * Creates and sets up the north side of our frame, while also
     * creating our drop down box that allows the user to choose and specifiy
     * a certain time for a meeting to take place.
     */
    private void setupNorthTextField() {
    	
    	myUserInputNorthTextField = new JTextField();
    	myUserInputNorthTextField.setPreferredSize(new Dimension(80, 20));
    	
    	final JLabel userInputLabel = 
    			new JLabel("Write a day to schdule an appointment mm/dd/year :");
    	
    	final JComponent northTextFieldContainer = new JPanel();
    	userInputLabel.setLabelFor(myUserInputNorthTextField);
    	
    	
    	northTextFieldContainer.setLayout(new FlowLayout());
    	northTextFieldContainer.add(userInputLabel);
    	northTextFieldContainer.add(myUserInputNorthTextField);
    	
    	final JLabel startingMeetingTime = new JLabel("Start");
    	
    	//Should be an enum instead to handle different hours, and can be changed for 
    	//different employee.
    	final JComboBox<String> timeTableList = new JComboBox<String>(myMeetingHours);
    	startingMeetingTime.setLabelFor(timeTableList);
    	
    	timeTableList.setSelectedIndex(0);//time starts at 8:00am
    	myTimeOfMeeting = 8;//sets the default time to be 8 am if user doesn't select. 
    	

    	final Action tableListActionListener = new AbstractAction() {
			/**
			 * Our Serial ID for actionListerner. 
			 */
			private static final long serialVersionUID = -3880585272227885844L;

			@Override
			public void actionPerformed(final ActionEvent theEvent) {
		
				JComboBox<?> tempComboBox = (JComboBox<?>)theEvent.getSource();
				String timeInStringForm = (String)tempComboBox.getSelectedItem();
				setTimeOfAppointment(timeInStringForm);
				
			}
    	};
    	
    	timeTableList.addActionListener(tableListActionListener);
    	northTextFieldContainer.add(startingMeetingTime);//start lable
    	northTextFieldContainer.add(timeTableList);
    	//TODO add one for end so the user can also change the lengh of meeting/event.
    	
    	
    	final JButton setMeeting = setMeetingButton();
    	
    	northTextFieldContainer.add(setMeeting);

    	myMasterFrame.add(BorderLayout.NORTH, northTextFieldContainer);
    	
    }
    
    
    
    
    /**
     * Sets up our meeting button with its own action and fielding of
     * creating and adding to a certain date.
     * 
     * @return A set Meeting button that will handle the action.
     */
    private JButton setMeetingButton() {
    	
    	final JButton meetingSetupButton = new JButton("Set Meeting");
    	meetingSetupButton.setPreferredSize(new Dimension(150, 30));
    	
    	final Action meetingActionListener = new AbstractAction() {

			/**
			 * Serial code for our action Listerner.
			 */
			private static final long serialVersionUID = 128969208988573992L;

			@Override
			public void actionPerformed(final ActionEvent theEvent) {
				
				try {
					
					//S is just a holder going ot make a diferent class to handle this.
					s.isValidDate(myUserInputNorthTextField.getText());
					
					//validate month year ect. Date is Deprecated but is a placeholder for now.
					Date tempDate = new Date(s.getDateValue());

					//TODO ALSO VALIDATE CHECK TO SEE IF ITS A HOLIDAY!!
					//adds the meeting to the storing method.
					myMeetingsSchduler.addMeeting(tempDate, myTimeOfMeeting);
					
				} catch (final ParseException theException) {
					JOptionPane.showMessageDialog(null, "Improper Date Format", "Improper Date Format",
							JOptionPane.ERROR_MESSAGE);
		
				} //catch its holiday or vacation ect.

			}
    	};
    	
    	meetingSetupButton.addActionListener(meetingActionListener);
    	
    	return meetingSetupButton;
    }
    
    
    /**
     * This method will create the southern side that will also be used
     * to solve the amount of meetings between two specified dates, where
     * the starting date will always be the date the client runs on the PC.
     * 
     * @return A button that count of meetings.
     */
    public JButton setupCheckAmountOfMeetings() {
    	
    	myUserInputSouthTextField = new JTextField();
    	myUserInputSouthTextField.setPreferredSize(new Dimension(80, 20));
    	
    	final JButton getEventCount = new JButton("Get Meeting Count");
    	getEventCount.setPreferredSize(new Dimension(200, 30));
    	
    	final Action getEventCountActionListener = new AbstractAction() {

			/**
			 * Serial code.
			 */
			private static final long serialVersionUID = 3568413091159320109L;

			@Override
			public void actionPerformed(ActionEvent e) {
				try {
					
					s.isValidDate(myUserInputSouthTextField.getText());
	
					
					//validate month year ect. Date is Deprecated but is a placeholder for now.
					Date endDate = new Date(s.getDateValue());

				
					MeetingCalendar tempCalendar = new MeetingCalendar();
					
					//watch out, internally it gets the current date to compare to.
					//so any meetings prior to the day the program Events wont be counted.
					Date startDate = new Date(tempCalendar.getDateValue());
					
									
					int amountMeetings = myMeetingsSchduler.getAmountOfMeetingsToThisDate(startDate,
							endDate);

					JOptionPane.showMessageDialog(null, "Amount of Meetings to end Date "
							+ " is : " + amountMeetings, 
							"Total Meetings to given Date",
							JOptionPane.INFORMATION_MESSAGE);
					//TODO MAKE POPUP THAT SHOWS INFO.
				
			} catch (final ParseException theException) {
				JOptionPane.showMessageDialog(null, "Improper Date Format",
						"Improper Date Format",
						JOptionPane.ERROR_MESSAGE);
	
				}
			}
    	};
    	
    	
    	getEventCount.addActionListener(getEventCountActionListener);
    	return getEventCount;
    }
    
    
    
    
    
    //TODO optimize this what if users want different Times?
    //use an enum instead would be much easier to deal with, and easier to interchange
    
    /**
     * Over its a simple checker to make sure the time is set accordingly to be used
     * later down the road, its a simple and quick fix, however should be implemented in
     * an enum, this was quicker however.
     * 
     * @param theTimeInStringForm
     */
    private void setTimeOfAppointment(final String theTimeInStringForm) {
    	
    	switch (theTimeInStringForm) {
    	case "8:00 AM":
    		myTimeOfMeeting = 8;
    		break;
    	case "9:00 AM":
    		myTimeOfMeeting = 9;
    		break;
    	case "10:00 AM":
    		myTimeOfMeeting = 10;
    		break;
    	case "11:00 AM":
    		myTimeOfMeeting = 11;
    		break;
    	case "12:00 PM":
    		myTimeOfMeeting = 12;
    		break;
    	case "1:00 PM":
    		myTimeOfMeeting = 13;
    		break;
    	case "2:00 PM":
    		myTimeOfMeeting = 14;
    		break;
    	case "3:00 PM":
    		myTimeOfMeeting = 15;
    		break;
    	
    	default: // throw exception or error.
    			
    	}
    	
    	
    	
    }
    
	

}
