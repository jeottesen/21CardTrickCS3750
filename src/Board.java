import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
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
	
	private final int COLUMN_GAP;
	private int columnGap;
	private int pixelsLost = 0;

	private Dealer dealer;

	public void Splash() {
	}

	public Board() {

		dealer = new Dealer(this);
		setLayout(null);

		// load background image
		try
		{
			backgroundImg = ImageIO.read(getClass().getResourceAsStream("images/background.jpg"));
			backgroundImg = backgroundImg.getScaledInstance(Globals.FRAME_WI, Globals.FRAME_HI, Image.SCALE_SMOOTH);

		} catch (IOException e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}

		// Temporary deck for testing. Will be getting cards from dealer later
		Deck deck = new Deck();

		COLUMN_GAP = Globals.COLUMN_TWO_LOCX - (Globals.COLUMN_ONE_LOCX + Globals.CARD_WI + 30);
		
		column1.setId(1);
		column2.setId(2);
		column3.setId(3);
		
		setColumnLocations(1);

		add(column1);
		// add(Box.createRigidArea(new Dimension(100, 0)));
		// add(Box.createHorizontalGlue());
		add(column2);
		// add(Box.createRigidArea(new Dimension(100, 0)));
		// add(Box.createHorizontalGlue());
		add(column3);

		dealer.deal();

		this.addComponentListener(new ComponentListener() {

			public void componentResized(ComponentEvent e) {
				Board board = ((Board) e.getSource());
				pixelsLost = 990 - board.getWidth();
				//float factor = ((float)(board.getWidth()/990f));
				setColumnLocations(pixelsLost);
				
				System.out.println("board.getWidth(): " + board.getWidth());
				//System.out.println("factor: " + factor);
				System.out.println("columnGap:" + columnGap);
			}
			
			public void componentShown(ComponentEvent e) {}
			public void componentMoved(ComponentEvent e) {}
			public void componentHidden(ComponentEvent e) {}
		});
	}
	
	public void setColumnLocations(int pixelsLost){
		
		System.out.println("::" + pixelsLost);
		if (pixelsLost / 2 <= COLUMN_GAP)
		{	
			System.out.println("If called");
			columnGap = COLUMN_GAP - (int) pixelsLost / 2;
		}
		column1.setLocation(Globals.COLUMN_ONE_LOCX, Globals.COLUMN_ONE_LOCY);
		column2.setLocation(Globals.COLUMN_ONE_LOCX + Globals.CARD_WI + columnGap + 30, Globals.COLUMN_ONE_LOCY);
		column3.setLocation(Globals.COLUMN_ONE_LOCX + (Globals.CARD_WI * 2) + (columnGap * 2) + (30 * 2), Globals.COLUMN_ONE_LOCY);
		
		//column1.setLocation(Globals.COLUMN_ONE_LOCX, Globals.COLUMN_ONE_LOCY);
		//column2.setLocation(Globals.COLUMN_TWO_LOCX, Globals.COLUMN_TWO_LOCY);
		//column3.setLocation(Globals.COLUMN_THREE_LOCX, Globals.COLUMN_THREE_LOCY);
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

	public Column getColumnOne() {
		return column1;
	}

	public Column getColumnTwo() {
		return column2;
	}

	public Column getColumnThree() {
		return column3;
	}

	@Override
	public void paintComponent(Graphics g) {
		super.paintComponent(g);

		/*   trying out setting column locations here 
		 *   so it can use new columnGap numbers from the Component listener 
		 */
	
		
		
		setBackground(Color.GRAY);
		g.drawImage(backgroundImg, 0, 0, this);

	}
	
	
	
}
