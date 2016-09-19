import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.swing.BoxLayout;
import java.awt.GridLayout;
import javax.imageio.ImageIO;
import javax.swing.Box;
import javax.swing.JPanel;
import java.awt.Dimension;


public class Board extends JPanel {
	

	public BufferedImage splashImg;
	public Image backgroundImg;
	
	
	// Since the UML Diagram shows these are a composition
	// relationship they need to be final. They would be
	// an Aggregation relationship without it.
	private final Column column1 = new Column();
	private final Column column2 = new Column();
	private final Column column3 = new Column();
	
	private Dealer dealer;
	
	public void Splash()
	{
	}

	public Board() {
		
		dealer = new Dealer(this);
		//BoxLayout boxLayout = new BoxLayout(columnPanel,BoxLayout.X_AXIS);
		//setLayout(null);
		//setLayout(new GridLayout(1, 3, 50, 0));
		
		setLayout(new BoxLayout(this,BoxLayout.X_AXIS));
		
		//load background image
		try
		{
			backgroundImg = ImageIO.read(getClass().getResourceAsStream("images/background.jpg"));
			backgroundImg = backgroundImg.getScaledInstance(Globals.FRAME_WI, Globals.FRAME_HI, Image.SCALE_SMOOTH);

		}
		catch(IOException e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
		

		
		// Temporary deck for testing. Will be getting cards from dealer later
		Deck deck = new Deck();
		
		column1.setId(1);
		column2.setId(2);
		column3.setId(3);
		
		//column1.setLocation(Globals.COLUMN_ONE_LOCX, Globals.COLUMN_ONE_LOCY);
		//column2.setLocation(Globals.COLUMN_TWO_LOCX, Globals.COLUMN_TWO_LOCY);
		//column3.setLocation(Globals.COLUMN_THREE_LOCX, Globals.COLUMN_THREE_LOCY);
		
		
		add(column1);
		//add(Box.createRigidArea(new Dimension(100, 0)));
		//add(Box.createHorizontalGlue());
		add(column2);
		//add(Box.createRigidArea(new Dimension(100, 0)));
		//add(Box.createHorizontalGlue());
		add(column3);
		
		
		dealer.deal();
	}
	
	public void addToColumn(int columnId, Card card) {
		
		switch (columnId) {
		case 1:
			column1.addCard(card);
			break;
		case 2:
			column2.addCard(card);
			break;
		case 3:
			column3.addCard(card);
			break;
		}
	}
	
	public Column getColumnOne()
	{
		return column1;
	}
	public Column getColumnTwo()
	{
		return column2;
	}
	public Column getColumnThree()
	{
		return column3;
	}

	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);

		setBackground(Color.GRAY);
		g.drawImage(backgroundImg, 0, 0, this);
	}
}
