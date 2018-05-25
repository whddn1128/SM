import java.util.Scanner;
public class PrintStatement {
	public static int Changed_Amount;
	public static int Exchanged_Amount;
	
	void Get_Answer()
	{
		String answer=null;
		Scanner s=new Scanner(System.in);
		System.out.println("would yout print the reciept? press Y/N");
		answer=s.nextLine();
		if(answer.equals("Y"))
		{
			Print_Statement(Changed_Amount,Exchanged_Amount);
		}
		else
		{
			System.out.println("proecdure complete");
		}
	}
	void Print_Statement(int changed_Amount, int Exchanged_amount)
	{
		int g_code;
		Payback payback=new Payback();
		g_code=payback.get_Gift_code();
		System.out.println("Changed_Amount="+changed_Amount);
		System.out.println("Exchanged_Amount="+Exchanged_amount);
		System.out.println("Gift_code="+g_code);
		
		
	}
	void set_Amount(int changed_Amount, int exchanged_amount)
	{
		this.Changed_Amount=changed_Amount;
		this.Exchanged_Amount=exchanged_amount;
	}
}