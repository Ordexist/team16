package models;

import java.util.ArrayList;
import java.util.Collections;

public class Deck {
    public java.util.List<Card> cards = new ArrayList<>();
    public int size;

    public Deck(){
    }

    public void shuffle() {
        Collections.shuffle(cards);
    }

    public Card topCard() {                     //decrements deck size and returns top card
        size--;
        return(this.cards.get(size));
    }
}
