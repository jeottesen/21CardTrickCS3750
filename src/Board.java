import java.awt.Color;
import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Board extends JPanel {
	
	public BufferedImage backgroundImg;
	
	// Since the UML Diagram shows these are a composition
	// relationship they need to be final. They would be
	// an Aggregation relationship without it.
	private final Column column1 = new Column();
	private final Column column2 = new Column();
	private final Column column3 = new Column();
	
	private Dealer dealer;

	public Board() {
		
		dealer = new Dealer();
		dealer.setBoard(this);
		
		setLayout(null);
		
		//load background image
		try
		{
			backgroundImg = ImageIO.read(getClass().getResourceAsStream("images/TableFelt.png"));
		}
		catch(IOException e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
		

		
		// Temporary deck for testing. Will be getting cards from dealer later
		Deck deck = new Deck();
		
		column1.setLocation(Globals.COLUMN_ONE_LOCX, Globals.COLUMN_ONE_LOCY);
		column2.setLocation(Globals.COLUMN_TWO_LOCX, Globals.COLUMN_TWO_LOCY);
		column3.setLocation(Globals.COLUMN_THREE_LOCX, Globals.COLUMN_THREE_LOCY);
		
		add(column1);
		add(column2);
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
		setBackground(Globals.BACKGROUND_COLOR);
		//g.drawImage(backgroundImg, 0, 0, this);
	}

}
