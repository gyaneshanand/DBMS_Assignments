import java.io.*;
import java.util.*;

class Record
{
	public String flag;
	public String id;
	public String name;
	public String dept;
	public String salary;
	public String field;
	public String num; 
	Record(String s1 ,String s2 ,String s3 ,String s4 ,String s5,String num)
	{
		this.flag =s1;
		this.id =s2;
		this.name =s3;
		this.dept =s4;
		this.salary =s5;
		this.num = num;

	}
}
class node
{
	ArrayList <String> values;
	ArrayList <node> pointers;
	ArrayList <String> data_pointers;
	node parent;
	int number_key;
	boolean is_root;
	boolean is_leaf;
	int n = 3;
	node last_pointers;
	node(boolean is_root , boolean is_leaf)
	{
		values = new ArrayList <String> ();
		pointers = new ArrayList <node>();
		parent = null;
		number_key = 0;
		this.is_root = is_root;
		this.is_leaf = is_leaf;
		this.last_pointers = null;
	}
	node(boolean is_root , boolean is_leaf, node parent)
	{
		values = new ArrayList <String> ();
		pointers = new ArrayList <node>();
		this.parent = parent;
		number_key = 0;
		this.is_root = is_root;
		this.is_leaf = is_leaf;
		this.last_pointers = null;
	}
}
class Btree1
{
	public node root;
	public ArrayList <Record> ar;
	public ArrayList <Record> ar1;
	Btree1()
	{
		ar = new ArrayList <Record> ();
		ar1 = new ArrayList <Record> ();
	}
	public node find(String V, int query)
	{
		node c = root;
		while(!c.is_leaf)
		{
			int index;
			for(index = 0;index < c.values.size();index++)
			{
				if(c.values.get(index).compareTo(V) >= 0)
				{
					//index-=1;
					break;
				}
			}
			//System.out.println(index);
			if(index == c.values.size())
			{
				//System.out.println("Gyanehs");
				c = c.pointers.get(c.pointers.size()-1);
			}
			else if(c.values.get(index).compareTo(V)==0)
			{
				//System.out.println("Gyanehsnhr");
				c = c.pointers.get(index+1);
			}
			else
			{
				//System.out.println("Gyanehsehcbehd");
				c = c.pointers.get(index);
			}
		}
		if (query==1)
		{
			return c;
		}
		for(int i = 0;i < c.values.size();i++)
		{
			if(c.values.get(i).compareTo(V) == 0)
			{
				return c;
			}
		}
		return null;
	}
	public void helper()
	{
		for(int i=0;i<ar.size()-1;i++)
		{
			int min=i;
			for(int j=i+1;j<ar.size();j++)
			{
				if(ar.get(min).field.compareTo(ar.get(j).field)<0)
				{
					min = j;
				}
			}
			Record temp = ar.get(min);
			ar.set(min,ar.get(i));
			ar.set(i,temp);
		}
	}
	public void printdata(String s , ArrayList<String> arr)
	{
		for(int i=0;i<ar.size();i++)
		{
			if((!ar.get(i).flag.equals("0000"))&&ar.get(i).field.compareTo(s)==0)
			{
				System.out.println("flag "+ar.get(i).flag + "id " +ar.get(i).id + "name " + ar.get(i).name + "department " + ar.get(i).dept + "salary " +ar.get(i).salary );
			}
		}
	}
	public  void printdata2(String s1, String s2, ArrayList<String> arr)
	{
		//System.out.println(-1000);
		for(int i=0;i<ar.size();i++)
		{
			/*System.out.println(1000);
			System.out.println(ar.get(i).field);*/
			if((!ar.get(i).flag.equals("0000"))&&ar.get(i).field.compareTo(s1)>=0 && ar.get(i).field.compareTo(s2)<=0)
			{
				System.out.println("flag "+ar.get(i).flag + "id " +ar.get(i).id + "name " + ar.get(i).name + "department " + ar.get(i).dept + "salary " +ar.get(i).salary );
			}
		}
	}
	public void find_original(String V)
	{
		node n = find(V,0);
		if(n!=null)
		{
			//System.out.println(n.)
			for(int i=0;i<ar.size();i++)
			{
				if(ar.get(i).field.equals(V) && !ar.get(i).flag.equals("0000"))
				{
					System.out.println(V +" " + ar.get(i).num);
					return;
				}
			}
		}
	}
	public void find_all(String v)
	{
		node n = find(v,0);
		ArrayList ar = new ArrayList <String>();
		while(n!=null)
		{
			int i;
			for(i=0;i<n.values.size();i++)
			{

				if(n.values.get(i).equals(v))
				{
					//System.out.println(n.data_pointers.get(i) + " " + i+1);
					ar.add(n.data_pointers.get(i));
				}
				else if(n.values.get(i).compareTo(v)>0)
				{
					n = null;
					break;
				}
			}
			if(n!=null && i==n.values.size())
			{
				n = n.last_pointers;
			}
		}
		printdata(v,ar);
	}

