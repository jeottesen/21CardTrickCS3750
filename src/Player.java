import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

import javax.swing.JPanel;

public class Player extends JPanel {
	
	// Association between Player and Dealer
	private Dealer dealer;
	
	// Aggregation relationship between Card and player
	// I don't think we actually need this I thought
	// the program wasn't supposed to know the card
	// This is how you implement the UML though so
	// we should ask him about it in class
	private Card card;
	
	private boolean hasSelectedCard;
	
	public Player(Dealer dealer) {
		this.dealer = dealer;
		setLayout(null);
		setSize(Globals.FRAME_WI, Globals.FRAME_HI);
		setPreferredSize(new Dimension(Globals.FRAME_WI, Globals.FRAME_HI));
		
		// add an empty mouse listener so that the events in ColumnBorder can propagate up
		addMouseListener(new MouseAdapter(){});
		
		setOpaque(false);
		
		ColumnBorder cb1 = new ColumnBorder(1);
		cb1.setLocation(5, 5);
		
		add(cb1);
		
		ColumnBorder cb2 = new ColumnBorder(2);
		cb2.setLocation(Globals.COLUMN_TWO_LOCX - 15, 5);
		
		add(cb2);
		

		ColumnBorder cb3 = new ColumnBorder(3);
		cb3.setLocation(Globals.COLUMN_THREE_LOCX - 15, 5);
		
		add(cb3);
		
	}
	
	public void indicateColumn(int id) {
		
	}
	
	public void pickCard() {
		
	}
	

	

	private class ColumnBorder extends JPanel {
		
		public int columnNumber;
		public boolean hoveredOver;
		private boolean selected;

		public ColumnBorder(int columnNumber) {
			this.columnNumber = columnNumber;
			setSize(Globals.CARD_WI + 30, Globals.COLUMN_HI + 30);
			setPreferredSize(new Dimension(Globals.CARD_WI + 30, Globals.COLUMN_HI + 30));

			setOpaque(false);
			addMouseListener(new MouseAdapter() {				
				@Override
				public void mouseExited(MouseEvent e) {
					System.out.println("ColumnBorder " + columnNumber + " Mouse Exited.");
					hoveredOver = false;
					repaint();
				}
	
				@Override
				public void mouseEntered(MouseEvent e) {
					System.out.println("ColumnBorder " + columnNumber + " Mouse Enter.");
					hoveredOver = true;
					repaint();
				}
	
				@Override
				public void mouseClicked(MouseEvent e) {
					System.out.println("ColumnBorder " + columnNumber + " Mouse Clicked.");
					selected = !selected; //toggle selected
					repaint();
					indicateColumn(columnNumber);
				}
			});
		}
		
		@Override
		public void paintComponent(Graphics g)
		{

			//setVisible(true);
			Graphics2D g2d = (Graphics2D)g;
			super.paintComponent(g2d);
			
			float thickness = 10;
			Stroke oldStroke = g2d.getStroke();
			g2d.setStroke(new BasicStroke(thickness));
			
			g2d.setColor(Globals.TRANSPARENT_WHITE);
			
			if (hoveredOver) {
				g2d.setColor(Globals.HOVERED_COLUMN_BORDER_COLOR);
			}
			if (selected) {
				g2d.setColor(Globals.SELECTED_COLUMN_BORDER_COLOR);
			}
			
			g2d.drawRoundRect(5, 5, Globals.CARD_WI + 20, Globals.COLUMN_HI + 20, 5, 5);
			
			g2d.setStroke(oldStroke);
		}

	}
	
	

}
