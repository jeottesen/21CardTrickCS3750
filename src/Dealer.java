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
	
	public void pickupCards() {
		
	}

}