	public void print_range(String v1 , String v2)
	{
		node n1 = find(v1,0);
		node n2 = find(v2,0);
		ArrayList ar = new ArrayList <String>();
		while(n1!=null && n2!=null && n1!=n2)
		{
			for(int i=0;i<n1.values.size();i++)
			{
				if(n1.values.get(i).compareTo(v1)>=0)
				{
					ar.add(n1.data_pointers.get(i));
				}
			}
			n1 = n1.last_pointers;
		}
		while(n2!=null)
		{
			int i;
			for(i=0;i<n2.values.size();i++)
			{

				if(n2.values.get(i).compareTo(v2)<=0)
				{
					//System.out.println(n.data_pointers.get(i) + " " + i+1);
					ar.add(n2.data_pointers.get(i));
					//return;
				}
				else if(n2.values.get(i).compareTo(v2)>0)
				{
					n2 = null;
					break;
				}
			}
			if(n2!=null && i==n2.values.size())
			{
				n2 = n2.last_pointers;
			}
		}
		printdata2(v1,v2,ar);
	}
	public void insert(String K , String P)
	{
		System.out.println(1);
		if (root == null)
		{
			root = new node(true , true);
			root.data_pointers = new ArrayList<String>();
			root.data_pointers.add(P);
			root.values.add(K);
			root.number_key+=1;
			return;
		}
		node L = find(K,1);
		//System.out.println(L.values.get(0) + "haaaaa");
		if(L.number_key < L.n) // 5 has to be replaced by n
		{
			insert_in_leaf(L,K,P);
		}
		else
		{
			node L1 = new node(false, true);
			node T = new node(false, false); // temporary node
			for(int i=0;i<L.values.size();i++)
			{
				T.values.add(L.values.get(i)); 
			}
			T.data_pointers = new ArrayList<String>();
			for(int i=0;i<L.data_pointers.size();i++)
			{
				T.data_pointers.add(L.data_pointers.get(i)); 
			}
			insert_in_leaf(T,K,P);
			// To be set L1.last pointer = L.lastpointer and L.lastpointer = L1
			L1.last_pointers = L.last_pointers;
			L.last_pointers = L1;
			L.values = new ArrayList<String>();
			L.data_pointers = new ArrayList<String>();
			for(int i=0;i<(L.n+1)/2;i++)
			{
				L.values.add(T.values.get(i));
				L.data_pointers.add(T.data_pointers.get(i));
				L.number_key = (L.n+1)/2;
				//L.is_leaf = true;
				//L.is_root = false;
			}
			L1.data_pointers = new ArrayList<String>();
			for(int i= (L.n+1)/2;i<=L.n;i++)
			{
				L1.data_pointers.add(T.data_pointers.get(i));
				L1.values.add(T.values.get(i));
				L1.number_key = L.n-((L.n+1)/2)+1;
				//L1.is_leaf = true;
				//L1.is_root = true;
			}
			String K1 = L1.values.get(0);
			insert_in_parent(L,K1,L1);
		}
		helper();
		print(root);
	}
	public void insert_in_leaf(node L , String K , String P)
	{
		if(K.compareTo(L.values.get(0))<0)
		{
			L.values.add(0,K);
			L.number_key+=1;
			L.data_pointers.add(0,P);
		}
		else
		{
			int i;
			for(i=0;i<L.values.size();i++)
			{
				if(L.values.get(i).compareTo(K)>=0)
				{
					break;
				}
			}
			L.values.add(i,K);
			L.data_pointers.add(i,P);
			L.number_key+=1;
		}
	}
	public void print(node n)
	{
		if(n==null)
		{
			return;
		}
		if(n.is_leaf)
		{
			for(int i=0;i<n.data_pointers.size();i++)
			{
				System.out.print(n.data_pointers.get(i) + " ");
			}
			System.out.println();	
		}
		else
		{	
			for(int i=0;i<n.values.size();i++)
			{
				System.out.print(n.values.get(i) + " ");
			}
			System.out.println();
			for(int i=0;i<n.pointers.size();i++)
			{
				print(n.pointers.get(i));
			}
		}

	}
	public void insert_in_parent(node N , String K1 , node N1)
	{
		if(N.is_root)
		{
			node R = new node(true,false);
			R.pointers.add(N);
			R.pointers.add(N1);
			R.number_key+=1;
			R.values.add(K1);
			N.parent = R;
			N.is_root = false;
			N1.parent = R;
			this.root = R;
			return;
		}
		node P = N.parent;
		if(P.number_key < P.n)
		{
			int i;
			for(i=0;i<P.pointers.size();i++)
			{
				if(P.pointers.get(i).equals(N))
				{
					break;
				}
			}
			P.values.add(i,K1);
			P.pointers.add(i+1,N1);
			P.number_key+=1;
			N1.parent = P;
		}
		else
		{
			node T = new node(false,false);
			for(int i=0;i<P.values.size();i++)
			{
				T.values.add(P.values.get(i)); 
			}
			T.pointers = new ArrayList<node>();
			for(int i=0;i<P.pointers.size();i++)
			{
				T.pointers.add(P.pointers.get(i)); 
			}
			//T.values = P.values;
			//T.pointers = P.pointers;
			int i;
			for(i=0;i<T.pointers.size();i++)
			{
				if(T.pointers.get(i).equals(N))
				{
					break;
				}
			}
			T.values.add(i,K1);
			T.pointers.add(i+1,N1);
			P.values = new ArrayList <String>();
			P.pointers = new ArrayList <node>();
			for(int j=0;j<=(P.n+1)/2;j++)
			{
				P.pointers.add(T.pointers.get(j));
			}
			P.number_key = (P.n+1)/2;
			for(int j=0;j<((P.n+1)/2);j++)
			{
				P.values.add(T.values.get(j));
			}
			node P1 = new node(false,false);
			String K2 = T.values.get(((P.n+1)/2));
			for(int j=(P.n+1)/2+1;j<=P.n+1;j++)
			{
				P1.pointers.add(T.pointers.get(j));
			}

			for(int j=((P.n+1)/2);j<=P.n;j++)
			{
				P1.values.add(T.values.get(j));
			}
			insert_in_parent(P,K2,P1);
		}
	}

