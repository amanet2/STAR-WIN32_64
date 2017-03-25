import java.awt.*;
import java.awt.geom.*;
import java.awt.image.*;
import javax.swing.*;
import java.awt.event.*;
import java.util.*;
import java.io.*;
import java.net.*;

public class homeScreen extends JLayeredPane
{
		Random rand;

		titlePanel seagullPanel;
		ImageIcon seagull;
	
	    ImageIcon chest;
		ImageIcon ship;
		ImageIcon trader;
		ImageIcon mapItem;
		ImageIcon repair;
		ImageIcon stormIcon;
		ImageIcon parchmentTexture;
		ImageIcon searchIcon;
		ImageIcon bigMapIcon;
		ImageIcon mysteryIcon;
		
		ImageIcon happyMerchant;
		ImageIcon unhappyMerchant;
		
		ImageIcon bigIsland;
		ImageIcon smallIsland;
		ImageIcon tinyIsland;
		ImageIcon backIslandIcon;
		ImageIcon rightIslandIcon;
		ImageIcon leftIslandIcon;
		
		ImageIcon visualHealth100;
		ImageIcon visualHealth80;
		ImageIcon visualHealth60;
		ImageIcon visualHealth40;
		ImageIcon visualHealth20;
		
		ImageIcon exitIcon;
		ImageIcon saveIcon;
		
	buttonPanel quitButtonPanel;
	buttonPanel saveButtonPanel;
	
	/* IMPORTANT: FULL MAP VIEW */
	buttonPanel bigMapViewButtonPanel;

	buttonPanel investigateButtonPanel;
	buttonPanel backButtonPanel;

	buttonPanel choice1ButtonPanel;
	buttonPanel choice2ButtonPanel;

	buttonPanel yesButtonPanel;
	buttonPanel noButtonPanel;

	buttonPanel choose1ButtonPanel;
	buttonPanel choose2ButtonPanel;
	buttonPanel choose3ButtonPanel;
	buttonPanel choose4ButtonPanel;
	buttonPanel choose5ButtonPanel;
	buttonPanel choose6ButtonPanel;
	buttonPanel choose7ButtonPanel;
	buttonPanel choose8ButtonPanel;
	buttonPanel choose9ButtonPanel;
	buttonPanel choose10ButtonPanel;

	buttonPanel repairButtonPanel;
	
	ArrayList<buttonPanel> chooseButtons;
	

	
	buttonPanel rightIslandButtonPanel;
	buttonPanel leftIslandButtonPanel;
	buttonPanel parentIslandButtonPanel;
	buttonPanel currentIslandButtonPanel;



	mapScreen mapPanel;

	titlePanel shipHealthPanel;
	titlePanel foodSuppliesPanel;
	titlePanel goldSuppliesPanel;
	titlePanel sitesOnIslandPanel;

	titlePanel screenModePanel;	//will say "investigate mode", "map screen", etc.
	titlePanel messagePanel;	//panel to hold message after opening chest, etc.
	
	titlePanel storyPanel1;
	titlePanel storyPanel2;
	titlePanel storyPanel3;
	
	titlePanel victoryMessagePanel;
	titlePanel defeatMessagePanel;

	titlePanel shipInfoBackingPanel;	//to be grey/etc. as a borders
	titlePanel visualHealthPanel;
	
	titlePanel merchantPanel;
	
	ArrayList<titlePanel> bigMapIslands;	

	Font scriptFont;

	public void showVictory(String s)
	{
		victoryMessagePanel.setVisible(true);
		victoryMessagePanel.setText(s);
	}
	
	public void showDefeat(String s)
	{
		defeatMessagePanel.setVisible(true);
		defeatMessagePanel.setText(s);	
	}
	
	public void buyItemsScreen()
	{
		merchantPanel.setVisible(true);
		screenModePanel.setText("ITEMS FOR SALE");
		messagePanel.setText("Buy +10k kCal of suppies? (-100 gold)");
	}

