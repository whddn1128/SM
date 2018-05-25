import java.util.Scanner;
public class PrintStatement {
	public static int Changed_Amount;
	public static int Exchanged_Amount;
	
	boolean Get_Answer(String answer)
	{
		//Scanner s=new Scanner(System.in);
		//System.out.println("would you print the reciept? press Y/N");
		//answer=s.nextLine();
		if(answer.equals("Y"))
		{
			return true;
			//Print_Statement(Changed_Amount,Exchanged_Amount);
		}
		else
		{
			return false;
			//System.out.println("proecdure complete");
		}
		
	}
	String Print_Statement()
	{
		int g_code;
		Payback payback=new Payback();
		g_code=payback.get_Gift_code();
		String print="Changed_Amount="+this.Changed_Amount+"Exchanged_Amount="+this.Exchanged_Amount+"\nGift_code"+g_code;
		return print;
		
	}
	void set_Amount(int changed_Amount, int exchanged_amount)
	{
		this.Changed_Amount=changed_Amount;
		this.Exchanged_Amount=exchanged_amount;
	}
}
