import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

public class mainMenu extends JLayeredPane
{
	newGameButtonPanel newGameButtonPanel;
	exitButtonPanel exitButtonPanel;
	public mainMenu()
	{
			JPanel firstPanel = new mainMenuBackground();
				firstPanel.setOpaque(true);

			exitButtonPanel = new exitButtonPanel();
			newGameButtonPanel = new newGameButtonPanel();

			JPanel mainMenuTitlePanel = new mainMenuTitlePanel();
	
		add(firstPanel, new Integer(0), 0);
		add(exitButtonPanel, new Integer(1), 0);
		add(newGameButtonPanel, new Integer(2),0);
		add(mainMenuTitlePanel, new Integer(3),0);
		
		
		
		setBounds(0, 0, 1024, 768);
	}
}
