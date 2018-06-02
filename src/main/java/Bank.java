import java.io.*;
import java.util.Scanner;

public class Bank {
	private static File file;
	private static String Bank_name;
	public static long info[] = {0,0,0,0,0};
	String Find_info(long Account) throws IOException
	{
		if(Account<20000&&Account>=10000)//계좌 형식으로 이렇게 일단 만들어놓고 하면 될듯
		{
			Bank_name="Shin han.txt";
		}
		else if(Account<30000&&Account>=20000)
		{
			Bank_name="kuk min.txt";
		}
		else if(Account<40000&&Account>=30000)
		{
			Bank_name="IBK.txt";

		}
		else if(Account<50000&&Account>=40000)
		{
			Bank_name="woori.txt";
		}
		else if(Account<60000&&Account>=50000)
		{
			Bank_name="nonghyeap.txt";
		}
		this.file=new File(Bank_name);
		FileReader fReader=new FileReader(file);
		BufferedReader breader=new BufferedReader(fReader);
		String str=null;//파일 정보를 입력받기 위한 변수
		
		int i=0;
	
		while((str=breader.readLine())!=null)
		{
			long Id=Long.parseLong(str);//파일에서 읽어오는 string을 int로 변환 시키려고 한거
			if(Id==Account)//0=> account number,1=>password,2=>Remains,3=>Limit,4=>Frequency
			{			//파일을 훑으면서 맞는 계좌를 찾으면 그때부터 정보 저장 한줄 한줄
				while(i!=5)
				{	
				
				Id=Long.parseLong(str);
				info[i]=Id;
				i++;
				str=breader.readLine();
				}
			}
		}
		if(i!=5)
		{
			return null;
		}
		fReader.close();
		breader.close();
		return Bank_name;
	}
	void update_Account(long Account_id, long Changed_Amount,long Use_frequency,long password,long limit,String Bank) throws IOException
	{
		this.file=new File(Bank);
		String dummy="";
		BufferedReader br=new BufferedReader(new InputStreamReader(new FileInputStream(file)));
		String line;
		this.info[0]=Account_id;
		this.info[1]=password;
		this.info[2]=Changed_Amount;
		this.info[3]=limit;
		this.info[4]=Use_frequency;
		while((line=br.readLine())!=null)
		{
			
			long Id=Long.parseLong(line);
			if(Id==Account_id)
			{
				for(int i=0;i<4;i++)
				{
					String deldata=br.readLine();
				}
			}
			else
			{
			dummy+=(line+"\r\n");
			}
			
		}
		FileWriter fw=new FileWriter(Bank);
		for(int i=0;i<5;i++)
		{
			dummy+=(Long.toString(info[i])+"\r\n");
		}
		fw.write(dummy);
		fw.close();
		br.close();
		
	}
	}
	
