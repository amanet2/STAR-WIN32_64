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

public class buttonPanel extends JPanel
{
	JButton button;
	Cursor clicky;
	public void decoratePanel(Icon i)
	{
		 clicky = new Cursor(Cursor.HAND_CURSOR);
		 button = new JButton(i);
		
		button.setCursor(clicky);
		
		add(button);
	}

	public void decoratePanel()
	{
		clicky = new Cursor(Cursor.HAND_CURSOR);
		
		button = new JButton("BUTTON");
		button.setCursor(clicky);
		add(button);
	}

	public void setText(String s)
	{
		button.setText(s);
	}

	public buttonPanel()
	{
		setOpaque(false);
		setPreferredSize(new Dimension(100,50));
		setBounds(930, 600, 80, 40);
		decoratePanel();
	}

	public buttonPanel(int x, int y)
	{
		setOpaque(false);
		setPreferredSize(new Dimension(100,50));
		setBounds(x, y, 80, 40);
		decoratePanel();
	}

	public buttonPanel(ImageIcon i,int x, int y)
	{
		setOpaque(false);
		setPreferredSize(new Dimension(50,50));
		setBounds(x, y, 50, 50);
		decoratePanel(i);
	}

	public buttonPanel(String s,int x, int y)
	{
		setOpaque(false);
		setPreferredSize(new Dimension(100,50));
		setBounds(x, y, 80, 40);
		decoratePanel();
		setText(s);
	}

	public buttonPanel(String s,int x, int y, int w, int h)
	{
		setOpaque(false);
		setPreferredSize(new Dimension(w,h));
		setBounds(x, y, w, h);
		decoratePanel();
		setText(s);
		button.setPreferredSize(new Dimension(w,h));
	}

	public buttonPanel(ImageIcon i, int x, int y, int w, int h)
	{
		setOpaque(false);
		setPreferredSize(new Dimension(w,h));
		setBounds(x, y, w, h);
		decoratePanel(i);
		
		button.setPreferredSize(new Dimension(w,h));
	}
}
