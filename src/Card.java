import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.image.BufferedImage;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Card  extends JPanel
{
	private CardValues value;
	private CardSuits suit;
	private Image image;

	public CardValues getValue() {
		return value;
	}

	public CardSuits getSuit() {
		return suit;
	}

	public Card(CardValues v, CardSuits s) {
		value = v;
		suit = s;

		setPreferredSize(new Dimension(Globals.CARD_WI, Globals.CARD_HI));
		setSize(Globals.CARD_WI, Globals.CARD_HI);
		
		setOpaque(false);
	}
	
	public void loadImage() {
		// moved getScaled instance from paint component
		// this way the program only scales the image down once.
		// rather than  every few seconds
		image = getCardImage().getScaledInstance(Globals.CARD_WI, Globals.CARD_HI, Image.SCALE_AREA_AVERAGING);
		
		if(image == null)
			setVisible(false);
	}

	@Override
	public void paintComponent(Graphics g)
	{

		//setVisible(true);
		Graphics2D g2d = (Graphics2D)g;
		super.paintComponent(g2d);
		
		RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
		g2d.setRenderingHints(qualityHints);

		g2d.drawImage(image, 0, 0, this);
	}

	private BufferedImage getCardImage()
	{
		try
		{
			return ImageIO.read(getClass().getResourceAsStream("/images/" + this.toString() + ".png"));
		} catch(IOException e)
		{
			System.err.println(e);
			return null;
		}
	}


	@Override
	public String toString() {
		String string;
		switch (value) {
		
		case ACE:
			string = "Ace" + suit.getValue();
			break;
		case JACK:
			string = "Jack"+ suit.getValue();
			break;
		case QUEEN:
			string = "Queen" + suit.getValue();
			break;
		case KING:
			string = "King" + suit.getValue();
			break;
		case TWO:
			string = "Two" + suit.getValue();
			break;
		case THREE:
			string = "Three" + suit.getValue();
			break;
		case FOUR:
			string = "Four" + suit.getValue();
			break;
		case FIVE:
			string = "Five" + suit.getValue();
			break;
		case SIX:
			string = "Six" + suit.getValue();
			break;
		case SEVEN:
			string = "Seven" + suit.getValue();
			break;
		case EIGHT:
			string = "Eight" + suit.getValue();
			break;
		case NINE:
			string = "Nine" + suit.getValue();
			break;
		case TEN:
			string = "Ten" + suit.getValue();
			break;
		default:
			string = value.getValue() + suit.getValue();
		}
		return string;
	}
}
