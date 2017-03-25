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

public class mainMenuButtonPanel extends JPanel
{
	JButton button;
	Cursor clicky;

	public void decoratePanel()
	{
		clicky = new Cursor(Cursor.HAND_CURSOR);
		button.setCursor(clicky);
		
		add(button);
	}

	public mainMenuButtonPanel()
	{
		button = new JButton("MAIN MENU");
		setOpaque(false);
		setPreferredSize(new Dimension(100,50));
		setBounds(930, 600, 80, 40);
		decoratePanel();
	}
}
