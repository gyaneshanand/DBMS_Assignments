/*AMAN ROY 2016011
GYANESH ANAND*/
import java.io.*;
import java.util.*;

class randomDataGenerator
{
	static int randomNo(int Min,int Max)
	{
		int n = 0 + (int)(Math.random()* ( Max - Min + 1));
		return n;
	}

	static String randomName(int fi)
	{
		int n = randomNo(4,8);
		char[] chars = "abc_defgh_ijklmnopqrstuvwxyzABCDEFG_HIJKLMNOPQRSTU_VWXYZ ".toCharArray();
		StringBuilder sb = new StringBuilder(n);
		Random random = new Random();
		for (int i = 0; i < n; i++) {
		    char c = chars[random.nextInt(chars.length)];
		    sb.append(c);
		}
		String s = sb.toString()+String.valueOf(fi);
		System.out.println(s);
		return s;
	}

	public static void main(String[] args) throws IOException
	{
		int d=1000;
		String path = System.getProperty("user.dir");
		PrintWriter w = new PrintWriter(path +"/"+ "Input.txt", "UTF-8");
		String tranx[] = {"reserve","cancel","my_flights","total_reservation","transfer"};
		String flights[] = {"Flight1","Flight2","Flight3","Flight4","Flight5"};
		String names[] = new String[d];
		names[0] = "Aman" ;
		int nd = 0;
		int f1=0,f2=0,f3=0,f4=0,f5=0;

		for(int x=1;x<=d;x++)
		{
			String s = "";
			int type = randomNo(0,4);
			int f,fi;

			if(type == 0)
			{
				s = s+tranx[type];
				f = randomNo(0,4);
				if(f==1)
				{
					fi=1;
					f1++;
				}
				else if(f==2)
				{
					fi=2;
					f2++;	
				}
				else if(f==3)
				{
					fi=3;
					f3++;	
				}
				else if(f==4)
				{
					fi=4;
					f4++;	
				}
				else
				{
					fi=5;
					f5++;	
				}
				String name = randomName(fi);
				nd++;
				names[nd] = name;
				System.out.println(names[nd]+ " *");
				
				s = s +" "+ flights[f] + " " + name;
			}
			else if(type==1)
			{
				s = s+tranx[type];
				f = randomNo(0,4);
				int k =randomNo(0,nd);
				s = s +" "+ flights[f] + " " +  names[k] ;
			}
			else if(type==2)
			{
				s = s+tranx[type];
				f = randomNo(0,4);
				s = s +" "+ flights[f];
			}
			else if(type==3)
			{
				s = s+tranx[type];
			}
			else
			{
				s = s+tranx[type];
				f = randomNo(0,4);
				s = s +" "+ flights[f];

				f = randomNo(0,4);
				int k =randomNo(0,nd);
				s = s +" "+ flights[f] + " " + names[k]  ;



			}
			w.println(s);
		}
		w.close();
	}
}
