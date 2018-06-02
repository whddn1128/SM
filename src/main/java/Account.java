
public class Account extends Bank{
	private static long Account;
	public static long Password;
	public static long Total_Amount;
	public static long Limit_Amount;
	public static long Use_frequency;
	
	public long account(){//load로 활용해서 어떻게 구현할지 생각이 안나서 일일이 그냥 만들었다
		this.Account=info[0];//controller가 메인될지 모르겠지만 거기서 이거 하나씩 불러서 인수 저장해야한다
		return this.Account; 
	}
	public long password(){
		this.Password=info[1];
		return this.Password;
	}
	public long remains(){
		this.Total_Amount=info[2];
		return this.Total_Amount;
	}
	public long Limit(){
		this.Limit_Amount=info[3];
		return this.Limit_Amount;
	}
	public long frequency(){
		this.Use_frequency=info[4];
		return this.Use_frequency;
	}
}
