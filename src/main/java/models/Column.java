package models;

import java.util.ArrayList;

public class Column {
    public java.util.List<Card> cards = new ArrayList<>();
    public int size = 0;

    public Column(){
    }

    public boolean hasCards() {
        if(size > 0){
            return true;
        }
        else {
            return false;
        }
    }

    public Card topCard() {
        return cards.get(size - 1);
    }

    public void addCard(Card card) {
        cards.add(card);
        size = size + 1;
    }

    public void removeCard() {
        size = size - 1;
        cards.remove(size);
    }

    public int size() {
        return size;
    }
}
