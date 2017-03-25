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

public class titlePanel extends JPanel
{

	JLabel title;
	
	public void decoratePanel(Icon i)
	{
		title = new JLabel(i);

		add(title);
	}

	public void decoratePanel(String s)
	{
		title = new JLabel(s);

		add(title);
	}


	public void setText(String s)
	{
		title.setText(s);
	}



	public titlePanel(String s)
	{
		//setOpaque(false);
		setPreferredSize(new Dimension(100,50));
		setBounds(50, 50,410, 50);
		decoratePanel(s);
	}

	public titlePanel(String s, int x, int y)
	{
		//setOpaque(false);
		setPreferredSize(new Dimension(100,50));
		setBounds(x, y,410, 50);
		decoratePanel(s);
	}

	public titlePanel(String s, int x, int y, int w, int h)
	{
		//setOpaque(false);
		setPreferredSize(new Dimension(100,50));
		setBounds(x,y,w,h);
		decoratePanel(s);
	}

	public titlePanel(String s, int x, int y, int w, int h,boolean choice)
	{
		setOpaque(choice);
		setPreferredSize(new Dimension(100,50));
		setBounds(x,y,w,h);
		decoratePanel(s);
	}
	public titlePanel(ImageIcon i, int x, int y, int w, int h)
	{
		setOpaque(false);
		setPreferredSize(new Dimension(w,h));
		setBounds(x, y, w, h);
		decoratePanel(i);
	}
}
