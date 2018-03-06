import java.util.*;
import java.io.*;

public class main
{
	public static void main(String [] args) throws IOException ,InterruptedException
	{
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		ArrayList<flight> list_flights = new ArrayList<flight>();
		flight f = new flight("Flight1");
		f.add("AMAN");
		f.add("AMAN1");
		f.add("AMAN2");
		f.add("AMAN3");
		f.add("AMAN4");
		list_flights.add(f);
		f = new flight("Flight2");
		f.add("MAN");
		f.add("MAN1");
		f.add("MAN2");
		f.add("MAN3");
		f.add("MAN4");
		list_flights.add(f);
		f = new flight("Flight3");
		f.add("AN");
		f.add("AN1");
		f.add("AN2");
		f.add("AN3");
		f.add("AN4");
		list_flights.add(f);
		f = new flight("Flight4");
		f.add("N");
		f.add("N1");
		f.add("N2");
		f.add("N3");
		f.add("N4");
		list_flights.add(f);
		f = new flight("Flight5");
		f.add("0");
		f.add("1");
		f.add("2");
		f.add("3");
		f.add("4");
		list_flights.add(f);
		Database d = new Database(list_flights);
		Thread t1 = new Thread(new Transaction(d));
		Thread t2 = new Thread(new Transaction(d));
		t1.start();
		t2.start();
		t1.join();
		t2.join();
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<d.list_flights.get(i).passenger_list.size();j++)
			{
				System.out.print(d.list_flights.get(i).passenger_list.get(j) + " ");
			}
			System.out.println();
		}

	}
}