import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Column extends JPanel {
	private ArrayList<Card> cards;
	private int id;  //it's in the diagram.
	
	public Column() {
		setLayout(null);
		int ColumnHeight = (Globals.CARD_SPACING * 7) + (Globals.CARD_HI - Globals.CARD_SPACING);
		setSize(Globals.CARD_WI, ColumnHeight);
		//System.out.println("Column Height: " + ((Globals.CARD_SPACING * 7) + (Globals.CARD_HI - Globals.CARD_SPACING)));
		setPreferredSize(new Dimension(Globals.CARD_WI, ColumnHeight));
		
		cards = new ArrayList<>();
		
		setOpaque(false);
	}
	
	public void setId(int id){
		this.id = id;
	}
	
	public int getId(){
		return id;
	}
	
	public void clearColumn()
	{
		cards.clear();
	}
	
	public void addCard(Card card) {
		cards.add(card);
		drawCards();
	}
	
	public ArrayList<Card> getCards()
	{
		return cards;
	}
	
	private void drawCards() {
		for(int i = cards.size() - 1; i >= 0; i--) {
			Card c = cards.get(i); 
			c.setLocation(0, i * Globals.CARD_SPACING);
			add(c);
		}
	}
	
	
}
