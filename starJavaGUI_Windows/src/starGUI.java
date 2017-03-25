import java.net.*;
import java.util.*;
import java.io.*;
import java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;

import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

import java.util.*;

import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.PrintWriter;
import java.util.Scanner;

import javax.sound.sampled.AudioInputStream;
import javax.sound.sampled.AudioSystem;
import javax.sound.sampled.Clip;


public class starGUI extends Thread
{

	//for audio in the game
	AudioInputStream audioIn;
	Clip audioClip;
	
	JFrame theFrame;	//the big frame for everything
	
	int firstTime;
	int loadedQuestions;
	
	int currentIsland;	//read from stdout, keep track of which picture to switch to

	Process pr;
        BufferedReader processOutput;
 	BufferedWriter processInput;
	
	mainMenu mainPane;	//main menu
	newGameMenu newGamePane;
	welcomeScreen welcomeScreenPane;
	homeScreen homeScreenPane;
	winScreen winScreenPane;
	deathScreen deathScreenPane;
	
	
	/*ANIMATION MUST BE IN THIS FILE!!!!  YOU NEED ACCESS TO THE FRAME IN ORDER TO ANIMATE PANELS*/
/*==================================================*/	
	int gullIndex = -200;
	
	class AnimationTimerTask extends TimerTask{
		public void run(){
			if(gullIndex > 1030)	//if gull gets out of the screen remove it and spawn it back at the beginning
			{
			
				try{
					Thread.sleep(5000);	//wait 5 seconds
				}
				catch(Exception te){}
				
				homeScreenPane.remove(homeScreenPane.seagullPanel);
				gullIndex = -200;
				animateCharacters();
				theFrame.repaint();
			}
			else{
				gullIndex++;
				animateCharacters();
				theFrame.repaint();
			}
		}
	}
	

	AnimationTimerTask animationTask;
	int firstRun = 1;
	
	public void initCharacters(){
		homeScreenPane.seagull = new ImageIcon(getClass().getResource("Images/gull.png"));
			
		homeScreenPane.seagullPanel = new titlePanel("",gullIndex,0,200,200);
			homeScreenPane.seagullPanel.add(new JLabel(homeScreenPane.seagull));
			homeScreenPane.seagullPanel.setOpaque(false);
			homeScreenPane.seagullPanel.setToolTipText("SEAGULL");
			
		homeScreenPane.add(homeScreenPane.seagullPanel, new Integer(409),0);
	}

	public void animateCharacters()
	{
		homeScreenPane.seagullPanel.setBounds(gullIndex,0,200,200);
		homeScreenPane.add(homeScreenPane.seagullPanel,new Integer(409),0);
	}
	
	public void playAnimation()
	{
		gullIndex = -200;
		if(firstRun == 1)
		{
			java.util.Timer aTimer = new java.util.Timer();  // Create a Timer.
			aTimer.schedule(animationTask, 0, 20);
			firstRun = 0;
		}
	}
	
/*================================================*/	
	

