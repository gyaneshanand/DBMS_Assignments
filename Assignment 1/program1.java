import java.util.*;
import java.io.*;


class Node <T>
{
	ArrayList <T> ar;
	String type;
	int size;
	String name;
	Node(ArrayList <T> ar ,String name, String type , int size)
	{
		this.ar = ar;
		this.type = type;
		this.size = size;
		this.name = name;
	}
}
public class program1
{
	public static void main(String []args) throws IOException
	{

		BufferedReader br = new BufferedReader(new FileReader("metadata.txt"));
		FileInputStream in = new FileInputStream("Input.txt");
		FileOutputStream out = new FileOutputStream("output.txt");
		String s;
		ArrayList <Node> ar = new ArrayList <Node> ();
		while((s = br.readLine())!=null)
		{
			StringTokenizer st = new StringTokenizer(s);
			String s1 = st.nextToken();
			ArrayList temp;
			String s2 = st.nextToken();
			if(s2.equals("String"))
			{
				temp = new ArrayList <String>();
			}
			else if(s2.equals("Integer"))
			{
				temp = new ArrayList <Integer>();
			}
			else if(s2.equals("Float"))
			{
				temp = new ArrayList <Float>();
			}
			else
			{
				temp = new ArrayList <Double>();
			}
			String s3 = st.nextToken();
			Node temp1 = new Node(temp,s1,s2,Integer.parseInt(s3));
			ar.add(temp1);
		}
		int c;
		int i=0;
		while((c=in.read())!=-1)
		{
			//System.out.println((char)c);
			for(int j=0;j<ar.size();j++)
			{
				String s1 ="";
				System.out.println(ar.get(j).size);
				for(int k=0;k<ar.get(j).size;k++)
				{
					out.write(c);
					char temporary = (char) c;
					s1 = s1 + Character.toString(temporary); 
					c = in.read();
				}
				System.out.println(s1);
				if(ar.get(j).type.equals("String"))
				{
					ar.get(j).ar.add(s1);
				}
				else if(ar.get(j).type.equals("Integer"))
				{
					ar.get(j).ar.add(Integer.parseInt(s1));
				}
				else if(ar.get(j).type.equals("Float"))
				{
					ar.get(j).ar.add(Float.parseFloat(s1));	
				}
				else
				{
					System.out.println(ar.get(j).type);
					ar.get(j).ar.add(Double.parseDouble(s1));
				}
			}
			out.write(c);
		}



	}


}