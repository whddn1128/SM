
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
	void get_Account() throws IOException
	{
		System.out.println("input Account number");
		this.User_Account=s.nextInt();
		U_bank=bank.Find_info(this.User_Account);
		Account account=new Account();
		this.User_password=account.password();
		this.User_Totalmoney=account.remains();
		this.User_limit=account.Limit();
		this.User_frequency=account.frequency();
		
	}

	void get_ReceiverAccount() throws IOException
	{
		System.out.println("input Reciever Account number");
		this.Receiver_Account=s.nextInt();
		R_bank=rbank.Find_info(this.Receiver_Account);
		Account raccount=new Account();
		this.Receiver_password=raccount.password();
		this.Receiver_Totalmoney=raccount.remains();
		this.Receiver_limit=raccount.Limit();
		this.Receiver_frequency=raccount.frequency();
		
	}
	boolean CheckPassword(int Password)
	{
		System.out.println("your account password is "+this.User_password);
		if(Password==this.User_password)
		{
			return true;
		}
		else
		{
			System.out.println("password missmatched ");
			return false;
		}
	}
	void input_Password()
	{
		boolean check;
		while(true)
		{
		System.out.println("input password");
		this.Password=s.nextInt();
		if((this.Password)>9999)
		{
			System.out.println("input 4 digit plz");
		}
		
		check=CheckPassword(this.Password);
		if(check==true)
		{
			System.out.println("password checked");
			break;
		}
		else if(check==false)
		{
			System.out.println("try again plz");
		}
		}
		
	}
	
	void input_Amount()
	{
		int check=0;
		while(true)
		{
		System.out.println(" input Amount");
		Input_Amount=s.nextInt();
		System.out.println("you have entered ["+Input_Amount+"] is it correct? press 1 to proceed");
		check=s.nextInt();
		if(check==1)
		{
			break;
		}
		}
		
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
	void category(String category) throws IOException
	{
		if(category.equals("send"))
		{
			boolean limitcheck;
			Send send=new Send();
			input_Password();
			get_ReceiverAccount();
			while(true)
			{
			input_Amount();
			this.Input_Amount=send.get_Amount(this.Input_Amount);
			limitcheck=Limit_Amount();	
			if(limitcheck==false)
			{
				System.out.println("your Limit is:"+User_limit+" check the value again");
			}
			else if(limitcheck==true)
			{
				System.out.println("proceeding... wait a sec plz");break;
			}
			}
			bank.update_Account(this.User_Account,this.User_Totalmoney-this.Input_Amount,this.User_frequency+1,U_bank);
			rbank.update_Account(Receiver_Account,Receiver_Totalmoney+Input_Amount,Receiver_frequency,R_bank);
			Payback payback=new Payback();
			payback.Check_Payback(User_frequency);
			PrintStatement p=new PrintStatement();
			p.set_Amount(this.User_Totalmoney-this.Input_Amount,this.Input_Amount);
			p.Get_Answer();
		}
		if(category.equals("withdraw"))
		{
			boolean limitcheck;
			Withdraw withdraw=new Withdraw();
			System.out.println(this.User_Account);
			System.out.println(this.User_password);
			System.out.println(this.User_Totalmoney);
			System.out.println(this.User_limit);
			System.out.println(this.User_frequency);
			input_Password();
			while(true)
			{
			input_Amount();
			this.Input_Amount=withdraw.get_Amount(this.Input_Amount);
			limitcheck=Limit_Amount();
			if(limitcheck==false)
			{
				System.out.println("your Limit is:"+User_limit+" check the value again");
			}
			else if(limitcheck==true)
			{
				System.out.println("proceed");break;
			}
			}
			bank.update_Account(this.User_Account,this.User_Totalmoney-this.Input_Amount,this.User_frequency+1,U_bank);
			Payback payback=new Payback();
			payback.Check_Payback(User_frequency);
			PrintStatement p=new PrintStatement();
			p.set_Amount(this.User_Totalmoney-this.Input_Amount,this.Input_Amount);
			p.Get_Answer();
		}
		if(category.equals("deposit"))
		{
			Deposit deposit=new Deposit();
			input_Amount();
			deposit.get_Amount(this.Input_Amount);
			this.Input_Amount=deposit.send_Amount();
			bank.update_Account(this.User_Account,this.User_Totalmoney+this.Input_Amount,this.User_frequency+1,U_bank);
			Payback payback=new Payback();
			payback.Check_Payback(User_frequency);
			PrintStatement p=new PrintStatement();
			p.set_Amount(this.User_Totalmoney+this.Input_Amount,this.Input_Amount);
			p.Get_Answer();
		}
		if(category.equals("check remain"))
		{
			CheckRemain checkremain=new CheckRemain();
			checkremain.show_Amount(User_Totalmoney);
			checkremain.Print_Total_Amount();
			Payback payback=new Payback();
			payback.Check_Payback(User_frequency);
			PrintStatement p=new PrintStatement();
			p.set_Amount(this.User_Totalmoney+this.Input_Amount,this.Input_Amount);
			p.Get_Answer();
		}
	}
}
