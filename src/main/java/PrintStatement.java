import java.util.Scanner;
public class PrintStatement {
	private static long Changed_Amount;
	private static long Exchanged_Amount;
	private static long Commission;
	
	int Get_Answer(String answer)
	{
		//Scanner s=new Scanner(System.in);
		//System.out.println("would you print the reciept? press Y/N");
		//answer=s.nextLine();
		if(answer.equals("Y")||answer.equals("y"))
		{
			return 1;
			//Print_Statement(Changed_Amount,Exchanged_Amount);
		}
		else if(answer.equals("N")||answer.equals("n"))
		{
			return 2;
			//System.out.println("proecdure complete");
		}
		else
		{
			return 3;
		}
		
	}
	String Print_Statement()
	{
		int g_code;
		set_commission();
		Payback payback=new Payback();
		g_code=payback.get_Gift_code();
		String print="Changed_Amount="+this.Changed_Amount+"    Exchanged_Amount="+this.Exchanged_Amount+"(¼ö¼ö·á:"+Commission+")"+"\n Payback Gift_code="+g_code;
		return print;
		
	}
	void set_Amount(long changed_Amount, long exchanged_amount)
	{
		this.Changed_Amount=changed_Amount;
		this.Exchanged_Amount=exchanged_amount;
	}
	void set_commission()
	{
		Commission c=new Commission();
		this.Commission=c.get_commission();
	}
}
