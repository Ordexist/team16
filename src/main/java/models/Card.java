package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.io.Serializable;

public class Card implements Serializable {
    public final int value;
    public final Suit suit;
    public final String img;

    @JsonCreator
    public Card(@JsonProperty("value") int value, @JsonProperty("suit") Suit suit) {
        this.value = value;
        this.suit = suit;
        String imgVal;

        switch(value){
          case 11:
            imgVal = "jack";
            break;
          case 12:
            imgVal = "queen";
            break;
          case 13:
            imgVal = "king";
            break;
          case 14:
            imgVal = "ace";
            break;
          default:
            imgVal = Integer.toString(value);
        }
        this.img = "/assets/cardImages/" + imgVal + "_of_" + this.suit.name().toLowerCase() + ".png";
    }

    public Suit getSuit() {
        return suit;
    }

    public int getValue() {
        return value;
    }

    public String toString() {
        return this.value + this.suit.toString();
    }
}
