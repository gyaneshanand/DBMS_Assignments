import java.io.*;
import java.util.*;

class node
{
	ArrayList <String> values; /*The keys/values stored in the node*/
	ArrayList <node> pointers; /*The pointers to the child node */
	ArrayList <String> data_pointers; /*The pointers to the data element in the file in case of leaf nodes*/

	node parent; /*The parent of current node*/
	int number_key;
	boolean is_root; 
	boolean is_leaf;

	int n = 5;

	node(boolean is_root , boolean is_leaf)
	{
		values = new ArrayList <String> ();
		pointers = new ArrayList <node>();
		parent = null;
		number_key = 0;
		this.is_root = is_root;
		this.is_leaf = is_leaf;
	}
	node(boolean is_root , boolean is_leaf, node parent)
	{
		values = new ArrayList <String> ();
		pointers = new ArrayList <node>();
		this.parent = parent;
		number_key = 0;
		this.is_root = is_root;
		this.is_leaf = is_leaf;
	}
}

class Record
{
	public String flag;
	public String id;
	public String name;
	public String dept;
	public String salary;

	Record(String s1 ,String s2 ,String s3 ,String s4 ,String s5)
	{
		this.flag =s1;
		this.id =s2;
		this.name =s3;
		this.dept =s4;
		this.salary =s5;

	}
}

class delete2
{
	public node root;
	
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
					break;
				}
			}
			if(index == c.values.size())
			{
				c = c.pointers.get(c.pointers.size()-1);
			}
			else if(c.values.get(index).compareTo(V)==0)
			{
				c = c.pointers.get(index+1);
			}
			else
			{
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

	public void delete(String key) throws IOException
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
		delete_entry(n , key , data_pointer);
	}

	public static void delete_entry(node N , String key , String pointer) throws IOException
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

	public static void deleteKeyPointer(node N ,String key , String pointer) throws IOException
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

	public static boolean isTooFewPointer(node N)
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

	public static void delete_key(String key) throws IOException
	{
		FileInputStream fis = new FileInputStream("data.txt");
		File file = new File("data.txt");
		long len = file.length();
		int skipVal[] = {4,4,20,10,10};
		ArrayList<Record> dataSet = new ArrayList<Record>();

		int offset = 0;
		long n = len/48;
		System.out.println(len);
		System.out.println(n);

		for (int i = 0 ;i <n ;i++ ) 
		{
			String s1="",s2="",s3="",s4="",s5="";

			offset = (i)*48;
			for(int j=0 ; j<5 ; j++)
			{
				System.out.println(offset);

				int l = skipVal[j];
				byte[] bs = new byte[l];
				//fis.skip(offset);
	         	int k = fis.read(bs , 0 , l);
	         	offset += l;

	         	String s = new String(bs, "UTF-8");
	         	
	         	System.out.println(s);
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
	        Record r = new Record(s1,s2,s3,s4,s5);
			dataSet.add(r);
		}
		for(int i = 0; i<dataSet.size() ; i++)
		{
			System.out.print(dataSet.get(i).flag+ "\t");
			System.out.print(dataSet.get(i).id+ "\t");
			System.out.print(dataSet.get(i).name+ "\t");
			System.out.print(dataSet.get(i).dept+ "\t");
			System.out.print(dataSet.get(i).salary+ "\t");
			System.out.println();
		}

		for(int i = 0; i<dataSet.size() ; i++)
		{
			if((dataSet.get(i).id).compareTo(key)==0)
			{
				dataSet.get(i).flag = "0000";
				break;
			}
		}
		for(int i = 0; i<dataSet.size() ; i++)
		{
			System.out.print(dataSet.get(i).flag+ "\t");
			System.out.print(dataSet.get(i).id+ "\t");
			System.out.print(dataSet.get(i).name+ "\t");
			System.out.print(dataSet.get(i).dept+ "\t");
			System.out.print(dataSet.get(i).salary+ "\t");
			System.out.println();
		}

		PrintWriter pw = new PrintWriter("data.txt");
		for (int i = 0 ;i <n ;i++ ) 
		{
			for(int j=0 ; j<5 ; j++)
			{
				if(j==0)
				{
					pw.print(dataSet.get(i).flag);
				}
				if(j==1)
				{
					pw.print(dataSet.get(i).id);
				}
				if(j==2)
				{
					pw.print(dataSet.get(i).name);
				}
				if(j==3)
				{
					pw.print(dataSet.get(i).dept);
				}
				if(j==4)
				{
					pw.print(dataSet.get(i).salary);
				}
			}
			
		}

		pw.close();
	}

	public static void main(String[] args) 
	{

		
	}

}