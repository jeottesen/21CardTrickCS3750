public enum CardSuits {

    SPADES("Spades"),
    HEARTS("Hearts"),
    DIAMONDS("Diamonds"),
    CLUBS("Clubs");

    private final String value;

    CardSuits(final String value) {
        this.value = value;
    }

    public String getValue() {
        return value;
    }

}
