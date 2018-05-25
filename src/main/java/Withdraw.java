public class Withdraw {
	private static int Amount;
	
	int get_Amount(int amount){
		int result;
		this.Amount=amount;
		result=send_Amount();
		return result;
	}
	int send_Amount()
	{
		Commission commission=new Commission();
		commission.get_Commission(Amount);
		this.Amount=commission.get_TotalAmount();
		return this.Amount;	
	}
}