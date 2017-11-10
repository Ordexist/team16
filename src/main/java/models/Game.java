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

    public Deck deck = new Deck();

    public java.util.List<Column> cols = new ArrayList<>(4);
    public int points = 0;

    public Game(){
        cols.add(new Column());
        cols.add(new Column());
        cols.add(new Column());
        cols.add(new Column());
    }

    public void dealFour() {
        // remove the top card from the deck and add it to a column; repeat for each of the four columns
        if(deck.size >= 4) {
            for (int i = 0; i < 4; i++) {
                cols.get(i).addCard(deck.topCard());
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
            if(cols.get(i).hasCards() && cols.get(columnNumber).hasCards()) {
                Card cardToRemove = cols.get(columnNumber).topCard();
                Card cardToCheck = cols.get(i).topCard();

                if(i != columnNumber && cardToRemove.suit == cardToCheck.suit && cardToRemove.value < cardToCheck.value) {  //if a higher card with matching suit
                    canRemove = 1;                                        //set remove flag
                }
            }
        }

        if(canRemove == 1) {           //remove card if flag is set
            cols.get(columnNumber).removeCard();
            points++;
            if(points >= 48){
                final JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, "You win!", "Congratulations!", JOptionPane.PLAIN_MESSAGE);
            }
        }
    }

    public void move(int columnFrom, int columnTo) {
        // remove the top card from the columnFrom column, add it to the columnTo column
        if(cols.get(columnFrom).hasCards() == true) {
            Card tempCard = cols.get(columnFrom).topCard();

            if(tempCard.getValue() == 14) {
                if(cols.get(columnTo).hasCards() == false) {
                    cols.get(columnFrom).removeCard();
                    cols.get(columnTo).addCard(tempCard);
                }
                else {                                        //moving card to column that is not empty, error
                    final JDialog dialog = new JDialog();
                    dialog.setAlwaysOnTop(true);
                    JOptionPane.showMessageDialog(dialog, "Can't move here!\nThere is already a card in column " + (columnTo + 1) + "!", "Move Invalid", JOptionPane.ERROR_MESSAGE);
                }
            }
            else {                                           //moving card that is not an ace, error
                final JDialog dialog = new JDialog();
                dialog.setAlwaysOnTop(true);
                JOptionPane.showMessageDialog(dialog, "You can only move Aces to empty columns!", "Move Invalid", JOptionPane.ERROR_MESSAGE);
            }
        }
        else {                                               //moving card from an empty column, error
            final JDialog dialog = new JDialog();
            dialog.setAlwaysOnTop(true);
            JOptionPane.showMessageDialog(dialog, "Column " + (columnFrom + 1) + " is empty!", "Move Invalid", JOptionPane.ERROR_MESSAGE);
        }
    }
}
