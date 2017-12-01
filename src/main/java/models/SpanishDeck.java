package models;

public class SpanishDeck extends Deck {
    public SpanishDeck() {
        //changed from 2-14 instead of 1-13 due to aces changing value
        for (int i = 2; i < 14; i++) {
            cards.add(new SpanishCard(i, Suit.Club));
            cards.add(new SpanishCard(i, Suit.Coin));
            cards.add(new SpanishCard(i, Suit.Cup));
            cards.add(new SpanishCard(i, Suit.Sword));
        }

        cards.add(new SpanishCard(1, Suit.Joker));
        cards.add(new SpanishCard(2, Suit.Joker));

        this.size = 50;
    }
}
