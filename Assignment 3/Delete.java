import java.io.*;
import java.util.*;

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

class Delete
{
	public static void delete(String key) throws IOException
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

	public static void main(String[] args) throws IOException
	{
		delete("5678");
	}
}

//12345678abcdeabcdeabcdeabcdedeprtdeprt1234567890