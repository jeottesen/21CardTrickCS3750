import java.awt.Color;

public class Globals 
{
	public static final int CARD_WI = 150;
	public static final int CARD_HI = (int) (CARD_WI * 1.25); // Keep aspect ratio of card
	
	public static final int COLUMN_SPACING = 393;
	public static final int CARD_SPACING = 50;
	
	public static final int COLUMN_ONE_LOCX = 25;
	public static final int COLUMN_ONE_LOCY = 50;
	
	public static final int COLUMN_TWO_LOCX = COLUMN_ONE_LOCX + COLUMN_SPACING;
	public static final int COLUMN_TWO_LOCY = COLUMN_ONE_LOCY;
	
	public static final int COLUMN_THREE_LOCX = COLUMN_TWO_LOCX + COLUMN_SPACING;
	public static final int COLUMN_THREE_LOCY = COLUMN_ONE_LOCY;
	
	public static final int REVEAL_LOCX = 418;
	public static final int REVEAL_LOCY = 200;
	
	public static final int CARDS_PER_COLUMN = 7;

	public static final int COLUMN_HI = 
			(Globals.CARD_SPACING * CARDS_PER_COLUMN) + (Globals.CARD_HI - Globals.CARD_SPACING);

	
	public static final int FRAME_WI = COLUMN_ONE_LOCX + COLUMN_SPACING * 2 + CARD_WI + COLUMN_ONE_LOCX + 20; // Keep screen symmetrical
	public static final int FRAME_HI = 768;
	
	//public static final int FRAME_WI = 1000; // Keep screen symmetrical
	//public static final int FRAME_HI = 600;
	
	public static final Color BACKGROUND_COLOR = new Color(0, 150, 136); // teal
	public static final Color BUTTON_HOVER_COLOR = new Color(0, 173, 157, 255); //lighter teal
	
	public static final Color BACKGROUND_COLOR_PURPLE = new Color(56, 4, 86);
	public static final Color BUTTON_HOVER_COLOR_PURPLE = new Color(107, 59, 134);
	
	//public static final Color HOVERED_COLUMN_BORDER_COLOR = new Color(255, 194, 60, 221); // amber with transparency
	//public static final Color SELECTED_COLUMN_BORDER_COLOR = new Color(76, 175, 80, 221); // Green 500 with 87% Transparency
	
	public static final Color HOVERED_COLUMN_BORDER_COLOR = new Color(255, 194, 60); // amber 
	public static final Color SELECTED_COLUMN_BORDER_COLOR = new Color(0, 230, 118); // Green 500 
	
	public static final Color HOVERED_COLUMN_BORDER_COLOR_PURPLE_THEME = new Color(240, 168, 35); // amber 
	public static final Color SELECTED_COLUMN_BORDER_COLOR_PURPLE_THEME = new Color(19, 194, 101); // Green 500 
	
}
