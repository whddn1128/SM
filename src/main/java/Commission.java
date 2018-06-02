import java.text.SimpleDateFormat;
import java.util.Date;
public class Commission {
	private static int Current_Time;
	public static long Commission;
	public static long Total_Amount;
	
	void get_Commission(long amount){
		check_Currenttime();
		if(Current_Time<22)
		{
			Commission=50;
		}
		else
		{
			Commission=100;
		}
		count_commission(amount);
			}
	void check_Currenttime(){
		Date date=new Date();
		SimpleDateFormat e1=new SimpleDateFormat("hh");
		String time=e1.format(date);
		Current_Time=Integer.parseInt(time);
	}
	void count_commission(long amount){
		Total_Amount=amount+Commission;
	}
	long get_TotalAmount(){
		return Total_Amount;
	}
	long get_commission()
	{
		return Commission;
	}
	void set_commissionzero()
	{
		Commission=0;
	}
}
