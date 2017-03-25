import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

public class newGameMenu extends JLayeredPane
{
	mainMenuButtonPanel backButtonPanel;
	startGameButtonPanel startGameButtonPanel;

	public newGameMenu()
	{
			JPanel firstPanel = new newGameMenuBackground();
			firstPanel.setOpaque(true);
	
			backButtonPanel = new mainMenuButtonPanel();
			startGameButtonPanel = new startGameButtonPanel();
				backButtonPanel.button.setBackground(Color.ORANGE);
				
			JPanel newGameMenuTitlePanel = new newGameMenuTitlePanel();
	
		add(firstPanel, new Integer(0), 0);
		add(backButtonPanel, new Integer(1), 0);
		add(startGameButtonPanel, new Integer(2),0);
		add(newGameMenuTitlePanel, new Integer(3),0);
		
		setBounds(0, 0, 1024, 768);
	}
}
