import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Column extends JPanel {
	private ArrayList<Card> cards;
	private int id;
	private double realWidth;
	
	public Column() {
		setLayout(null);
		setOpaque(false);//this way the Board's background color shows
		int ColumnHeight = (Globals.CARD_SPACING * 7) + (Globals.CARD_HI - Globals.CARD_SPACING);
		setSize(Globals.CARD_WI, ColumnHeight);
		//System.out.println("Column Height: " + ((Globals.CARD_SPACING * 7) + (Globals.CARD_HI - Globals.CARD_SPACING)));
		setPreferredSize(new Dimension(Globals.CARD_WI, ColumnHeight));
		cards = new ArrayList<>();	
		realWidth = 280;  //current size of Frame is 840.  (840 / 3) is actual column width,
						  //even though the columns think they're 200.
	}
	
	
	
	public void setId(int id){
		this.id = id;
	}
	public int getId(){
		return id;
	}
	
	public void addCard(Card card) {
		cards.add(card);
		drawCards();
		//setCardListener(card);
	}
	
	private void drawCards() {
		for(int i = cards.size() - 1; i >= 0; i--) {
			Card c = cards.get(i); 
			c.setLocation(36, (i * Globals.CARD_SPACING) + 30 );//center the cards horizontally in the column (based on board width 840)
																//make some space above the cards
			add(c);
		}
	}
	}
