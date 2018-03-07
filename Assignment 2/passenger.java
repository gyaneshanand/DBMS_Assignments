import java.io.*;
import java.util.*;


public class passenger
{
	ArrayList <flight> flight_info;
	String id;
	String flight_name;
	passenger(String id , String flight_name)
	{
		flight_info = new ArrayList <flight>();
		this.id = id;
		this.flight_name =flight_name;
	}
	public void add_flight(flight f)
	{
		flight_info.add(f);
	}
}