	/*
		ADD ALL THE VARIOUS PANES THAT WILL BE CYCLED THROUGH BY THE GUI SUCH AS HOMESCREEN, INVESTIGATION SCREEN, ETC.
		ALSO ADD ALL LISTENERS TO ALL THE BUTTONS IN THE PROGRAM HERE IN THE CONSTRUCTOR
	*/
	public starGUI()
	{
		animationTask = new AnimationTimerTask();
		firstTime = 1;
		loadedQuestions = 0;
		currentIsland = 1;
		
		theFrame = new JFrame("STAR");
		theFrame.setLayout(new BorderLayout());
		theFrame.setPreferredSize(new Dimension(1024,768));
		
		mainPane = new mainMenu();
		mainPane.setBounds(0, 0, 1024, 768);

		newGamePane = new newGameMenu();
		newGamePane.setBounds(0,0,1024,768);
	
		welcomeScreenPane = new welcomeScreen();
		welcomeScreenPane.setBounds(0,0,1024,768);

		homeScreenPane = new homeScreen();
		homeScreenPane.setBounds(0,0,1024,768);

			initCharacters();

		winScreenPane = new winScreen();
		winScreenPane.setBounds(0,0,1024,768);

		deathScreenPane = new deathScreen();
		deathScreenPane.setBounds(0,0,1024,768);

		//add all listeners
		mainPane.newGameButtonPanel.button.addActionListener(new MouseClickHandler());
		mainPane.exitButtonPanel.button.addActionListener(new MouseClickHandler());
		
		newGamePane.backButtonPanel.button.addActionListener(new MouseClickHandler());
		newGamePane.startGameButtonPanel.button.addActionListener(new MouseClickHandler());

		welcomeScreenPane.backButtonPanel.button.addActionListener(new MouseClickHandler());
		welcomeScreenPane.continueButtonPanel.button.addActionListener(new MouseClickHandler());

		homeScreenPane.quitButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.investigateButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.bigMapViewButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.backButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.choice1ButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.choice2ButtonPanel.button.addActionListener(new MouseClickHandler());

		homeScreenPane.choose1ButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.choose2ButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.choose3ButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.choose4ButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.choose5ButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.choose6ButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.choose7ButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.choose8ButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.choose9ButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.choose10ButtonPanel.button.addActionListener(new MouseClickHandler());

		homeScreenPane.yesButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.noButtonPanel.button.addActionListener(new MouseClickHandler());
		

		homeScreenPane.rightIslandButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.leftIslandButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.parentIslandButtonPanel.button.addActionListener(new MouseClickHandler());
		homeScreenPane.currentIslandButtonPanel.button.addActionListener(new MouseClickHandler());

		winScreenPane.continueButtonPanel.button.addActionListener(new MouseClickHandler());

		deathScreenPane.continueButtonPanel.button.addActionListener(new MouseClickHandler());

		homeScreenPane.repairButtonPanel.button.addActionListener(new MouseClickHandler());
	}
	
	//playAudio must take a WAV file, so dont bother with ".wav"
	/*public void playAudio(String url)
	{				
		audioIn = AudioSystem.getAudioInputStream(getClass().getResource("Music/" + url + ".wav"));
		audioClip = AudioSystem.getClip();
		audioClip.open(audioIn);
		audioClip.start();
	}*/
	
	

