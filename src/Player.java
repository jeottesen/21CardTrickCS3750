import javax.swing.JPanel;

public class Player  extends JPanel{
	
	// Association between Player and Dealer
	private Dealer dealer;
	
	// Aggregation relationship between Card and player
	// I don't think we actually need this I thought
	// the program wasn't supposed to know the card
	// This is how you implement the UML though so
	// we should ask him about it in class
	private Card card;
	
	private boolean hasSelectedCard;
	
	public Player(Dealer dealer) 
	{
		this.dealer = dealer;
	}
	
	public void indicateColumn() 
	{
		
	}
	
	public void pickCard() {
		
	}

}
