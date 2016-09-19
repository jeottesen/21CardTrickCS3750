import java.awt.BasicStroke;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.RenderingHints;
import java.awt.Stroke;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import java.awt.FlowLayout;
import java.awt.GridLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JButton;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.Insets;

public class Player extends JPanel {
	
	// Association between Player and Dealer
	private Dealer dealer;
	
	// Aggregation relationship between Card and player
	// I don't think we actually need this I thought
	// the program wasn't supposed to know the card
	// This is how you implement the UML though so
	// we should ask him about it in class
	private Card card;
	
	private boolean hasSelectedCard;
	
	private int selectedColumnID;
	
	private JPanel columnPanel, wordsPanel, buttonPanel;

	private JLabel questionLabel;
	private JLabel columnIdLabel;
	
	private JButton btnYes;
	
	public Player(Dealer dealer) {
		this.dealer = dealer;
		
		setLayout(new BorderLayout());
		setSize(Globals.FRAME_WI, Globals.FRAME_HI);
		setPreferredSize(new Dimension(Globals.FRAME_WI, Globals.FRAME_HI));
		

		createMessageJLabels();
		createWordsPanel();
		createButtonPanel();
		
		wordsPanel.add(questionLabel);
		wordsPanel.add(columnIdLabel);
		wordsPanel.add(buttonPanel);
		
		buttonPanel.setVisible(false);  //will be set visible when user clicks on a column

		/*
		 * Configuring the columnPanel as a horizontally-oriented BoxLayout
		 * Panel. This may help with resizing columns and cards later.
		 */
		columnPanel = new JPanel();
		columnPanel.setLayout(null);
		columnPanel.setOpaque(false);// so we can see Board's background color
		
		//columnPanel.add(Box.createRigidArea(new Dimension(5, 0)));
		columnPanel.add(Box.createRigidArea(new Dimension(5, 5)));
		
		// add an empty mouse listener so that the events in ColumnBorder can propagate up
		addMouseListener(new MouseAdapter(){});
		
		setOpaque(false);
		
		ColumnBorder cb1 = new ColumnBorder(1);
		cb1.setLocation(5, 5);
		
		columnPanel.add(cb1);
		
		ColumnBorder cb2 = new ColumnBorder(2);
		cb2.setLocation(Globals.COLUMN_TWO_LOCX - 15, 5);
		
		columnPanel.add(cb2);
		

		ColumnBorder cb3 = new ColumnBorder(3);
		cb3.setLocation(Globals.COLUMN_THREE_LOCX - 15, 5);
		
		columnPanel.add(cb3);
		
		this.add(columnPanel, BorderLayout.CENTER);
		this.add(wordsPanel, BorderLayout.SOUTH);
		
		deselectColumns();
	
		
	}
	
	public void deselectColumns(){
		for (Component c : columnPanel.getComponents()){
			if (c.getClass() == ColumnBorder.class){
				((ColumnBorder)c).selected = false;
				((ColumnBorder)c).repaint();
			}
		}
	}
	
	public void indicateColumn(int id) {
		dealer.pickupCards(id);
	}
	
	public void pickCard() {
		// TODO We should have the JLabel say something like
		// Please pick a card from what you see here. 
		// Have you picked the card?
		// then a button to confirm and then we can add the column borders
		// and start the game
		
	}
	

	private void createWordsPanel() {
		wordsPanel = new JPanel();
		wordsPanel.setBackground(Color.PINK);
		wordsPanel.setLayout(new BoxLayout(wordsPanel, BoxLayout.Y_AXIS));
		wordsPanel.setPreferredSize(new Dimension(Globals.FRAME_WI, 170));
		wordsPanel.setBackground(Globals.BACKGROUND_COLOR);
	}
	
