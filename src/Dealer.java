import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.util.Stack;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JLabel;
import java.awt.Font;

public class Dealer {
	// Association with Board class
	private Board board;

	// Association between Player and Dealer
	private Player player;

	private Stack<Card> trickDeck;

	private int dealNumber = 1;

	private JPanel overlay;
	private Card revealCard;

	public Dealer(Board board) {
		this.board = board;
		player = new Player(this);

		board.add(player);

		/*
		 * this listener is all about repositioning things horizontally when the
		 * board is resized. It calls methods in the player and the board, and
		 * also adjusts the reveal card, message, and overlay.
		 */
		board.addComponentListener(new ComponentListener() {

			public void componentResized(ComponentEvent e) {
				Board board = ((Board) e.getSource());

				int deltaBoardWidthPixels = Globals.FRAME_WI - board.getWidth();
				board.setColumnLocations(deltaBoardWidthPixels);
				player.setColumnLocations(deltaBoardWidthPixels);
				player.setSize(new Dimension(board.getWidth(), Globals.FRAME_HI));
				player.revalidate();
				player.repaint();
				if (dealNumber == 4)
				{
					overlay.setBounds(0, 0, board.getWidth(), board.getHeight());
					overlay.revalidate();
					overlay.repaint();
					int reveal_LocX = (board.getWidth() - Globals.CARD_WI) / 2;
					revealCard.setLocation(reveal_LocX, Globals.REVEAL_LOCY);
					revealCard.revalidate();
					revealCard.repaint();
				}
			}

			public void componentShown(ComponentEvent e) {
			}

			public void componentMoved(ComponentEvent e) {
			}

			public void componentHidden(ComponentEvent e) {
			}
		});

		dealNumber = 0;

		Deck deck = new Deck();
		trickDeck = new Stack<>();
		trickDeck.addAll(deck.random21());
	}

	public void deal() {
		dealNumber++; // dealNumber becomes 1 when the cards are first dealt
		board.getColumnOne().clearColumn();
		board.getColumnTwo().clearColumn();
		board.getColumnThree().clearColumn();

		for (int i = 0; i < Globals.CARDS_PER_COLUMN; i++)
		{
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

	public void revealCard() {
		// stop player from being able to interact with field
		player.setVisible(false);

		revealCard = board.getColumnTwo().getCards().get(3);

		overlay = new JPanel() {
			@Override
			public void paintComponent(Graphics g) {
				String revealMessage = "This is your secret card!";
				Graphics2D g2d = (Graphics2D) g;
				super.paintComponent(g2d);

				RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
						RenderingHints.VALUE_ANTIALIAS_ON);
				qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);

				g2d.setRenderingHints(qualityHints);
				g2d.setColor(new Color(0, 0, 0, 200));
				g2d.fillRect(0, 0, board.getWidth(), board.getHeight());
				g2d.setColor(Color.WHITE);
				g2d.setFont(new Font("Verdana", Font.BOLD, 30));
				int stringWidth = g2d.getFontMetrics().stringWidth(revealMessage); 
				// centering the message string
				int stringLocX = (board.getWidth() - stringWidth) / 2;
				g2d.drawString(revealMessage, stringLocX, 635);
			}

		};
		
		
		// centers it horizontally
		int reveal_LocX = (board.getWidth() - Globals.CARD_WI) / 2; 

		overlay.setLayout(null);
		overlay.setOpaque(false);

		overlay.setBounds(0, 0, board.getWidth(), board.getHeight());
		overlay.setVisible(true);

		board.add(overlay);
		board.setComponentZOrder(overlay, 0);
		board.setComponentZOrder(board.getColumnOne(), 1);
		board.setComponentZOrder(board.getColumnOne(), 2);
		board.setComponentZOrder(board.getColumnOne(), 3);

		overlay.add(revealCard);
		revealCard.setLocation(reveal_LocX, Globals.REVEAL_LOCY);
		overlay.repaint();

		board.repaint();
	}

	public void pickupCards(int column) {

		if (column == 1)
		{
			trickDeck.addAll(board.getColumnTwo().getCards());
			trickDeck.addAll(board.getColumnOne().getCards());
			trickDeck.addAll(board.getColumnThree().getCards());

		} else if (column == 2)
		{
			trickDeck.addAll(board.getColumnOne().getCards());
			trickDeck.addAll(board.getColumnTwo().getCards());
			trickDeck.addAll(board.getColumnThree().getCards());

		} else if (column == 3)
		{
			trickDeck.addAll(board.getColumnOne().getCards());
			trickDeck.addAll(board.getColumnThree().getCards());
			trickDeck.addAll(board.getColumnTwo().getCards());
		}

		// For testing
		/*
		 * if (dealNumber < 3){ JOptionPane.showMessageDialog(null, "Next Deal"
		 * ); }
		 */
		deal();

	}

}