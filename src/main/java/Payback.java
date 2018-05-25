
import java.util.Scanner;
public class Payback {
	private static int Gift_code;
	
	int check_Frequency(int frequency)
	{
	
		if(frequency>=10&&frequency<20)
		{
			return 1;
		}
		else if(frequency>=20&&frequency<30)
		{
			return 2;
		}
		else if(frequency>=30&&frequency<40)
		{
			return 3;
		}
		else
		{
			return -1;
		}
	}
	
	void Check_Payback(int frequency)
	{
		int option=0;
		if(frequency>=10)
		{
			option=check_Frequency(frequency);
			switch(option)
			{
			case -1:
				System.out.println("payback not available");
			case 1:
				Choose_Gift_code(1);break;
			case 2:
				Choose_Gift_code(2);break;
			case 3:
				Choose_Gift_code(3);break;
			}                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                                 
		}
	}
	void Choose_Gift_code(int option)
	{
		int choice=0;
		Scanner s= new Scanner(System.in);
		System.out.println("choose one you want to get");
		if(option==1)
		{
			System.out.println("1= cofee 2= beverage");
			choice=s.nextInt();
			switch(choice)
			{
			case 1:
				this.Gift_code=11; break;
			case 2:
				this.Gift_code=12; break;
				default:
					System.out.println("invalid choice choose again");
			}
		}
		else if(option==2)
		{
			System.out.println("1=piece cake 2=piece pizza");
			choice=s.nextInt();
			switch(choice)
			{
			case 1:
				this.Gift_code=21; break;
			case 2:
				this.Gift_code=22; break;
				default:
					System.out.println("invalid choice choose again");
			}
		}
		else if(option==3)
		{
			System.out.println("1=rice packet 2=5000won");
			choice=s.nextInt();
			switch(choice)
			{
			case 1:
				this.Gift_code=31; break;
			case 2:
				this.Gift_code=32; break;
				default:
					System.out.println("invalid choice choose again");
			}
		}
	}
	int get_Gift_code()
	{
		int g_code;
		g_code=add_Gift_code(this.Gift_code);
		return g_code;
	}
	int add_Gift_code(int Gift_code)
	{
		return this.Gift_code;
	}
}
