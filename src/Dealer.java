import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.RenderingHints;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Stack;
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
		

		final TweenManager tweenManager = new TweenManager();
		
		
		// The thread that plays the animation
		Timer timer = new Timer(Animations.FRAME_RATE, new ActionListener() {
			@Override
			public void actionPerformed(ActionEvent e) {
				
				// pass it how many milliseconds it has been since the
				// last frame update. This should always be about the same
				tweenManager.update(Animations.FRAME_RATE);
			}
		});
		
		Tween.registerAccessor(JPanel.class, new PanelAccessor());
		Timeline timeline = Timeline.createSequence();
		
		for (int i = 0; i < Globals.CARDS_PER_COLUMN; i++) {
			Card c1 = trickDeck.pop();
			Card c2 = trickDeck.pop();
			Card c3 = trickDeck.pop();
			
			c1.setLocation(0, -1 * Globals.CARD_HI);
			c2.setLocation(0, -1 * Globals.CARD_HI);
			c3.setLocation(0, -1 * Globals.CARD_HI);
			
			board.addToColumn(1, c1);
			board.addToColumn(2, c2);
			board.addToColumn(3, c3);
			
			
			timeline.push(Tween.to(c1, PanelAccessor.POS_XY, 100).target(0, board.getColumnOne().nextCardY()).ease(Cubic.OUT));
			timeline.push(Tween.to(c2, PanelAccessor.POS_XY, 100).target(0, board.getColumnOne().nextCardY()).ease(Cubic.OUT));
			timeline.push(Tween.to(c3, PanelAccessor.POS_XY, 100).target(0, board.getColumnOne().nextCardY()).ease(Cubic.OUT));
		}
		
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
