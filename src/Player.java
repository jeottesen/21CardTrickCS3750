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
import java.awt.Toolkit;
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

	private ColumnBorder cb1, cb2, cb3;
	private JPanel columnPanel, wordsPanel, buttonPanel;

	private JLabel questionLabel, columnIdLabel, startInstructionsLabel;
	
	private JButton btnReady, btnYes;

	public Player(Dealer dealer) {
		this.dealer = dealer;
		hasSelectedCard = false;

		setLayout(new BorderLayout());
		setSize(new Dimension(Globals.FRAME_WI, Globals.FRAME_HI));

		createMessageJLabels();
		createWordsPanel();
		createButtonPanel();

		wordsPanel.add(startInstructionsLabel);
		wordsPanel.add(questionLabel);
		wordsPanel.add(Box.createRigidArea(new Dimension(1, 10)));
		wordsPanel.add(columnIdLabel);
		wordsPanel.add(buttonPanel);

		// buttonPanel.setVisible(false); //will be set visible when user clicks
		// on a column

		/*
		 * Configuring the columnPanel as a horizontally-oriented BoxLayout
		 * Panel. This may help with resizing columns and cards later.
		 */
		columnPanel = new JPanel();
		columnPanel.setLayout(null);
		columnPanel.setOpaque(false);// so we can see Board's background color


		/*
		 * add an empty mouse listener so that the events in ColumnBorder can
		 * propagate up --it *was* empty: now it's here so that the user can
		 * deselect their column if they selected by accident, by clicking
		 * anywhere outside those columns.
		 */
		addMouseListener(new MouseAdapter() {
			public void mouseClicked(MouseEvent e) {
				deselectColumns();
				questionLabel.setText("Which column is your card in?");
				columnIdLabel.setVisible(true);
				btnYes.setVisible(false);
				// buttonPanel.setVisible(false);
			}
		});

		setOpaque(false);

		cb1 = new ColumnBorder(1);
		cb1.setLocation(Globals.COLUMN_ONE_LOCX - 15, Globals.COLUMN_ONE_LOCY - 15);

		columnPanel.add(cb1);

		cb2 = new ColumnBorder(2);
		cb2.setLocation(Globals.COLUMN_TWO_LOCX - 15, Globals.COLUMN_TWO_LOCY - 15);

		columnPanel.add(cb2);

		cb3 = new ColumnBorder(3);
		cb3.setLocation(Globals.COLUMN_THREE_LOCX - 15, Globals.COLUMN_THREE_LOCY - 15);

		columnPanel.add(cb3);

		this.add(columnPanel, BorderLayout.CENTER);
		this.add(wordsPanel, BorderLayout.SOUTH);

		deselectColumns();

	}

	public void deselectColumns() {
		for (Component c : columnPanel.getComponents())
		{
			if (c.getClass() == ColumnBorder.class)
			{
				((ColumnBorder) c).selected = false;
				((ColumnBorder) c).repaint();
			}
		}
	}

	
	/* this method will be called from the Dealer object when the board is resized.
	 * the Dealer adds a Component listener to the board -- the listener calls this method.
	 * The listener sends the difference between the original board width and the new board width,
	 * as the argument deltaWindowWidthPixels, and here the ColumnBorder locations are altered accordingly.
	 */
	public void setColumnLocations(int deltaBoardWidthPixels) {
		cb1.setLocation(Globals.COLUMN_ONE_LOCX - 15, Globals.COLUMN_ONE_LOCY - 15);
		cb2.setLocation((Globals.COLUMN_TWO_LOCX - 15) - deltaBoardWidthPixels / 2, Globals.COLUMN_TWO_LOCY - 15);
		cb3.setLocation((Globals.COLUMN_THREE_LOCX - 15) - 
				((deltaBoardWidthPixels / 2 <= Globals.COLUMN_GAP) ? deltaBoardWidthPixels : 0), Globals.COLUMN_THREE_LOCY - 15);
	}

	public void indicateColumn(int id) {
		dealer.pickupCards(id);
	}

	public void pickCard() {
		hasSelectedCard = true;
	}

	private void createWordsPanel() {
		wordsPanel = new JPanel();
		wordsPanel.setOpaque(false);
		wordsPanel.setLayout(new BoxLayout(wordsPanel, BoxLayout.Y_AXIS));
		wordsPanel.setPreferredSize(new Dimension(Globals.FRAME_WI, 170));
	}

	private void createMessageJLabels() {
		startInstructionsLabel = new JLabel();
		questionLabel = new JLabel();
		columnIdLabel = new JLabel();
		Styles.configureJLabel(startInstructionsLabel, true);
		Styles.configureJLabel(questionLabel, false);
		Styles.configureJLabel(columnIdLabel, false);
		startInstructionsLabel.setText("Please pick your secret card. Shh!");
		questionLabel.setText("Which column is your card in?");
	}

	private void createButtonPanel() {
		buttonPanel = new JPanel();
		buttonPanel.setOpaque(false);
		// buttonPanel.setPreferredSize(new Dimension(100, 50));
		// buttonPanel.setLayout(new BoxLayout(buttonPanel, BoxLayout.X_AXIS));
		buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 20, 10));
		createButtons();
		buttonPanel.add(btnReady);
		buttonPanel.add(btnYes);
	}

	private void createButtons() {
		btnReady = new JButton("Done");
		btnYes = new JButton("Yes!");
		Styles.configureJButton(btnReady, true);
		Styles.configureJButton(btnYes, false);
		btnReady.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				hasSelectedCard = true;
				btnReady.setVisible(false);
				btnYes.setVisible(true);
				buttonPanel.setVisible(false);
				startInstructionsLabel.setVisible(false);
				questionLabel.setVisible(true);
				columnIdLabel.setVisible(true);
				for (Component c : columnPanel.getComponents())
				{
					if (c.getClass() == ColumnBorder.class)
					{
						c.repaint();
					}
				}
				pickCard();

			}
		});
		btnYes.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				columnIdLabel.setVisible(true);
				buttonPanel.setVisible(false);
				questionLabel.setText("Which column is your card in?");
				indicateColumn(selectedColumnID);
			}
		});
	}

	private class ColumnBorder extends JPanel {
		public int columnNumber;
		public boolean hoveredOver;
		private boolean selected;

		public ColumnBorder(final int columnNumber) {
			this.columnNumber = columnNumber;
			setSize(Globals.CARD_WI + 30, Globals.COLUMN_HI + 30);
			setPreferredSize(new Dimension(Globals.CARD_WI + 30, Globals.COLUMN_HI + 30));

			setOpaque(false);
			addMouseListener(new MouseAdapter() {
				@Override
				public void mouseExited(MouseEvent e) {
					if (hasSelectedCard)
					{
						columnIdLabel.setText("");
						hoveredOver = false;
						repaint();
					}
				}

				@Override
				public void mouseEntered(MouseEvent e) {
					if (hasSelectedCard)
					{
						columnIdLabel.setText("Column " + columnNumber + "?");
						hoveredOver = true;
						repaint();
					}
				}

				@Override
				public void mouseClicked(MouseEvent e) {
					if (hasSelectedCard)
					{
						deselectColumns(); // deselect all other columns
						selected = !selected; // toggle this column
						columnIdLabel.setVisible(false);
						selectedColumnID = columnNumber;
						buttonPanel.setVisible(true);
						btnYes.setVisible(true);
						questionLabel.setText("Your card is in Column " + columnNumber + "?");
						repaint();
					}
				}
			});
		}

		@Override
		public void paintComponent(Graphics g) {

			// setVisible(true);
			Graphics2D g2d = (Graphics2D) g;
			super.paintComponent(g2d);

			RenderingHints qualityHints = new RenderingHints(RenderingHints.KEY_ANTIALIASING,
					RenderingHints.VALUE_ANTIALIAS_ON);
			qualityHints.put(RenderingHints.KEY_RENDERING, RenderingHints.VALUE_RENDER_QUALITY);
			g2d.setRenderingHints(qualityHints);

			float thickness = 10;
			Stroke oldStroke = g2d.getStroke();
			g2d.setStroke(new BasicStroke(thickness));

			if (hasSelectedCard)
				g2d.setColor(Color.WHITE);
			else
				g2d.setColor(new Color(255, 255, 255, 0));

			if (hoveredOver)
			{
				g2d.setColor(Globals.HOVERED_COLUMN_BORDER_COLOR_PURPLE_THEME);
			}
			if (selected)
			{
				g2d.setColor(Globals.SELECTED_COLUMN_BORDER_COLOR_PURPLE_THEME);
			}

			g2d.drawRoundRect(5, 5, Globals.CARD_WI + 20, Globals.COLUMN_HI + 20, 5, 5);
			g2d.setStroke(oldStroke);

		}

	}

}