import java.awt.*;
import java.awt.geom.*;
import javax.swing.*;
import java.awt.event.*;

public class winScreen extends JLayeredPane
{
	buttonPanel continueButtonPanel;

	public winScreen()
	{
		JPanel firstPanel = new winScreenBackground();
		firstPanel.setOpaque(true);
			
		titlePanel winTitlePanel = new titlePanel("YOU WIN!",500,100);
			winTitlePanel.setText("YOU WIN!");
			winTitlePanel.setOpaque(false);
			winTitlePanel.setForeground(Color.GREEN);
			//winTitlePanel.setFont(new Font("Times New Roman", Font.BOLD, 18));

		continueButtonPanel = new buttonPanel("CONTINUE",500,610);
	
		add(firstPanel, new Integer(0), 0);
		add(continueButtonPanel, new Integer(1),0);
		add(winTitlePanel, new Integer(2),0);
		
		setBounds(0, 0, 1024, 768);
	}
}
