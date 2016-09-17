import java.awt.Color;
import java.awt.Dimension;
import java.util.Stack;

<<<<<<< HEAD
import javax.swing.JFrame;
import javax.swing.JPanel;

public class Dealer extends JFrame {
=======
import javax.swing.JOptionPane;

public class Dealer {
>>>>>>> origin/Joshua-Crandall
	
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
		//Card SecretCard;
		//SecretCard = trickDeck.get(10);
		
<<<<<<< HEAD
		setTitle("Transparent Panel");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        JPanel p1 = new JPanel();
        
        // Set the background, black with 125 as alpha value
        // This is less transparent
        p1.setBackground(new Color(0,0,0,125));
        
        p1.setPreferredSize(new Dimension(250,150));
        
        // Add the panels to the JFrame
        add(p1);
        
        setSize(600,400);
        setVisible(true);
=======
		board.getColumnTwo().getCards().get(4);
		
		//  This should be the last line
		JOptionPane.showMessageDialog(null, "Tell the truth, this is your card!");
		
		// Board.newDeal --  This does not exist yet.  But if we choose to implement it, it should be called here.
>>>>>>> origin/Joshua-Crandall
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
