
public class Account extends Bank{
	private static long Account;
	public static long Password;
	public static long Total_Amount;
	public static long Limit_Amount;
	public static long Use_frequency;
	
	public long account(){//load�� Ȱ���ؼ� ��� �������� ������ �ȳ��� ������ �׳� �������
		this.Account=info[0];//controller�� ���ε��� �𸣰����� �ű⼭ �̰� �ϳ��� �ҷ��� �μ� �����ؾ��Ѵ�
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
