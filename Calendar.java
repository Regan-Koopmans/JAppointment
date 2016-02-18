/*Complete this class to implement a fully functional sparse table.  Read the comments to determine what each aspect of the class is supposed to do.
You must add any additional features (methods, references) which may aid you in your
task BUT you are not allowed to remove or change the names or properties of any of the features you where given.

Importing Java's built in data structures will result in a mark of 0.*/


public class Calendar
{
	public Calendar()
	{
		/*You may implement this constructor to suit your needs, or you may add additional constructors.  This is the constructor which will be used for marking*/ 
		days = new Appointment[30];
		months = new Appointment[12];
	}
	
	/*Insertion*/
	public void addAppointment(String month, int day, String description, int duration)
	{
		/*Insert an appointment at the given month and day combination.  Intialize the appointment with the remainder of the parameters.
		
		Duplicate appointments are allowed.*/
		Appointment newAppointment = new Appointment(description,duration);
		newAppointment.day = day;
		newAppointment.month = month;
		System.out.println(day + " " + month + " " + description);
		Appointment monthPointer = getMonthAppointment(month);		

		if (monthPointer == null)
		{
			months[convertToMonthEnum(month)] = newAppointment;
		}
		else if (monthPointer.day > day) 
		{
			newAppointment.right = monthPointer;
			months[convertToMonthEnum(month)] = newAppointment;
		}
		else
		{

			while (monthPointer.right != null && monthPointer.right.day < day && monthPointer.day != day)
			{
				monthPointer = monthPointer.right;
			}
		
			if (monthPointer.day == day)
			{
				while (monthPointer.back != null)
					monthPointer = monthPointer.back;

				monthPointer.back = newAppointment;
			}
			else
			{
				if (monthPointer.right == null)
				{
					//System.out.println("APPEND INSERT");
					monthPointer.right = newAppointment;
				}
				else
				{
					//System.out.println("MIDDLE INSERT");
					Appointment temp = monthPointer.right;
					monthPointer.right = newAppointment;
					newAppointment.right = temp;
				}
			}
		}
		
		//Adjust day list.

		Appointment dayPointer = days[day];
		if (days[day] == null)
		{
			days[day] = newAppointment;
			return;
		}

		if (newAppointment.day < dayPointer.day)
		{
			newAppointment.down = dayPointer;
			days[day] = newAppointment;
			return;
		}
		System.out.println(convertToMonthEnum(dayPointer.month));
		while (dayPointer.down != null && 
convertToMonthEnum(dayPointer.down.month) < convertToMonthEnum(month))
		{
			dayPointer = dayPointer.right;
		}
		if (dayPointer == null)
		{
			dayPointer = newAppointment;
			return;
		}
		Appointment temp = dayPointer.down;
		dayPointer.down = newAppointment;
		newAppointment.down = temp;
	}

	public int convertToMonthEnum(String month)
	{
		switch (month)
			{
				case "Jan": return 0;
		
				case "Feb": return 1;
		
				case "Mar": return 2;
		
				case "Apr": return 3;
		
				case "May": return 4;
		
				case "Jun": return 5;
			
				case "Jul": return 6;
			
				case "Aug": return 7;
			
				case "Sep": return 8;
			
				case "Oct": return 9;
	
				case "Nov": return 10;
	
				case "Dec": return 11;

			}
		return -1;
	}
	
	/*Deletion methods*/
	public Appointment deleteAppointment(String month, int day)
	{
		/*Delete the first appointment at the given month and day combination and return the deleted appointment.
		If no such appointment exists, return null.*/
	
		Appointment appointmentPointer = getAppointment(month,day);
		Appointment monthPointer = getMonthAppointment(month);
		Appointment dayPointer = getDayAppointment(day);
		System.out.println(dayPointer);
		if (appointmentPointer != null && dayPointer != null && monthPointer != null)
		{
			if (monthPointer == appointmentPointer)
			{
				System.out.println("howdy");
			}
			else 
			{
				while (monthPointer.right != appointmentPointer)
					monthPointer = monthPointer.right;				
			}
	
			if (dayPointer == appointmentPointer)
			{
				System.out.println("hey there!");
			}
			else 
			{
				while (dayPointer.down != appointmentPointer)
					dayPointer = dayPointer.down; 
			}

			
			if (appointmentPointer.back != null)
			{
				Appointment temp = appointmentPointer;
				appointmentPointer = appointmentPointer.back;
				return temp;
			}
			dayPointer.down = dayPointer.down.down;
			monthPointer.right = monthPointer.right.right;
			return appointmentPointer;
		}
		return null;
	}

