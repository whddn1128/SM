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
	void Print_Total_Amount()
	{
		System.out.println("your remain money id: "+Remain);
	}
}
