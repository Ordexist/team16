package models;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

/**
 * Assignment 1: Each of the blank methods below require implementation to get AcesUp to build/run
 */
public class Game {

    public java.util.List<Card> deck = new ArrayList<>();

    public java.util.List<java.util.List<Card>> cols = new ArrayList<>(4);
    public int points = 0;

    public Game(){
        cols.add( new ArrayList<Card>(13));
        cols.add( new ArrayList<Card>(13));
        cols.add( new ArrayList<Card>(13));
        cols.add( new ArrayList<Card>(13));
    }

    public void buildDeck() {
        for(int i = 2; i < 15; i++){
            deck.add(new Card(i,Suit.Clubs));
            deck.add(new Card(i,Suit.Hearts));
            deck.add(new Card(i,Suit.Diamonds));
            deck.add(new Card(i,Suit.Spades));
        }
    }

    public void shuffle() {
        // shuffles the deck so that it is random
        Collections.shuffle(deck);
    }

    public void dealFour() {
        // remove the top card from the deck and add it to a column; repeat for each of the four columns
        if(deck.size() >= 4) {
            for (int i = 0; i < 4; i++) {
                addCardToCol(i, deck.remove(0));
            }
        } else {
            final JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(dialog, "No more cards in the Deck!", "No more cards!", JOptionPane.ERROR_MESSAGE);
        }
    }

    public void remove(int columnNumber) {
        // remove the top card from the indicated column
        int canRemove = 0;
        for(int i = 0; i < this.cols.size(); i++){                    //check the card to remove against all other top cards
            if(columnHasCards(i) && columnHasCards(columnNumber)){
                Card cardToRemove = getTopCard(columnNumber);
                Card cardToCheck = getTopCard(i);
                if(i != columnNumber && cardToRemove.suit == cardToCheck.suit && cardToRemove.value < cardToCheck.value){  //if a higher card with matching suit
                    canRemove = 1;                                        //set remove flag
                }
            }
        }

        if(canRemove == 1){           //remove card if flag is set
            removeCardFromCol(columnNumber);
            points++;
        }
    }

    private boolean columnHasCards(int columnNumber) {
        if(cols.get(columnNumber).size() > 0){
            return true;
        }
        else {
            return false;
        }
    }

    private Card getTopCard(int columnNumber) {
        return this.cols.get(columnNumber).get(this.cols.get(columnNumber).size()-1);
    }


    public void move(int columnFrom, int columnTo) {
        // remove the top card from the columnFrom column, add it to the columnTo column
        Card topCard = getTopCard(columnFrom);
        if(columnFrom < 4 && columnTo < 4 && columnHasCards(columnFrom) == true && columnHasCards(columnTo) == false && topCard.getValue() == 14) {
            removeCardFromCol(columnFrom);
            addCardToCol(columnTo, topCard);
        } else if( columnHasCards(columnTo) == true ){
            final JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(dialog, "Can't move here!\nThere is already a card in column " + (columnTo + 1) + "!", "Move Invalid", JOptionPane.ERROR_MESSAGE);
        } else if( topCard.getValue() != 14) {
            final JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(dialog, "You can only move Aces to empty columns!", "Move Invalid", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void addCardToCol(int columnTo, Card cardToMove) {
        cols.get(columnTo).add(cardToMove);
    }

    private void removeCardFromCol(int colFrom) {
        this.cols.get(colFrom).remove(this.cols.get(colFrom).size()-1);
    }
}
