import java.io.IOException;
import java.util.Scanner;

public class Main {

	public static void main(String[] args) throws IOException, InterruptedException {
		// TODO Auto-generated method stub
	Controller c=new Controller();
	String category;
	
	c.get_Account();
	System.out.println("type in the menu=> send withdraw deposit check remain");
	Scanner s=new Scanner(System.in);
	category=s.nextLine();
	c.category(category);
	s.close();

	}

}
 