import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.awt.BorderLayout;
import javax.imageio.ImageIO;
import javax.swing.BoxLayout;
import javax.swing.border.CompoundBorder;
import javax.swing.border.LineBorder;
import javax.swing.border.EmptyBorder;
import javax.swing.BorderFactory;
import javax.swing.JPanel;
import javax.swing.JLabel;

public class Board extends JPanel {
	
	public BufferedImage backgroundImg;
	
	private int selectedColumn;
	
	// Since the UML Diagram shows these are a composition
	// relationship they need to be final. They would be
	// an Aggregation relationship without it.
	private final Column column1 = new Column();
	private final Column column2 = new Column();
	private final Column column3 = new Column();

	private Dealer dealer;	
	private JPanel columnPanel;
	private JPanel wordsPanel;
	private JLabel questionLabel;
	private JLabel columnIdLabel;
	private CompoundBorder compoundBorderHover;
	private CompoundBorder compoundBorder;
	private LineBorder insideBorderHover;
	private LineBorder insideBorder;
	private EmptyBorder emptyBorder;

	public Board() {
		this.setLayout(new BorderLayout());
		
		emptyBorder = new EmptyBorder(10, 15, 28, 15);  //insets (top, left, bottom, right)  
		//we might want to change insets based on how many cards there are...
		
		insideBorder = new LineBorder(Color.ORANGE, 10, true);
		insideBorderHover = new LineBorder(Color.GREEN, 10, true);
		compoundBorder = new CompoundBorder(emptyBorder, insideBorder);
		compoundBorderHover = new CompoundBorder(emptyBorder, insideBorderHover);
		
		
		questionLabel = new JLabel();
		questionLabel.setForeground(Color.WHITE);
		questionLabel.setText("Which column is your card in?");
		questionLabel.setFont(new Font("Helvetica", Font.PLAIN, 24));
		questionLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		columnIdLabel = new JLabel();
		columnIdLabel.setForeground(Color.WHITE);
		columnIdLabel.setFont(new Font("Helvetica", Font.PLAIN, 24));
		columnIdLabel.setAlignmentX(CENTER_ALIGNMENT);
		
		wordsPanel = new JPanel();
		wordsPanel.setLayout(new BoxLayout(wordsPanel, BoxLayout.Y_AXIS));
		wordsPanel.setPreferredSize(new Dimension(Globals.FRAME_WI, 100));
		wordsPanel.setBackground(Globals.BACKGROUND_COLOR);
		wordsPanel.add(questionLabel);
		wordsPanel.add(columnIdLabel);
		
		/* Configuring the columnPanel as a horizontally-oriented BoxLayout Panel.
		 * This may help with resizing columns and cards later.
		 */
		columnPanel = new JPanel();
		columnPanel.setLayout(new BoxLayout(columnPanel, BoxLayout.X_AXIS));
		columnPanel.setOpaque(false);//so we can see Board's background color
		
		//load background image
		try
		{
			backgroundImg = ImageIO.read(getClass().getResourceAsStream("images/TableFelt.png"));
		}
		catch(IOException e)
		{
			System.out.println("ERROR: " + e.getMessage());
		}
		
		dealer = new Dealer();
		dealer.setBoard(this);
		
		// Temporary deck for testing. Will be getting cards from dealer later
		Deck deck = new Deck();
		
		//column1.setLocation(Globals.COLUMN_ONE_LOCX, Globals.COLUMN_ONE_LOCY);
		//column2.setLocation(Globals.COLUMN_TWO_LOCX, Globals.COLUMN_TWO_LOCY);
		//column3.setLocation(Globals.COLUMN_THREE_LOCX, Globals.COLUMN_THREE_LOCY);
		
		/*column1.setBorder(compoundBorder);
		column2.setBorder(compoundBorder);
		column3.setBorder(compoundBorder);*/
		
		column1.setId(1);
		column2.setId(2);
		column3.setId(3);
		
		setMouseListener(column1, this);
		setMouseListener(column2, this);
		setMouseListener(column3, this);
	
		//column2.setOpaque(true);//just to get a look at it while working on layout
		
		columnPanel.add(column1);
		columnPanel.add(column2);
		columnPanel.add(column3);
		
		
		
		this.add(columnPanel, BorderLayout.CENTER);
		this.add(wordsPanel, BorderLayout.SOUTH);
		
		
		// Will move to Dealers deal function
		for (int i = 0; i < Globals.CARDS_PER_COLUMN; i++) {
			addToColumn(1, deck.draw());
			addToColumn(2, deck.draw());
			addToColumn(3, deck.draw());
		}
	}
	
	public void addToColumn(int columnId, Card card) {
		
		switch (columnId) {
		case 1:
			column1.addCard(card);
			break;
		case 2:
			column2.addCard(card);
			break;
		case 3:
			column3.addCard(card);
			break;
		}
	}

	/*
	 * getter method to give access to private Dealer.
	 * I don't know if this is good form, OOP-wise, because
	 * I'm returning a reference to an object.  I'll take
	 * anyone's input about this.  --geese
	 */
	//public Dealer getDealer(){
		//return dealer;
	//}
	
		
	@Override
	public void paintComponent(Graphics g)
	{
		super.paintComponent(g);
		setBackground(Globals.BACKGROUND_COLOR);
		//g.drawImage(backgroundImg, 0, 0, this);
	}

	//used by MouseListener on Columns
	public void setSelectedColumn(Column column, int id, boolean hoveringOver){
		if (hoveringOver){
			column.setBorder(compoundBorderHover);
			columnIdLabel.setText("Column " + id + "?");
			
		}else{
			column.setBorder(null);
			columnIdLabel.setText("");
		}
			
		
	}
	
	private void setMouseListener(Column column, Board board){
		Player player = (Player)(dealer.getPlayer());
		int id = column.getId();
		
		column.addMouseListener(new MouseListener() {
			
			@Override
			public void mouseReleased(MouseEvent e) {}
			
			@Override
			public void mousePressed(MouseEvent e) {}
			
			@Override
			public void mouseExited(MouseEvent e) {
				board.setSelectedColumn(column, id, false);
			}
			
			@Override
			public void mouseEntered(MouseEvent e) {
				board.setSelectedColumn(column, id, true);
			}
			
			@Override
			public void mouseClicked(MouseEvent e) {
				player.indicateColumn(id);
			}
		});
	}

	
}
