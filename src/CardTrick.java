import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.geom.Rectangle2D;
import javax.swing.*;
import javax.swing.border.LineBorder;


public class CardTrick extends JFrame
{
    SplashPanel splash;
    
	public static void main(String[] args)
	{
		new CardTrick();
	}
	
	CardTrick()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(Globals.FRAME_WI, Globals.FRAME_HI);

		//Before adding board, display splash screen
		splash = new SplashPanel("/images/splash_alt.png");
		splash.setBounds(0, 0, Globals.FRAME_WI, Globals.FRAME_HI);
		splash.setLayout(null);
		add(splash);

		//Add play button
		addButton(new JButton("Play!"), Globals.FRAME_WI/2, 250, 175, 75);
		
		setVisible(true);
	}

    private void addButton(final JButton button, int locX, int locY, int Wi, int Hi)
	{
		button.setBounds(locX, locY, Wi, Hi);
		Styles.configureJButton(button, true);
		button.setFont(new Font("Helvetica", Font.PLAIN, 36));

		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				buttonPressed(button, e);
			}
		});
		button.setVisible(true);
		splash.add(button);
	}

	public void buttonPressed(JButton button, ActionEvent e)
	{
	    splash.setVisible(false);
	    button.setVisible(false);
	    
	    remove(splash);
	    remove(button);
	    
	    //Add board and start trick on button press
	    add(new Board());
	}


}

//geese:  testing a commit to git
//geese:  testing a commit to geese_branch
//geese:  testing Windows style line endings
//geese:  well, this is fun...trying another fix for line endings