	public void portScreen()
	{
		merchantPanel.setVisible(true);
		seagullPanel.setVisible(false);
		merchantPanel.title.setIcon(happyMerchant);

		mapPanel.setBackground(mapPanel.portBG);

		quitButtonPanel.setVisible(false);
		saveButtonPanel.setVisible(false);
		bigMapViewButtonPanel.setVisible(false);
	
		victoryMessagePanel.setVisible(false);
		defeatMessagePanel.setVisible(false);
		
		investigateButtonPanel.button.setVisible(false);

		screenModePanel.setText("SHIP AT PORT");
		messagePanel.setText("Repair Ship for 500 gold?");

		yesButtonPanel.button.setVisible(true);
		noButtonPanel.button.setVisible(true);

		parentIslandButtonPanel.button.setVisible(false);
		rightIslandButtonPanel.button.setVisible(false);
		leftIslandButtonPanel.button.setVisible(false);
		currentIslandButtonPanel.button.setVisible(false);

		backButtonPanel.button.setVisible(false);

		repairButtonPanel.button.setVisible(false);
	}

	public void questionScreen()
	{
		seagullPanel.setVisible(false);
		mapPanel.setBackground(mapPanel.questionBG);

		quitButtonPanel.setVisible(false);
				saveButtonPanel.setVisible(false);
		bigMapViewButtonPanel.setVisible(false);
		investigateButtonPanel.button.setVisible(false);

		//screenModePanel.setText("QUESTION TIME");
		//messagePanel.setText("Answer: ");

		parentIslandButtonPanel.button.setVisible(false);
		rightIslandButtonPanel.button.setVisible(false);
		leftIslandButtonPanel.button.setVisible(false);
		currentIslandButtonPanel.button.setVisible(false);

		choose1ButtonPanel.setVisible(true);
			choose1ButtonPanel.button.setIcon(null);
			choose1ButtonPanel.button.setText("1");

		choose2ButtonPanel.setVisible(true);
			choose2ButtonPanel.button.setIcon(null);
			choose2ButtonPanel.button.setText("2");

		choose3ButtonPanel.setVisible(true);
			choose3ButtonPanel.button.setIcon(null);
			choose3ButtonPanel.button.setText("3");

		choose4ButtonPanel.setVisible(true);
			choose4ButtonPanel.button.setIcon(null);
			choose4ButtonPanel.button.setText("4");

		choose5ButtonPanel.setVisible(false);
		choose6ButtonPanel.setVisible(false);
		choose7ButtonPanel.setVisible(false);
		choose8ButtonPanel.setVisible(false);
		choose9ButtonPanel.setVisible(false);
		choose10ButtonPanel.setVisible(false);

		backButtonPanel.button.setVisible(true);

		repairButtonPanel.button.setVisible(false);
	}

	public void traderScreen()
	{
			seagullPanel.setVisible(false);
		merchantPanel.setVisible(true);
		
		mapPanel.setBackground(mapPanel.traderStore);

		quitButtonPanel.setVisible(false);
				saveButtonPanel.setVisible(false);
		bigMapViewButtonPanel.setVisible(false);
		investigateButtonPanel.button.setVisible(false);

		screenModePanel.setText("AT THE TRADING POST");
		messagePanel.setText("Buy supplies? [yes/no]");

		choice1ButtonPanel.button.setVisible(false);
		choice2ButtonPanel.button.setVisible(false);
		
		yesButtonPanel.button.setVisible(true);
		noButtonPanel.button.setVisible(true);

		parentIslandButtonPanel.button.setVisible(false);
		rightIslandButtonPanel.button.setVisible(false);
		leftIslandButtonPanel.button.setVisible(false);
		currentIslandButtonPanel.button.setVisible(false);

		choose1ButtonPanel.setVisible(false);
		choose2ButtonPanel.setVisible(false);
		choose3ButtonPanel.setVisible(false);
		choose4ButtonPanel.setVisible(false);
		choose5ButtonPanel.setVisible(false);
		choose6ButtonPanel.setVisible(false);
		choose7ButtonPanel.setVisible(false);
		choose8ButtonPanel.setVisible(false);
		choose9ButtonPanel.setVisible(false);
		choose10ButtonPanel.setVisible(false);

		backButtonPanel.button.setVisible(false);

		repairButtonPanel.button.setVisible(false);
	}

