import java.awt.Dimension;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.*;

public class CardTrick extends JFrame {

    SplashPanel splash;
    Board board;
    
	public static void main(String[] args)
	{
		new CardTrick();
	}
	
	CardTrick()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(Globals.FRAME_WI, Globals.FRAME_HI);
		setMinimumSize(new Dimension(Globals.FRAME_WI - 400, Globals.FRAME_HI - 10));
		

		//Before adding board, display splash screen
		splash = new SplashPanel("/images/splash_alt.png");
		splash.setBounds(0, 0, Globals.FRAME_WI, Globals.FRAME_HI);
		splash.setLayout(null);
		add(splash);

		// Run this in a different thread to decrease loading speed
		new Thread(new Runnable() {
			@Override
			public void run() {
		
				board = new Board();
				add(board);
				board.setVisible(false);
				// animate the button moving in from the left
				Animations.movePanel(
						addButton(new JButton("Play!"), 
								Globals.FRAME_WI + 175, 250, 175, 75),
						Globals.FRAME_WI/2, 250);
				
			}
		}).start();

		setVisible(true);
		
	}

    private JButton addButton(final JButton button, int locX, int locY, int Wi, int Hi)
	{
		button.setBounds(locX, locY, Wi, Hi);
		Styles.configureJButton(button, true);
		button.setFont(new Font("Helvetica", Font.BOLD, 36));

		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				buttonPressed(button, e);
			}
		});
		button.setVisible(true);
		splash.add(button);
		
		return button;
	}

	public void buttonPressed(JButton button, ActionEvent e)
	{
	    splash.setVisible(false);
	    button.setVisible(false);
	    
	    remove(splash);
	    remove(button);
	    
	    //Add board and start trick on button press
	    board.setVisible(true);
		board.startGame();
	}

}