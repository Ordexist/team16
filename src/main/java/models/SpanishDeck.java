package models;

public class SpanishDeck extends Deck {
    public SpanishDeck() {
        for (int i = 1; i < 13; i++) {
            cards.add(new SpanishCard(i, Suit.Clubs));
            cards.add(new SpanishCard(i, Suit.Coins));
            cards.add(new SpanishCard(i, Suit.Cups));
            cards.add(new SpanishCard(i, Suit.Swords));
        }

        cards.add(new SpanishCard(13, Suit.Joker));
        cards.add(new SpanishCard(13, Suit.Joker));

        this.size = 50;
        this.shuffle();
    }
}