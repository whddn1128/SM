
public class CheckRemain {
	private static long Remain;
	void show_Amount(long amount)
	{
		Check_TotalAmount(amount);
	}
	void Check_TotalAmount(long amount)
	{
		this.Remain=amount;
	}
	String Print_Total_Amount()
	{
		String str="귀하의 남은 잔액은: "+Remain+" 입니다";
		return str;
	}
}
