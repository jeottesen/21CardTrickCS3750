import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class Dealer{
	// Association with Board class
	private Board board;
	
	// Association between Player and Dealer
	private Player player;
	
	private Stack<Card> trickDeck;
	
	private int dealNumber = 1;
	
	public Dealer(Board board) {
		this.board = board;
		
		player = new Player(this);
		
		board.add(player);
		
		dealNumber = 0;
		
		Deck deck = new Deck();
		trickDeck = new Stack<>();
		trickDeck.addAll(deck.random21());
	}
	
	
	
		
	public void deal() {		
		dealNumber++;  //dealNumber becomes 1 when the cards are first dealt
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
		if (dealNumber < 4)
			player.deselectColumns();
		
		board.revalidate();
		if (dealNumber == 4)
			revealCard();
		
	}
	
	public void revealCard() 
	{
		// stop player from being able to interact with field
		player.setVisible(false);
		
		Card revealCard = board.getColumnTwo().getCards().get(3);
		
		
		JPanel overlay = new JPanel() {
		    @Override
		    public void paintComponent(Graphics g)
		    {
		        Graphics2D g2d = (Graphics2D)g;
		        super.paintComponent(g2d);
		        
		        RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
				
				g2d.setRenderingHints(qualityHints);
		        g2d.setColor(new Color(0,0,0,200));
		        g2d.fillRect(0, 0, Globals.FRAME_WI, Globals.FRAME_WI);
		        g2d.setColor(Color.WHITE);
		        g2d.setFont(new Font("Helvetica", Font.PLAIN, 30));
		        g2d.drawString("This is the card you picked!", 300, 635);
		    }
		};

		overlay.setLayout(null);
		overlay.setOpaque(false);
        overlay.setBounds(0, 0, Globals.FRAME_WI, Globals.FRAME_HI);
        overlay.setVisible(true);
        
        board.add(overlay);
        board.setComponentZOrder(overlay, 0);
        board.setComponentZOrder(board.getColumnOne(), 1);
        board.setComponentZOrder(board.getColumnOne(), 2);
        board.setComponentZOrder(board.getColumnOne(), 3);
        
        overlay.add(revealCard);
        revealCard.setLocation(Globals.REVEAL_LOCX,Globals.REVEAL_LOCY);
        overlay.repaint();
        
		board.repaint();
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
		
		
		
		//  For testing	
		/*
		if (dealNumber < 3){
			JOptionPane.showMessageDialog(null, "Next Deal");
		}*/
		deal();
		
	}

}
