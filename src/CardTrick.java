import java.awt.Dimension;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;


public class CardTrick extends JFrame
{
		
	public static void main(String[] args)
	{
		new CardTrick();
	}
	
	CardTrick()
	{
		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(Globals.FRAME_WI, Globals.FRAME_HI);
		//setPreferredSize(new Dimension(Globals.FRAME_WI + 1, Globals.FRAME_HI + 1));
		//setMaximumSize(new Dimension(Globals.FRAME_WI + 1, Globals.FRAME_HI + 1));
		setMinimumSize(new Dimension(Globals.FRAME_WI - 400, Globals.FRAME_HI - 10));
		add(new Board());
		setVisible(true);
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
//geese:  testing a commit to geese_branch
//geese:  testing Windows style line endings
//geese:  well, this is fun...trying another fix for line endings