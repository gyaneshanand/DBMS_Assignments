import java.io.*;
import java.util.*;

class data
{
	public String id;
	data(String id)
	{
		this.id = id;
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
public class Btree
{
	public node root;
	/*Btree()
	{
		root = new node(true , false);
	}*/
	public node find(String V)
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
		for(int i = 0;i < c.values.size();i++)
		{
			if(c.values.get(i).compareTo(V) == 0)
			{
				return c;
			}
		}
		return null;
	}
	public void insert(String K , String P)
	{
		if (root == null)
		{
			root = new node(true , true);
			root.data_pointers = new ArrayList<String>();
			root.data_pointers.add(P);
		}
		node L = find(K);
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
			for(int i=0;i<L.pointers.size();i++)
			{
				T.data_pointers.add(L.data_pointers.get(i)); 
			}
			insert_in_leaf(T,K,P);
			// To be set L1.last pointer = L.lastpointer and L.lastpointer = L1
			L.values = new ArrayList<String>();
			L.data_pointers = new ArrayList<String>();
			for(int i=0;i<(L.n+1)/2;i++)
			{
				L.values.add(T.values.get(i));
				L.data_pointers.add(T.data_pointers.get(i));
				L.number_key = (L.n+1)/2;
			}
			for(int i= (L.n+1)/2;i<=L.n;i++)
			{
				L1.data_pointers.add(T.data_pointers.get(i));
				L1.values.add(T.values.get(i));
				L1.number_key = L.n-((L.n+1)/2)+1;
			}
			String K1 = L1.values.get(0);
			insert_in_parent(L,K1,L1);
		}
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
				i++;
			}
			L.values.add(i,K);
			L.data_pointers.add(i,P);
		}
	}

	public void insert_in_parent(node N , String K1 , node N1)
	{
		if(N.is_root)
		{
			node R = new node(true,false);
			R.pointers.add(N);
			R.pointers.add(N1);
			R.number_key+=2;
			R.values.add(K1);
			N.parent = R;
			N.is_root = false;
			N1.parent = R;
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
			T.values = P.values;
			T.pointers = P.pointers;
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
			for(int j=0;j<(P.n+1)/2;j++)
			{
				P.pointers.add(T.pointers.get(j));
			}
			P.number_key = (P.n+1)/2;
			for(int j=0;j<((P.n+1)/2)-1;j++)
			{
				P.values.add(T.values.get(j));
			}
			node P1 = new node(false,false);
			String K2 = T.values.get((P.n+1)/2);
			for(int j=(P.n+1)/2;j<=P.n;j++)
			{
				P1.pointers.add(T.pointers.get(i));
			}

			for(int j=(P.n+1)/2;j<P.n;j++)
			{
				P1.values.add(T.values.get(i));
			}
			insert_in_parent(P,K2,P1);
		}
	}
}