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
	Record(String s1 ,String s2 ,String s3 ,String s4 ,String s5)
	{
		this.flag =s1;
		this.id =s2;
		this.name =s3;
		this.dept =s4;
		this.salary =s5;

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
		for(int i=0;i<ar1.size()-1;i++)
		{
			int min=i;
			for(int j=i+1;j<ar1.size();j++)
			{
				if(ar1.get(min).field.compareTo(ar1.get(j).field)<0)
				{
					min = j;
				}
			}
			Record temp = ar1.get(min);
			ar1.set(min,ar1.get(i));
			ar1.set(i,temp);
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
		for(int i=0;i<ar.size();i++)
		{
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
			for(int i=0;i<n.values.size();i++)
			{
				if(n.values.get(i).equals(V))
				{
					System.out.println(n.data_pointers.get(i) + " " + i+1);
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
					System.out.println(n.data_pointers.get(i) + " " + i+1);
					ar.add(n.data_pointers.get(i));
				}
				else if(n.values.get(i).compareTo(v)>0)
				{
					n = null;
					break;
				}
			}
			if(i==n.values.size())
			{
				n = n.last_pointers;
			}
		}
		printdata(v,ar);
	}

	public void print_range(String v1 , String v2)
	{
		node n1 = find(v1,0);
		node n2 = find(v1,0);
		ArrayList ar = new ArrayList <String>();
		while(n1!=n2)
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
					return;
				}
				else if(n2.values.get(i).compareTo(v2)>0)
				{
					n2 = null;
					break;
				}
			}
			if(i==n2.values.size())
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
}

public class Btree
{
	public static void main(String [] args) throws IOException
	{
		InputStreamReader ir = new InputStreamReader(System.in);
		//FileInputStream in = new FileInputStream("Input.txt");
		//FileOutputStream out = new FileOutputStream("output.txt");
		/*int c;
		int count = 0;
		String s = "";
		while((c=in.read())!=-1)
		{
			String s = "";
			char ch =(char)c;
			s +=  Character.toString(ch);
			count+=1;
			if(count%48==0)
			{

			}
		}*/
//       
		Btree1 b1 = new Btree1();
		String ar[] = new String[]{"4","52","100","148","196","244","292","340","388","436" };
		String ar1[] = new String[]{"Aman","Aman","Bman","Cman","Aman","an","Gama","Aaaa","Baaa","Caba"};
		for(int i=0;i<10;i++)
		{
			b1.insert(ar1[i],ar[i]);
		}
		b1.find_original("Aman");
	}
}