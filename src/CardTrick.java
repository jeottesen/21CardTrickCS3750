import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class CardTrick extends JFrame
{
		
	private Player player;
	private Dealer dealer;
	private Board board;
	

	public static void main(String[] args)
	{
		new CardTrick();
	}
	
	CardTrick()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(Globals.FRAME_WI, Globals.FRAME_HI);

		add(board = new Board(dealer));
		
		setVisible(true);
		dealer = new Dealer(board);
		player = new Player(dealer);
		player.testDealer();

	}


	private void addButton(JButton button, int locX, int locY, int Wi, int Hi)
	{
		button.setBounds(locX, locY, Wi, Hi);
		//		button.setBackground(Color.CYAN);
		//		button.setForeground(Color.BLUE);
		button.setBorderPainted(true);

		//button.addActionListener(l);

		button.addActionListener(new ActionListener() 
		{
			public void actionPerformed(ActionEvent e) 
			{
				buttonPressed(button, e);
			}
		});

		add(button);
	}

	public void buttonPressed(JButton button, ActionEvent e)
	{


	}


}

//geese:  testing a commit to git
//Zach: test commit
//Test Commit (Ashley)