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

public class newGameMenuTitlePanel extends JPanel
{
	public void decoratePanel()
	{
		JLabel title = new JLabel("NEW GAME MENU");

		add(title);
	}

	public newGameMenuTitlePanel()
	{
		setOpaque(false);
		setPreferredSize(new Dimension(100,50));
		setBounds(50, 50,410, 50);
		decoratePanel();
	}
}
