/*AMAN ROY 2016011
GYANESH ANAND 2016039*/
import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;
public class flight
{
	ArrayList <String> passenger_list;
	String name;
	int number;
	public ReentrantLock lock;
	flight(String name , int number)
	{
		this.name = name;
		this.number = number;
		passenger_list = new ArrayList <String>();
		lock = new ReentrantLock();
	}
	public void add(String p)
	{
		this.passenger_list.add(p);
	}
}