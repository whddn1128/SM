
public class CheckRemain {
	private static int Remain;
	void show_Amount(int amount)
	{
		Check_TotalAmount(amount);
	}
	void Check_TotalAmount(int amount)
	{
		this.Remain=amount;
	}
	String Print_Total_Amount()
	{
		String str="your remain money : "+Remain;
		return str;
	}
}