	public void stormScreen()
	{
		choice1ButtonPanel.button.setText("HOLD ON & PRAY");
		choice2ButtonPanel.button.setText("HOLD ON & PRAY");
	
		seagullPanel.setVisible(false);
		mapPanel.setBackground(mapPanel.stormBG);

		quitButtonPanel.setVisible(false);
				saveButtonPanel.setVisible(false);
		bigMapViewButtonPanel.setVisible(false);
		victoryMessagePanel.setVisible(false);
		defeatMessagePanel.setVisible(false);
		
		investigateButtonPanel.button.setVisible(false);

		screenModePanel.setText("STUCK IN A STORM!");
		messagePanel.setText("Press 1 or 2 for next action...");
		
		storyPanel1.setText("Your vessel is in a storm!");
		storyPanel2.setText("The captain is called upon");
		storyPanel3.setText("to make a decision!");
		
		storyPanel1.setVisible(true);
		storyPanel2.setVisible(true);
		storyPanel3.setVisible(true);

		choice1ButtonPanel.button.setVisible(true);
		choice2ButtonPanel.button.setVisible(true);

		parentIslandButtonPanel.button.setVisible(false);
		rightIslandButtonPanel.button.setVisible(false);
		leftIslandButtonPanel.button.setVisible(false);
		currentIslandButtonPanel.button.setVisible(false);

		choose1ButtonPanel.setVisible(false);
		choose2ButtonPanel.setVisible(false);
		choose3ButtonPanel.setVisible(false);
		choose4ButtonPanel.setVisible(false);
		choose5ButtonPanel.setVisible(false);
		choose6ButtonPanel.setVisible(false);
		choose7ButtonPanel.setVisible(false);
		choose8ButtonPanel.setVisible(false);
		choose9ButtonPanel.setVisible(false);
		choose10ButtonPanel.setVisible(false);

		backButtonPanel.button.setVisible(false);

		repairButtonPanel.button.setVisible(false);
	}

	public void enemySpottedScreen()
	{
		choice1ButtonPanel.button.setText("TAKE OFFENSIVE");
		choice2ButtonPanel.button.setText("TAKE DEFENSIVE");
	
		seagullPanel.setVisible(false);
		mapPanel.setBackground(mapPanel.battleBG);

		quitButtonPanel.setVisible(false);
				saveButtonPanel.setVisible(false);
		bigMapViewButtonPanel.setVisible(false);
		victoryMessagePanel.setVisible(false);
		defeatMessagePanel.setVisible(false);
		
		investigateButtonPanel.button.setVisible(false);

		screenModePanel.setText("ENEMY SHIP SPOTTED");
		messagePanel.setText("Press 1 or 2 for next action...");

		storyPanel1.setText("You encounter an enemy vessel!");
		storyPanel2.setText("As Captain you must make the");
		storyPanel3.setText("decisions for the ship... ");
		
		storyPanel1.setVisible(true);
		storyPanel2.setVisible(true);
		storyPanel3.setVisible(true);
		
		choice1ButtonPanel.button.setVisible(true);
		choice2ButtonPanel.button.setVisible(true);

		parentIslandButtonPanel.button.setVisible(false);
		rightIslandButtonPanel.button.setVisible(false);
		leftIslandButtonPanel.button.setVisible(false);
		currentIslandButtonPanel.button.setVisible(false);

		choose1ButtonPanel.setVisible(false);
		choose2ButtonPanel.setVisible(false);
		choose3ButtonPanel.setVisible(false);
		choose4ButtonPanel.setVisible(false);
		choose5ButtonPanel.setVisible(false);
		choose6ButtonPanel.setVisible(false);
		choose7ButtonPanel.setVisible(false);
		choose8ButtonPanel.setVisible(false);
		choose9ButtonPanel.setVisible(false);
		choose10ButtonPanel.setVisible(false);

		backButtonPanel.button.setVisible(false);

		repairButtonPanel.button.setVisible(false);
	}
	
