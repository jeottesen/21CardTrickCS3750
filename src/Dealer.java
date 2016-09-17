import java.util.Stack;

import javax.swing.JOptionPane;

public class Dealer {
	
	// Association with Board class
	private Board board;
	
	// Association between Player and Dealer
	private Player player;
	
	private Stack<Card> trickDeck;
	
	private int dealNumber = 1;
	
	public Dealer(Board board) {
		this.board = board;
		dealNumber = 1;
		
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
	
	public void revealCard() 
	{
		
		board.getColumnTwo().getCards().get(4);
		
		//  This should be the last line
		JOptionPane.showMessageDialog(null, "Tell the truth, this is your card!");
		
		// Board.newDeal --  This does not exist yet.  But if we choose to implement it, it should be called here.
	}
	
	public void pickupCards(int column) 
	{
		if (column == 1)
		{		
			trickDeck.addAll(board.getColumnTwo().getCards());
			trickDeck.addAll(board.getColumnOne().getCards());	
			trickDeck.addAll(board.getColumnThree().getCards());
			
		}
		else if (column == 2)
		{
			trickDeck.addAll(board.getColumnOne().getCards());	
			trickDeck.addAll(board.getColumnTwo().getCards());
			trickDeck.addAll(board.getColumnThree().getCards());
			
		}
		else if (column == 3)
		{	
			trickDeck.addAll(board.getColumnOne().getCards());	
			trickDeck.addAll(board.getColumnThree().getCards());
			trickDeck.addAll(board.getColumnTwo().getCards());
		}
		
		if (dealNumber == 3)
		{
			revealCard();
		}
		dealNumber++;
	}

}
