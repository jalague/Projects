package Game;


import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.text.BadLocationException;

import java.awt.GridLayout;
import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.Enumeration;
import java.util.HashMap;

import javax.swing.JSplitPane;
import javax.swing.JLabel;
import javax.swing.JScrollBar;
import javax.swing.JList;
import javax.swing.JMenuBar;
import java.awt.Button;
import javax.swing.JCheckBox;
import javax.swing.JTextPane;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.JTextField;
import javax.swing.JRadioButton;
import javax.swing.JTextArea;
import javax.swing.JRadioButtonMenuItem;
import javax.swing.AbstractButton;
import javax.swing.ButtonGroup;

public class MainGui extends JFrame {

	private JPanel contentPane;
	private final ButtonGroup buttonGroup = new ButtonGroup();
	private final ButtonGroup buttonGroup_1 = new ButtonGroup();
	protected HashMap<String, String> numbers=null;
	
	boolean intervals =false;
	
	GameThread currentThread=null;
	class GameThread extends Thread {
		RunGame r;
		
		ArrayList<String> ds;
		ArrayList<String> ms;
		int gT;
		int d;
		HashMap<String,String> nums;
		boolean is;
		public GameThread(ArrayList<String> drinks,ArrayList<String> mixers,int gameTime, int delay, boolean intervals,HashMap<String,String> numbers) 
		{
			ds=drinks;
			ms=mixers;
			gT=gameTime;
			d=delay;
			nums=numbers;
			is=intervals;
		}
		public void run() {
			try {
				 r= new RunGame(ds, ms, gT, d, is, nums);
			}
			catch(Exception e) {
			e.getMessage();
			}
		}
	} 
	
	
	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			
			public void run() {
				
				try {
					
					MainGui frame = new MainGui();
					
					 frame.setDefaultCloseOperation (MainGui.EXIT_ON_CLOSE);
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public MainGui() {
		
		
		 
	
		System.out.println("Hello, Welcome to Chugg!");
		System.out.println();
		System.out.println("Let's begin by setting up the game and then we'll get to Chuggin");
		
		ArrayList<String> drinks	=	new ArrayList<String>();

		ArrayList<String> mixers= new ArrayList<String>();
		
		
	//	boolean intervals =false;
		
		
		
		
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 529, 1050);
		contentPane = new JPanel();
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		
		JLabel lblHelloAndWelcome = new JLabel("Hello, and welcome to Chugg!");
		lblHelloAndWelcome.setBounds(12, 13, 182, 16);
		contentPane.add(lblHelloAndWelcome);
		
		Button begin = new Button("BEGIN!");
		begin.setFont(new Font("Dialog", Font.BOLD, 13));
		begin.setForeground(Color.BLACK);
		begin.setBackground(Color.GREEN);
		begin.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent arg0) {
				 Enumeration<AbstractButton> group = buttonGroup.getElements();
				String intervalString="12";
				while(group.hasMoreElements()){
					AbstractButton elem = group.nextElement();
					if(elem.isSelected()){
						intervalString= elem.getLabel();
					}
					
				}
				Enumeration<AbstractButton> group1 = buttonGroup_1.getElements();
				String gameTimeST="60";
				while(group1.hasMoreElements()){
					AbstractButton elem = group1.nextElement();
					if(elem.isSelected()){
						gameTimeST= elem.getLabel();
					}
						
				}
				
				System.out.println("Game Time: "+ gameTimeST +", "+ "Interval: "+ intervalString);
				int delay=Integer.parseInt(intervalString);
				int gameTime=Integer.parseInt(gameTimeST);
				
				if(numbers!=null){
					if(currentThread==null){
					currentThread = new GameThread(drinks, mixers, gameTime, delay, intervals, numbers);
					currentThread.start();
					}
				}
				else{
					System.out.println("Please Enter numbers");
				
				}
//				Thread game = new Thread();
//				game.start();
			}
		});
		begin.setBounds(90, 940, 79, 24);
		contentPane.add(begin);
		
		JLabel lblFirstLetsStart = new JLabel("First, lets start by selecting in the alcoholic drinks you have available");
		lblFirstLetsStart.setBounds(12, 42, 489, 16);
		contentPane.add(lblFirstLetsStart);
		
		JCheckBox whiskey = new JCheckBox("Whiskey");
		whiskey.setBounds(12, 67, 86, 25);
		contentPane.add(whiskey);
		
		JCheckBox beer = new JCheckBox("Beer");
		beer.setBounds(12, 97, 86, 25);
		contentPane.add(beer);
		
		JCheckBox vodka = new JCheckBox("Vodka");
		vodka.setBounds(129, 67, 79, 25);
		contentPane.add(vodka);
		
		JCheckBox tequilla = new JCheckBox("Tequilla");
		tequilla.setBounds(129, 97, 79, 25);
		contentPane.add(tequilla);
		
		JCheckBox gin = new JCheckBox("Gin");
		gin.setBounds(250, 67, 62, 25);
		contentPane.add(gin);
		
		JCheckBox rum = new JCheckBox("Rum");
		rum.setBounds(250, 97, 68, 25);
		contentPane.add(rum);
		
		
		JCheckBox brandy = new JCheckBox("Brandy");
		brandy.setBounds(316, 67, 113, 25);
		contentPane.add(brandy);
		
		JCheckBox schnapps = new JCheckBox("Schnapps");
		schnapps.setBounds(316, 97, 113, 25);
		contentPane.add(schnapps);
		
		JButton drinks1 = new JButton("Enter Drinks");
		drinks1.setBounds(12, 126, 113, 25);
		contentPane.add(drinks1);
		
		drinks1.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				drinks.clear();
				
				if(whiskey.isSelected()){
					
					drinks.add("Whiskey");
				}
				if(beer.isSelected()){
					drinks.add("beer");
				}
				if(vodka.isSelected()){
					drinks.add("Vodka");
				}
				if(tequilla.isSelected()){
					drinks.add("Tequilla");
				}
				if(gin.isSelected()){
					drinks.add("Gin");
				}
				if(rum.isSelected()){
					drinks.add("Rum");
				}
				if(schnapps.isSelected()){
					drinks.add("Schnapps");
				}
				
			System.out.println();
			for(int x=0; x<drinks.size(); x++){
				
				System.out.print(drinks.get(x)+ ", ");
			}
			}
		});
		

		
		JButton btnStop = new JButton("STOP!");
		btnStop.setFont(new Font("Tahoma", Font.BOLD, 14));
		btnStop.setForeground(Color.LIGHT_GRAY);
		btnStop.setBackground(Color.RED);
		btnStop.setBounds(264, 940, 81, 24);
		contentPane.add(btnStop);
		btnStop.addActionListener(new ActionListener() {
			@SuppressWarnings("deprecation")
			public void actionPerformed(ActionEvent e) {
				currentThread.stop();
				currentThread=null;
				System.out.println("Game Ended");
			}
			});
		
		JLabel lblNextPleaseEnter = new JLabel("Next, please enter the types of mixers you have available");
		lblNextPleaseEnter.setBounds(12, 164, 387, 16);
		contentPane.add(lblNextPleaseEnter);
		
		JCheckBox OJ = new JCheckBox("Orange Juice");
		OJ.setBounds(12, 189, 113, 25);
		contentPane.add(OJ);
		
		JCheckBox Coke = new JCheckBox("Coca-cola");
		Coke.setBounds(12, 220, 113, 25);
		contentPane.add(Coke);
		
		JCheckBox lemonade = new JCheckBox("Lemonade");
		lemonade.setBounds(12, 250, 113, 25);
		contentPane.add(lemonade);
		
		JCheckBox cranberry = new JCheckBox("Cranberry Juice");
		cranberry.setBounds(129, 189, 128, 25);
		contentPane.add(cranberry);
		
		JCheckBox dP = new JCheckBox("Dr. Pepper");
		dP.setBounds(129, 220, 113, 25);
		contentPane.add(dP);
		
		JCheckBox clubSoda = new JCheckBox("Club Soda");
		clubSoda.setBounds(129, 250, 113, 25);
		contentPane.add(clubSoda);
		
	
		
		
		JLabel lblPleaseEnterThe = new JLabel("Please Enter the names, phone numbers, and service providers for each player.");
		lblPleaseEnterThe.setBounds(12, 322, 477, 16);
		contentPane.add(lblPleaseEnterThe);
		
		JLabel lblExJohn = new JLabel("Example:   (16 player max)");
		lblExJohn.setBounds(12, 341, 300, 16);
		contentPane.add(lblExJohn);
		
		JLabel lblJohnTmobile = new JLabel("John, 4143336671, T-mobile");
		lblJohnTmobile.setBounds(12, 358, 259, 16);
		contentPane.add(lblJohnTmobile);
		
		JLabel lblTinaAtt = new JLabel("Tina, 4153126678, ATT");
		lblTinaAtt.setBounds(12, 375, 165, 16);
		contentPane.add(lblTinaAtt);
		
		JLabel lblPleaseEnterHow = new JLabel("Please select how long the game should last in minutes.");
		lblPleaseEnterHow.setBounds(16, 736, 417, 16);
		contentPane.add(lblPleaseEnterHow);
		
		JCheckBox chckbxSpriteup = new JCheckBox("Sprite/7-Up");
		chckbxSpriteup.setBounds(260, 189, 113, 25);
		contentPane.add(chckbxSpriteup);
		
		JCheckBox chckbxGingerAle = new JCheckBox("Ginger ale");
		chckbxGingerAle.setBounds(260, 220, 113, 25);
		contentPane.add(chckbxGingerAle);
		
		JCheckBox chckbxNewCheckBox_5 = new JCheckBox("Tomato Juice");
		chckbxNewCheckBox_5.setBounds(260, 248, 113, 25);
		contentPane.add(chckbxNewCheckBox_5);
		
		JCheckBox chckbxRedBull = new JCheckBox("Red Bull");
		chckbxRedBull.setBounds(376, 189, 113, 25);
		contentPane.add(chckbxRedBull);
		
		JCheckBox chckbxOtherJuice = new JCheckBox("Other Juice");
		chckbxOtherJuice.setBounds(376, 214, 113, 25);
		contentPane.add(chckbxOtherJuice);
		
		JCheckBox chckbxOtherSoda = new JCheckBox("Other Soda");
		chckbxOtherSoda.setBounds(376, 248, 113, 25);
		contentPane.add(chckbxOtherSoda);
		
		
		JButton mixersButton = new JButton("Enter Mixers");
		mixersButton.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				mixers.clear();
				
				if(OJ.isSelected()){
					mixers.add("Orange Juice");
				}
				if(Coke.isSelected()){
					mixers.add("Coke");
				}
				if(lemonade.isSelected()){
					mixers.add("Lemonade");
				}
				if(cranberry.isSelected()){
					mixers.add("Cranberry Juice");
				}
				if(dP.isSelected()){
					mixers.add("Dr. Pepper");
				}
				if(clubSoda.isSelected()){
					mixers.add("Club Soda");
				}
				if(chckbxSpriteup.isSelected()){
					mixers.add("Sprite/7-up");
				}
				if(chckbxGingerAle.isSelected()){
					mixers.add("Ginger Ale");
				}
				if(chckbxNewCheckBox_5.isSelected()){
					mixers.add("Tomato Juice");
				}
				if(chckbxRedBull.isSelected()){
					mixers.add("Red Bull");
				}
				if(chckbxOtherJuice.isSelected()){
					mixers.add("Other Juice");
				}
				if(chckbxOtherSoda.isSelected()){
					mixers.add("Other Soda");
				}
				
				
				
			System.out.println();
			for(int x=0; x<mixers.size(); x++){
				
				System.out.print(mixers.get(x)+ ", ");
			}
			
			}
		});
		//mixersButton.getAction();
		mixersButton.setBounds(12, 284, 113, 25);
		contentPane.add(mixersButton);
		
		JRadioButton rdbtnmin = new JRadioButton("15");
		buttonGroup_1.add(rdbtnmin);
		rdbtnmin.setBounds(12, 755, 43, 25);
		contentPane.add(rdbtnmin);
		
		JRadioButton radioButton_1 = new JRadioButton("30");
		buttonGroup_1.add(radioButton_1);
		radioButton_1.setBounds(59, 755, 49, 25);
		contentPane.add(radioButton_1);
		
		JRadioButton radioButton_2 = new JRadioButton("45");
		buttonGroup_1.add(radioButton_2);
		radioButton_2.setBounds(107, 755, 43, 25);
		contentPane.add(radioButton_2);
		
		JRadioButton radioButton_3 = new JRadioButton("60");
		buttonGroup_1.add(radioButton_3);
		radioButton_3.setBounds(154, 755, 49, 25);
		contentPane.add(radioButton_3);
		
		JRadioButton radioButton_4 = new JRadioButton("90");
		buttonGroup_1.add(radioButton_4);
		radioButton_4.setBounds(199, 755, 43, 25);
		contentPane.add(radioButton_4);
		
		JRadioButton radioButton_5 = new JRadioButton("120");
		buttonGroup_1.add(radioButton_5);
		radioButton_5.setBounds(246, 755, 49, 25);
		contentPane.add(radioButton_5);
		
		JRadioButton radioButton = new JRadioButton("150");
		buttonGroup_1.add(radioButton);
		radioButton.setBounds(302, 755, 56, 25);
		contentPane.add(radioButton);
		
		JRadioButton radioButton_6 = new JRadioButton("180");
		buttonGroup_1.add(radioButton_6);
		radioButton_6.setBounds(362, 755, 62, 25);
		contentPane.add(radioButton_6);
		
		JLabel lblNewLabel = new JLabel("Finally, please select a Chugg interval. ");
		lblNewLabel.setBounds(16, 781, 489, 16);
		contentPane.add(lblNewLabel);
		
		JTextArea textArea = new JTextArea();
		textArea.setBounds(12, 404, 387, 288);
		contentPane.add(textArea);
		
		
		JButton btnEnterNumbers = new JButton("Enter Numbers");
		btnEnterNumbers.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				try {
					String text= textArea.getDocument().getText(0,textArea.getDocument().getLength());
					Setup s = new Setup();
					numbers=s.parseNumbers(text);
				} catch (BadLocationException e1) {
					System.out.println("Something wrong with text area");
					e1.printStackTrace();
				}
				
			}
		});
		btnEnterNumbers.setBounds(11, 705, 156, 24);
		contentPane.add(btnEnterNumbers);
		
		
		
		
		JLabel lblNewLabel_1 = new JLabel("This will be the time (in minutes) before a person is chosen at random to Chugg!");
		lblNewLabel_1.setBounds(16, 796, 489, 16);
		contentPane.add(lblNewLabel_1);
		
		JRadioButton radioButton_7 = new JRadioButton("2");
		buttonGroup.add(radioButton_7);
		radioButton_7.setBounds(16, 812, 35, 25);
		contentPane.add(radioButton_7);
		
		JRadioButton radioButton_8 = new JRadioButton("3");
		buttonGroup.add(radioButton_8);
		radioButton_8.setBounds(51, 812, 35, 25);
		contentPane.add(radioButton_8);
		
		JRadioButton radioButton_9 = new JRadioButton("5");
		buttonGroup.add(radioButton_9);
		radioButton_9.setBounds(90, 812, 35, 25);
		contentPane.add(radioButton_9);
		
		JRadioButton radioButton_10 = new JRadioButton("6");
		buttonGroup.add(radioButton_10);
		radioButton_10.setBounds(132, 812, 35, 25);
		contentPane.add(radioButton_10);
		
		JRadioButton radioButton_11 = new JRadioButton("10");
		buttonGroup.add(radioButton_11);
		radioButton_11.setBounds(165, 812, 43, 25);
		contentPane.add(radioButton_11);
		
		JRadioButton radioButton_12 = new JRadioButton("15");
		buttonGroup.add(radioButton_12);
		radioButton_12.setBounds(254, 812, 49, 25);
		contentPane.add(radioButton_12);
		
		JRadioButton radioButton_13 = new JRadioButton("20");
		buttonGroup.add(radioButton_13);
		radioButton_13.setBounds(302, 812, 43, 25);
		contentPane.add(radioButton_13);
		
		JRadioButton radioButton_14 = new JRadioButton("30");
		buttonGroup.add(radioButton_14);
		radioButton_14.setBounds(349, 812, 43, 25);
		contentPane.add(radioButton_14);
		
		JRadioButton radioButton_15 = new JRadioButton("12");
		buttonGroup.add(radioButton_15);
		radioButton_15.setBounds(212, 812, 43, 25);
		contentPane.add(radioButton_15);
		
		JLabel lblAreYouReady = new JLabel("Are you ready to begin??? Drink Responsibly!");
		lblAreYouReady.setBounds(16, 870, 279, 16);
		contentPane.add(lblAreYouReady);
		
		JLabel lblChugg = new JLabel("CHUGG!");
		lblChugg.setBounds(16, 899, 56, 16);
		contentPane.add(lblChugg);
		
		JCheckBox chckbxCheckThisBox = new JCheckBox("Check if you want Chuggs to be sent out at random times within the interval");
		chckbxCheckThisBox.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				intervals= true;
				System.out.println(intervals);
			}
		});
		
		chckbxCheckThisBox.setBounds(12, 842, 489, 25);
		contentPane.add(chckbxCheckThisBox);
		
		
	 
		
	}
}
