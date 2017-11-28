package models;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

public class RegularCard extends Card{

    @JsonCreator
    public RegularCard(@JsonProperty("value") int value, @JsonProperty("suit") Suit suit){
        super(value, suit);
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
}
