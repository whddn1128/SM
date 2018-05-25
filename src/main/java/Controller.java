import java.io.IOException;
import java.util.Scanner;
import java.util.Random; 
public class Controller {
	public static int User_Account;
	public static int Receiver_Account;
	private static int Password;
	private static String Category;
	public static int Input_Amount;
	private static int User_id;
	private static int User_password;
	private static int User_limit;
	private static int User_Totalmoney;
	private static int User_frequency;
	private static int Receiver_id;
	private static int Receiver_password;
	private static int Receiver_limit;
	private static int Receiver_Totalmoney;
	private static int Receiver_frequency;
	private static String U_bank;
	private static String R_bank;
	Scanner s=new Scanner(System.in);
	Bank bank=new Bank();
	Bank rbank=new Bank();
	void get_Account(String account_id) throws IOException
	{
		//System.out.println("input Account number");
		this.User_Account=Integer.parseInt(account_id);
		
		U_bank=bank.Find_info(this.User_Account);
		Account account=new Account();
		this.User_password=account.password();
		this.User_Totalmoney=account.remains();
		this.User_limit=account.Limit();
		this.User_frequency=account.frequency();
		System.out.println(this.User_password);
		
		
	}
	void get_ReceiverAccount(String account_id) throws IOException
	{
	//	System.out.println("input Reciever Account number");
		this.Receiver_Account=Integer.parseInt(account_id);
		R_bank=rbank.Find_info(this.Receiver_Account);
		Account raccount=new Account();
		this.Receiver_password=raccount.password();
		this.Receiver_Totalmoney=raccount.remains();
		this.Receiver_limit=raccount.Limit();
		this.Receiver_frequency=raccount.frequency();
		
	}
	boolean CheckPassword(int Password)
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
		this.Password=Integer.parseInt(password);
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
	
	int input_Amount(String amount)
	{
		Input_Amount=Integer.parseInt(amount);
		return this.Input_Amount;
	}
		void SetInput_Amount(int amount)
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
	int getInputAmount()
	{
		return this.Input_Amount;
	}
	int getRAccount()
	{
		return this.Receiver_Account;
	}
	int getsumU()
	{

		return this.User_Totalmoney-this.Input_Amount;
	}
	int getsumR()
	{
		return this.User_Totalmoney+this.Input_Amount;
	}
	int getUfreq()
	{
		return this.User_frequency+1;
	}
	int getRfreq()
	{
		return this.Receiver_frequency;
	}
	int getUpassword()
	{
		return this.User_password;
	}
	int getRpassword()
	{
		return this.Receiver_password;
	}
	int getUlimit()
	{
		return this.User_limit;
	}
	int getRlimit()
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
	

