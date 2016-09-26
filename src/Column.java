import java.awt.Dimension;
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
		int ColumnHeight = (Globals.CARD_SPACING * 7) + (Globals.CARD_HI - Globals.CARD_SPACING + Globals.COLUMN_ONE_LOCY);
		
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
		removeAll();
		repaint();
	}
	
	public int nextCardY() {
		return Globals.COLUMN_ONE_LOCY + ((Globals.CARDS_PER_COLUMN) - cards.size()) * Globals.CARD_SPACING;
	}
	
	public void addCard(Card card) {
		cards.add(card);
		add(card);
	}
	
}