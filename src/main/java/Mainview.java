import java.io.IOException;
import java.util.Scanner;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.JTextField;
import javax.swing.WindowConstants;

public class Mainview extends JFrame {
	Controller controller=new Controller();
	private Color bgColor=new Color(250,250,250);
	private Color fontColor= Color.blue;
	SelectMenu selectMenu= new SelectMenu();
	InputAccount inputaccount=new InputAccount();
	InputPassword ip=new InputPassword();
	InputRaccount ra=new InputRaccount();
	InputAmount inputamount=new InputAmount();
	LimitCheck limit=new LimitCheck();
	Printstatement statement=new Printstatement();
	Print print=new Print();
	PayBack payback=new PayBack();
	private static int mode=0;
	public void mainview(Controller controller) throws IOException, InterruptedException {
		

		setTitle("Pay back Atm");
		setSize(1000,600);
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		
		setResizable(false);
		setVisible(true);
		getContentPane().add(inputaccount);
		
		
	
	}
	public void nextPanel(String panel) {
        getContentPane().removeAll();
        switch (panel) {
            case "selectMenu" :
                getContentPane().add(selectMenu);
                break;
            case "inputpassword" :
                getContentPane().add(ip);
                break;
            case "inputreceiver":
                getContentPane().add(ra);
                break;
            case "inputamount":
                getContentPane().add(inputamount);
                break;
            case "limit":
                getContentPane().add(limit);
                break;
            case "statement":
                getContentPane().add(statement);
                break;
            case "print":
                getContentPane().add(print);
                break;
            case "payback":
                getContentPane().add(payback);
                break;
            case "inputaccount":
                getContentPane().add(inputaccount);
                break;
            default:
                System.out.println("invalid access");
        }
        revalidate();
        repaint();
    }
	private class Inputwindow extends JLabel{
		private Inputwindow(String text, int x)
		{
			super(text);
			 setBackground(bgColor);
	         setForeground(fontColor);
	         setBounds(100, x, 500, 300);
		}
	}
	private class Input extends JTextField{
		public Input(int input)
		{
			super();
			setBackground(bgColor);
			setForeground(fontColor);
			this.setBounds(400,input,500,50);
			this.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE));
		}
	}
	private class Printstatement extends JPanel{
		public Printstatement(){
			setLayout(null);
			setBackground(bgColor);
			setForeground(fontColor);
			
			Inputwindow statementwindow = new Inputwindow("�������� �����ðڽ��ϱ�? Y/N",50);
			add(statementwindow);
			Input answer=new Input(160);
			add(answer);
			Inputwindow paybackchoicewindow=new Inputwindow("���̺� ������ ��ȣ�� �����ϼ���.",90);
			add(paybackchoicewindow);
			Inputwindow alert1=new Inputwindow("�ܾ���ȸ�� ��� ��ĭ���� �μ���",130);
			Input choice=new Input(200);
			add(choice);
			
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				PrintStatement p=new PrintStatement();
				Payback pay=new Payback();
				if(mode==4)
				{
					CheckRemain remain=new CheckRemain();
					Mainview.show(remain.Print_Total_Amount());
					nextPanel("selectMenu");
				}
				else{
				int result=pay.Check_Payback((controller.getUfreq()-1));
				if(result>=1)
				{
				pay.Choose_Gift_code(result,Integer.parseInt(choice.getText()));
				}
				p.set_Amount(controller.getsumU(),controller.getInputAmount());
				boolean YN=p.Get_Answer(answer.getText());
				if(YN==true)
					nextPanel("print");
				else
				{
					Mainview.this.show("�ŷ��� �������ϴ�");
					nextPanel("selectMenu");
				}
				}
			});
			add(next);
		}
	}
	private class Print extends JPanel{
		public Print(){
			setLayout(null);
			setBackground(bgColor);
			setForeground(fontColor);
			PrintStatement p=new PrintStatement();
			Inputwindow inputwindow=new Inputwindow("�������� ���÷��� ������ ��������",80);
			add(inputwindow);
			
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				String str=p.Print_Statement();
				Mainview.show(str);
				nextPanel("selectMenu");
			});
			add(next);
		}
	}
	private class PayBack extends JPanel{
		public PayBack(){
			setLayout(null);
			setBackground(bgColor);
			setForeground(fontColor);
			
			Inputwindow paybackwindow=new Inputwindow("���̺��� ������ ������ Ȯ�����ּ���",80);
			add(paybackwindow);
			
			
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				Payback p=new Payback();
				int result=p.Check_Payback(controller.getUfreq()-1);
				if(result==0)
				{
					Mainview.show("��� ������ �ȵ˴ϴ�");
				}
				else if(result==1)
				{
					Mainview.show("1=Ŀ�� 2=ź������");
				}
				else if(result==2)
				{
					Mainview.show("1=���  2=���ö�");
				}
				else if(result==3)
				{
					Mainview.show("1=�������� 2=���� ����ũ ");
				}
				nextPanel("statement");
				
			});
			add(next);
		}
	}
	private class LimitCheck extends JPanel{
		public LimitCheck(){
			setLayout(null);
			setBackground(bgColor);
			setForeground(fontColor);
			
			Inputwindow limitcheckwindow = new Inputwindow("�ѵ��� Ȯ���մϴ�",80);
			add(limitcheckwindow);
			
		
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				boolean limitcheck=controller.Limit_Amount();
				if(limitcheck==true)
				{
					Mainview.this.show("�ѵ��ʰ��� �ƴմϴ�");
					if(controller.max()==true)
					{
					Bank bank=new Bank();
					Bank rbank=new Bank();
					try {
						bank.update_Account(Integer.parseInt(controller.get_UAccount()),controller.getsumU(),controller.getUfreq(),controller.getUpassword(),controller.getUlimit(),controller.getUbank());
						
						if(mode==1)
						{
							rbank.update_Account(controller.getRAccount(),controller.getsumR(),controller.getRfreq(),controller.getRpassword(),controller.getRlimit(),controller.getRbank());
						}
						nextPanel("payback");
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					}
					else
					{
						Mainview.show("�ܾ׺����Դϴ�");
						nextPanel("inputamount");
					}
				}
				else
				{
					Mainview.this.show("�ѵ��ʰ� �Դϴ�");
					nextPanel("inputamount");
				}
			});
			add(next);
		}
	}
	private class InputRaccount extends JPanel{
		public InputRaccount(){
			setLayout(null);
			setBackground(bgColor);
			setForeground(fontColor);
			
			Inputwindow accountwindow = new Inputwindow("�۱� ��� ���¸� �Է��ϼ���",80);
			add(accountwindow);
			
			Input accountinput= new Input(190);
			add(accountinput);
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				try {
					controller.get_ReceiverAccount(accountinput.getText());
					nextPanel("inputamount");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			add(next);
		}
	}
	private class InputAmount extends JPanel{
		public InputAmount(){
			setLayout(null);
			setBackground(bgColor);
			setForeground(fontColor);
			
			Inputwindow inputamount = new Inputwindow("�ݾ��� �Է��ϼ���",80);
			add(inputamount);
			
			Input amount=new Input(190);
			add(amount);
			
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				if(mode==1)
				{
				Send send=new Send();
				controller.SetInput_Amount(send.get_Amount(controller.input_Amount(amount.getText())));
				nextPanel("limit");
				}
				else if(mode==2)
				{
				Withdraw withdraw=new Withdraw();
				controller.SetInput_Amount(withdraw.get_Amount(controller.input_Amount(amount.getText())));
				nextPanel("limit");
				}
				else if(mode==3)
				{
				Deposit deposit=new Deposit();
				deposit.set_Amount(controller.input_Amount(amount.getText()));
				controller.SetInput_Amount(deposit.get_Amount());
				nextPanel("payback");
				}
				
			});
			add(next);
			
		}
	}
	private class InputAccount extends JPanel {
		
		public InputAccount(){
			setLayout(null);
			setBackground(bgColor);
			setForeground(fontColor);
			
			Inputwindow accountwindow = new Inputwindow("���¸� �Է��ϼ���",80);
			add(accountwindow);
			
			Input accountinput= new Input(190);
			add(accountinput);
			
			NextButton next= new NextButton();
			next.addActionListener((ActionEvent e)->{
				
					if(Integer.parseInt(accountinput.getText())<10000||Integer.parseInt(accountinput.getText())>40000)
					{
						Mainview.show("���¹�ȣ ������ �߸� ����ϴ�.�ٽ� �Է��ϼ���");
						nextPanel("inputaccount");
					}
					else
					{
						try {
					controller.get_Account(accountinput.getText());
					nextPanel("selectMenu");
							} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
					}
			});
			add(next);
			/*NextButton next2=new NextButton();
			next2.addActionListener((ActionEvent e1)->{
				nextPanel("showinfo");
				
			});
			add(next2);*/
			
		}
	}
    private class NextButton extends JButton {
        private NextButton() {
            this("����");
        }
        private NextButton(String text) {
            super(text);
            setBounds(370,500,80,30);
            setBorder(BorderFactory.createEmptyBorder());
            setForeground(Color.white);
            setBackground(new Color(45,203,113));
            setOpaque(true);
        }
    }
    public static void show(String msg) {
        JOptionPane.showMessageDialog(null,msg);
    }
    private class InputPassword extends JPanel{
    	private InputPassword()
    	{
    		setLayout(null);
			setBackground(bgColor);
			setForeground(fontColor);
			
			Inputwindow password=new Inputwindow("��й�ȣ �Է�",80);
			add(password);
			JPasswordField pwInput = new JPasswordField();
			pwInput.setBounds(400,190,300,50);
			pwInput.setBackground(bgColor);
            pwInput.setForeground(fontColor);
            pwInput.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE));
            add(pwInput);
       
            NextButton next=new NextButton();
            next.addActionListener((ActionEvent e)->{
            	String str=String.valueOf(pwInput.getPassword());
            	boolean OK=controller.input_Password(str);
            	if(OK==true)
            	{
            	Mainview.this.show("��й�ȣ Ȯ��!");
            	if(mode==1)
            	{
            	nextPanel("inputreceiver");
            	}
            	else if(mode==2)
            	{
            		nextPanel("inputamount");
            	}
            	}
            	else
            	{	
            	Mainview.this.show("��й�ȣ Ʋ��");
            	}
            });
            add(next);
    	}
    }
	private class SelectMenu extends JPanel{
		private SelectMenu()
		{
			setLayout(null);
			setBackground(bgColor);
			
			MenuButton send= new MenuButton("��ü",20);
			send.addActionListener((ActionEvent e) ->{
				try {
					mode=1;
					controller.category("send");
					nextPanel("inputpassword");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			add(send);
			
			MenuButton withdraw=new MenuButton("���",70);
			withdraw.addActionListener((ActionEvent e)->{
				try {
					mode=2;
					controller.category("withdraw");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
				nextPanel("inputpassword");
			});
			add(withdraw);
			
			MenuButton deposit=new MenuButton("�Ա�",120);
			deposit.addActionListener((ActionEvent e)->{
				try {
					mode=3;
					controller.category("deposit");
					nextPanel("inputamount");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			add(deposit);
			
			MenuButton checkremain=new MenuButton("�ܾ���ȸ",170);
			checkremain.addActionListener((ActionEvent e)->{
				try {
					mode=4;
					controller.category("check remain");
					nextPanel("statement");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			add(checkremain);
		}
		private class MenuButton extends JButton{
			
			private MenuButton(String menu,int x)
			{
				super(menu);
				this.setBounds(250,x,500,30);
				setBackground(Color.cyan);
				setOpaque(true);
				setBorder(BorderFactory.createEmptyBorder());
			}
		}
		
	}
		
	}

 