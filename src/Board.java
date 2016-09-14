import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel {
	
	public BufferedImage backgroundImg;

	public Board() {
		//load background image
		try
		{
			backgroundImg = ImageIO.read(getClass().getResourceAsStream("images/TableFelt.png"));
		}
		catch(IOException e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
		
		System.out.println("Making a test card");
		Card test = new Card(CardValues.ACE, CardSuits.SPADES);
		CardPanel c = new CardPanel(test);
		add(c);
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setBackground(Color.GREEN);
		g.drawImage(backgroundImg, 0, 0, this);
	}

}
