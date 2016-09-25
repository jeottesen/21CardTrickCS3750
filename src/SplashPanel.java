import java.awt.Color;
import java.awt.Component;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import javax.imageio.ImageIO;
import javax.swing.JButton;
import javax.swing.JPanel;
import javax.swing.border.LineBorder;

public class SplashPanel extends JPanel
{
    private Image image;
    
    public SplashPanel(String fname)
    {
        //reads the image
        try {
            image =ImageIO.read(getClass().getResourceAsStream(fname));
			image = image.getScaledInstance(-1, Globals.FRAME_HI, Image.SCALE_SMOOTH);

        } catch (IOException ioe) {
            System.out.println("Could not read file");
        }
    }

    //this will draw the image
    public void paintComponent(Graphics g) {
        super.paintComponent(g);
        g.drawImage(image,-180,0,this);
    }
}