	public void delete(String key, String name) throws IOException
	{
		/*Pointer P is not taken as parameter as it can be extracted from the node structure*/
		node n = find(key,1); /* n contains the leaf node that contains the key key*/

		/*Extracting the data pointer of the key*/
		String data_pointer = "";
		for(int i =0;i<n.values.size();i++)
		{
			if((n.values.get(i)).compareTo(key)==0)
			{
				data_pointer = n.data_pointers.get(i);
				break;
			}
		}
		/*Calling the function delete_entry() to delete the key*/
		delete_key(key , name);
	}

	public void delete_entry(node N , String key , String pointer) throws IOException
	{
		N.number_key -= 1;
		deleteKeyPointer(N,key,pointer);

		/*(N is the root and N has only one remaining child , then make the child of N the new root of the tree and delete N*/
		if(N.is_root && N.number_key==1)
		{
			node child = N.pointers.get(0); /*Child node can be accessed from the 0th pointer of its pointer list*/
			child.is_root = true; /*the child of N the new root of the tree*/
			child.parent = null; /* N has no child now it would be garbage collected*/
			N.is_root = false ;
		}

		/*N has too few values/pointers*/
		if(isTooFewPointer(N))
		{
			node Nprime = new node(false,false);
			/*if(N.pointers.get(N.pointers.size()-1) != null)*/
			node parent_of_N = N.parent;
			/*getting the index of N in parent of N*/
			int ind=0;
			boolean isPred = false;
			for(int i =0;i<N.values.size();i++)
			{
				if((N.values.get(i)).compareTo(key)==0)
				{
					ind = i ;
					break;
				}
			}
			String Kprime = N.values.get(ind);
			if(ind!=0)
			{
				node N1 = parent_of_N.pointers.get(ind-1);
				double no = (double)N1.number_key;
				if(no < ((Math.ceil(N1.n/2))+1))
				{
					Nprime = N1;
					isPred = false;
				}
			}
			else
			{
				node N2 = parent_of_N.pointers.get(ind+1);
				double no = (double)N2.number_key;
				if(no < ((Math.ceil(N2.n/2))+1))
				{
					Nprime = N2;
					isPred = true;
				}
			}
			/*Checking if (entries in N and Nprime can fit in a single node)*/
			int x = Nprime.number_key + N.number_key;
			double x1 = (double)x;
			/* Coalesce nodes */
			if(x < (N.n - 1))
			{
				/*N is a predecessor of N*/
				if(isPred)
				{
					/*swap(N,Nprime);*/
				}
			
				if(N.is_leaf == false)
				{
					for(int i =0;i<N.values.size();i++)
					{
						Nprime.values.add(N.values.get(i));
						Nprime.pointers.add(N.pointers.get(i));
						Nprime.data_pointers.add(N.data_pointers.get(i));

					}
				}
				else
				{
					for(int i =0;i<N.values.size();i++)
					{
						Nprime.values.add(N.values.get(i));
						Nprime.pointers.add(N.pointers.get(i));
						Nprime.data_pointers.add(N.data_pointers.get(i));

					}

				}
				delete_entry(parent_of_N,Kprime,N.data_pointers.get(0));
				node child = N.pointers.get(0); /*Child node can be accessed from the 0th pointer of its pointer list*/
				child.parent = null; /* N has no child now it would be garbage collected*/
			}

			/*else Redistribution: borrow an entry from Nprime */
			else
			{
				if(isPred==false)
				{
					if(N.is_leaf == false)
					{
						node m = Nprime.pointers.get(Nprime.pointers.size()-1);
						/*remove(Nprime,m-1);
						remove(Nprime,m);
						insertOfDelete(Nprime.pointers.get(Nprime.pointers.size()-1),Kprime);*/
						node p = parent_of_N.pointers.get(ind);
						p = parent_of_N.pointers.get(ind-1);						
					}
				}
				else
				{
					node m = Nprime.pointers.get(Nprime.pointers.size()-1);
					/*remove(Nprime,m);
					insertOfDelete(Nprime.pointers.get(Nprime.pointers.size()-1),Kprime);*/
					/*replace Kprime in parent(N) by Nprime .K m*/
					node p = parent_of_N.pointers.get(ind);
					p = parent_of_N.pointers.get(ind);
				}
			}
		}

	}

