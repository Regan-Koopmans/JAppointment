public class Diary
{
	/*Use this class to test your implementation.  This file will be overwritten for marking purposes.*/
	
	static int compare(int testCase, String s1, String s2, int mark)
	{
		System.out.println("Test Case: "+testCase+" testing if "+s1+" == "+s2);
		
		if(s1.equals(s2))
		{
			System.out.println("Awarded 1 mark, mark so far is: "+mark);
			return 1;
		}
		
		return 0;
	}
	
	static int trivialInserts(int currentMark)
	{
		System.out.println("Trivial inserts: \n===========");
		int mark = 0;
		//Test 1 insert Jan 1
		
		Calendar c = new Calendar();
		
		c.addAppointment("Jan", 1, "A", 4);
		
		Appointment a =  c.getAppointment("Jan", 1);
		
		mark+= compare(1,"A",a.getDescription(),mark);
		mark+= compare(2,"4",a.getDuration()+"",mark);
		
		
		//Test 2 insert Jun 5
		c.addAppointment("Jun", 5, "B", 3);
		a =  c.getAppointment("Jun", 5);
		
		mark+= compare(3,"B",a.getDescription(),mark);
		mark+= compare(4,"3",a.getDuration()+"",mark);
		
// 		Test 3 insert April 9
		c.addAppointment("Apr", 9, "C", 2);
		a =  c.getAppointment("Apr", 9);
		
		mark+= compare(5,"C",a.getDescription(),mark);
		mark+= compare(6,"2",a.getDuration()+"",mark);
		
		System.out.println("Mark for trivial inserts: "+mark+"\n===============");
		return mark;
		
	}
	
	static int advancedInserts()
	{
		System.out.println("Advanced inserts: \n===========");
		int mark = 0;
		int i = 0;//Test case counter
		int nameCounter = 0;
		int fromName = 0;
		
		//System.out.println("Trace1");
		
		Calendar c = new Calendar();
		
 	//	String months[] = new String[]{"Feb","Mar","Apr","Jun","Aug"};
	//	String names[] = new String[]{"A","B","C","D","E","F","G","H","I","J"};
		
		//A-D
		for(int j = 1; j < 4; ++j)
		{
				c.addAppointment(months[j], 3, names[nameCounter++], 4);		
		}

		c.printTable();		

		//E
		c.addAppointment(months[5], 3, names[nameCounter++], 4);
		
		Appointment a = c.getAppointment(months[0],3);

		c.printTable();
		
		//First round of marking.

		while(a!=null)
		{
			
			mark+= compare(i,names[fromName++],a.getDescription(),mark);
			a = a.right;
			++i;
		}
		
		//2
		c.addAppointment(months[3],7,names[nameCounter++],5);
		c.addAppointment(months[3],9,names[nameCounter++],5);
		
		
		a = c.getAppointment(months[3],3);

		c.printTable();		

		while(a!=null)
		{
			mark+= compare(i,names[fromName++],a.getDescription(),mark);
			a = a.down;
			++i;
		}
		
		//3
		for(int j = 0; j < 4; ++j)
			c.addAppointment(months[7],15,names[nameCounter++],j+1);
		
		c.printTable();
		
		a = c.getAppointment(months[7],3);
		
		while(a!=null)
		{
			mark+= compare(i,names[fromName++],a.getDescription(),mark);
			a = a.down;
			++i;
		}
		
		System.out.println("Mark for advanced inserts: "+mark+"\n===============");
		
		return mark;
	}
	
	
	
	static void print(Appointment app) {
        String out = "";
        out += app.getDescription();
        out += " [right: " + (app.right != null ? app.right.getDescription() + "]": "null]");
        out += " [down: " + (app.down != null ? app.down.getDescription() + "]": "null]");
        out += " [back: " + (app.back != null ? app.back.getDescription() + "]": "null]");
        System.out.println(out);
    }
 
    public static void main(String[] args)
    {
        Calendar c = new Calendar();
 
        System.out.println("----------------- INSERT MARCH ------------------");
        c.addAppointment("Mar",18,"March-18",5);
        Appointment a = c.getAppointment("Mar",18);
 
        c.addAppointment("Mar", 17, "March-17", 5);
        c.addAppointment("Mar", 17, "March-17(2)", 5);
        Appointment b = c.getAppointment("Mar",17);
 
        c.addAppointment("Mar", 20, "March-20", 5);
        Appointment cc = c.getAppointment("Mar",20);
 
        c.addAppointment("Mar", 19, "March-19", 5);
        c.addAppointment("Mar", 19, "March-19(2)", 5);
        c.addAppointment("Mar", 19, "March-19(3)", 5);
        Appointment d = c.getAppointment("Mar",19);
        print(b);
        print(a);
 
        print(d);
        print(cc);

		c.printTable(); 
		c.printTableColumn();

        System.out.println();
        System.out.println("----------------- INSERT FEBRUARY ------------------");
        c.addAppointment("Feb", 19, "February-19", 5);
        Appointment e = c.getAppointment("Feb",19);
 
 
        c.addAppointment("Feb", 17, "February-17", 5);
        c.addAppointment("Feb", 17, "February-17(2)", 5);
        Appointment f = c.getAppointment("Feb",17);
 
 
        c.addAppointment("Feb", 18, "February-18", 5);
        c.addAppointment("Feb", 18, "February-18(2)", 5);
        c.addAppointment("Feb", 18, "February-18(3)", 5);
        Appointment g = c.getAppointment("Feb",18);
 
        print(f);
        print(g);
        print(e);
 
 
        System.out.println();
        System.out.println("----------------- INSERT APRIL ------------------");
        c.addAppointment("Apr", 19, "April-19", 5);
        Appointment h = c.getAppointment("Apr",19);
 
        c.addAppointment("Apr", 17, "April-17", 5);
        Appointment i = c.getAppointment("Apr",17);
 
        c.addAppointment("Apr", 18, "April-18", 5);
        c.addAppointment("Apr", 18, "April-18(2)", 5);
        c.addAppointment("Apr", 18, "April-18(3)", 5);
        Appointment j = c.getAppointment("Apr",18);
 
        c.addAppointment("Apr", 20, "April-20", 5);
        Appointment k = c.getAppointment("Apr",20);
 
        print(i);
        print(j);
        print(h);
        print(k);
 
        System.out.println();
        System.out.println("----------------- INSERT SEPTEMBER ------------------");
        c.addAppointment("Sep", 17, "September-17", 5);
        Appointment l = c.getAppointment("Sep",17);
 
        c.addAppointment("Sep", 18, "September-18", 5);
        Appointment m = c.getAppointment("Sep",18);
 
        c.addAppointment("Sep", 21, "September-21", 5);
        Appointment n = c.getAppointment("Sep",21);
 
        print(l);
        print(m);
        print(n);
 
        System.out.println();
        System.out.println("----------------- INSERT MISC ------------------");
        c.addAppointment("Feb", 21, "February-21", 5);
        Appointment o = c.getAppointment("Feb",21);
 
        c.addAppointment("Dec", 21, "December-21", 5);
        Appointment p = c.getAppointment("Dec",21);
 
        c.addAppointment("Jan", 21, "January-21", 5);
        Appointment q = c.getAppointment("Jan",21);
 
        c.addAppointment("Jan", 30, "January-30", 5);
        Appointment r = c.getAppointment("Jan",30);
 
        c.addAppointment("Jan", 1, "January-1", 5);
        c.addAppointment("Jan", 1, "January-1(2)", 5);
        Appointment s = c.getAppointment("Jan",1);
 
        print(o);
        print(p);
        print(q);
        print(s);
        print(r);
 
        System.out.println();
        System.out.println("----------------- FINAL TABLE ------------------");
        print(s);
        print(q);
        print(r);
 
        print(f);
        print(g);
        print(e);
        print(o);
 
        print(b);
        print(a);
        print(d);
        print(cc);
 
        print(i);
        print(j);
        print(h);
        print(k);
 
        print(l);
        print(m);
        print(n);
 
        print(p);
 
        System.out.println();
        System.out.println("----------------- DELETE MAR 17 ------------------");
        c.deleteAppointment("Mar", 17);
        f = c.getAppointment("Feb",17);
        b = c.getAppointment("Mar",17);
        a = c.getAppointment("Mar",18);
        i = c.getAppointment("Apr",17);
 
        print(f); print(b); print(a); print(i);
 
        System.out.println();
        System.out.println("----------------- DELETE FEB 18(2) ------------------");
        c.deleteAppointment("Feb", 18, "February-18(2)");
        f = c.getAppointment("Feb",17);
        g = c.getAppointment("Feb",18);
        e = c.getAppointment("Feb",19);
        a = c.getAppointment("Mar",18);
 
        print(f); print(g); print(e); print(a);

        System.out.println();
        System.out.println("----------------- DELETE FEB 18 ------------------");
        c.deleteAppointment("Feb", 18);
        f = c.getAppointment("Feb",17);
        g = c.getAppointment("Feb",18);
        e = c.getAppointment("Feb",19);
        a = c.getAppointment("Mar",18);
 
        print(f); print(g); print(e); print(a);
 
        System.out.println();
        System.out.println("----------------- DELETE FEB 17 ------------------");
        c.deleteAppointment("Feb", 17);
        f = c.getAppointment("Feb",17);
        g = c.getAppointment("Feb",18);
        e = c.getAppointment("Feb",19);
        b = c.getAppointment("Mar",17);
 
        print(f); print(g); print(e); print(b);

		c.printTable();
 
        System.out.println();
        System.out.println("----------------- DELETE FEB 17(2) ------------------");
        c.deleteAppointment("Feb", 17, "February-17(2)");
        g = c.getAppointment("Feb",18);
        e = c.getAppointment("Feb",19);
        b = c.getAppointment("Mar",17);
		System.out.println(g);

        print(g); print(e); print(b);
		c.printTable();
        //
 
        System.out.println();
        System.out.println("----------------- DELETE MAR 18 ------------------");
        c.deleteAppointment("Mar", 18);
        g = c.getAppointment("Feb",18);
        j = c.getAppointment("Apr",18);
        b = c.getAppointment("Mar",17);
        d = c.getAppointment("Mar",19);
 
        print(g); print(j); print(b); print(d);
 
        System.out.println();
        System.out.println("----------------- DELETE APR 17 ------------------");
        c.addAppointment("Jan", 17, "January-17", 5);
        c.deleteAppointment("Apr", 17);
        Appointment z = c.getAppointment("Jan", 17);
        b = c.getAppointment("Mar",17);
        l = c.getAppointment("Sep",17);
        j = c.getAppointment("Apr",18);
 
        print(z); print(b); print(l); print(j);
 
        System.out.println();
        System.out.println("----------------- DELETE APR 19 ------------------");
        c.deleteAppointment("Apr", 19);
        e = c.getAppointment("Feb",19);
        d = c.getAppointment("Mar",19);
        j = c.getAppointment("Apr",18);
        k = c.getAppointment("Apr",20);
 
        print(e); print(d); print(j); print(k);
 
        System.out.println();
        System.out.println("----------------- TABLE AFTER DELETES ------------------");
 
        //a = c.getAppointment("Mar",18);
        b = c.getAppointment("Mar",17);
        cc = c.getAppointment("Mar",20);
        d = c.getAppointment("Mar",19);
        e = c.getAppointment("Feb",19);
        //f = c.getAppointment("Feb",17);
        g = c.getAppointment("Feb",18);
        //h = c.getAppointment("Apr",19);
        //i = c.getAppointment("Apr",17);
 
        j = c.getAppointment("Apr",18);
        k = c.getAppointment("Apr",20);
        l = c.getAppointment("Sep",17);
        m = c.getAppointment("Sep",18);
        n = c.getAppointment("Sep",21);
        o = c.getAppointment("Feb",21);
        p = c.getAppointment("Dec",21);
        q = c.getAppointment("Jan",21);
        r = c.getAppointment("Jan",30);
        s = c.getAppointment("Jan",1);
        z = c.getAppointment("Jan", 17);
 
        print(s);
        print(z);
        print(q);
        print(r);
 
        print(g);
        print(e);
        print(o);
 
        print(b);
        print(d);
        print(cc);
 
        print(j);
        print(k);
 
        print(l);
        print(m);
        print(n);
 
        print(p);
 
        System.out.println();
        System.out.println("----------------- clearMonth = MARCH ------------------");
        c.clearMyMonth("Mar");
        s = c.getAppointment("Jan",1);
        z = c.getAppointment("Jan", 17);
        q = c.getAppointment("Jan",21);
        r = c.getAppointment("Jan",30);
 
        g = c.getAppointment("Feb",18);
        e = c.getAppointment("Feb",19);
        o = c.getAppointment("Feb",21);
 
        j = c.getAppointment("Apr",18);
        k = c.getAppointment("Apr",20);
 
        l = c.getAppointment("Sep",17);
        m = c.getAppointment("Sep",18);
        n = c.getAppointment("Sep",21);
 
        p = c.getAppointment("Dec",21);
 
        print(s); print(z); print(q); print(r); print(g); print(e); print(o);
        print(j); print(k); print(l); print(m); print(n); print(p);
 
        System.out.println();
        System.out.println("----------------- clearDay = 21 ------------------");
        c.clearMyDays(21);
        s = c.getAppointment("Jan",1);
        z = c.getAppointment("Jan", 17);
        r = c.getAppointment("Jan",30);
 
        g = c.getAppointment("Feb",18);
        e = c.getAppointment("Feb",19);
 
        j = c.getAppointment("Apr",18);
        k = c.getAppointment("Apr",20);
 
        l = c.getAppointment("Sep",17);
        m = c.getAppointment("Sep",18);
        print(s); print(z); print(r); print(g); print(e); print(j); print(k);
        print(l); print(m);
 
        System.out.println();
        System.out.println("----------------- getAppointment() ------------------");
        print(c.getAppointment("Apr", 18));
        print(c.getAppointment("Feb", 19));
        print(c.getAppointment("Jan", 1));
        print(c.getAppointment("Sep", 17));
 
        System.out.println();
        System.out.println("----------------- getMonthAppointment() ------------------");
        print(c.getMonthAppointment("Jan"));
        print(c.getMonthAppointment("Apr"));
        print(c.getMonthAppointment("Sep"));
 
        System.out.println();
        System.out.println("----------------- getDayAppointment() ------------------");
        print(c.getDayAppointment(18));
        print(c.getDayAppointment(17));
        print(c.getDayAppointment(20));
 
        System.out.println();
        System.out.println("----------------- clearMyYear() ------------------");
        c.clearMyYear();
        s = c.getAppointment("Jan",1);
        z = c.getAppointment("Jan", 17);
        r = c.getAppointment("Jan",30);
 
        g = c.getAppointment("Feb",18);
        e = c.getAppointment("Feb",19);
 
        j = c.getAppointment("Apr",18);
        k = c.getAppointment("Apr",20);
 
        l = c.getAppointment("Sep",17);
        m = c.getAppointment("Sep",18);

	}	
	
	static String months[] = new String[]{"Jan","Feb","Mar","Apr","May","Jun","Jul","Aug","Sep","Oct","Nov","Dec"};
	
	static String names[] = new String[]{"A","B","C","D","E","F","G","H","I","J","K","L","M","N","O","P"};
	
}
