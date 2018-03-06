import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Transaction implements Runnable
{
	//public ArrayList<flight> list_flights;
	public Database d;
	public int size=10;
	public Transaction(Database d)
	{
		this.d = d;
	}
	public void run()
	{
		try
	{
		Random rand = new Random();
		int number = rand.nextInt(2)+1;
		if(number==1)
		{
			System.out.println("Enter flight name and passenger Id in one line by space separating them");
			Scanner sc = new Scanner(System.in);
			String s = sc.nextLine();
			StringTokenizer st = new StringTokenizer(s);
			String s1 = st.nextToken();
			flight f;
			for(int i=0;i<d.list_flights.size();i++)
			{
				f = this.d.list_flights.get(i);
				if(f.name.equals(s1))
				{
					while(!f.lock.tryLock(1000,TimeUnit.MILLISECONDS));
					reserve(f,st.nextToken());
					Thread.sleep(2000);
					f.lock.unlock();				}
			}

		}
		else if(number == 2)
		{

		}
		else if(number == 3)
		{

		}
		else if(number == 4)
		{

		}
		else 
		{

		}
	}
	catch(InterruptedException e)
	{
		System.out.println("Error");
	}	
	}
	public void reserve(flight f, String i)
	{
		f.add(i);
	}

	public int cancel(flight f, String i)
	{
		int j=0;
		while(j<f.passenger_list.size())
		{
			if(f.passenger_list.get(j).equals(i))
			{
				f.passenger_list.remove(j);
				return 1;
			}
		}
		return 0;
	}
	public void My_Flights(String id)
	{
		for(int i=0;i<d.list_flights.size();i++)
		{
			for(int j=0;j<d.list_flights.get(i).passenger_list.size();j++)
			{
				if(d.list_flights.get(i).passenger_list.get(j).equals(id))
				{
					System.out.print(d.list_flights.get(i).name+" ");
				}
			}
		}
		System.out.println();
	}

	public int Total_Reservations()
	{
		int count = 0;
		for(int i=0;i<d.list_flights.size();i++)
		{
			for(int j=0;j<d.list_flights.get(i).passenger_list.size();j++)
			{
				count ++;
			}
		}
		System.out.println(count);
		return count;
	}

	public void Transfer(flight F1,flight F2,String i)
	{
		int status = cancel(F1,i);
		if(status==1)
		{
			F2.passenger_list.add(i);
		}
	}
}