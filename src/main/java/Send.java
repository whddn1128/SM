
public class Send {
	Commission commission=new Commission();
	public static long Amount;
	public static long Receiver_Account;
	
	long get_Amount(long amount){
		long result;
		this.Amount=amount;
		result=send_Amount();
		return result;
	}
	long send_Amount(){
		commission.get_Commission(Amount);
		this.Amount=commission.get_TotalAmount();
		return this.Amount;
	}
}
