import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

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

	public void Splash() {
	}

	public Board() {
		dealer = new Dealer(this);
		// Run this in a different thread to decrease loading speed
		EventQueue.invokeLater(new Runnable() {
			@Override
			public void run() {
				
				setLayout(null);

				// load background image
				try {
					backgroundImg = ImageIO.read(getClass().getResourceAsStream("images/background.jpg"));
					backgroundImg = backgroundImg.getScaledInstance(Globals.FRAME_WI, Globals.FRAME_HI,
							Image.SCALE_SMOOTH);

				} catch (IOException e) {
					System.out.println("ERROR: " + e.getMessage());
				}

				column1.setId(1);
				column2.setId(2);
				column3.setId(3);

				column1.setLocation(Globals.COLUMN_ONE_LOCX, 0);
				column2.setLocation(Globals.COLUMN_TWO_LOCX, 0);
				column3.setLocation(Globals.COLUMN_THREE_LOCX, 0);

				add(column1);
				add(column2);
				add(column3);

			}
		});
	}
	
	public void startGame() {
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
	}
}
