import java.awt.BasicStroke;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.Stroke;
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
		setSize(Globals.FRAME_WI, Globals.FRAME_HI);
		setPreferredSize(new Dimension(Globals.FRAME_WI, Globals.FRAME_HI));
		
		
		
		setOpaque(false);
		
		ColumnBorder cb1 = new ColumnBorder(1);
		cb1.setLocation(10, 10);
		
		addMouseListener(cb1);
		add(cb1);
		
	}
	
	public void indicateColumn(int id) {
		
	}
	
	public void pickCard() {
		
	}
	
	

	private class ColumnBorder extends JPanel implements MouseListener {
		
		private int columnNumber;
		private boolean hoveredOver;
		private boolean selected;

		public ColumnBorder(int columnNumber) {
			this.columnNumber = columnNumber;
			setSize(Globals.CARD_WI + 40, Globals.COLUMN_HI + 40);
			setPreferredSize(new Dimension(Globals.CARD_WI + 40, Globals.COLUMN_HI + 40));

			setOpaque(false);
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
			
			if (hoveredOver) {
				g2d.setColor(Globals.HOVERED_COLUMN_BORDER_COLOR);
			}
			// TODO else if selected change green or something
			else {
				g2d.setColor(Globals.TRANSPARENT_WHITE);
			}
			
			g2d.drawRoundRect(10, 10, Globals.CARD_WI + 20, Globals.COLUMN_HI + 20, 5, 5);
			
			g2d.setStroke(oldStroke);
		}
		
		@Override
		public void mouseReleased(MouseEvent e) {
		}

		@Override
		public void mousePressed(MouseEvent e) {
		}

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
			// set selected
			indicateColumn(columnNumber);
		}
	}
	
	

}
