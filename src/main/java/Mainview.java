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
	private static int mode=0;
	private static String account_num=null;
	private static int update_setter=0;
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
        if(update_setter==1)
        {
        	 try {
     			boolean check=controller.get_Account(account_num);
     		} catch (Exception e1) {
     			e1.printStackTrace();
     		}
        }
        switch (panel) {
        		
            case "selectMenu" :
                getContentPane().add(selectMenu);
                break;
            case "inputpassword" :
            	InputPassword ip=new InputPassword();
                getContentPane().add(ip);
                break;
            case "inputreceiver":
            	InputRaccount ra=new InputRaccount();
                getContentPane().add(ra);
                break;
            case "inputamount":
            	InputAmount inputamount=new InputAmount();
                getContentPane().add(inputamount);
                break;
            case "limit":
            	LimitCheck limit=new LimitCheck();
                getContentPane().add(limit);
                break;
            case "statement":
            	Printstatement statement=new Printstatement();
                getContentPane().add(statement);
                break;
            case "print":
            	Print print=new Print();
                getContentPane().add(print);
                break;
            case "payback":
            	PayBack payback=new PayBack();
                getContentPane().add(payback);
                break;
            case "inputaccount":
                getContentPane().add(inputaccount);
                break;
            case "fcheck":
            	FrequencyCheck fcheck=new FrequencyCheck();
            	getContentPane().add(fcheck);
            	break;
            case "rstatement":
            	RemainStatement rstatement=new RemainStatement();
            	getContentPane().add(rstatement);
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
	private class RemainStatement extends JPanel{
		public RemainStatement(){
			setLayout(null);
			setBackground(bgColor);
			setForeground(fontColor);
			
			Inputwindow statementwindow = new Inputwindow("잔액을 확인합니다",50);
			add(statementwindow);
			
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				CheckRemain remain=new CheckRemain();
				Mainview.show(remain.Print_Total_Amount());
				nextPanel("selectMenu");
				update_setter=1;
			});
			add(next);
		}
	}
	private class Printstatement extends JPanel{
		public Printstatement(){
			setLayout(null);
			setBackground(bgColor);
			setForeground(fontColor);
			
			Inputwindow statementwindow = new Inputwindow("영수증을 뽑으시겠습니까? Y/N",50);
			add(statementwindow);
			Input answer=new Input(160);
			add(answer);
			Inputwindow paybackchoicewindow=new Inputwindow("페이벡 종류를 번호로 선택하세요.",90);
			add(paybackchoicewindow);
			Input choice=new Input(200);
			add(choice);
			
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				PrintStatement p=new PrintStatement();
				Payback pay=new Payback();
				int process=0;
				long result=pay.Check_Payback((controller.getUfreq()-1));
				if(mode==3)
				{
					result=0;
				}
				if(result>=1)
				{
					
					if(choice.getText().equals("1")||choice.getText().equals("2"))
					{
						pay.Choose_Gift_code(result,Long.parseLong(choice.getText()));
						process=1;
					}
					else if(choice.getText()==""){
						process=2;
						nextPanel("statement");
					}
					else
					{
						process=2;
						Mainview.show("정확한 옵션을 선택해주세요");
						nextPanel("statement");
					}
					
				}
				else
				{
					process=1;
				}
				p.set_Amount(controller.getsumU(),controller.getInputAmount());
				Bank bank=new Bank();
				Bank rbank=new Bank();
				try {
					bank.update_Account(Long.parseLong(controller.get_UAccount()),controller.getsumU(),controller.getUfreq(),controller.getUpassword(),controller.getUlimit(),controller.getUbank());
					
						if(mode==1)
						{
							rbank.update_Account(controller.getRAccount(),controller.getsumR(),controller.getRfreq(),controller.getRpassword(),controller.getRlimit(),controller.getRbank());
						}
					}
					catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				int YN=p.Get_Answer(answer.getText());
				if(process==1)
				{
				if(YN==1)
					nextPanel("print");
				else if(YN==2)
				{
					if(result>=1)
					{
						Mainview.show("페이벡이 있으므로 영수증이 자동으로 뽑힙니다");
						nextPanel("print");
					}
					else
					{
					Mainview.this.show("거래가 끝났습니다");
					update_setter=1;
					nextPanel("selectMenu");
					}
					
				}
				else
				{
					Mainview.show("Y/N만 선택해주세요");
					nextPanel("statement");
				}
				}
				else
				{
					nextPanel("statement");
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
			Inputwindow inputwindow=new Inputwindow("영수증을 보시려면 다음을 누르세요",80);
			add(inputwindow);
			
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				String str=p.Print_Statement();
				Mainview.show(str);
				update_setter=1;
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
			
			Inputwindow paybackwindow=new Inputwindow("페이벡은 다음을 눌러서 확인해주세요",80);
			add(paybackwindow);
			
			
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				Payback p=new Payback();
				int result=p.Check_Payback(controller.getUfreq()-1);
				if(result==0)
				{
					Mainview.show("요건 충족이 안됩니다");
				}
				else if(result==1)
				{
					Mainview.show("1=커피 2=탄산음료");
				}
				else if(result==2)
				{
					Mainview.show("1=김밥  2=도시락");
				}
				else if(result==3)
				{
					Mainview.show("1=조각피자 2=조각 케이크 ");
				}
				update_setter=0;
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
		
			Inputwindow limitcheckwindow = new Inputwindow("한도를 확인합니다",80);
			add(limitcheckwindow);
			
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				update_setter=0;
				boolean limitcheck=controller.Limit_Amount();
				boolean max=controller.max();
				if(controller.getUlimit()<=0)
				{
					Mainview.show("한도가 없는 계좌 입니다.");
					
					if(max==true)
					{
						nextPanel("payback");
					}
					else
					{
						Mainview.show("잔액부족입니다");
						nextPanel("inputamount");
					}
				}
				else
				{
				if(limitcheck==true)
				{
					Mainview.show("한도초과가 아닙니다");
					if(max==true)
					{
						nextPanel("payback");
					}
					else
					{
						Mainview.show("잔액부족입니다");
						nextPanel("inputamount");
					}
				}
				else
				{
					Mainview.show("한도초과 입니다");
					nextPanel("inputamount");
				}
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
			
			Inputwindow accountwindow = new Inputwindow("송금 대상 계좌를 입력하세요",80);
			add(accountwindow);
			
			Input accountinput= new Input(190);
			add(accountinput);
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				try{
					boolean check=true;
					if(Long.parseLong(accountinput.getText())<10000||Long.parseLong(accountinput.getText())>=60000)
					{
						Mainview.show("계좌번호 형식이 잘못 됬습니다.다시 입력하세요");
						nextPanel("inputreceiver");
					}
					else
					{
					try {
						check=controller.get_ReceiverAccount(accountinput.getText());
					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
					if(check==true)
					{
					if(accountinput.getText().equals(controller.get_UAccount()))
					{
						
						Mainview.show("본인 계좌로는 송금이 불가능 합니다");
						nextPanel("inputreceiver");
					}
					else
					{
						try {
							controller.get_ReceiverAccount(accountinput.getText());
						} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
						}
						nextPanel("inputamount");
					}
					}
					else
					{
						Mainview.show("해당 계좌는 조회가 되지 않습니다");
					}
					} 
				}catch(java.lang.NumberFormatException e4)
				{
					Mainview.show("입력하신 값이 너무 큽니다 다시 입력하세요");
					nextPanel("inputreceiver");
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
			
			Inputwindow inputamount = new Inputwindow("금액을 입력하세요",80);
			add(inputamount);
			
			Input amount=new Input(190);
			add(amount);
			
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				update_setter=0;
				try{
				if(Long.parseLong(amount.getText())<=0)
				{
					Mainview.show("0이하의 금액은 입력이 불가능합니다");
					nextPanel("inputamount");
				}
				else{
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
				nextPanel("statement");
				}
				}
				}catch(java.lang.NumberFormatException e4)
				{
					Mainview.show("입력하신 값이 너무 큽니다 다시 입력하세요");
					nextPanel("inputamount");
				}
			});
			add(next);
			
		}
	}
	private class FrequencyCheck extends JPanel{
		public FrequencyCheck(){
			setLayout(null);
			setBackground(bgColor);
			setForeground(fontColor);
			Inputwindow fcheck=new Inputwindow("사용 횟수를 조회합니다",80);
			add(fcheck);
			
			NextButton next=new NextButton();
			next.addActionListener((ActionEvent e)->{
				Mainview.show("사용횟수는 "+String.valueOf(controller.getUfreq())+"입니다");
				update_setter=0;
				nextPanel("selectMenu");
			});
			add(next);
		}
	}
	private class InputAccount extends JPanel {
		
		public InputAccount(){
			setLayout(null);
			setBackground(bgColor);
			setForeground(fontColor);
			
			Inputwindow accountwindow = new Inputwindow("계좌를 입력하세요",80);
			add(accountwindow);
			
			Input accountinput= new Input(190);
			add(accountinput);
			
			NextButton next= new NextButton();
			next.addActionListener((ActionEvent e)->{
				boolean check=true;
				try{
					if(Long.parseLong(accountinput.getText())<10000||Long.parseLong(accountinput.getText())>=60000)
					{
						Mainview.show("계좌번호 형식이 잘못 됬습니다.다시 입력하세요");
						nextPanel("inputaccount");
					}
				
					else
					{
						try {
							account_num=accountinput.getText();
							check=controller.get_Account(account_num);
						} catch (Exception e2) {
							e2.printStackTrace();
						}
						try {
							if(check==true)
								nextPanel("selectMenu");
							else if(check==false)
							{
								Mainview.show("해당 계좌는 존재하지 않습니다");
								nextPanel("inputaccount");
							}
							} catch (Exception e1) {
							// TODO Auto-generated catch block
							e1.printStackTrace();
							}
					}}
				catch(java.lang.NumberFormatException e4)
				{
					Mainview.show("입력하신 값이 너무 큽니다 다시 입력하세요");
					nextPanel("inputaccount");
				}
			});
			add(next);
			
		}
	}
    private class NextButton extends JButton {
        private NextButton() {
            this("다음");
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
			
			Inputwindow password=new Inputwindow("비밀번호 입력",80);
			add(password);
			JPasswordField pwInput = new JPasswordField();
			pwInput.setBounds(400,190,300,50);
			pwInput.setBackground(bgColor);
            pwInput.setForeground(fontColor);
            pwInput.setBorder(BorderFactory.createMatteBorder(0, 0, 2, 0, Color.ORANGE));
            add(pwInput);
       
            NextButton next=new NextButton();
            next.addActionListener((ActionEvent e)->{
            	update_setter=0;
            	String str=String.valueOf(pwInput.getPassword());
            	boolean OK=controller.input_Password(str);
            	if(OK==true)
            	{
            	Mainview.this.show("비밀번호 확인!");
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
            	Mainview.this.show("비밀번호 틀림");
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
			MenuButton send= new MenuButton("이체",20);
			send.addActionListener((ActionEvent e) ->{
				update_setter=0;
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
			
			MenuButton withdraw=new MenuButton("출금",70);
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
			
			MenuButton deposit=new MenuButton("입금",120);
			deposit.addActionListener((ActionEvent e)->{
				try {
					mode=3;
					Commission c=new Commission();
					c.set_commissionzero();
					controller.category("deposit");
					nextPanel("inputamount");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			add(deposit);
			
			MenuButton checkremain=new MenuButton("잔액조회",170);
			checkremain.addActionListener((ActionEvent e)->{
				try {
					mode=4;
					controller.category("check remain");
					nextPanel("rstatement");
				} catch (Exception e1) {
					// TODO Auto-generated catch block
					e1.printStackTrace();
				}
			});
			add(checkremain);
			
			MenuButton fcheck=new MenuButton("사용횟수 조회",220);
			fcheck.addActionListener((ActionEvent e)->{
				nextPanel("fcheck");
			});
			add(fcheck);
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

 