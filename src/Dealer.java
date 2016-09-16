import java.util.ArrayList;
import java.util.Stack;

public class Dealer {
	
	// Association between Dealer and Deck
	//private Deck deck;
	
	// Association between Player and Dealer
	// Making player final so that the Column can get a reference to it.
	private final Player player = new Player();
	
	//Association with Board class
	private Board board;
	
	private Stack<Card> trickDeck;
	
	private int dealNumber;
	
	public Dealer(){
		this.board = board;
		dealNumber = 0;
		
		Deck deck = new Deck();
		trickDeck = new Stack<>();
		trickDeck.addAll(deck.random21());
	}
	
	 
	//setter method to build association relationship
	public void setBoard(Board board){this.board = board;}
	
	/*
	 * getter method to give access to private Player.
	 * I don't know if this is good form, OOP-wise, because
	 * I'm returning a reference to an object.  I'll take
	 * anyone's input about this.  --geese
	 */
	public Player getPlayer(){
		return player;
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
