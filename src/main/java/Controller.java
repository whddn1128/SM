import java.io.IOException;
import java.util.Scanner;
import java.util.Random; 
public class Controller {
	public static long User_Account;
	public static long Receiver_Account;
	private static long Password;
	private static String Category;
	public static long Input_Amount;
	private static long User_id;
	private static long User_password;
	private static long User_limit;
	private static long User_Totalmoney;
	private static long User_frequency;
	private static long Receiver_id;
	private static long Receiver_password;
	private static long Receiver_limit;
	private static long Receiver_Totalmoney;
	private static long Receiver_frequency;
	private static String U_bank;
	private static String R_bank;
	Scanner s=new Scanner(System.in);
	Bank bank=new Bank();
	Bank rbank=new Bank();
	boolean get_Account(String account_id) throws IOException
	{
		//System.out.println("input Account number");
		this.User_Account=Long.parseLong(account_id);
		if(bank.Find_info(this.User_Account)!=null)
		{
			U_bank=bank.Find_info(this.User_Account);
			Account account=new Account();
			this.User_password=account.password();
			this.User_Totalmoney=account.remains();
			this.User_limit=account.Limit();
			this.User_frequency=account.frequency();
			System.out.println(this.User_password);
			return true;
		}
		else
		{
			return false;
		}
		
		
	}
	boolean get_ReceiverAccount(String account_id) throws IOException
	{
	//	System.out.println("input Reciever Account number");
		this.Receiver_Account=Long.parseLong(account_id);
		if(rbank.Find_info(this.Receiver_Account)!=null)
		{
		R_bank=rbank.Find_info(this.Receiver_Account);
		Account raccount=new Account();
		this.Receiver_password=raccount.password();
		this.Receiver_Totalmoney=raccount.remains();
		this.Receiver_limit=raccount.Limit();
		this.Receiver_frequency=raccount.frequency();
		return true;
		}
		else
		{
			return false;
		}
		
	}
	boolean CheckPassword(long Password)
	{
		if(Password==this.User_password)
		{
			return true;
		}
		else
		{
			return false;
		}
	}
	boolean input_Password(String password)
	{
		boolean check;
		while(true)
		{
			try{
		this.Password=Long.parseLong(password);
			}
			catch(java.lang.NumberFormatException e){
				Mainview.show("숫자만 입력가능합니다");
			}
			
		if((this.Password)>9999)
		{
			Mainview.show("4자리를 입력하세요");
		}
		
		check=CheckPassword(this.Password);
		if(check==true)
		{
			return true;
		}
		else if(check==false)
		{	
			return false;
		}
		}
		
	}
	
	long input_Amount(String amount)
	{
		Input_Amount=Long.parseLong(amount);
		return this.Input_Amount;
	}
	void SetInput_Amount(long amount)
	{
		this.Input_Amount=amount;
	}
	boolean Limit_Amount()
	{
		if(Input_Amount>User_limit)
		{
			
			return false;
		}
		else
		{
			return true;
		}
	}
	boolean max()
	{
		if(Input_Amount>User_Totalmoney)
		{
			return false;
		}
		else
		{
			return true;
		}
	}
	String get_UAccount(){
		String str=String.valueOf(this.User_Account);
		return str;
	}
	long getInputAmount()
	{
			if(this.Input_Amount<0)
				return -this.Input_Amount;
			else
				return this.Input_Amount;
			
	}
	long getRAccount()
	{
		return this.Receiver_Account;
	}
	long getsumU()
	{

		return this.User_Totalmoney-this.Input_Amount;
	}
	long getsumR()
	{
		return this.Receiver_Totalmoney+this.Input_Amount;
	}
	long getUfreq()
	{
		return this.User_frequency+1;
	}
	long getRfreq()
	{
		return this.Receiver_frequency;
	}
	long getUpassword()
	{
		return this.User_password;
	}
	long getRpassword()
	{
		return this.Receiver_password;
	}
	long getUlimit()
	{
		return this.User_limit;
	}
	long getRlimit()
	{
		return this.Receiver_limit;
	}
	String getUbank()
	{
		return U_bank;
	}
	String getRbank()
	{
		return R_bank;
	}
	
	void category(String category) throws IOException
	{
		if(category.equals("send"))
		{
			Send send=new Send();
		}
		if(category.equals("withdraw"))
		{
			boolean limitcheck;
			Withdraw withdraw=new Withdraw();
			
		}
		if(category.equals("deposit"))
		{
		
		}
		if(category.equals("check remain"))
		{
		  CheckRemain checkremain=new CheckRemain();
		  checkremain.show_Amount(User_Totalmoney);
		}
	}
}
	

