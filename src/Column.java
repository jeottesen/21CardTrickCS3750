import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Column extends JPanel {
	private ArrayList<Card> cards;
	
	public Column() {
		setLayout(null);
		int ColumnHeight = (Globals.CARD_SPACING * 7) + (Globals.CARD_HI - Globals.CARD_SPACING);
		setSize(Globals.CARD_WI, ColumnHeight);
		System.out.println("Column Height: " + ((Globals.CARD_SPACING * 7) + (Globals.CARD_HI - Globals.CARD_SPACING)));
		setPreferredSize(new Dimension(Globals.CARD_WI, ColumnHeight));
		
		cards = new ArrayList<>();
	}
	
	
	public void addCard(Card card) {
		cards.add(card);
		drawCards();
	}
	
	public Card getCard(int getCard)
	{
		return cards.get(getCard);
	}
	
	private void drawCards() {
		for(int i = cards.size() - 1; i >= 0; i--) {
			Card c = cards.get(i); 
			c.setLocation(0, i * Globals.CARD_SPACING);
			add(c);
		}
	}
	
	
}
