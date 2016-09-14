import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.net.URL;
import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class CardPanel extends JPanel
{
	private Card panelCard;
	URL url;
	BufferedImage image;
	

	public Card getCard()
	{		return panelCard;
	}
	
	CardPanel(Card card)
	{	
		setPreferredSize(new Dimension(Globals.CARD_WI, Globals.CARD_HI));
		this.panelCard = card;
		image = getCardImage(panelCard);
		System.out.println("is image null: "+ image.getWidth());
		setBackground(Color.GREEN);
	}
	
	@Override
	public void paintComponent(Graphics g)
	{
		
		//setVisible(true);
		Graphics2D g2d = (Graphics2D)g;
        super.paintComponent(g2d);
		
		if(panelCard == null)
		{
			System.out.println("Panel is null");
			setVisible(false);
		}
		else
		{				
			System.out.println("Draw image called");
			g2d.drawImage(image, 0, 0, this);
		}	
	}
	
	public BufferedImage getCardImage(Card card)
	{
		try
		{
			return ImageIO.read(getClass().getResourceAsStream("/images/" + card.toString() + ".png"));
		} catch(IOException e)
		{
			System.err.println(e);
			return null;
		}
	}
	
}
