import java.io.*;
import java.util.*;

class randomDataGenerator
{
	static int numlength(int n)
	{
	    if (n == 0) return 1;
	    int l;
	    n=Math.abs(n);
	    for (l=0;n>0;++l)
	        n/=10;
	    return l;           
	}

	static int randomNo(int low,int high)
	{
		Random r = new Random();
		int Low = low;
		int High = high;
		int n = r.nextInt(High-Low) + Low;
		return n;
	}

	public static void main(String[] args) 
	{
		for(int x=1;x<=100;x++)
		{
			String s="";

			String s1;
			int n = randomNo(0,99999);
			int l = numlength(n);
			if(l==1)
			{
				s1="0000"+String.valueOf(n);
			}
			else if(l==2)
			{
				s1="000"+String.valueOf(n);
			}
			else if(l==3)
			{
				s1="00"+String.valueOf(n);
			}
			else if(l==4)
			{
				s1="0"+String.valueOf(n);
			}
			else
			{
				s1=String.valueOf(n);
			}
			

			char[] chars = "abc defgh ijklmnopqrstuvwxyzABCDEFG HIJKLMNOPQRSTU VWXYZ ".toCharArray();
			StringBuilder sb = new StringBuilder(20);
			Random random = new Random();
			for (int i = 0; i < 20; i++) {
			    char c = chars[random.nextInt(chars.length)];
			    sb.append(c);
			}
			String s2 = sb.toString();
			//System.out.println(output);
			
			//System.out.println(s);

			int fl=randomNo(0,9999);
			int fr=randomNo(0,99);
			String s3;
			String s4;
			int l1 = numlength(fl);
			if(l1==1)
			{
				s3="000"+String.valueOf(n);
			}
			else if(l1==2)
			{
				s3="00"+String.valueOf(n);
			}
			else if(l1==3)
			{
				s3="0"+String.valueOf(n);
			}
			else
			{
				s3=String.valueOf(n);
			}
			int l2 = numlength(fr);
			if(l2==1)
			{
				s4="0"+String.valueOf(n);
			}
			else
			{
				s4=String.valueOf(n);
			}
			s=s1+s2+s3+"."+s4;
			System.out.println(s);
		}





	}
}