	/*
		CONTROL PROGRAM FLOW HERE, BY MATCHING SRC/DEST

		E.G. IF I PRESS THE [GO RIGHT] BUTTON ON THE HOMESCREEN, I SHOULD EXECUTE THE moveToIsand(island * i)
		METHOD FOR THE RIGHT ISLAND.  IF I PRESS THE [INVESTIGATE] BUTTON ON THE HOMESCREEN, I SHOULD EXECUTE THE
		investigateScreen() METHOD IN THE PROGRAM
	*/
	public class MouseClickHandler extends MouseAdapter implements ActionListener 	//handle button presses
	{
		//control all presses
		public void actionPerformed (ActionEvent e) 
		{
			if(e.getSource() == mainPane.newGameButtonPanel.button)	//if we want to start game
			{
				
				try{
				changeDisplay(newGamePane);
				executeGameCommand("1\n",pr);}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == mainPane.exitButtonPanel.button)	//if we want to start game
			{
				
				try{
					pr.destroy();
					pr.waitFor();
					System.exit(0);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == newGamePane.backButtonPanel.button || e.getSource() == welcomeScreenPane.backButtonPanel.button || e.getSource() == homeScreenPane.quitButtonPanel.button)
			{
				try{
					changeDisplay(mainPane);
					if(e.getSource() == homeScreenPane.quitButtonPanel.button || e.getSource() == welcomeScreenPane.backButtonPanel.button){
						if(audioClip != null){audioClip.stop();}
						
						//play main menu song
								if(audioClip != null) audioClip.stop();
		
								
								//start playing main screen sounds
								audioIn = AudioSystem.getAudioInputStream(getClass().getResource("Music/opener.wav"));
								audioClip = AudioSystem.getClip();
								audioClip.open(audioIn);
								audioClip.start();
		
						System.out.println("asdf");
						executeGameCommand("q\n",pr);
					}
					else
						executeGameCommand("2\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
				
			}
			else if(e.getSource() == newGamePane.startGameButtonPanel.button)
			{
			
				try{

					if(audioClip != null){ audioClip.stop();}
			
					audioIn = AudioSystem.getAudioInputStream(getClass().getResource("Music/introduction.wav"));
					audioClip = AudioSystem.getClip();
					audioClip.open(audioIn);
					audioClip.start();

				changeDisplay(welcomeScreenPane);
				executeGameCommand("1\n",pr);}
				catch(Exception fff){fff.printStackTrace();}
				
			}
			else if(e.getSource() == welcomeScreenPane.continueButtonPanel.button)
			{
				try{
				if(audioClip != null){ audioClip.stop();}
			
				audioIn = AudioSystem.getAudioInputStream(getClass().getResource("Music/waves.wav"));
				audioClip = AudioSystem.getClip();
				audioClip.open(audioIn);
				audioClip.start();

				
				
				homeScreenPane.remove(homeScreenPane.seagullPanel);
				homeScreenPane.bigMapIslands.clear();
				changeDisplay(homeScreenPane);
				homeScreenPane.returnToMapScreen();
				playAnimation();
				homeScreenPane.messagePanel.setText("Welcome to the Game!");
				executeGameCommand("d\n",pr);

					if(loadedQuestions == 0){
					/*	Read the questions file to the game if you haven't already*/
						executeGameCommand("anq\n",pr);
						executeGameCommand("d\n",pr);

						// Open the file
						FileInputStream fstream = new FileInputStream("data/questions.txt");
						BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

						String strLine;

						//Read File Line By Line
						while ((strLine = br.readLine()) != null)   {
						  // Print the content on the console
						  executeGameCommand(strLine + "\n",pr);
						}

						//Close the input stream
						br.close();

						executeGameCommand("b\n",pr);
						executeGameCommand("d\n",pr);
						loadedQuestions = 1;
					}
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.quitButtonPanel.button || e.getSource() == winScreenPane.continueButtonPanel.button || e.getSource() == deathScreenPane.continueButtonPanel.button)
			{
				try{
				audioClip.stop();
				
								audioIn = AudioSystem.getAudioInputStream(getClass().getResource("Music/opener.wav"));
								audioClip = AudioSystem.getClip();
								audioClip.open(audioIn);
								audioClip.start();
				
				changeDisplay(mainPane);
				resetInvestigateIcons();
				executeGameCommand("q\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.investigateButtonPanel.button || e.getSource() == homeScreenPane.currentIslandButtonPanel.button)
			{
				try{
				if(audioClip != null){audioClip.stop();}
				
				audioIn = AudioSystem.getAudioInputStream(getClass().getResource("Music/town.wav"));
				audioClip = AudioSystem.getClip();
				audioClip.open(audioIn);
				audioClip.start();
				
				homeScreenPane.investigateScreen(currentIsland);
				executeGameCommand("i\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.backButtonPanel.button)
			{
				try{
				audioClip.stop();
				
				//start playing main screen sounds
				audioIn = AudioSystem.getAudioInputStream(getClass().getResource("Music/waves.wav"));
				audioClip = AudioSystem.getClip();
				audioClip.open(audioIn);
				audioClip.start();			
				
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				homeScreenPane.returnToMapScreen();
				executeGameCommand("b\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.bigMapViewButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("m\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.choose1ButtonPanel.button || e.getSource() == homeScreenPane.choice1ButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("1\n",pr);
				executeGameCommand("d\n",pr);

				if(e.getSource() == homeScreenPane.choice1ButtonPanel.button)
					homeScreenPane.returnToMapScreen();
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.choose2ButtonPanel.button || e.getSource() == homeScreenPane.choice2ButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("2\n",pr);
				executeGameCommand("d\n",pr);

				if(e.getSource() == homeScreenPane.choice2ButtonPanel.button)
					homeScreenPane.returnToMapScreen();
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.choose3ButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("3\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.choose4ButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("4\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.choose5ButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("5\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.choose6ButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("6\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.choose7ButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("7\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.choose8ButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("8\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.choose9ButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("9\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.choose10ButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("10\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.rightIslandButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				homeScreenPane.victoryMessagePanel.setVisible(false);
				homeScreenPane.defeatMessagePanel.setVisible(false);	
				
				executeGameCommand("r\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.leftIslandButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				homeScreenPane.victoryMessagePanel.setVisible(false);
				homeScreenPane.defeatMessagePanel.setVisible(false);
				
				executeGameCommand("l\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.parentIslandButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				homeScreenPane.victoryMessagePanel.setVisible(false);
				homeScreenPane.defeatMessagePanel.setVisible(false);	
				
				executeGameCommand("b\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.yesButtonPanel.button)
			{
			homeScreenPane.merchantPanel.title.setIcon(homeScreenPane.happyMerchant);
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("y\n",pr);
				executeGameCommand("d\n",pr);
					
			
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.noButtonPanel.button)
			{
				homeScreenPane.merchantPanel.title.setIcon(homeScreenPane.unhappyMerchant);
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("n\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == homeScreenPane.repairButtonPanel.button)
			{
				try{
				//changeDisplay(mainPane);
				//System.out.println("asdf");
				executeGameCommand("p\n",pr);
				executeGameCommand("d\n",pr);
				}
				catch(Exception fff){fff.printStackTrace();}
			}
			else if(e.getSource() == newGamePane.startGameButtonPanel.button)
			{
				changeDisplay(welcomeScreenPane);
			}
			else if(e.getSource() == welcomeScreenPane.continueButtonPanel.button)
			{
				changeDisplay(homeScreenPane);
			}
		}
	}
	
	public void decorateFrame()
	{

		//start out with our main menu
		theFrame.add(mainPane, BorderLayout.CENTER);
	}
	
	public void changeDisplay(JLayeredPane newPane)
	{		
		theFrame.setContentPane(newPane);
		theFrame.repaint(); 
	}
	

	
	private void createGUI()
	{	
		ProcessBuilder pb = new ProcessBuilder("../../bin/star.exe");
		try{
		pr = pb.start();
		}
		catch(Exception e){}

		processOutput = new BufferedReader(new InputStreamReader(pr.getInputStream()));
		processInput = new BufferedWriter(new OutputStreamWriter(pr.getOutputStream()));

		theFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		decorateFrame();
		
		theFrame.pack();
		theFrame.setVisible(true);
	}
	
	public void run()
	{
		createGUI();
		theFrame.setSize(1024,806);
		//changeDisplay(); //comment this in to change display, use this with actions to desired effect
	}

	public synchronized void executeGameCommand(String comm, Process pr)
	{
	    try
	    {
		int lineCounter = 0;
		
		while(true)
		{
			String line = processOutput.readLine();

			System.out.println(++lineCounter + ": " + line);

			if(line.length() > 1)
			{
				if(line.charAt(0) == '+' || line.charAt(0) == '-')
				{
					homeScreenPane.messagePanel.setText(line);		
				}
			}
			
			if(line.length() > 0)
			{
				if(line.equalsIgnoreCase("You limp away from a lost battle!"))
				{
					homeScreenPane.showDefeat(line);
				}
				else if(line.equalsIgnoreCase("You make a successful battle with the enemy ship!"))
				{
					homeScreenPane.showVictory("You win the battle!");
				}
				
				if(line.equalsIgnoreCase("You barely weather the storm"))
				{
					homeScreenPane.showVictory(line);
				}
				else if(line.equalsIgnoreCase("Your priorities were misplaced, and your ship suffers damage as a result!"))
				{
					homeScreenPane.showDefeat("Your ship incurs damage in the storm");
				}
			}
			//check for win
			if(line.length() > 0){
				if(line.equalsIgnoreCase("You have accomplished your goal and found the founatin of youth!  Press any key to continue:")){
					if(audioClip != null){ audioClip.stop();}
			
					audioIn = AudioSystem.getAudioInputStream(getClass().getResource("Music/victory.wav"));
					audioClip = AudioSystem.getClip();
					audioClip.open(audioIn);
					audioClip.start();
	changeDisplay(winScreenPane); 
	break;
				}
			}

			//check for death
			if(line.length() > 0)
			{
				if(line.equalsIgnoreCase("You ran out of supplies and have died!  Press any key to continue:")){
					if(audioClip != null){ audioClip.stop();}
			
					audioIn = AudioSystem.getAudioInputStream(getClass().getResource("Music/death.wav"));
					audioClip = AudioSystem.getClip();
					audioClip.open(audioIn);
					audioClip.start();

					changeDisplay(deathScreenPane); 
					homeScreenPane.victoryMessagePanel.setVisible(false); 
					homeScreenPane.defeatMessagePanel.setVisible(false);
					break;
				}
			}

			if(line.length() > 0)
			{
				if(line.equalsIgnoreCase("Enter number of site you want to explore (b to go back):"))
				{ 
					homeScreenPane.investigateScreen(currentIsland);
				}
			}
			
			if(line.length() > 0)
			{
				if(line.equalsIgnoreCase("PRINT MAPS"))
				{
					int flag1 = 1;
					String ds = "foo";
					if(homeScreenPane.bigMapIslands.size() == 0)
					{
						
						String ps = "foo";
						//String ta = "foo";
						System.out.println("FODODOD");
						int d = 0;
						int p = 0;
							
						int flag2 = 1;	//innerlopp
						int flag3 = 0;	//1 for complete data packet
						int flag4 = 0;	//1 for break out of here
						
						while(flag1 == 1)
						{	
							if(flag4 == 1) break;
							
							flag3 = 0;
							
							flag2 = 1;
							
							while(flag2 == 1)
							{
								ds = processOutput.readLine();	//islands
								System.out.println("GOT: "+ds+"[STOP]");
								if(ds.length() > 1)
								{
									if(ds.charAt(0) == 'e' || ds.charAt(0) == 'E'){flag4 = 1; break; }
									//get depth and position to draw on screen
									if(ds.length() >= 6)
									{
										if(ds.substring(0,6).equalsIgnoreCase("DEPTH:"))
										{
											d = Integer.valueOf(ds.substring(7,ds.length()));
											System.out.println("DEP: " + d);
											flag2 = 0;
										}
									}
								}
							}
							
							if(flag4 == 1) break;
							
							flag2=1;
							
							while(flag2==1)
							{
								ps = processOutput.readLine();
								System.out.println("GOT: "+ps+"[STOP]");

								if(ps.length() > 1)
								{
									if(ps.charAt(0) == 'e' || ps.charAt(0) == 'E'){ flag4 = 1; break; }
									
									if(ps.length() >= 9)
									{
										if(ps.substring(0,9).equalsIgnoreCase("POSITION:"))
										{
											p = Integer.valueOf(ps.substring(10,ps.length()));
											System.out.println("POS: " + p);
											flag3 = 1;
											flag2 = 0;
										}
									}
								}
							}
							
							if(flag4 == 1) break;
							//add titlepanel to the arrlist
							if(flag3 == 1)
								homeScreenPane.bigMapIslands.add(new titlePanel(homeScreenPane.tinyIsland,175+50*(p),2*50*d+50,50,50));
						}
						
						int rex=100;
						for(titlePanel tp : homeScreenPane.bigMapIslands)
						{
							homeScreenPane.add(tp,new Integer(rex),0);
								rex++;
							
						}
					}
					else{
					
						flag1 = 1;
						while(flag1 == 1)
						{
							ds = processOutput.readLine();	//islands
							
							if(ds.length() > 1)
							{
								if(ds.charAt(0) == 'e' || ds.charAt(0) == 'E'){break; }
							}
						}
					}
					
					homeScreenPane.bigMapScreen();
					break;
				}
			}
			
			if(line.length() > 0)
			{
				if(line.equalsIgnoreCase("Wrong answer"))
					homeScreenPane.messagePanel.setText(line);
			}

			//check for q/a situation
			if(line.length() > 0){
				if(line.equalsIgnoreCase("Enter the # of your answer (b to go back):")){homeScreenPane.questionScreen();}
			}

			if(line.length() > 0)
			{
				if(line.equalsIgnoreCase("Press 1 to take evasive action first, or 2 to move into attack position first:"))
				{ homeScreenPane.enemySpottedScreen(); }
			}

			if(line.length() > 0)
			{
				if(line.equalsIgnoreCase("Press 1 to tie down sails first or 2 to seal all hatches first:"))
				{ homeScreenPane.stormScreen();}
			}

			if(line.length() > 0)
			{
				if(line.equalsIgnoreCase("Investigating Island"))
				{
					String s = processOutput.readLine();	//islands
					currentIsland = Integer.valueOf(s);
					resetInvestigateIcons();
				}
			}

			if(line.length() > 0)
			{
				if(line.equalsIgnoreCase("Buy Supplies? [y/n]:"))
				{ homeScreenPane.traderScreen();}
			}

			if(line.length() > 0)
			{
				if(line.equalsIgnoreCase("Buy ration pack? (+100 kcal/-100 gold):"))
				{ homeScreenPane.buyItemsScreen();}
			}

			if(line.length() > 0)
			{
				if(line.equalsIgnoreCase("Enter Command ([q]uit/[r|l]right|left/[b]ack island/[i]nvestigate/[p]ort/[m]ap):"))
				{ homeScreenPane.returnToMapScreen();}
			}

			if(line.length() > 0)
			{
				if(line.equalsIgnoreCase("Restore ship health for 500 gold? [y/n]:"))
				{
					homeScreenPane.portScreen();
				}
			}

			if(line.length() > 0){
				if(line.charAt(line.length()-1) == ':' || line.equalsIgnoreCase("Enter m to go back:") || line.equalsIgnoreCase("2. EXIT") || line.equalsIgnoreCase("2. EXIT TO MAIN MENU") || line.equalsIgnoreCase("Enter Command ([q]uit/[r|l]right|left/[b]ack island/[i]nvestigate/[p]ort/[m]ap):") || line.equalsIgnoreCase("Enter number of site you want to explore (b to go back):") || line.equalsIgnoreCase("You ran out of supplies and have died!  Press any key to continue:") || line.equalsIgnoreCase("Press 1 to tie down sails first or 2 to seal all hatches first:") || line.equalsIgnoreCase("Press 1 to take evasive action first, or 2 to move into attack position first:") || line.equalsIgnoreCase("Buy Supplies? [y/n]:") || line.equalsIgnoreCase("Buy ration pack? (+100 kcal/-100 gold):") || line.equalsIgnoreCase("Restore ship health for 500 gold? [y/n]:") || line.equalsIgnoreCase("Enter in form [prompt],[answer],[option1],[o2],[o3] (b to go back):")){break;}}

			
			if(line.length() >= 14)
			{
				if(line.substring(line.length()-14,line.length()).equalsIgnoreCase("Treasure Chest"))
				{
					int st = line.indexOf("[");
					int ed = line.indexOf("]");
					int fnd = Integer.valueOf(line.substring(st+1,ed));
					homeScreenPane.chooseButtons.get(fnd-1).button.setIcon(homeScreenPane.chest);
				}
			}

			if(line.length() >= 10)
			{
				if(line.substring(line.length()-6,line.length()).equalsIgnoreCase("Trader"))
				{
					int startIndex = line.indexOf("[");
					int endIndex = line.indexOf("]");
					int find = Integer.valueOf(line.substring(startIndex+1,endIndex));
					homeScreenPane.chooseButtons.get(find-1).button.setIcon(homeScreenPane.trader);
				}
			}
			
			if(line.length() >= 15)
			{
				if(line.substring(line.length()-15,line.length()).equalsIgnoreCase("Map to new area"))
				{
					int startIndex = line.indexOf("[");
					int endIndex = line.indexOf("]");
					int find = Integer.valueOf(line.substring(startIndex+1,endIndex));
					homeScreenPane.chooseButtons.get(find-1).button.setIcon(homeScreenPane.mapItem);
				}
			}
			
			if(line.length() >= 12){
				if(line.substring(0,12).equalsIgnoreCase("SHIP HEALTH:"))
				{
					homeScreenPane.shipHealthPanel.setText(line);
					
					setVisualHealth(Integer.valueOf(line.substring(13,line.length())));
					
				}
			}
			
			if(line.length() > 0)
			{
				if(line.equalsIgnoreCase("You read a map to show you the way on your journey!"))
				if(line.equalsIgnoreCase("You read a map to show you the way on your journey!"))
				{
					homeScreenPane.messagePanel.setText("Map to new island(s) found!");
				}
			}

			if(line.length() >= 9)
			{
				if(line.substring(0,9).equalsIgnoreCase("QUESTION:"))
				{
					homeScreenPane.messagePanel.setText(line);
					String f1 = processOutput.readLine();	//options
					String f2 = processOutput.readLine();	//======
					String f3 = processOutput.readLine();	//option1
					String f4 = processOutput.readLine();	//option1
					String f5 = processOutput.readLine();	//option3
					String f6 = processOutput.readLine();	//option4

					homeScreenPane.screenModePanel.setText(f3 + " / " + f4 + " / " + f5 + " / " + f6);
				}
			}

			if(line.length() >= 10){
			
				if(line.substring(0,10).equalsIgnoreCase("SHIP FOOD:"))
				{
					homeScreenPane.foodSuppliesPanel.setText(line);
				}
				if(line.substring(0,10).equalsIgnoreCase("SHIP GOLD:"))
				{
					homeScreenPane.goldSuppliesPanel.setText(line);
				}
			}

			if(line.length() >= 16)
			{
				if(line.substring(0,16).equalsIgnoreCase("SITES ON ISLAND:"))
				{
					homeScreenPane.sitesOnIslandPanel.setText(line);
				}
			}

			if(line.length() == 10){
				if(line.equalsIgnoreCase("[R Locked]"))
				{
					homeScreenPane.rightIslandButtonPanel.button.setEnabled(false);
					homeScreenPane.rightIslandButtonPanel.setVisible(true);
					homeScreenPane.repaint();
				}
			}

			if(line.length() == 10){
				if(line.equalsIgnoreCase("[L Locked]"))
				{
					homeScreenPane.leftIslandButtonPanel.button.setEnabled(false);
					homeScreenPane.leftIslandButtonPanel.setVisible(true);
					homeScreenPane.repaint();
				}
			}

			if(line.length() == 11){
				if(line.equalsIgnoreCase("[No Parent]"))
				{
					homeScreenPane.parentIslandButtonPanel.setVisible(false);
					homeScreenPane.repaint();
				}
			}

			if(line.length() == 10){
				if(line.equalsIgnoreCase("[No Right]"))
				{
					homeScreenPane.rightIslandButtonPanel.setVisible(false);
					homeScreenPane.repaint();
				}
			}

			if(line.length() >= 9){
				if(line.equalsIgnoreCase("[No Left]"))
				{
					homeScreenPane.leftIslandButtonPanel.setVisible(false);
					homeScreenPane.repaint();
				}
			}

			if(line.length() == 5)
			{
				if(line.equalsIgnoreCase("Right"))
				{
					homeScreenPane.rightIslandButtonPanel.setVisible(true);
					homeScreenPane.rightIslandButtonPanel.button.setEnabled(true);
					homeScreenPane.repaint();
				}
			}

			if(line.length() == 4)
			{
				if(line.equalsIgnoreCase("Left"))
				{
					homeScreenPane.leftIslandButtonPanel.setVisible(true);
					homeScreenPane.leftIslandButtonPanel.button.setEnabled(true);
					homeScreenPane.repaint();
				}
			}

			if(line.length() == 6)
			{
				if(line.equalsIgnoreCase("Parent"))
				{
					homeScreenPane.parentIslandButtonPanel.setVisible(true);
					//homeScreenPane.parentIslandButtonPanel.button.setEnabled(true);
					homeScreenPane.repaint();
				}
			}

		}

		String commandToSend = comm;
		System.out.println("***** "+comm+ " **********");
		//commandToSend = "dir\n" + "exit\n";

		processInput.write(commandToSend);
		processInput.flush();

	    }
	    catch(Exception x)
	    {
		x.printStackTrace();
	    }
	}

	public void resetInvestigateIcons()
	{
		for(buttonPanel bp : homeScreenPane.chooseButtons){
			bp.button.setIcon(homeScreenPane.mysteryIcon);
		}
	}
	
	public void setVisualHealth(int i)
	{
		if(i <= 100 && i > 80)
		{
			homeScreenPane.visualHealthPanel.title.setIcon(homeScreenPane.visualHealth100);
		}
		else if(i <= 80 && i > 60)
		{
			homeScreenPane.visualHealthPanel.title.setIcon(homeScreenPane.visualHealth80);
		}
		else if(i <= 60 && i > 40)
		{
			homeScreenPane.visualHealthPanel.title.setIcon(homeScreenPane.visualHealth60);
		}
		else if(i <= 40 && i > 20)
		{
			homeScreenPane.visualHealthPanel.title.setIcon(homeScreenPane.visualHealth40);
		}
		else if(i <= 20)
		{
			homeScreenPane.visualHealthPanel.title.setIcon(homeScreenPane.visualHealth20);
		}
		
	}
	
	public static void main(String[] args)
	{
		starGUI sG = new starGUI();
		
		if(sG.audioClip != null) sG.audioClip.stop();
		
		try{
		//start playing main screen sounds
		sG.audioIn = AudioSystem.getAudioInputStream(sG.getClass().getResource("Music/opener.wav"));
		sG.audioClip = AudioSystem.getClip();
		sG.audioClip.open(sG.audioIn);
		sG.audioClip.start();
		}
		catch(Exception f){f.printStackTrace();}
				
		sG.start();
	}
}
