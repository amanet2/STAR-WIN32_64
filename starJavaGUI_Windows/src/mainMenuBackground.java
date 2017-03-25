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

public class mainMenuBackground extends JPanel
{
	
	public void decoratePanel()
	{
		try{
			BufferedImage myPicture = ImageIO.read(new File("Images/mainMenuBackground.jpg"));
			Image scaledImage = myPicture.getScaledInstance(this.getWidth(),this.getHeight(),Image.SCALE_SMOOTH);
			JLabel picLabel = new JLabel(new ImageIcon(scaledImage));

			add(picLabel);
		}
		catch(Exception e){e.printStackTrace();}
	}
	
	public mainMenuBackground()
	{
		setPreferredSize(new Dimension(1024,768));
		setBounds(0, 0, 1024, 768);
		decoratePanel();
	}
}
