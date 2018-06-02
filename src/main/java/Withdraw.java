
public class Withdraw {
	private static long Amount;
	
	long get_Amount(long amount){
		long result;
		this.Amount=amount;
		result=send_Amount();
		return result;
	}
	long send_Amount()
	{
		Commission commission=new Commission();
		commission.get_Commission(Amount);
		this.Amount=commission.get_TotalAmount();
		return this.Amount;	
	}
}
