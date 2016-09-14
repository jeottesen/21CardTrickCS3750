public class Card 
{
	private CardValues value;
    private CardSuits suit;

	public Card(CardValues v, CardSuits s) {
        value = v;
        suit = s;
    }
	
	public void equals(Card otherCard)
	{
		//  Sets the values of the other card equal to the values of the current card
		this.suit = otherCard.suit;
		this.value = otherCard.value;
	}
	
    @Override
    public String toString() {
        String string;
        switch (value) {
            /*case BACK:
                string = "card_back";
                break;*/
            case ACE:
            //case ONE:
                string = "Ace" + suit.getValue();
                break;
            case JACK:
                string = "Jack"+ suit.getValue();
                break;
            case QUEEN:
                string = "Queen" + suit.getValue();
                break;
            case KING:
                string = "King" + suit.getValue();
                break;
            case TWO:
                string = "Two" + suit.getValue();
                break;
            case THREE:
                string = "Three" + suit.getValue();
                break;
            case FOUR:
                string = "Four" + suit.getValue();
                break;
            case FIVE:
                string = "Five" + suit.getValue();
                break;
            case SIX:
                string = "Six" + suit.getValue();
                break;
            case SEVEN:
                string = "Seven" + suit.getValue();
                break;
            case EIGHT:
                string = "Eight" + suit.getValue();
                break;
            case NINE:
                string = "Nine" + suit.getValue();
                break;
            case TEN:
                string = "Ten" + suit.getValue();
                break;
            default:
                string = value.getValue() + suit.getValue();
        }
        return string;
    }
}
