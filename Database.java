import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Database
{
	public ArrayList<flight> list_flights;
	public ReentrantLock lock;
	public ArrayList<String> transaction_list;
	Database(ArrayList<flight> list , ArrayList<String> transaction_list)
	{
		this.list_flights = list;
		lock = new ReentrantLock();
		this.transaction_list = transaction_list;
	}
}