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
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.Component;
import javax.swing.Box;
import javax.swing.BoxLayout;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;

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
	
	private JPanel columnPanel;
	private JPanel wordsPanel;

	private JLabel questionLabel;
	private JLabel columnIdLabel;
	
	public Player() {
		this.dealer = dealer;
		
		setLayout(new BorderLayout());
		setSize(Globals.FRAME_WI, Globals.FRAME_HI);
		setPreferredSize(new Dimension(Globals.FRAME_WI, Globals.FRAME_HI));
		

		createMessageJLabels();
		createWordsPanel();
		
		wordsPanel.add(questionLabel);
		wordsPanel.add(columnIdLabel);

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
	
	public void setDealer(Dealer dealer){
		this.dealer = dealer;
	}
	
	public void deselectColumns(){
		for (Component c : columnPanel.getComponents()){
			if (c.getClass() == ColumnBorder.class){
				((ColumnBorder)c).selected = false;
			}
		}
	}
	
	public void indicateColumn(int id) {
		dealer.pickupCards(id);
	}
	
	public void pickCard() {
		
	}
	

	private void createWordsPanel() {
		wordsPanel = new JPanel();
		wordsPanel.setLayout(new BoxLayout(wordsPanel, BoxLayout.Y_AXIS));
		wordsPanel.setPreferredSize(new Dimension(Globals.FRAME_WI, 100));
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
					selected = !selected; //toggle selected
					repaint();
					indicateColumn(columnNumber);					
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
