import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Transaction1 implements Runnable
{
	//public ArrayList<flight> list_flights;
	public Database d;
	String Flight1;
	String Flight2;
	String passengerid;
	int Transactiontype;
	public Transaction1(Database d,int Transactiontype,String Flight1 , String Flight2 , String passengerid)
	{
		this.d = d;
		this.Transactiontype = Transactiontype;
		this.passengerid = passengerid;
		this.Flight1 = Flight1;
		this.Flight2 = Flight2;
	}
	public Transaction1(Database d,int Transactiontype,String Flight1 , String passengerid)
	{
		this.d = d;
		this.Transactiontype = Transactiontype;
		this.passengerid = passengerid;
		this.Flight1 = Flight1;
		this.Flight2 = null;
	}
	public Transaction1(Database d,int Transactiontype,String passengerid)
	{
		this.d = d;
		this.Transactiontype = Transactiontype;
		this.passengerid = passengerid;
		this.Flight1 = null;
		this.Flight2 = null;
	}
	public Transaction1(Database d,int Transactiontype)
	{
		this.d = d;
		this.Transactiontype = Transactiontype;
		this.passengerid = null;
		this.Flight1 = null;
		this.Flight2 = null;
	}
	public void run()
	{
		try
	{
		if(this.Transactiontype==1)
		{
			flight f;
			//System.out.println(2);
			for(int i=0;i<d.list_flights.size();i++)
			{
				f = this.d.list_flights.get(i);
				if(f.name.equals(Flight1))
				{
					while(!d.lock.tryLock(1000,TimeUnit.MILLISECONDS));
					reserve(f,passengerid);
					
					d.lock.unlock();
					break;				}
			}
			//System.out.println(-2);

		}
		else if(this.Transactiontype == 2)
		{
			flight f;
			//System.out.println(1);
			for(int i=0;i<d.list_flights.size();i++)
			{
				f = this.d.list_flights.get(i);
				if(f.name.equals(Flight1))
				{
					//System.out.println(500);
					while(!d.lock.tryLock(1000,TimeUnit.MILLISECONDS));
					//System.out.println(-500);
					cancel(f,passengerid);
					
					d.lock.unlock();
					break;
				}
			}
			//System.out.println(-1);
			
		}
		else if(this.Transactiontype == 3)
		{
			flight f;
			while(!d.lock.tryLock(1000,TimeUnit.MILLISECONDS));
			My_Flights(passengerid);
			d.lock.unlock();
		}
		else if(this.Transactiontype == 4)
		{
			flight f;
			//System.out.println(4);
			while(!d.lock.tryLock(1000,TimeUnit.MILLISECONDS));
			//System.out.println(-4);
			System.out.println(Total_Reservations());
			//System.out.println(-40);
			d.lock.unlock();
			//System.out.println
		}
		else 
		{
			flight f = null;
			flight f1 = null;
			for(int i=0;i<d.list_flights.size();i++)
			{
				f = this.d.list_flights.get(i);
				if(f.name.equals(Flight1))
				{
					//while(!f.lock.tryLock(1000,TimeUnit.MILLISECONDS));
					break;
				}
			}
			for(int i=0;i<d.list_flights.size();i++)
			{
				f1 = this.d.list_flights.get(i);
				if(f1.name.equals(Flight2))
				{
					//while(!f1.lock.tryLock(1000,TimeUnit.MILLISECONDS));
					break;
				}
			}
			while(!d.lock.tryLock(1000,TimeUnit.MILLISECONDS));
			Transfer(f,f1,passengerid);
			d.lock.unlock();
			//f1.lock.unlock();
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
			j++;
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