	public Appointment deleteAppointment(String month, int day, String description)
	{
		/*Delete the first appointment at the given month and day combination  with the description and return the deleted appointment.
		If no such appointment exists, return null.*/
		Appointment appointmentPointer = getAppointment(month,day);
		Appointment monthPointer = getMonthAppointment(month);
		Appointment dayPointer = getDayAppointment(day);
		System.out.println(dayPointer);
		if (appointmentPointer != null && dayPointer != null && monthPointer != null)
		{
			if (monthPointer == appointmentPointer)
			{
				System.out.println("howdy");
			}
			else 
			{
				while (monthPointer.right != appointmentPointer)
					monthPointer = monthPointer.right;				
			}
	
			if (dayPointer == appointmentPointer)
			{
				System.out.println("hey there!");
			}
			else 
			{
				while (dayPointer.down != appointmentPointer)
					dayPointer = dayPointer.down; 
			}
			
			if (appointmentPointer.back != null)
			{
				Appointment temp = appointmentPointer;
				appointmentPointer = appointmentPointer.back;
				return temp;
			}
			dayPointer.down = dayPointer.down.down;
			monthPointer.right = monthPointer.right.right;
			return appointmentPointer;
		}
		return null;
	}
	
	/*Clearing Methods*/
	public void clearMyMonth(String month)
	{
		/*All appointements for the given month should be deleted.
		If the month has no appointments, simply do nothing.*/

		Appointment monthPointer = getMonthAppointment(month);
		Appointment dayPointer = null;
		for (int x = 0; x < 30; x++)
		{
			if (dayPointer != null)
			{
				dayPointer = getDayAppointment(x);
				while (dayPointer.down != null && dayPointer.down.month != month)
				{
					dayPointer = dayPointer.down;		
				}
				if (dayPointer.down.month == month)
					dayPointer.down = dayPointer.down.down;
				 
			}
		}
		months[convertToMonthEnum(month)] = null;
	}
	
	public void clearMyDays(int day)
	{
		/*All appointements for the given day should be deleted.
		If the day has no appointments, simply do nothing.*/
		Appointment monthPointer = null;
		Appointment dayPointer = getDayAppointment(day);
		for (int x = 0; x < 12; x++)
		{	
			monthPointer = months[x];
			if (monthPointer != null)
			{
				if (monthPointer.day == day)
				{
					months[x] = months[x].right;
				}
				else
				{
					while (monthPointer.right != null && monthPointer.right.day != day)
					{
						monthPointer = monthPointer.right;
					} 
					monthPointer.right = null;
								
				}	
			}
		}
	}

	public void clearMyYear()
	{
		/*Delete all appointments from the calendar.*/
		for (int x = 0; x < 30; x++)
			days[x] = null;
		for (int x = 0; x < 12; x++)
			months[x] = null;
	}
	
	
	/*Query methods*/
	public Appointment getAppointment(String month, int day)
	{
		/*Return the head appointment of the month and day combination.  If no such appointment exists, return null*/
		Appointment monthAppointment = getMonthAppointment(month);
		while (monthAppointment != null && monthAppointment.day != day)
		{
			monthAppointment = monthAppointment.right;
		}
		if (monthAppointment == null)
			 return null;	
		else
			return monthAppointment;

	}
	
	public Appointment getMonthAppointment(String month)
	{
		/*Return the head appointment for the month passed as a parameter.
		If no such appointment exists, return null*/
		Appointment monthAppointment = null;
		switch (month)
		{
			case "Jan": monthAppointment = months[0];
			break;
			case "Feb": monthAppointment = months[1];
			break;
			case "Mar": monthAppointment = months[2];
			break;
			case "Apr": monthAppointment = months[3];
			break;
			case "May": monthAppointment = months[4];
			break;
			case "Jun": monthAppointment = months[5];
			break;
			case "Jul": monthAppointment = months[6];
			break;
			case "Aug": monthAppointment = months[7];
			break;
			case "Sep": monthAppointment = months[8];
			break;
			case "Oct": monthAppointment = months[9];
			break;
			case "Nov": monthAppointment = months[10];
			break;
			case "Dec": monthAppointment = months[11];
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
