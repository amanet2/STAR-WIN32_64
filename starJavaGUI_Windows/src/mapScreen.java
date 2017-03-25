import java.net.*;
import java.util.*;
import java.io.*;
import java.lang.Math.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.*;

import javax.imageio.*;
import javax.swing.*;
import javax.swing.border.*;
import javax.swing.event.*;

public class mapScreen extends JPanel
{
	ImageIcon oceanSurface;
	ImageIcon town1;
	ImageIcon town2;
	ImageIcon town3;
	ImageIcon town4;
	ImageIcon town5;
	ImageIcon town6;
	ImageIcon town7;
	ImageIcon town8;
	ImageIcon town9;
	ImageIcon town10;
	ImageIcon town11;
	ImageIcon traderStore;
	ImageIcon questionBG;
	ImageIcon portBG;
	ImageIcon stormBG;
	ImageIcon battleBG;
	ImageIcon bigMapSurface;
	
	JLabel mapTexture;

	public void decoratePanel()
	{
		setBackground(Color.BLUE);
		try{
			BufferedImage myPicture = ImageIO.read(new File("Images/ocean.png"));
			Image scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			oceanSurface = new ImageIcon(scaledImage);
			
			mapTexture = new JLabel(oceanSurface);

			add(mapTexture);	//sets to the ocean surface first
			myPicture = ImageIO.read(new File("Images/bigMapSurface.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			bigMapSurface = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/town1.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			town1 = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/town2.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			town2 = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/town3.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			town3 = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/town4.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			town4 = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/town5.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			town5 = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/town6.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			town6 = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/town7.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			town7 = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/town8.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			town8 = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/town9.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			town9 = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/town10.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			town10 = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/town11.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			town11 = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/traderStore.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			traderStore = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/questionBG.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			questionBG = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/portBG.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			portBG = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/stormBG.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			stormBG = new ImageIcon(scaledImage);
			
			myPicture = ImageIO.read(new File("Images/battleBG.png"));
			scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			battleBG = new ImageIcon(scaledImage);
		}
		catch(Exception e){e.printStackTrace();}
		
		setOpaque(false);
	}

	public void setBackground(ImageIcon icon)
	{
		mapTexture.setIcon(icon);
	}



	public mapScreen()
	{
		//setOpaque(false);
		setPreferredSize(new Dimension(300,300));
		setBounds(0, 0,1024,520);
		decoratePanel();
		
	}

	public mapScreen(int x, int y)
	{
		//setOpaque(false);
		setPreferredSize(new Dimension(100,50));
		setBounds(x, y,500, 500);
		decoratePanel();
	}
}
