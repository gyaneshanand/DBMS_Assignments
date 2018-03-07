/*AMAN ROY 2016011
GYANESH ANAND 2016039*/
import java.util.*;
import java.io.*;
import java.util.concurrent.*;
public class main1
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
		ArrayList<Transaction1> ar = new ArrayList<Transaction1>();
		ExecutorService exec = Executors.newFixedThreadPool(1);
		long startTime = System.nanoTime();
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
				(new Transaction1(d,1,st.nextToken(),st.nextToken())).run();
			}
			else if(s1.equals("cancel"))
			{
				(new Transaction1(d,2,st.nextToken(),st.nextToken())).run();
			}
			else if(s1.equals("my_flights"))
			{
				(new Transaction1(d,3,st.nextToken())).run();
			}
			else if(s1.equals("total_reservation"))
			{
				(new Transaction1(d,4)).run();
			}
			else
			{
				(new Transaction1(d,5,st.nextToken(),st.nextToken(),st.nextToken())).run();
			}
		}
		/*ArrayList<Thread> ar1 = new ArrayList<Thread>();
		for(int i=0;i<ar.size();i++)
		{
			ar1.add(new Thread(ar.get(i)));
			exec.execute(ar.get(i));
		}
		if(!exec.isTerminated()) {
		exec.shutdown();
		exec.awaitTermination(5L, TimeUnit.SECONDS);
		}*/
		for(int i=0;i<5;i++)
		{
			for(int j=0;j<d.list_flights.get(i).passenger_list.size();j++)
			{
				System.out.print(d.list_flights.get(i).passenger_list.get(j) + " ");
			}
			System.out.println();
		}
		long endTime = System.nanoTime();
		System.out.println("Time in Nanoseconds " + (endTime-startTime));

	}
}