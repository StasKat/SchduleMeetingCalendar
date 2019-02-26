package exceptions;

/**
 * created my own exception so that we know exactly how to handle
 * the situation of adding to an already filled time slot.
 * 
 * @author stask atrechko
 * @version 3
 */
public class OverLappingTimesException extends RuntimeException {

	/**
	 * Serial code for our exception.
	 */
	private static final long serialVersionUID = 5553062665553365724L;
	
	/**
	 * A new type of exception to handle overlapping hours when a meeting or an event
	 * is already present on those hours.
	 * @param theString the string representing the exception.
	 */
	public OverLappingTimesException(final String theString){
		super("MeetingTimes Overlapping. " + theString);
	}
	

}
