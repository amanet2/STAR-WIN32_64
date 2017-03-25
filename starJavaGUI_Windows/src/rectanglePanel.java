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

public class rectanglePanel extends JPanel
{

	public void decoratePanel(Color c)
	{
		setBackground(c);
	}


	public rectanglePanel()
	{
		//setOpaque(false);
		setPreferredSize(new Dimension(100,50));
		setBounds(50, 50,410, 50);
		decoratePanel(Color.WHITE);
	}


	public rectanglePanel(Color c, int x, int y, int w, int h)
	{
		//setOpaque(false);
		setPreferredSize(new Dimension(w,h));
		setBounds(x,y,w,h);
		decoratePanel(c);
	}
}
