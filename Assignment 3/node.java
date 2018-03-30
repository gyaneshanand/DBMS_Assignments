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