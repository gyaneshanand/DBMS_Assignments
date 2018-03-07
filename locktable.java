import java.util.*;
import java.io.*;

public class locktable
{
	HashMap<String , ArrayList<Integer>> map = new HashMap<String,ArrayList<Integer>>();
	locktable()
	{
		for(int i=0;i<5;i++)
		{
			map.put("Flight"+Integer.toString(i+1),new ArrayList<Integer>());
		}
	}
	public void addlock(String index , int i)
	{
		map.get(index).add(i);
	}

}