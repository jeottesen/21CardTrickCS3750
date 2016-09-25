import java.awt.Color;

public class Globals 
{

	public static int buffer_right_edge = 20;

	public static final int CARD_WI = 150;
	public static final int CARD_HI = (int) (CARD_WI * 1.452); // Keep aspect ratio of card
	
	// dynamic_sizing_branch
	public static final int COLUMN_SPACING = 393;
	public static final int CARD_SPACING = 50;
	
	public static final int COLUMN_ONE_LOCX = 40;
	public static final int COLUMN_ONE_LOCY = 40;
	
	// splash
	//public static final int COLUMN_SPACING = 415;
	//public static final int CARD_SPACING = 45;
	
	//public static final int COLUMN_ONE_LOCX = 150;
	//public static final int COLUMN_ONE_LOCY = 35;

	
	public static final int COLUMN_TWO_LOCX = COLUMN_ONE_LOCX + COLUMN_SPACING;
	public static final int COLUMN_TWO_LOCY = COLUMN_ONE_LOCY;
	
	public static final int COLUMN_THREE_LOCX = COLUMN_TWO_LOCX + COLUMN_SPACING;
	public static final int COLUMN_THREE_LOCY = COLUMN_ONE_LOCY;
	
	// dynamic_sizing_branch
	public static final int COLUMN_GAP = COLUMN_TWO_LOCX - (COLUMN_ONE_LOCX + CARD_WI + 30);
	
	public static final int REVEAL_LOCY = 200;
	
	// splash
	// public static final int REVEAL_LOCX = 565;
	// public static final int REVEAL_LOCY = 170;

	
	public static final int CARDS_PER_COLUMN = 7;

	public static final int COLUMN_HI = 
			(CARD_SPACING * CARDS_PER_COLUMN) + (CARD_HI - CARD_SPACING);

	

	// dynamic_sizing_branch
	public static final int FRAME_WI = COLUMN_ONE_LOCX + COLUMN_SPACING * 2 + CARD_WI + COLUMN_ONE_LOCX; // Keep screen symmetrical
	public static final int FRAME_HI = 768;

	// splash
	//public static final int FRAME_WI = 1280; // Keep screen symmetrical
	//public static final int FRAME_HI = 720;

	
	//public static final int FRAME_WI = 1000; // Keep screen symmetrical
	//public static final int FRAME_HI = 600;
	
	public static final Color BACKGROUND_COLOR = new Color(0, 150, 136); // teal
	public static final Color BUTTON_HOVER_COLOR = new Color(0, 173, 157, 255); //lighter teal
	
	public static final Color BACKGROUND_COLOR_PURPLE = new Color(56, 4, 86);
	public static final Color BUTTON_HOVER_COLOR_PURPLE = new Color(107, 59, 134);
	
	public static final Color HOVERED_COLUMN_BORDER_COLOR = new Color(255, 194, 60); // amber 
	public static final Color SELECTED_COLUMN_BORDER_COLOR = new Color(0, 230, 118); // Green 500 
	
	public static final Color SELECTED_COLUMN_BORDER_COLOR_PURPLE_THEME = new Color(82, 210, 143); // Green 500
	public static final Color HOVERED_COLUMN_BORDER_COLOR_PURPLE_THEME = new Color(191, 156, 210); 
}