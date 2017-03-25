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

public class exitButtonPanel extends JPanel
{
	JButton button;
	Cursor clicky;
	public void decoratePanel()
	{
		button = new JButton("EXIT");
			button.setBackground(Color.RED);

		 clicky = new Cursor(Cursor.HAND_CURSOR);
			button.setCursor(clicky);

		add(button);
	}

	public exitButtonPanel()
	{
	setOpaque(false);
		setPreferredSize(new Dimension(100,50));
		setBounds(930, 600, 80, 40);
		decoratePanel();
	}
}
