package models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public java.util.List<Card> cards = new ArrayList<>();
    public int size = 52;

    public Deck(){
        for(int i = 2; i < 15; i++) {
            cards.add(new Card(i,Suit.Clubs));
            cards.add(new Card(i,Suit.Hearts));
            cards.add(new Card(i,Suit.Diamonds));
            cards.add(new Card(i,Suit.Spades));
        }

        this.shuffle();
    }

    public void shuffle() {
        // shuffles the deck so that it is random
        Collections.shuffle(cards);
    }

    public Card topCard() {                     //decrements deck size and returns top card
        size--;
        return(this.cards.get(size));
    }
}
