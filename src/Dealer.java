import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;

public class Dealer{
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
		
		board.getColumnOne().clearColumn();
		board.getColumnTwo().clearColumn();
		board.getColumnThree().clearColumn();
		

		for (int i = 0; i < Globals.CARDS_PER_COLUMN; i++) {
			board.addToColumn(1, trickDeck.pop());
			board.addToColumn(2, trickDeck.pop());
			board.addToColumn(3, trickDeck.pop());
			
			board.getColumnOne().repaint();
			board.getColumnTwo().repaint();
			board.getColumnThree().repaint();
		}
		
		board.revalidate();
		
	}
	
	public void revealCard() 
	{
		Card revealCard = board.getColumnTwo().getCards().get(3);
		JPanel overlay = new JPanel();
		
		overlay.setLayout(null);
		overlay.setOpaque(true);
		board.getColumnOne().setVisible(false);
		board.getColumnTwo().setVisible(false);
		board.getColumnThree().setVisible(false);
		board.repaint();
		overlay.setSize(Globals.FRAME_WI, Globals.FRAME_HI);
		overlay.setPreferredSize(new Dimension(Globals.FRAME_WI, Globals.FRAME_HI));
        overlay.setBounds(0, 0, Globals.FRAME_WI, Globals.FRAME_HI);
        overlay.setBackground(new Color(0,0,0,125));
        overlay.setVisible(true);
        board.add(overlay);
        overlay.add(revealCard);
        revealCard.setLocation(320,200);
		
        JOptionPane.showMessageDialog(null, "Tell the truth, this is your card!");
		
		//Board.newDeal() --  This does not exist yet.  But if we choose to implement it, it should be called here.
		//  For now, until we find a replacement.
		System.exit(0);
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
		//  For testing
		JOptionPane.showMessageDialog(null, "Next Deal");
		//
		deal();
		dealNumber++;
	}

}
