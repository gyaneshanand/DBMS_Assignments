/*AMAN ROY 2016011
GYANESH ANAND 2016039*/
import java.util.*;
import java.io.*;
import java.util.concurrent.*;
public class main
{
	public static void main(String [] args) throws IOException ,InterruptedException
	{
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		ArrayList<flight> list_flights = new ArrayList<flight>();
		ArrayList<String> list_transactions = new ArrayList<String>();
		flight f = new flight("Flight1",1);
		f.add("AMAN");
		f.add("AMAN1");
		f.add("AMAN2");
		f.add("AMAN3");
		f.add("AMAN4");
		list_flights.add(f);
		f = new flight("Flight2",2);
		f.add("MAN");
		f.add("MAN1");
		f.add("MAN2");
		f.add("MAN3");
		f.add("MAN4");
		list_flights.add(f);
		f = new flight("Flight3",3);
		f.add("AN");
		f.add("AN1");
		f.add("AN2");
		f.add("AN3");
		f.add("AN4");
		list_flights.add(f);
		f = new flight("Flight4",4);
		f.add("N");
		f.add("N1");
		f.add("N2");
		f.add("N3");
		f.add("N4");
		list_flights.add(f);
		f = new flight("Flight5",5);
		f.add("0");
		f.add("1");
		f.add("2");
		f.add("3");
		f.add("4");
		list_flights.add(f);
		String input;
		while(!(input = br.readLine()).equals(""))
		{
			//System.out.println(input);
			list_transactions.add(input);
		}
		Database d = new Database(list_flights , list_transactions);
		ExecutorService exec = Executors.newFixedThreadPool(5);
		ArrayList<Transaction> ar = new ArrayList<Transaction>();
		int c = 1;
		//locktable l = new locktable();
		while(list_transactions.size()!=0)
		{
			Random rand = new Random();
			int number = rand.nextInt(list_transactions.size())+1;
			String s = list_transactions.get(0);
			System.out.println(s);
			list_transactions.remove(0);
			StringTokenizer st = new StringTokenizer(s);
			String s1 = st.nextToken();
			if(s1.equals("reserve"))
			{
				String s2 = st.nextToken();
				ar.add(new Transaction(d,1,s2,st.nextToken(),c));
				//l.map.get(s2).add(c);
				c++;
			}
			else if(s1.equals("cancel"))
			{
				String s2 = st.nextToken();
				ar.add(new Transaction(d,2,s2,st.nextToken(),c));
				//l.map.get(s2).add(c);
				c++;
			}
			else if(s1.equals("my_flights"))
			{
				ar.add(new Transaction(d,3,st.nextToken(),c));
				/*l.map.get("Flight1").add(c);
				l.map.get("Flight2").add(c);
				l.map.get("Flight3").add(c);
				l.map.get("Flight4").add(c);
				l.map.get("Flight5").add(c);*/
				c++;
			}
			else if(s1.equals("total_reservation"))
			{
				ar.add(new Transaction(d,4,c));
				/*l.map.get("Flight1").add(c);
				l.map.get("Flight2").add(c);
				l.map.get("Flight3").add(c);
				l.map.get("Flight4").add(c);
				l.map.get("Flight5").add(c);*/
				c++;
			}
			else
			{
				String s2 = st.nextToken();
				String s3 = st.nextToken();
				ar.add(new Transaction(d,5,s2,s3,st.nextToken(),c));
				/*l.map.get(s2).add(c);
				l.map.get(s3).add(c);*/
				c++;
			}
		}
		//System.out.println(l.map.get("Flight1").get(0));
		/*for(int i=0;i<ar.size();i++)
		{
			ar.get(i).addlock(l);
		}*/
		ArrayList<Thread> ar1 = new ArrayList<Thread>();
		long startTime = System.nanoTime();
		for(int i=0;i<ar.size();i++)
		{
			ar1.add(new Thread(ar.get(i)));
			exec.execute(ar.get(i));
		}
		if(!exec.isTerminated()) {
		exec.shutdown();
		exec.awaitTermination(5L, TimeUnit.SECONDS);
		}
		long endTime = System.nanoTime();
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<d.list_flights.get(i).passenger_list.size();j++)
			{
				System.out.print(d.list_flights.get(i).passenger_list.get(j) + " ");
			}
			System.out.println();
		}
		System.out.println("Time in Nanoseconds " + (endTime-startTime));

	}
}