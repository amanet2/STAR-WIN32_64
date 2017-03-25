import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

public class deathScreen extends JLayeredPane
{
	buttonPanel continueButtonPanel;

	public deathScreen()
	{
		JPanel firstPanel = new deathScreenBackground();
		firstPanel.setOpaque(true);

		continueButtonPanel = new buttonPanel("CONTINUE",400,500);
	
		add(firstPanel, new Integer(0), 0);
		add(continueButtonPanel, new Integer(1),0);
		
		setBounds(0, 0, 1024, 768);
	}
}
