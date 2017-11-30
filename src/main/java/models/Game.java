package models;

import controllers.ApplicationController;
import models.Deck;
import java.util.ArrayList;
import java.util.concurrent.TimeUnit;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;


public class Game {
    public Deck deck;

    public java.util.List<Column> cols = new ArrayList<>(4);
    public int points = 0;
    public int canRemove; //0 = Cant remove, 1 = Can Remove, 2 = Column Empty
    public int canMove;   //0 = Cant move, 1 = Can Move, 2 = Only Aces can move, 3 = Column empty
    public int wonGame = 0;
    public static int gameModeSet; /*GameModes -- 0-Regular, 1-Aces, 2-Spanish, 3-NoSelection*/
    public static void updateGameMode() {
      gameModeSet = ApplicationController.gameModeNum;
    }

    public Game(){
        cols.add(new Column());
        cols.add(new Column());
        cols.add(new Column());
        cols.add(new Column());

        updateGameMode();

        if(gameModeSet == 0 || gameModeSet == 1){
            deck = new RegularDeck();
        }
        else if(gameModeSet == 2){
            deck = new SpanishDeck();
        }
    }

    public void shuffle(){
        deck.shuffle();
    }

    public void dealFour() {
        // remove the top card from the deck and add it to a column; repeat for each of the four columns
        if(deck.size >= 4) {
            for (int i = 0; i < 4; i++) {
                cols.get(i).addCard(deck.topCard());
            }
        } else if(deck.size == 2) {
            for (int i = 0; i < 2; i++) {
                cols.get(i).addCard(deck.topCard());
            }
        }
    }

    //customDeal to setup game for testing purposes (i.e. shuffled cards are random and hard to test)
    public void customDeal(int c1, int c2, int c3, int c4) {
        this.cols.get(0).addCard(deck.cards.get(c1));
        deck.cards.remove(c1);
        this.cols.get(1).addCard(deck.cards.get(c2));
        deck.cards.remove(c2);
        this.cols.get(2).addCard(deck.cards.get(c3));
        deck.cards.remove(c3);
        this.cols.get(3).addCard(deck.cards.get(c4));
        deck.cards.remove(c4);
    }

    public void remove(int columnNumber) {
      // remove the top card from the indicated column
      canRemove = 0;
      if(!cols.get(columnNumber).hasCards()){
        canRemove = 2;
      } else {
      for(int i = 0; i < this.cols.size(); i++){                    //check the card to remove against all other top cards
          if(cols.get(i).hasCards() && cols.get(columnNumber).hasCards()) {
              Card cardToRemove = cols.get(columnNumber).topCard();
              Card cardToCheck = cols.get(i).topCard();

              if(i != columnNumber && cardToRemove.suit == cardToCheck.suit && cardToRemove.value < cardToCheck.value) {  //if a higher card with matching suit
                  canRemove = 1;                                        //set remove flag
              }
          }
      }
    }

      if(canRemove == 1) {           //remove card if flag is set
          cols.get(columnNumber).removeCard();
          points++;
          if(points >= 48 && (gameModeSet == 0 || gameModeSet == 1)){            //in regular modes, 48 points is required
            wonGame = 1;
          }
          else if(points >= 46 && gameModeSet == 2){                           //in spanish mode, 46 points is required
              wonGame = 1;
          }
      }
  }

    public void move(int columnFrom, int columnTo) {
      if(gameModeSet == 0 || gameModeSet == 2){
        // remove the top card from the columnFrom column, add it to the columnTo column
        if(cols.get(columnFrom).hasCards() == true) {
            Card tempCard = cols.get(columnFrom).topCard();

            if(cols.get(columnTo).hasCards() == false) {
                cols.get(columnFrom).removeCard();
                cols.get(columnTo).addCard(tempCard);
                canMove = 1;
            } else {                                           //moving card that is not an ace, error
                canMove = 0;
            }
        }
        else {                                               //moving card from an empty column, error
          canMove = 3;
        }
      }

      if(gameModeSet == 1){
        // remove the top card from the columnFrom column, add it to the columnTo column
        if(cols.get(columnFrom).hasCards() == true) {
            Card tempCard = cols.get(columnFrom).topCard();

            if(tempCard.getValue() == 14) {
                if(cols.get(columnTo).hasCards() == false) {
                    cols.get(columnFrom).removeCard();
                    cols.get(columnTo).addCard(tempCard);
                    canMove = 1;
                }
                else {                                        //moving card to column that is not empty, error
                  canMove = 0;
                }
            } else {                                           //moving card that is not an ace, error
                canMove = 2;
            }
        } else {                                               //moving card from an empty column, error
          canMove = 3;
        }
      }
    }

}
