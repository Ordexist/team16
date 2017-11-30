package models;

public class RegularDeck extends Deck{
    public RegularDeck(){
        for(int i = 2; i < 15; i++) {
            cards.add(new RegularCard(i,Suit.Clubs));
            cards.add(new RegularCard(i,Suit.Hearts));
            cards.add(new RegularCard(i,Suit.Diamonds));
            cards.add(new RegularCard(i,Suit.Spades));
        }

        this.size = 52;
    }
}
