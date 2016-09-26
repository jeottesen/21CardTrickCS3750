import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ComponentEvent;
import java.awt.event.ComponentListener;
import java.awt.event.WindowEvent;
import java.util.Stack;
import java.util.concurrent.TimeUnit;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.Timer;

import aurelienribon.tweenengine.BaseTween;
import aurelienribon.tweenengine.Timeline;
import aurelienribon.tweenengine.Tween;
import aurelienribon.tweenengine.TweenCallback;
import aurelienribon.tweenengine.TweenManager;
import aurelienribon.tweenengine.equations.Cubic;

import javax.swing.JLabel;
import java.awt.Font;

public class Dealer {
	// Association with Board class
	private Board board;

	// Association between Player and Dealer
	private Player player;

	private Stack<Card> trickDeck;

	private int dealNumber = 1;

	
	final TweenManager tweenManager = new TweenManager();

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
	
	
	public void cancelAnimation() {
		// tell the tweenManager an insane amount of time has passed so
		// that the animation will end early. this is necessary so that
		// the cards get placed appropriately
		tweenManager.update(1000000);
	}
	
		

	public void deal() {
		// dealNumber becomes 1 when the cards are first dealt
		dealNumber++; 

		board.getColumnOne().clearColumn();
		board.getColumnTwo().clearColumn();
		board.getColumnThree().clearColumn();


		// The thread that plays the animation
		Timer timer = new Timer(Globals.FRAME_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// pass it how many milliseconds it has been since the
				// last frame update. This should always be about the same
				tweenManager.update(Globals.FRAME_RATE);
			}
		});
		
		Tween.registerAccessor(JPanel.class, new PanelAccessor());
		Timeline timeline = Timeline.createSequence();
		
		for (int i = 0; i < Globals.CARDS_PER_COLUMN; i++) {
			Card c1 = trickDeck.pop();
			Card c2 = trickDeck.pop();
			Card c3 = trickDeck.pop();
			
			// set the location of the cards so they start from the top
			c1.setLocation(0, -1 * Globals.CARD_HI);
			c2.setLocation(0, -1 * Globals.CARD_HI);
			c3.setLocation(0, -1 * Globals.CARD_HI);
			
			board.addToColumn(1, c1);
			board.addToColumn(2, c2);
			board.addToColumn(3, c3);
			
			// add the tweens to the timeline for each card
			timeline.push(Tween.to(c1, PanelAccessor.POS_XY, 100).target(0, board.getColumnOne().nextCardY()).ease(Cubic.OUT));
			// make sure not to add the reveal card to the timeline
			if (dealNumber != 4 || board.getColumnTwo().getCards().size() != 4)
				timeline.push(Tween.to(c2, PanelAccessor.POS_XY, 100).target(0, board.getColumnOne().nextCardY()).ease(Cubic.OUT));
			timeline.push(Tween.to(c3, PanelAccessor.POS_XY, 100).target(0, board.getColumnOne().nextCardY()).ease(Cubic.OUT));

		}
		
		// stop the timer when it is done
		timeline.setCallbackTriggers(TweenCallback.COMPLETE);
		timeline.setCallback(new TweenCallback() {
			@Override
			public void onEvent(int type, BaseTween<?> source) {
				timer.stop();
			}
		});
		timeline.start(tweenManager);
		
		timer.start();
		
		if (dealNumber < 4)
			player.deselectColumns();

		board.revalidate();
		if (dealNumber == 4)
		{
			revealCard();
			

			/*
			 *  a "Play Again" confirm dialog pops up.  It's in a new thread
			 *  that waits a couple seconds so that the reveal card shows first,
			 *  and then "play again?" pops up a couple of seconds later.
			 */
			new Thread(new Runnable() {
				@Override
				public void run() {
					try
					{
						Thread.sleep(2000);
					} catch (InterruptedException e)
					{
						// TODO Auto-generated catch block
						e.printStackTrace();
					}
					
					int jop = JOptionPane.showConfirmDialog
							(board.dummyPanelForJDialogPane, "Play Again?", "New Card Trick", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
					
					if (jop == JOptionPane.YES_OPTION){
						board.resetGame();
					}else{
						System.exit(0);
					}
					
				}
			}).start();
		
		}

	}

	public void revealCard() {
		// stop player from being able to interact with field
		player.setVisible(false);

		revealCard = board.getColumnTwo().getCards().get(3);
		

		overlay = new JPanel() 
		{
			JButton playAgain = new JButton("Play Again");

			
			
			@Override
			public void paintComponent(Graphics g) 
			{
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
				g2d.setFont(new Font("Helvetica", Font.BOLD, 30));
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