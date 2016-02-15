/*Complete this class to implement a fully functional sparse table.  Read the comments to determine what each aspect of the class is supposed to do.
You must add any additional features (methods, references) which may aid you in your
task BUT you are not allowed to remove or change the names or properties of any of the features you where given.

Importing Java's built in data structures will result in a mark of 0.*/

public class Calendar
{
	public Calendar()
	{
		/*You may implement this constructor to suit your needs, or you may add additional constructors.  This is the constructor which will be used for marking*/ 
		days = new Appointment[31];
		months = new Appointment[12];
	}
	
	/*Insertion*/
	public void addAppointment(String month, int day, String description, int duration)
	{
		/*Insert an appointment at the given month and day combination.  Intialize the appointment with the remainder of the parameters.
		
		Duplicate appointments are allowed.*/
		Appointment newAppointment  = new Appointment(description,duration);
		Appointment monthPointer = getMonthAppointment(month);
		Appointment dayPointer = getDayAppointment(day);
		
		
		if (dayPointer == null)
			dayPointer = newAppointment;
		
		if (monthPointer == null)
			monthPointer = newAppointment;
	}
	
	/*Deletion methods*/
	public Appointment deleteAppointment(String month, int day)
	{
		/*Delete the first appointment at the given month and day combination and return the deleted appointment.
		If no such appointment exists, return null.*/
				
		return null;
	}
	
	public Appointment deleteAppointment(String month, int day, String description)
	{
		/*Delete the first appointment at the given month and day combination  with the description and return the deleted appointment.
		If no such appointment exists, return null.*/
				
		return null;
	}
	
	/*Clearing Methods*/
	public void clearMyMonth(String month)
	{
		/*All appointements for the given month should be deleted.
		If the month has no appointments, simply do nothing.*/
		
	}
	
	public void clearMyDays(int day)
	{
		/*All appointements for the given day should be deleted.
		If the day has no appointments, simply do nothing.*/
		 months = null;
	}
	
	public void clearMyYear()
	{
		/*Delete all appointments from the calendar.*/
		for (int x = 0; x < 31; x++)
			days[x] = null;
		for (int x = 0; x < 12; x++)
			months[x] = null;
	}
	
	
	/*Query methods*/
	public Appointment getAppointment(String month, int day)
	{
		/*Return the head appointment of the month and day combination.  If no such appointment exists, return null*/
		
		Appointment monthPointer  = getMonthAppointment(month);
		Appointment dayPointer  = getDayAppointment(day);
		Appointment appointmentPointer; 
		while (monthPointer != dayPointer)
			monthPointer = monthPointer.right;
			while (monthPointer != dayPointer || dayPointer != null)
				dayPointer = dayPointer.down;
			
		if (dayPointer != null)
			System.out.println(dayPointer.getDescription());
		return dayPointer;
	}
	
	public Appointment getMonthAppointment(String month)
	{
		/*Return the head appointment for the month passed as a parameter.
		If no such appointment exists, return null*/
		Appointment monthAppointment = null;
		
		switch (month)
		{
			case "January": monthAppointment = months[0];
			break;
			case "February": monthAppointment = months[1];
			break;
			case "March": monthAppointment = months[2];
			break;
			case "April": monthAppointment = months[3];
			break;
			case "May": monthAppointment = months[4];
			break;
			case "June": monthAppointment = months[5];
			break;
			case "July": monthAppointment = months[6];
			break;
			case "August": monthAppointment = months[7];
			break;
			case "September": monthAppointment = months[8];
			break;
			case "October": monthAppointment = months[9];
			break;
			case "November": monthAppointment = months[10];
			break;
			case "December": monthAppointment = months[11];
			break;
		}
		return monthAppointment;
	}
	
	public Appointment getDayAppointment(int day)
	{
		if (day >= 0 && day < 32) 
			return days[day];
		else 
			return null;
	}
	
	Appointment [] days;
	Appointment [] months;
	
}