	public void deleteKeyPointer(node N ,String key , String pointer) throws IOException
	{
		int del = 0;
		for(int i =0;i<N.values.size();i++)
		{
			if((N.values.get(i)).compareTo(key)==0)
			{
				del = i ;
				break;
			}
		}
		N.values.remove(del);
		/*Also go to that data pointer and make the flag of the Record to be 0000 */
		/*
		code here
		*/
		N.data_pointers.remove(del);
	}

	public boolean isTooFewPointer(node N)
	{
		double no = (double)N.number_key;
		if(N.is_leaf)
		{
			
			if(no < Math.ceil((N.n-1)/2))
			{
				return true;
			}
		}
		else
		{
			if(no < ((Math.ceil(N.n/2))+1))
			{
				return true;
			}

		}
		return false;
	}

	public void delete_key(String key,String name) throws IOException
	{
		
		for(int i = 0; i<ar.size() ; i++)
		{
			if((ar.get(i).field).compareTo(key)==0 && ar.get(i).name.equals(name))
			{
				ar.get(i).flag = "0000";
				break;
			}
		}
		
	}
}

public class Btree
{
	public static void printfile(Btree1 b1) throws IOException
	{
		PrintWriter pw = new PrintWriter("data1.txt");
		for (int i = 0 ;i <b1.ar1.size() ;i++ ) 
		{
			for(int j=0 ; j<5 ; j++)
			{
				if(j==0)
				{
					pw.print(b1.ar1.get(i).flag);
				}
				if(j==1)
				{
					pw.print(b1.ar1.get(i).id);
				}
				if(j==2)
				{
					pw.print(b1.ar1.get(i).name);
				}
				if(j==3)
				{
					pw.print(b1.ar1.get(i).dept);
				}
				if(j==4)
				{
					pw.print(b1.ar1.get(i).salary);
				}
			}
			
		}

		pw.close();
	}
	public static void main(String [] args) throws IOException
	{
		InputStreamReader ir = new InputStreamReader(System.in);
		BufferedReader br = new BufferedReader(ir);
		System.out.print("Enter the Key type : 1: ID , 2: Flag");
		int s11 = Integer.parseInt(br.readLine());
		String s10;
		Btree1 b1 = new Btree1();
		
		FileInputStream fis = new FileInputStream("data.txt");
		File file = new File("data.txt");
		long len = file.length();
		int skipVal[] = {4,4,20,10,10};
		ArrayList<Record> dataSet = new ArrayList<Record>();

		int offset = 0;
		long n = len/48;
		//System.out.println(len);
		//System.out.println(n);
		int i;
			for (i = 0 ;i <n ;i++ ) 
			{
				String s1="",s2="",s3="",s4="",s5="";

				offset = (i)*48;
				for(int j=0 ; j<5 ; j++)
				{
					//System.out.println(offset);

					int l = skipVal[j];
					byte[] bs = new byte[l];
					//fis.skip(offset);
		         	int k = fis.read(bs , 0 , l);
		         	offset += l;

		         	String s = new String(bs, "UTF-8");
		         	
		         	//System.out.println(s);
		         	if(j==0)
		         	{
		         		 s1 = s;
		         	}
		         	if(j==1)
		         	{
		         		s2 =s;
		         	}
		         	if(j==2)
		         	{
		         		s3 =s;
		         	}
		         	if(j==3)
		         	{
		         		s4 =s;
		         	}
		         	if(j==4)
		         	{
		         		s5 =s;
		         	}
		        }
		        Record r = new Record(s1,s2,s3,s4,s5,String.valueOf(i*48));
		        r.field = s2;
		        b1.ar.add(r);
		        b1.ar1.add(r);
		        b1.insert(s2,String.valueOf(i*48));
		        b1.helper();
		        printfile(b1);
				dataSet.add(r);
			}
			/*b1.find_original("2563");
			//System.out.println("Aman IS kutta");
			b1.find_all("2563");*/
			//b1.print_range("2563","5678");
			/*b1.delete("2563","abcdeabcdeabcdeabcde");
			printfile(b1);
			b1.find_all("2563");*/
			int query = 1; 
			while(query!=0)
			{
				System.out.println("Menu");
				query = Integer.parseInt(br.readLine());
				switch(query)
				{
					case 1: System.out.println("Enter the key ");
							b1.find_original(br.readLine());
							break;
					case 2: System.out.println("Enter the key ");
							b1.find_all(br.readLine());
							break;
					case 3: System.out.println("Enter the two range of value ");
							s10 = br.readLine();
							StringTokenizer st = new StringTokenizer(s10);
							b1.print_range(st.nextToken(),st.nextToken());
							break;
					case 4: System.out.println("Enter the record to be inserted and that too space separated otherwise I am not going to do anything and I have not handled the null pointer error either so your choice");
							s10 = br.readLine();
							String [] temp = s10.split(" ");
							Record r = new Record(temp[0],temp[1],temp[2],temp[3],temp[4],String.valueOf(i*48));
							r.field = temp[s11];
							b1.insert(temp[s11],String.valueOf(i*48));
							i++;
							b1.ar.add(r);
					        b1.ar1.add(r);
					        b1.helper();
					        printfile(b1);
							break;
					case 5: System.out.println("Enter the record which you want to delete space separated");
							s10 = br.readLine();
							temp = s10.split(" ");
							b1.delete(temp[1],temp[2]);
							printfile(b1);
							break;
				}
			}


//       
		
		/*String ar[] = new String[]{"4","52","100","148","196","244","292","340","388","436" };
		String ar1[] = new String[]{"Aman","Aman","Bman","Cman","Aman","an","Gama","Aaaa","Baaa","Caba"};
		for(int i=0;i<10;i++)
		{
			b1.insert(ar1[i],ar[i]);
		}
		b1.find_original("Aman");*/
	}
}