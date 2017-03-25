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

public class newGameButtonPanel extends JPanel
{
	JButton button;
	Cursor clicky;

	public void decoratePanel()
	{
		//button = new JButton("NEW GAME");
		button.setBackground(Color.GREEN);

		clicky = new Cursor(Cursor.HAND_CURSOR);
		
		button.setCursor(clicky);

		add(button);
	}

	public newGameButtonPanel()
	{
		button = new JButton("NEW GAME");
		setOpaque(false);
		setPreferredSize(new Dimension(100,50));
		setBounds(20, 20, 80, 40);
		decoratePanel();
	}
}