	public void returnToMapScreen()
	{
			merchantPanel.setVisible(false);
					seagullPanel.setVisible(true);
			merchantPanel.title.setIcon(happyMerchant);
			mapPanel.setBackground(mapPanel.oceanSurface);

		quitButtonPanel.setVisible(true);
				saveButtonPanel.setVisible(true);
		bigMapViewButtonPanel.setVisible(true);
		choice1ButtonPanel.button.setVisible(false);
		choice2ButtonPanel.button.setVisible(false);
		
		bigMapViewButtonPanel.setVisible(true);

		investigateButtonPanel.button.setVisible(true);

		yesButtonPanel.button.setVisible(false);
		noButtonPanel.button.setVisible(false);

		screenModePanel.setText("MAP SCREEN MODE");
		
		if(messagePanel.title.getText().length() > 0)
		{
			if(messagePanel.title.getText().charAt(0) != '+'
			&& messagePanel.title.getText().charAt(0) != '-')
			{
				messagePanel.setText("Returned to Map Screen!");
			}
		}
		
		for(titlePanel bp : bigMapIslands)
		{
			bp.setVisible(false);
		}

		parentIslandButtonPanel.button.setVisible(true);
		rightIslandButtonPanel.button.setVisible(true);
		leftIslandButtonPanel.button.setVisible(true);
		currentIslandButtonPanel.button.setVisible(true);

		choose1ButtonPanel.setVisible(false);
		choose2ButtonPanel.setVisible(false);
		choose3ButtonPanel.setVisible(false);
		choose4ButtonPanel.setVisible(false);
		choose5ButtonPanel.setVisible(false);
		choose6ButtonPanel.setVisible(false);
		choose7ButtonPanel.setVisible(false);
		choose8ButtonPanel.setVisible(false);
		choose9ButtonPanel.setVisible(false);
		choose10ButtonPanel.setVisible(false);

		backButtonPanel.button.setVisible(false);

		repairButtonPanel.button.setVisible(true);
		
		storyPanel1.setVisible(false);
		storyPanel2.setVisible(false);
		storyPanel3.setVisible(false);
		

	}
	
	public void bigMapScreen()
	{		
		seagullPanel.setVisible(false);
		mapPanel.setBackground(mapPanel.bigMapSurface);
		quitButtonPanel.setVisible(false);
		saveButtonPanel.setVisible(false);
		
		victoryMessagePanel.setVisible(false);
		defeatMessagePanel.setVisible(false);
		screenModePanel.setText("BIG MAP MODE");
		messagePanel.setText("Reading the map...");
		investigateButtonPanel.button.setVisible(false);
		yesButtonPanel.button.setVisible(false);
		noButtonPanel.button.setVisible(false);
		parentIslandButtonPanel.button.setVisible(false);
		rightIslandButtonPanel.button.setVisible(false);
		leftIslandButtonPanel.button.setVisible(false);
		currentIslandButtonPanel.button.setVisible(false);
		
		choose1ButtonPanel.setVisible(false);
		choose2ButtonPanel.setVisible(false);
		choose3ButtonPanel.setVisible(false);
		choose4ButtonPanel.setVisible(false);
		choose5ButtonPanel.setVisible(false);
		choose6ButtonPanel.setVisible(false);
		choose7ButtonPanel.setVisible(false);
		choose8ButtonPanel.setVisible(false);
		choose9ButtonPanel.setVisible(false);
		choose10ButtonPanel.setVisible(false);
		
		for(titlePanel bp : bigMapIslands)
		{
			bp.setVisible(true);
		}

		backButtonPanel.button.setVisible(false);
		repairButtonPanel.button.setVisible(false);
	}

