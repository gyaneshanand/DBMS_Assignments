import java.util.*;
import java.io.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class Transaction implements Runnable
{
	//public ArrayList<flight> list_flights;
	public Database d;
	String Flight1;
	String Flight2;
	String passengerid;
	int Transactiontype;
	int Transactionnumber;
	static locktable l;
	public Transaction(Database d,int Transactiontype,String Flight1 , String Flight2 , String passengerid , int Transactionnumber)
	{
		this.d = d;
		this.Transactiontype = Transactiontype;
		this.passengerid = passengerid;
		this.Flight1 = Flight1;
		this.Flight2 = Flight2;
		this.Transactionnumber = Transactionnumber;
	}
	public Transaction(Database d,int Transactiontype,String Flight1 , String passengerid , int Transactionnumber )
	{
		this.d = d;
		this.Transactiontype = Transactiontype;
		this.passengerid = passengerid;
		this.Flight1 = Flight1;
		this.Flight2 = null;
		this.Transactionnumber = Transactionnumber;
	}
	public Transaction(Database d,int Transactiontype,String passengerid , int Transactionnumber )
	{
		this.d = d;
		this.Transactiontype = Transactiontype;
		this.passengerid = passengerid;
		this.Flight1 = null;
		this.Flight2 = null;
		this.Transactionnumber = Transactionnumber;
	}
	public Transaction(Database d,int Transactiontype , int Transactionnumber )
	{
		this.d = d;
		this.Transactiontype = Transactiontype;
		this.passengerid = null;
		this.Flight1 = null;
		this.Flight2 = null;
		this.Transactionnumber = Transactionnumber;
	}
	public void addlock (locktable t)
	{
		this.l = t;
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
					//System.out.println(l);	
					while((!f.lock.tryLock(1000,TimeUnit.MILLISECONDS)));
					System.out.println(f.name + " locked "+this.Transactionnumber);
					reserve(f,passengerid);
					//Thread.sleep(100);
					f.lock.unlock();
					System.out.println(f.name + " unlocked "+this.Transactionnumber);
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
					while((!f.lock.tryLock(1000,TimeUnit.MILLISECONDS)));
					System.out.println(f.name + " locked "+this.Transactionnumber);
					//System.out.println(-500);
					cancel(f,passengerid);
					//Thread.sleep(100);
					f.lock.unlock();
					System.out.println(f.name + " unlocked "+this.Transactionnumber);
					break;
				}
			}
			//System.out.println(-1);
			
		}
		else if(this.Transactiontype == 3)
		{
			flight f;
			for(int i=0;i<d.list_flights.size();i++)
			{
				f = this.d.list_flights.get(i);
				while((!f.lock.tryLock(1000,TimeUnit.MILLISECONDS)));
				System.out.println(f.name + " locked "+this.Transactionnumber);
			}
			My_Flights(passengerid);
			for(int i=d.list_flights.size()-1;i>=0;i--)
			{
				d.list_flights.get(i).lock.unlock();
				System.out.println(d.list_flights.get(i).name + " unlocked "+this.Transactionnumber);
			}
		}
		else if(this.Transactiontype == 4)
		{
			flight f;
			//System.out.println(4);
			for(int i=0;i<d.list_flights.size();i++)
			{
				//System.out.println("C" + " "+i);
				f = this.d.list_flights.get(i);
				while((!f.lock.tryLock(1000,TimeUnit.MILLISECONDS)));
				System.out.println(f.name + " locked "+this.Transactionnumber);
			}
			//System.out.println(-4);
			System.out.println(Total_Reservations());
			//System.out.println(-40);
			for(int i=d.list_flights.size()-1;i>=0;i--)
			{
				d.list_flights.get(i).lock.unlock();
				System.out.println(d.list_flights.get(i).name + " unlocked "+this.Transactionnumber);
			}
			//System.out.println(100);
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
					//System.out.println(1131);
					break;
				}
			}
			for(int i=0;i<d.list_flights.size();i++)
			{
				f1 = this.d.list_flights.get(i);
				if(f1.name.equals(Flight2))
				{
					break;
				}
			}
			if(f.number<f1.number)
			{
				while((!f.lock.tryLock(1000,TimeUnit.MILLISECONDS)));
				System.out.println(f.name + " locked "+this.Transactionnumber);
				while((!f1.lock.tryLock(1000,TimeUnit.MILLISECONDS)));
				System.out.println(f1.name + " locked "+this.Transactionnumber);
				Transfer(f,f1,passengerid);
				f1.lock.unlock();
				System.out.println(f1.name + " unlocked "+this.Transactionnumber);
				f.lock.unlock();
				System.out.println(f.name + " unlocked "+this.Transactionnumber);
			}
			else
			{
				while((!f1.lock.tryLock(1000,TimeUnit.MILLISECONDS)));
				System.out.println(f1.name + " locked "+this.Transactionnumber);
				while((!f.lock.tryLock(1000,TimeUnit.MILLISECONDS)));
				System.out.println(f.name + " locked "+this.Transactionnumber);
				f.lock.unlock();
				System.out.println(f.name + " unlocked "+this.Transactionnumber);
				f1.lock.unlock();
				System.out.println(f1.name + " unlocked "+this.Transactionnumber);
			}
			
			//System.out.println(100000);
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
		System.out.println("reserved " + i);
	}

	public int cancel(flight f, String i)
	{
		int j=0;
		while(j<f.passenger_list.size())
		{
			if(f.passenger_list.get(j).equals(i))
			{
				f.passenger_list.remove(j);
				System.out.println("cancelled "+i);
				return 1;
			}
			j++;
		}
		System.out.println("Nothing to delete");
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
			System.out.println("Transfereed " + i + F1.name + " to" + F2.name);
		}
	}
}