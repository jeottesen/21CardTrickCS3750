public class Globals 
{
	public static final int CARD_WI = 200;
	public static final int CARD_HI = (int) (CARD_WI * 1.25); // Keep aspect ratio of card
	
	public static final int COLUMN_SPACING = CARD_WI + 100;
	public static final int CARD_SPACING = 50;
	
	public static final int COLUMN_ONE_LOCX = 20;
	public static final int COLUMN_ONE_LOCY = 20;
	
	public static final int COLUMN_TWO_LOCX = COLUMN_ONE_LOCX + COLUMN_SPACING;
	public static final int COLUMN_TWO_LOCY = COLUMN_ONE_LOCY;
	
	public static final int COLUMN_THREE_LOCX = COLUMN_TWO_LOCX + COLUMN_SPACING;
	public static final int COLUMN_THREE_LOCY = COLUMN_ONE_LOCY;
	
	public static final int CARDS_PER_COLUMN = 7;
	
	public static final int FRAME_WI = COLUMN_ONE_LOCX + COLUMN_SPACING * 2 + CARD_WI + COLUMN_ONE_LOCX; // Keep screen symmetrical
	public static final int FRAME_HI = 768;
	
}