	public void investigateScreen(int num)
	{		
		merchantPanel.setVisible(false);
		seagullPanel.setVisible(false);
		merchantPanel.title.setIcon(happyMerchant);
		
		if(num == 1)
			mapPanel.setBackground(mapPanel.town1);
		else if(num == 2)
			mapPanel.setBackground(mapPanel.town2);
		else if(num == 3)
			mapPanel.setBackground(mapPanel.town3);
		else if(num == 4)
			mapPanel.setBackground(mapPanel.town4);
		else if(num == 5)
			mapPanel.setBackground(mapPanel.town5);
		else if(num == 6)
			mapPanel.setBackground(mapPanel.town6);
		else if(num == 7)
			mapPanel.setBackground(mapPanel.town7);
		else if(num == 8)
			mapPanel.setBackground(mapPanel.town8);
		else if(num == 9)
			mapPanel.setBackground(mapPanel.town9);
		else if(num == 10)
			mapPanel.setBackground(mapPanel.town10);
		else if(num == 11)
			mapPanel.setBackground(mapPanel.town11);
		else
			mapPanel.setBackground(mapPanel.town1);


		quitButtonPanel.setVisible(false);
		saveButtonPanel.setVisible(false);
		victoryMessagePanel.setVisible(false);
		defeatMessagePanel.setVisible(false);
		bigMapViewButtonPanel.setVisible(false);
		
		screenModePanel.setText("INVESTIGATE MODE");

		if(messagePanel.title.getText().length() > 1)
		{
			if(! messagePanel.title.getText().equalsIgnoreCase("Map to new island(s) found!")
			&& messagePanel.title.getText().charAt(0) != '+'
			&& messagePanel.title.getText().charAt(0) != '-'
			&& !(messagePanel.title.getText().equalsIgnoreCase("wrong answer")))
			{
				messagePanel.setText("Investigating Island");
			}
		}
		
		investigateButtonPanel.button.setVisible(false);

		yesButtonPanel.button.setVisible(false);
		noButtonPanel.button.setVisible(false);

		parentIslandButtonPanel.button.setVisible(false);
		rightIslandButtonPanel.button.setVisible(false);
		leftIslandButtonPanel.button.setVisible(false);
		currentIslandButtonPanel.button.setVisible(false);

		choose1ButtonPanel.setVisible(true);
		choose2ButtonPanel.setVisible(true);
		choose3ButtonPanel.setVisible(true);
		choose4ButtonPanel.setVisible(true);
		choose5ButtonPanel.setVisible(true);
		choose6ButtonPanel.setVisible(true);
		choose7ButtonPanel.setVisible(true);
		choose8ButtonPanel.setVisible(true);
		choose9ButtonPanel.setVisible(true);
		choose10ButtonPanel.setVisible(true);

		backButtonPanel.button.setVisible(true);

		repairButtonPanel.button.setVisible(false);
	}