	private void createMessageJLabels() {
		questionLabel = new JLabel();
		questionLabel.setForeground(Color.WHITE);
		questionLabel.setText("Which column is your card in?");
		questionLabel.setFont(new Font("Helvetica", Font.PLAIN, 24));
		questionLabel.setAlignmentX(CENTER_ALIGNMENT);

		columnIdLabel = new JLabel();
		columnIdLabel.setForeground(Color.WHITE);
		columnIdLabel.setFont(new Font("Helvetica", Font.PLAIN, 24));
		columnIdLabel.setAlignmentX(CENTER_ALIGNMENT);
	}

	private void createButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		//buttonPanel.setPreferredSize(new Dimension(100, 50));
		//buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		createButtons();
		buttonPanel.add(btnYes);
	}
	
	private void createButtons(){
		btnYes = new JButton("Yes!");
		configureButton(btnYes);
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				columnIdLabel.setVisible(true);
				buttonPanel.setVisible(false);
				questionLabel.setText("");
				indicateColumn(selectedColumnID);
			}
		});
	}

	private void configureButton(JButton button) {
		button.setBackground(Globals.BACKGROUND_COLOR);
		button.setForeground(Color.WHITE);
		button.setBorder(new LineBorder(Color.WHITE, 2, false));
		button.setPreferredSize(new Dimension(100, 50));
		button.setFont(new Font("Helvetica", Font.PLAIN, 22));
		button.setMargin(new Insets(0, 20, 0, 20));
	}
	
	private class ColumnBorder extends JPanel {
		
		public int columnNumber;
		public boolean hoveredOver;
		private boolean selected;

		public ColumnBorder(int columnNumber) {
			this.columnNumber = columnNumber;
			setSize(Globals.CARD_WI + 30, Globals.COLUMN_HI + 30);
			setPreferredSize(new Dimension(Globals.CARD_WI + 30, Globals.COLUMN_HI + 30));

			setOpaque(false);
			addMouseListener(new MouseAdapter() {				
				@Override
				public void mouseExited(MouseEvent e) {
					//System.out.println("ColumnBorder " + columnNumber + " Mouse Exited.");
					columnIdLabel.setText("");
					hoveredOver = false;
					repaint();
				}
	
				@Override
				public void mouseEntered(MouseEvent e) {
					//System.out.println("ColumnBorder " + columnNumber + " Mouse Enter.");
					
					columnIdLabel.setText("Column " + columnNumber + "?");
					hoveredOver = true;
					repaint();
				}
	
				@Override
				public void mouseClicked(MouseEvent e) {
					//System.out.println("ColumnBorder " + columnNumber + " Mouse Clicked.");
					deselectColumns(); // deselect all other columns
					selected = !selected; //toggle this column
					columnIdLabel.setVisible(false);
					selectedColumnID = columnNumber;
					buttonPanel.setVisible(true);
					questionLabel.setText("Your card is in column " + columnNumber + "?");
					repaint();					
				}
			});
		}
		
		@Override
		public void paintComponent(Graphics g)
		{

			//setVisible(true);
			Graphics2D g2d = (Graphics2D)g;
			super.paintComponent(g2d);
			
			RenderingHints qualityHints = new RenderingHints(
					  RenderingHints.KEY_ANTIALIASING,
					  RenderingHints.VALUE_ANTIALIAS_ON );
			qualityHints.put(
			  RenderingHints.KEY_RENDERING,
			  RenderingHints.VALUE_RENDER_QUALITY );
			g2d.setRenderingHints( qualityHints );
			
			float thickness = 10;
			Stroke oldStroke = g2d.getStroke();
			g2d.setStroke(new BasicStroke(thickness));
			
			g2d.setColor(Color.WHITE);
			
			if (hoveredOver) {
				g2d.setColor(Globals.HOVERED_COLUMN_BORDER_COLOR);
			}
			if (selected) {
				g2d.setColor(Globals.SELECTED_COLUMN_BORDER_COLOR);
			}
			
			g2d.drawRoundRect(5, 5, Globals.CARD_WI + 20, Globals.COLUMN_HI + 20, 5, 5);
			
			g2d.setStroke(oldStroke);
		}

	}
	
	

}
