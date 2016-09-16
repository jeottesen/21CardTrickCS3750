import java.util.ArrayList;
import java.util.Stack;

public class Dealer {
	
	// Association between Dealer and Deck
	//private Deck deck;
	
	// Association between Player and Dealer
	//private Player player;
	
	private CardTrick cardTrick;
	private Stack<Card> trickDeck;
	private int dealNumber;
	
	public Dealer(CardTrick cardTrick){
		this.cardTrick = cardTrick;
		trickDeck = new Stack<Card>();
	}
	
	public void deal() {
		if (dealNumber == 1){		
			trickDeck = cardTrick.theDeck.random21();			
		}
		
		for (int i = 0; i < Globals.CARDS_PER_COLUMN; i++) {
			cardTrick.theBoard.addToColumn(1, trickDeck.pop());
			cardTrick.theBoard.addToColumn(2, trickDeck.pop());
			cardTrick.theBoard.addToColumn(3, trickDeck.pop());
		}
	}
	
	public void revealCard() {
		
	}
	
	public void pickupCards() {
		
	}

}
