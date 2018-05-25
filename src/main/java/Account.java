public class Account extends Bank{
	private static int Account;
	public static int Password;
	public static int Total_Amount;
	public static int Limit_Amount;
	public static int Use_frequency;
	
	public int account(){
		this.Account=info[0];
		return this.Account; 
	}
	public int password(){
		this.Password=info[1];
		return this.Password;
	}
	public int remains(){
		this.Total_Amount=info[2];
		return this.Total_Amount;
	}
	public int Limit(){
		this.Limit_Amount=info[3];
		return this.Limit_Amount;
	}
	public int frequency(){
		this.Use_frequency=info[4];
		return this.Use_frequency;
	}
}