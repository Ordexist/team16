package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class SpanishCard extends Card {
    @JsonCreator
    public SpanishCard(@JsonProperty("value") int value, @JsonProperty("suit") Suit suit) {
        super(value, suit);

        String imgVal;
        imgVal = Integer.toString(value);
        this.img = "/assets/spanishCardImages/" + this.suit.name().toLowerCase() + "_" + value + ".png";
    }
}