	public homeScreen()
	{
		rand = new Random();
		bigMapIslands = new ArrayList<titlePanel>();
		chooseButtons = new ArrayList<buttonPanel>();
		
			JPanel firstPanel = new homeScreenBackground();
			firstPanel.setOpaque(true);

		backButtonPanel = new buttonPanel("BACK",180,450);

		choice1ButtonPanel = new buttonPanel("CHOICE 1",0,460,512,30);
		choice2ButtonPanel = new buttonPanel("CHOICE 2",512,460,512,30);

		yesButtonPanel = new buttonPanel("YES",360,460);
			yesButtonPanel.button.setBackground(new Color(46,139,87));


			
		noButtonPanel = new buttonPanel("NO",435,460);
			noButtonPanel.button.setBackground(new Color(255,127,80));

			
    	chest = new ImageIcon(getClass().getResource("Images/chestIcon.jpg"));
		ship = new ImageIcon(getClass().getResource("Images/pirateShip.png"));
		mapItem = new ImageIcon(getClass().getResource("Images/map.png"));
		trader = new ImageIcon(getClass().getResource("Images/trader.gif"));
		repair = new ImageIcon(getClass().getResource("Images/repair.png"));
		stormIcon = new ImageIcon(getClass().getResource("Images/stormIcon.png"));
		bigMapIcon = new ImageIcon(getClass().getResource("Images/bigmapicon.png"));
		mysteryIcon = new ImageIcon(getClass().getResource("Images/mystery.png"));
		
		happyMerchant = new ImageIcon(getClass().getResource("Images/happyMerchant.png"));
		unhappyMerchant = new ImageIcon(getClass().getResource("Images/unhappyMerchant.png"));

		visualHealth100 = new ImageIcon(getClass().getResource("Images/visualHealth100.png"));
		visualHealth80 = new ImageIcon(getClass().getResource("Images/visualHealth80.png"));
		visualHealth60 = new ImageIcon(getClass().getResource("Images/visualHealth60.png"));
		visualHealth40 = new ImageIcon(getClass().getResource("Images/visualHealth40.png"));
		visualHealth20 = new ImageIcon(getClass().getResource("Images/visualHealth20.png"));
		
		bigIsland = new ImageIcon(getClass().getResource("Images/bigIsland.png"));
		smallIsland = new ImageIcon(getClass().getResource("Images/smallIsland.png"));
		tinyIsland = new ImageIcon(getClass().getResource("Images/tinyIsland.png"));
		backIslandIcon = new ImageIcon(getClass().getResource("Images/backIslandIcon.png"));
		rightIslandIcon = new ImageIcon(getClass().getResource("Images/rightIslandIcon.png"));
		leftIslandIcon = new ImageIcon(getClass().getResource("Images/leftIslandIcon.png"));

		parchmentTexture = new ImageIcon(getClass().getResource("Images/parchment.png"));
			scriptFont = new Font("URW Chancery L",Font.BOLD,24);	
			
		searchIcon = new ImageIcon(getClass().getResource("Images/investigateIcon.png"));
		
		exitIcon = new ImageIcon(getClass().getResource("Images/exitIcon.png"));
		saveIcon = new ImageIcon(getClass().getResource("Images/saveIcon.png"));
		
		saveButtonPanel = new buttonPanel(saveIcon,895,590,100,70);
			saveButtonPanel.button.setBackground(Color.GREEN);
			saveButtonPanel.button.setToolTipText("SAVE GAME (WIP)");
		
		quitButtonPanel = new buttonPanel(exitIcon,895,670,100,70);
			quitButtonPanel.button.setBackground(Color.RED);
			quitButtonPanel.button.setToolTipText("EXIT TO MAIN MENU");
			
		investigateButtonPanel = new buttonPanel(searchIcon,150,590,80,80);
			investigateButtonPanel.button.setBackground(Color.ORANGE);
			investigateButtonPanel.button.setOpaque(false);
			investigateButtonPanel.button.setBorderPainted(false);
			investigateButtonPanel.button.setToolTipText("INVESTIGATE ISLAND (5000kC)");


			
		bigMapViewButtonPanel = new buttonPanel(bigMapIcon,37,590,80,80);
			bigMapViewButtonPanel.button.setBackground(Color.CYAN);
			bigMapViewButtonPanel.button.setToolTipText("VIEW LARGE MAP");
			bigMapViewButtonPanel.button.setOpaque(false);
			//bigMapViewButtonPanel.button.setContentAreaFilled(false);
			bigMapViewButtonPanel.button.setBorderPainted(false);
			
		merchantPanel = new titlePanel(happyMerchant,600,320,200,200);
			merchantPanel.setOpaque(false);
			merchantPanel.title.setToolTipText("BRITISH MERCHANT");
			
		
		choose1ButtonPanel = new buttonPanel(chest,250,450);
		choose2ButtonPanel = new buttonPanel(chest,305,450);
		choose3ButtonPanel = new buttonPanel(chest,360,450);
		choose4ButtonPanel = new buttonPanel(chest,415,450);
		choose5ButtonPanel = new buttonPanel(chest,470,450);
		choose6ButtonPanel = new buttonPanel(chest,525,450);
		choose7ButtonPanel = new buttonPanel(chest,580,450);
		choose8ButtonPanel = new buttonPanel(chest,635,450);
		choose9ButtonPanel = new buttonPanel(chest,690,450);
		choose10ButtonPanel = new buttonPanel(chest,745,450);
		
		chooseButtons.add(choose1ButtonPanel);
		chooseButtons.add(choose2ButtonPanel);
		chooseButtons.add(choose3ButtonPanel);
		chooseButtons.add(choose4ButtonPanel);
		chooseButtons.add(choose5ButtonPanel);
		chooseButtons.add(choose6ButtonPanel);
		chooseButtons.add(choose7ButtonPanel);
		chooseButtons.add(choose8ButtonPanel);
		chooseButtons.add(choose9ButtonPanel);
		chooseButtons.add(choose10ButtonPanel);
		
		repairButtonPanel = new buttonPanel(repair,260,590,80,80);
			repairButtonPanel.button.setBackground(Color.PINK);
			repairButtonPanel.button.setOpaque(false);
			repairButtonPanel.button.setBorderPainted(false);
			repairButtonPanel.button.setToolTipText("REPAIR SHIP (500g)");



		shipHealthPanel = new titlePanel("Ship Health: ",550,610);
			shipHealthPanel.setOpaque(false);
			//shipHealthPanel.title.setForeground(Color.YELLOW);

		foodSuppliesPanel = new titlePanel("Food Supplies: ",550,635);
			foodSuppliesPanel.setOpaque(false);
			//foodSuppliesPanel.title.setForeground(Color.YELLOW);
	
		goldSuppliesPanel = new titlePanel("Gold: ",550,660);
			goldSuppliesPanel.setOpaque(false);
			//goldSuppliesPanel.title.setForeground(Color.YELLOW);

		sitesOnIslandPanel = new titlePanel("Sites on island",550,685);
			sitesOnIslandPanel.setOpaque(false);
			//sitesOnIslandPanel.title.setFont(new Font("URW Chancery L",Font.BOLD,20));
			
		Font myFont1 = new Font("Serif", Font.BOLD, 18);	
		Font myFont2 = new Font("Times New Roman", Font.ITALIC, 24);
		screenModePanel = new titlePanel("Screen Mode",0,550,1024,30);
		messagePanel = new titlePanel("Message",0,520,1024,30);
			screenModePanel.setBackground(Color.GRAY);
			messagePanel.setBackground(Color.GRAY);
			screenModePanel.title.setFont(myFont1);
			messagePanel.title.setFont(myFont1);

		shipInfoBackingPanel = new titlePanel("",650,586,200,150);
			shipInfoBackingPanel.setOpaque(false);
			shipInfoBackingPanel.add(new JLabel(parchmentTexture));
			
		visualHealthPanel = new titlePanel("",395,600,250,150);
			visualHealthPanel.add(new JLabel(visualHealth100));
			visualHealthPanel.setOpaque(false);
			visualHealthPanel.title.setToolTipText("VISUAL HEALTH");
		
		storyPanel1 = new titlePanel("Message",550,160,400,30);
		storyPanel2 = new titlePanel("Message",550,190,400,30);
		storyPanel3 = new titlePanel("Message",550,220,400,30);
			storyPanel1.setOpaque(false);
			storyPanel2.setOpaque(false);
			storyPanel3.setOpaque(false);
			storyPanel1.title.setForeground(new Color(255,215,0));
			storyPanel2.title.setForeground(new Color(255,215,0));
			storyPanel3.title.setForeground(new Color(255,215,0));
			//sitesOnIslandPanel.setOpaque(false);
			
		
		victoryMessagePanel = new titlePanel("Message",0,475,1024,40);
			victoryMessagePanel.setBackground(Color.BLACK);
			victoryMessagePanel.title.setForeground(new Color(48,128,20));
			victoryMessagePanel.title.setFont(myFont2);
			//victoryMessagePanel.setOpaque(false);
			victoryMessagePanel.setVisible(false);
		defeatMessagePanel = new titlePanel("Message",0,475,1024,40);
			defeatMessagePanel.setBackground(Color.BLACK);
			defeatMessagePanel.title.setForeground(new Color(176,23,31));
			defeatMessagePanel.title.setFont(myFont2);
			//defeatMessagePanel.setOpaque(false);
			defeatMessagePanel.setVisible(false);
		
		rightIslandButtonPanel = new buttonPanel(rightIslandIcon,850,60,80,80);
			rightIslandButtonPanel.setOpaque(false);
			rightIslandButtonPanel.button.setOpaque(false);
			rightIslandButtonPanel.button.setBorderPainted(false);
			
		leftIslandButtonPanel = new buttonPanel(leftIslandIcon,850,380,80,80);
			leftIslandButtonPanel.setOpaque(false);
			leftIslandButtonPanel.button.setOpaque(false);
			leftIslandButtonPanel.button.setBorderPainted(false);
			
			
		parentIslandButtonPanel = new buttonPanel(backIslandIcon,20,200,80,80);
			parentIslandButtonPanel.setOpaque(false);
			parentIslandButtonPanel.button.setOpaque(false);
			parentIslandButtonPanel.button.setBorderPainted(false);
			
		currentIslandButtonPanel = new buttonPanel(bigIsland,450,200,100,100);
			currentIslandButtonPanel.setOpaque(false);
			currentIslandButtonPanel.button.setOpaque(false);
			currentIslandButtonPanel.button.setBorderPainted(false);
		


		mapPanel = new mapScreen();
		
		


		/*goRightButtonPanel = new goRightButtonPanel();
		goLeftButtonPanel = new goRightButtonPanel();
		goBackButtonPanel = new goBackButtonPanel();
		investigateButtonPanel = new investigateButtonPanel();
		portButtonPanel = new portButtonPanel();*/
	
		add(firstPanel, new Integer(0), 0);
		add(quitButtonPanel, new Integer(1), 0);
		add(saveButtonPanel,new Integer(51),0);
		add(investigateButtonPanel, new Integer(8),0);
		add(bigMapViewButtonPanel, new Integer(41),0);
		add(backButtonPanel, new Integer(6),0);
		add(choice1ButtonPanel,new Integer(22),0);
		add(choice2ButtonPanel,new Integer(23),0);

		add(choose1ButtonPanel,new Integer(11),0);
		add(choose2ButtonPanel,new Integer(12),0);
		add(choose3ButtonPanel,new Integer(13),0);
		add(choose4ButtonPanel,new Integer(14),0);
		add(choose5ButtonPanel,new Integer(15),0);
		add(choose6ButtonPanel,new Integer(16),0);
		add(choose7ButtonPanel,new Integer(17),0);
		add(choose8ButtonPanel,new Integer(18),0);
		add(choose9ButtonPanel,new Integer(19),0);
		add(choose10ButtonPanel,new Integer(20),0);

		add(shipInfoBackingPanel, new Integer(35),0);
		add(visualHealthPanel, new Integer(40),0);
		add(foodSuppliesPanel, new Integer(36),0);
		add(goldSuppliesPanel, new Integer(37),0);
		add(shipHealthPanel, new Integer(38),0);
		add(mapPanel,new Integer(5),0);
		add(sitesOnIslandPanel,new Integer(39),0);

		add(rightIslandButtonPanel, new Integer(9),0);
		add(leftIslandButtonPanel, new Integer(21),0);
		add(parentIslandButtonPanel, new Integer(10),0);

		add(screenModePanel, new Integer(24),0);
		add(yesButtonPanel, new Integer(25),0);
		add(noButtonPanel, new Integer(26),0);
		
		add(currentIslandButtonPanel, new Integer(27),0);
		add(messagePanel, new Integer(28),0);

		
		add(repairButtonPanel, new Integer(50),0);
		
		add(storyPanel1,new Integer(30),0);
		add(storyPanel2,new Integer(31),0);
		add(storyPanel3,new Integer(32),0);
		
		add(victoryMessagePanel,new Integer(33),0);
		add(defeatMessagePanel,new Integer(34),0);
		add(merchantPanel,new Integer(200),0);
		
		
		rightIslandButtonPanel.button.setBackground(Color.GREEN);	
			rightIslandButtonPanel.button.setToolTipText("GO TO ISLAND");
			rightIslandButtonPanel.button.setBorderPainted(false);
			
		leftIslandButtonPanel.button.setBackground(Color.GREEN);
			leftIslandButtonPanel.button.setToolTipText("GO TO ISLAND");
			leftIslandButtonPanel.button.setBorderPainted(false);
			
		parentIslandButtonPanel.button.setBackground(Color.GREEN);
			parentIslandButtonPanel.button.setToolTipText("GO TO ISLAND");
			parentIslandButtonPanel.button.setBorderPainted(false);

		currentIslandButtonPanel.button.setBackground(Color.GREEN);
			currentIslandButtonPanel.button.setToolTipText("INVESTIGATE ISLAND");

		
		/*add(goRightButtonPanel, new Integer(2), 0);
		add(goLeftButtonPanel, new Integer(3), 0);
		add(goBackButtonPanel, new Integer(4), 0);
		add(investigateButtonPanel, new Integer(5), 0);
		add(portButtonPanel, new Integer(6), 0);*/
		
		
		setBounds(0, 0, 1024, 768);
	}
}
