import java.awt.Color;
import java.awt.Component;
import java.awt.List;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.concurrent.TimeUnit;

import javax.imageio.ImageIO;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.SwingConstants;


public class MainFrame extends JFrame 
{
	ArrayList<Card> deck = new ArrayList<Card>();
	
	
	CardPanel[] columnOne = new CardPanel[7];
	CardPanel[] columnTwo = new CardPanel[7];
	CardPanel[] columnThree = new CardPanel[7];
	loadImages cardImages = new loadImages();
	
	//	JButton leftHit = new JButton("Hit");
	
	private enum playerSide{ColumnOne, ColumnTwo, ColumnThree}
	private int deckPos = 0;
	private int ColumnOnePos = 0;
	private int ColumnTwoPos = 0;
	private int ColumnThreePos = 0;
	

	MainFrame()
	{
		
		
		for (int i = 6; i >= 0; i--)
		{
			columnOne[i] = new CardPanel(cardImages);
			columnOne[i].setBounds(Globals.COLUMN_ONE_LOCX, Globals.COLUMN_ONE_LOCY + (i * Globals.CARD_SPACING), 
					               Globals.CARD_WI, Globals.CARD_HI);
			add(columnOne[i]);
			
			columnTwo[i] = new CardPanel(cardImages);
			columnTwo[i].setBounds(Globals.COLUMN_TWO_LOCX, Globals.COLUMN_TWO_LOCY + (i * Globals.CARD_SPACING), 
					               Globals.CARD_WI, Globals.CARD_HI);
			add(columnTwo[i]);
			
			
			columnThree[i] = new CardPanel(cardImages);
			columnThree[i].setBounds(Globals.COLUMN_THREE_LOCX, Globals.COLUMN_THREE_LOCY + (i * Globals.CARD_SPACING), 
					               Globals.CARD_WI, Globals.CARD_HI);
			add(columnThree[i]);
		}
		
	
		
		//addButton(leftHit, Globals.LEFT_HIT_LOCX, Globals.LEFT_HIT_LOCY, Globals.BUTTON_WI, Globals.BUTTON_HI);
		
		createDeck();
		//Collections.shuffle(deck);
		newDeal();
		
		URL url = getClass().getResource("/images/TableFelt.png");
		JLabel background=new JLabel(new ImageIcon(url));
	    background.setBounds(0, 0, Globals.FRAME_WI, Globals.FRAME_HI);
		add(background);
		
	}
	
	
	//  Call this function to deal a new hand.
	private void newDeal()
	{

		
	}
	
	private void addButton(JButton button, int locX, int locY, int Wi, int Hi)
	{
		button.setBounds(locX, locY, Wi, Hi);
//		button.setBackground(Color.CYAN);
//		button.setForeground(Color.BLUE);
		button.setBorderPainted(true);
		
		//button.addActionListener(l);
		
		button.addActionListener(new ActionListener() 
		{
		    public void actionPerformed(ActionEvent e) 
		    {
		    	buttonPressed(button, e);
		    }
		});
		
		add(button);
	}
	
	/*
	 * Call this function to deal a card to the player's left side, right side, or to the dealer
	 * The player's right side should only be active if they have a split
	 */
	private void dealCard(playerSide side)
	{

		
	}
	
	
	
	//  This function should only be called once.  Once the deck is created, it won't need to be created again.
	
	
	public void buttonPressed(JButton button, ActionEvent e)
	{
		
		
	}
	
	
	
	private void createDeck()
	{
		// creates the deck
		deck.add(new Card('S', 'A', 11, "AceSpades"));
		deck.add(new Card('S', '2', 2, "TwoSpades"));
		deck.add(new Card('S', '3', 3, "ThreeSpades"));
		deck.add(new Card('S', '4', 4, "FourSpades"));
		deck.add(new Card('S', '5', 5, "FiveSpades"));
		deck.add(new Card('S', '6', 6, "SixSpades"));
		deck.add(new Card('S', '7', 7, "SevenSpades"));
		deck.add(new Card('S', '8', 8, "EightSpades"));
		deck.add(new Card('S', '9', 9, "NineSpades"));
		deck.add(new Card('S', 'T', 10, "TenSpades"));
		deck.add(new Card('S', 'J', 10, "JackSpades"));
		deck.add(new Card('S', 'Q', 10, "QueenSpades"));
		deck.add(new Card('S', 'K', 10, "KingSpades"));
		
		deck.add(new Card('D', 'A', 11, "AceDiamonds"));
		deck.add(new Card('D', '2', 2, "TwoDiamonds"));
		deck.add(new Card('D', '3', 3, "ThreeDiamonds"));
		deck.add(new Card('D', '4', 4, "FourDiamonds"));
		deck.add(new Card('D', '5', 5, "FiveDiamonds"));
		deck.add(new Card('D', '6', 6, "SixDiamonds"));
		deck.add(new Card('D', '7', 7, "SevenDiamonds"));
		deck.add(new Card('D', '8', 8, "EightDiamonds"));
		deck.add(new Card('D', '9', 9, "NineDiamonds"));
		deck.add(new Card('D', 'T', 10, "TenDiamonds"));
		deck.add(new Card('D', 'J', 10, "JackDiamonds"));
		deck.add(new Card('D', 'Q', 10, "QueenDiamonds"));
		deck.add(new Card('D', 'K', 10, "KingDiamonds"));
		
		deck.add(new Card('C', 'A', 11, "AceClubs"));
		deck.add(new Card('C', '2', 2, "TwoClubs"));
		deck.add(new Card('C', '3', 3, "ThreeClubs"));
		deck.add(new Card('C', '4', 4, "FourClubs"));
		deck.add(new Card('C', '5', 5, "FiveClubs"));
		deck.add(new Card('C', '6', 6, "SixClubs"));
		deck.add(new Card('C', '7', 7, "SevenClubs"));
		deck.add(new Card('C', '8', 8, "EightClubs"));
		deck.add(new Card('C', '9', 9, "NineClubs"));
		deck.add(new Card('C', 'T', 10, "TenClubs"));
		deck.add(new Card('C', 'J', 10, "JackClubs"));
		deck.add(new Card('C', 'Q', 10, "QueenClubs"));
		deck.add(new Card('C', 'K', 10, "KingClubs"));

		deck.add(new Card('H', 'A', 11, "AceHearts"));
		deck.add(new Card('H', '2', 2, "TwoHearts"));
		deck.add(new Card('H', '3', 3, "ThreeHearts"));
		deck.add(new Card('H', '4', 4, "FourHearts"));
		deck.add(new Card('H', '5', 5, "FiveHearts"));
		deck.add(new Card('H', '6', 6, "SixHearts"));
		deck.add(new Card('H', '7', 7, "SevenHearts"));
		deck.add(new Card('H', '8', 8, "EightHearts"));
		deck.add(new Card('H', '9', 9, "NineHearts"));
		deck.add(new Card('H', 'T', 10, "TenHearts"));
		deck.add(new Card('H', 'J', 10, "JackHearts"));
		deck.add(new Card('H', 'Q', 10, "QueenHearts"));
		deck.add(new Card('H', 'K', 10, "KingHearts"));
	}

	
}