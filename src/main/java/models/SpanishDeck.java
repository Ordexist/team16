package models;

public class SpanishDeck extends Deck {
    public SpanishDeck() {
        for (int i = 1; i < 13; i++) {
            cards.add(new SpanishCard(i, Suit.Club));
            cards.add(new SpanishCard(i, Suit.Coin));
            cards.add(new SpanishCard(i, Suit.Cup));
            cards.add(new SpanishCard(i, Suit.Sword));
        }

        cards.add(new SpanishCard(1, Suit.Joker));
        cards.add(new SpanishCard(2, Suit.Joker));

        this.size = 50;
        this.shuffle();
    }
}
