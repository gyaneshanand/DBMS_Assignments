import java.io.*;
import java.util.*;
class data
{
	public String id;
	data1(String id)
	{
		this.id = id;
	}
}
class node
{
	ArrayList <String> values;
	ArrayList <node> pointers;
	ArrayList <node> data_pointers;
	node parent;
	int number_key;
	boolean is_root;
	boolean is_leaf;
	node(boolean is_root , boolean is_leaf)
	{
		values = new ArrayList <String> ();
		pointers = new <node>();
		parent = null;
		number_key = 0;
		this.is_root = is_root;
		this.is_leaf = is_leaf;
	}
	node(boolean is_root , boolean is_leaf, node parent)
	{
		values = new ArrayList <String> ();
		pointers = new <node>();
		this.parent = parent;
		number_key = 0;
		this.is_root = is_root;
		this.is_leaf = is_leaf;
	}
}
	public node find(String V)
	{
		node c = root;
		while(!c.is_leaf)
		{
			int index;
			for(index = 0;index < c.values.size();index++)
			{
				if(c.values.get(index).compare(V) < 0)
				{
					index ++;
				}
			}
			if(index == c.values.size())
			{
				c = c.pointers.get(c.pointers.size()-1);
			}
			else if(c.values.get(index).compare(V)==0)
			{
				c = c.pointers.get(index+1);
			}
			else
			{
				c = c.pointers.get(index);
			}
			for(int i = 0;i < c.values.size();i++)
			{
				if(c.values.get(i).compare(V) == 0)
				{
					return c;
				}
			}
			return null;
		}
	}