import java.util.Stack;

public class Dealer {
	
	// Association with Board class
	private Board board;
	
	// Association between Player and Dealer
	private Player player;
	
	private Stack<Card> trickDeck;
	
	private int dealNumber;
	
	public Dealer(Board board) {
		this.board = board;
		dealNumber = 0;
		
		Deck deck = new Deck();
		trickDeck = new Stack<>();
		trickDeck.addAll(deck.random21());
	}
	
	public void deal() {

		for (int i = 0; i < Globals.CARDS_PER_COLUMN; i++) {
			board.addToColumn(1, trickDeck.pop());
			board.addToColumn(2, trickDeck.pop());
			board.addToColumn(3, trickDeck.pop());
		}
		
	}
	
	public void revealCard() {
		
	}
	
	public void pickupCards(int column) 
	{
		if (column == 1)
		{
			for (int i = 0; i < 7; i++)
			{	
				trickDeck.add(board.getColumnTwo().getCard(i));
			}
			for (int i = 7; i < 14; i++)
			{	
				trickDeck.add(board.getColumnOne().getCard(i));
			}
			for (int i = 14; i < 21; i++)
			{	
				trickDeck.add(board.getColumnThree().getCard(i));
			}
		}
		else if (column == 2)
		{
			for (int i = 0; i < 7; i++)
			{	
				trickDeck.add(board.getColumnOne().getCard(i));
			}
			for (int i = 7; i < 14; i++)
			{	
				trickDeck.add(board.getColumnTwo().getCard(i));
			}
			for (int i = 14; i < 21; i++)
			{	
				trickDeck.add(board.getColumnThree().getCard(i));
			}
		}
		else if (column == 3)
		{
			for (int i = 0; i < 7; i++)
			{	
				trickDeck.add(board.getColumnOne().getCard(i));
			}
			for (int i = 7; i < 14; i++)
			{	
				trickDeck.add(board.getColumnThree().getCard(i));
			}
			for (int i = 14; i < 21; i++)
			{	
				trickDeck.add(board.getColumnTwo().getCard(i));
			}
		}
	}

}
