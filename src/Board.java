import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.image.BufferedImage;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JPanel;
import java.awt.Graphics2D;
import java.awt.RadialGradientPaint;
import java.awt.geom.Point2D;

public class Board extends JPanel {

	public BufferedImage splashImg;
	public Image backgroundImg;

	// Since the UML Diagram shows these are a composition
	// relationship they need to be final. They would be
	// an Aggregation relationship without it.
	private final Column column1 = new Column();
	private final Column column2 = new Column();
	private final Column column3 = new Column();

	private int columnGap;

	private Dealer dealer;

	public void Splash() {
	}


	public Board() {

		int screenWidth = (int) Toolkit.getDefaultToolkit().getScreenSize().getWidth();
		int screenHeight = (int) Toolkit.getDefaultToolkit().getScreenSize().getHeight();

		dealer = new Dealer(this);
		// Run this in a different thread to decrease loading speed
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				setLayout(null);

				// load background image
				try
				{
					backgroundImg = ImageIO.read(getClass().getResourceAsStream("images/alt_purple_backgroundBIG.png"));
					backgroundImg = backgroundImg.getScaledInstance(screenWidth, screenHeight, Image.SCALE_SMOOTH);
				} catch (IOException e)
				{
					System.out.println("ERROR: " + e.getMessage());
				}

				column1.setId(1);
				column2.setId(2);
				column3.setId(3);
				
				// 0 because zero change in window width at this point
				setColumnLocations(0); 

				add(column1);
				add(column2);
				add(column3);
			}
		});
		
	}
	
	public void startGame() {
		dealer.deal();
	}
	
	public void resetGame() {
		removeAll();
		dealer = new Dealer(this);
		column1.clearColumn();
		column2.clearColumn();
		column3.clearColumn();
		
		add(column1);
		add(column2);
		add(column3);
		repaint();
		
		dealer.deal();
	}

	/* this method will be called from the Dealer object when the board is resized.
	 * Dealer has a reference to the board, and adds a Component listener to board.
	 * This method is called from the Component listener.
	 * The listener sends the difference between the original board width and the new board width,
	 * as the argument deltaWindowWidthPixels, and here the column locations are altered accordingly.
	 */
	public void setColumnLocations(int deltaBoardWidthPixels) {
		// the only thing changing on window resize is the space BETWEEN columns
		columnGap = Globals.COLUMN_GAP - (int) deltaBoardWidthPixels / 2;

		column1.setLocation(Globals.COLUMN_ONE_LOCX, 0);
		column2.setLocation(Globals.COLUMN_ONE_LOCX + Globals.CARD_WI + columnGap + 30, 0);
		column3.setLocation(Globals.COLUMN_ONE_LOCX + (Globals.CARD_WI * 2) + (columnGap * 2) + (30 * 2), 0);
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

		setBackground(Color.GRAY);
		g.drawImage(backgroundImg, 0, 0, this);

		Point2D gradient_CenterPoint = new Point2D.Float(this.getWidth() / 2, 400 - 100);
		float radius = 400f;
		float[] dist = { 0.2f, .8f };  //first cloat is where first color begins, and then gradually reaches second color at second float
		Color[] colors = { new Color(255, 255, 255, 40), new Color(255, 255, 255, 0) };

		RadialGradientPaint radialGradientPaint = new RadialGradientPaint(gradient_CenterPoint, radius, dist,
				colors);

		Graphics2D g2d = (Graphics2D) g;
		g2d.setPaint(radialGradientPaint);
		g2d.fillArc((this.getWidth() / 2 - 400), -100, 800, 800, 0, 360);//upper left-hand corner, width, height, startArc, endArc

	}

}