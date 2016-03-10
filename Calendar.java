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
		--day;
		Appointment newAppointment = new Appointment(description,duration);
		newAppointment.day = day;
		newAppointment.month = month;
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
				System.out.println("Inserting " + description);
				while (monthPointer.back != null)
					monthPointer = monthPointer.back;

				monthPointer.back = newAppointment;
				return;
			}
			else
			{
				if (monthPointer.right == null)
				{
					monthPointer.right = newAppointment;
				}
				else
				{
					if (monthPointer.right.day == day)
					{
						monthPointer = monthPointer.right;
						while (monthPointer.back != null)
							monthPointer = monthPointer.back;
						
						monthPointer.back = newAppointment;
					}
					else 
					{
						Appointment temp = monthPointer.right;
						monthPointer.right = newAppointment;
						newAppointment.right = temp;
					}
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
		else if (convertToMonthEnum(newAppointment.month) < convertToMonthEnum(dayPointer.month))
		{
			newAppointment.down = dayPointer;
			days[day] = newAppointment;
			return;
		}
		else
		{
			while (dayPointer.down != null && 
				convertToMonthEnum(dayPointer.down.month) < convertToMonthEnum(month) && !dayPointer.month.equals(month))
			{
				dayPointer = dayPointer.down;
			}

			if (dayPointer.month.equals(month))
			{
				return;
			}
			else 
			{
				Appointment temp = dayPointer.down;
				dayPointer.down = newAppointment;
				newAppointment.down = temp;
			}
		}
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

		Appointment appointmentPointer = getAppointment(month,day);
		Appointment monthPointer = getMonthAppointment(month);
		Appointment dayPointer = getDayAppointment(day);
		if (appointmentPointer != null && dayPointer != null && monthPointer != null)
		{
			if (monthPointer == appointmentPointer)
			{
				Appointment temp = monthPointer.right;
				if (appointmentPointer.back != null)
				{
					months[convertToMonthEnum(month)] = appointmentPointer.back;
					appointmentPointer.back.right = temp;		
				}
				else	
				{
					months[convertToMonthEnum(month)] = temp;
				}
				return appointmentPointer;
			}
			else 
			{

				while (monthPointer.right != appointmentPointer)
					monthPointer = monthPointer.right;	

				
				if (monthPointer.right != null && monthPointer.right.back != null)
				{
					Appointment temp = monthPointer.right.right;
					monthPointer.right = monthPointer.right.back;
					monthPointer.right.right = temp;
				}
				else monthPointer.right = monthPointer.right.right;
				
			}
	
			if (dayPointer == appointmentPointer)
			{
				Appointment temp = appointmentPointer.down;
				if (appointmentPointer.back != null)
				{
					days[day] = appointmentPointer.back;
					days[day].down = temp;	
				}
				else
				{
					days[day] = temp;
					
				}
			}
			else 
			{
				while (dayPointer != null && dayPointer.down != appointmentPointer)
					dayPointer = dayPointer.down; 
			}

			return appointmentPointer;
		}
		return null;
	}

	public Appointment deleteAppointment(String month, int day, String description)
	{
		day--;
		Appointment appointmentPointer = getAppointment(month,day+1);
		Appointment monthPointer = getMonthAppointment(month);
		Appointment dayPointer = getDayAppointment(day+1);
		if (appointmentPointer != null && dayPointer != null && monthPointer != null)
		{
			if (appointmentPointer.getDescription().equals(description))	
			{
				if (appointmentPointer.back != null)
				{
					if (days[day] == appointmentPointer)
					{
						days[day] = appointmentPointer.back;
						days[day].down = appointmentPointer.down;
					}
					else 
					{
						while (dayPointer.down != null && dayPointer.down != appointmentPointer)
							dayPointer = dayPointer.down;
						
						if (dayPointer.down != null)
							dayPointer.down = dayPointer.down.down;
							
					}

					if (months[convertToMonthEnum(month)] == appointmentPointer)
					{
						months[convertToMonthEnum(month)] = appointmentPointer.back;
						months[convertToMonthEnum(month)].right = appointmentPointer.right;
					}
					else 
					{
						while (monthPointer.right != null && monthPointer.right != appointmentPointer)
							monthPointer = monthPointer.right;
						
						if (monthPointer.right != null)
							monthPointer.right = monthPointer.right.right;
					}
				}
				else 
				{
					
					System.out.println("test");
					if (days[day] == appointmentPointer)
						days[day] = appointmentPointer.back;
					if (months[convertToMonthEnum(month)] == appointmentPointer)
						if (appointmentPointer.back != null)
							months[convertToMonthEnum(month)] = appointmentPointer.back;
						else months[convertToMonthEnum(month)] = appointmentPointer.right;
					else 
					{
						System.out.println("test2");
						while (appointmentPointer.back != null && !appointmentPointer.back.getDescription().equals(description))
							appointmentPointer = appointmentPointer.back;
					}
					
				}
				return appointmentPointer;
			}
			else 
			{
				while (appointmentPointer.back != null && 
					!appointmentPointer.back.getDescription().equals(description))
				{
					appointmentPointer = appointmentPointer.back;
				}
				if (appointmentPointer.back != null)
				{
					Appointment temp = appointmentPointer.back;
					appointmentPointer.back = appointmentPointer.back.back;
					return temp;
				}
			}
		}
		return null;
	}
	

	public void clearMyMonth(String month)
	{
		Appointment monthPointer = getMonthAppointment(month);
		Appointment dayPointer = null;
		for (int x = 1; x < 31; x++)
		{
			dayPointer = getDayAppointment(x);
			if (dayPointer != null)
			{
				if (days[x-1].month == month)
				{
					System.out.println("Changing first");
					days[x-1] = days[x-1].down;	
				}
				else
				{
					while (dayPointer.down != null && dayPointer.down.month != month)
					{
						dayPointer = dayPointer.down;		
					}
					if (dayPointer.down != null && dayPointer.down.month == month)
					{
						dayPointer.down = dayPointer.down.down;
					}
				}
			}
		}
		months[convertToMonthEnum(month)] = null;
	}
	
	public void clearMyDays(int day)
	{
		Appointment monthPointer = null;
		Appointment dayPointer = getDayAppointment(day);
		for (int x = 0; x < 12; x++)
		{	
			monthPointer = months[x];
			if (monthPointer != null)
			{
				
				if (monthPointer.day == day)
				{
					System.out.println("At begining of month");
					months[x] = months[x].right;
				}
				else
				{

					while (monthPointer.right != null && monthPointer.right.day != day-1)
					{
						System.out.println("traverse");
						monthPointer = monthPointer.right;
					} 
					if (monthPointer.right != null)
					{
						System.out.println(monthPointer.day);
						monthPointer.right = monthPointer.right.right;
					}			
				}	
			}
		}
		days[day-1] = null;
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
		day--;
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
		if (day >= 1 && day <= 30) 
			return days[day-1];
		else 
			return null;
	}

	public void printTable()
	{
		Appointment monthPointer = null;
		System.out.println();	

		for (int x = 0; x < 12; x++)
		{
			System.out.print(x + "\t");
			monthPointer = months[x];
				
			for (int y = 0; y < 30; y++)
			{
				if (monthPointer != null && monthPointer.day == y)
				{
					System.out.print(monthPointer.getDescription());
					monthPointer = monthPointer.right;
				}
				else 
					System.out.print("-"); 
			} 
			System.out.println();
		}
		System.out.println();
	}
		
	public void printTableColumn()
	{
		Appointment dayPointer = null;
		System.out.println();
	
		for (int x = 0; x < 30; x++)
		{
			System.out.print(x+1 + "\t");
			dayPointer = days[x];
		
			for (int y = 0; y < 12; y++)
			{
				if (dayPointer != null && convertToMonthEnum(dayPointer.month) == y)
				{
					System.out.print(dayPointer.getDescription());	
					dayPointer = dayPointer.down;
				}
				else 
					System.out.print("-");
			}
			System.out.println();
		}
		System.out.println();
	}

	Appointment [] days;
	Appointment [] months;	
}
