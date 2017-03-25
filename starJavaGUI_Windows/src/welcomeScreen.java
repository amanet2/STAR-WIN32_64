import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

public class welcomeScreen extends JLayeredPane
{
	homeScreenButtonPanel continueButtonPanel;
	mainMenuButtonPanel backButtonPanel;

	public welcomeScreen()
	{
			JPanel firstPanel = new welcomeScreenBackground();
			firstPanel.setOpaque(true);
	
			continueButtonPanel = new homeScreenButtonPanel();
			
			backButtonPanel = new mainMenuButtonPanel();
			
			backButtonPanel.button.setBackground(Color.RED);
			continueButtonPanel.button.setBackground(Color.GREEN);
			
		add(firstPanel, new Integer(0), 0);
		add(continueButtonPanel, new Integer(1),0);
		add(backButtonPanel, new Integer(2),0);
		
		
		setBounds(0, 0, 1024, 768);
	}
}
