import java.io.*;
import java.util.*;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class Database
{
	public ArrayList<flight> list_flights;
	public ReentrantLock lock;
	Database(ArrayList<flight> list )
	{
		this.list_flights = list;
		lock = new ReentrantLock();
	}
}