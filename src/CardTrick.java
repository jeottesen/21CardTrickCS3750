import java.awt.EventQueue;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.WindowConstants;

public class CardTrick extends JFrame {

	public static void main(String[] args) {
		new CardTrick();
	}

	CardTrick() {

		setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
		setSize(Globals.FRAME_WI, Globals.FRAME_HI);
		
		Board board = new Board();

		add(board);
		setVisible(true);
		
		board.startGame();
	}

}