
public class Send {
	Commission commission=new Commission();
	public static int Amount;
	public static int Receiver_Account;
	
	int get_Amount(int amount){
		int result;
		this.Amount=amount;
		result=send_Amount();
		return result;
	}
	int send_Amount(){
		commission.get_Commission(Amount);
		this.Amount=commission.get_TotalAmount();
		return this.Amount;
	}
}
