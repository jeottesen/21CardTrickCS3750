import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.ArrayList;

import javax.swing.JPanel;

public class Column extends JPanel {
	private ArrayList<Card> cards;
	private int id; 
	
	public ArrayList<Card> getCards()
	{
		return cards;
	}
	
	public Column() {
		setLayout(null);
		int ColumnHeight = (Globals.CARD_SPACING * 7) + (Globals.CARD_HI - Globals.CARD_SPACING);
		
		setSize(Globals.CARD_WI, ColumnHeight);
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
		repaint();
	}
	
	public int nextCardY() {
		return ((Globals.CARDS_PER_COLUMN - 1) - cards.size()) * Globals.CARD_SPACING;
	}
	
	public void addCard(Card card) {
		card.setLocation(0, nextCardY());
		cards.add(card);
		add(card);
	}
	
}