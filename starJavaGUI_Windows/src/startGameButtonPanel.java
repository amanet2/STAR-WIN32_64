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

public class startGameButtonPanel extends JPanel
{
	JButton button;
	Cursor clicky;

	public void decoratePanel()
	{
		clicky = new Cursor(Cursor.HAND_CURSOR);
		
		button.setCursor(clicky);
		button.setBackground(Color.GREEN);


		add(button);
	}

	public startGameButtonPanel()
	{
		button = new JButton("NEW GAME");
	setOpaque(false);
		setPreferredSize(new Dimension(100,50));
		setBounds(20, 20, 80, 40);
		decoratePanel();
	}
}
