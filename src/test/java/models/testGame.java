package models;

import org.junit.Test;
import controllers.ApplicationController;
import java.util.Arrays;

import static org.junit.Assert.*;

public class testGame {

    @Test
    public void testGameCreation(){
        ApplicationController.gameModeNum = 0;
        Game g = new Game();
        assertNotNull(g);
    }

    @Test
    public void testGameBuildDeckRegAce(){
        ApplicationController.gameModeNum = 0;
        Game g = new Game();
        assertEquals(52,g.deck.cards.size());
    }

    @Test
    public void testGameBuildDeckSpanish(){
        ApplicationController.gameModeNum = 2;
        Game g = new Game();
        assertEquals(50,g.deck.cards.size());
    }

    @Test
    public void testGameStart(){
        ApplicationController.gameModeNum = 0;
        Game g = new Game();
        g.deck.shuffle();
        g.dealFour();
        assertEquals(1,g.cols.get(0).cards.size());
        assertEquals(1,g.cols.get(1).cards.size());
        assertEquals(1,g.cols.get(2).cards.size());
        assertEquals(1,g.cols.get(3).cards.size());
    }

    @Test
    public void testCustomDeal(){
        ApplicationController.gameModeNum = 0;
        Game g = new Game();
        g.customDeal(0,3,6,9);
        assertEquals("2Clubs",g.cols.get(0).cards.get(0).toString());
        assertEquals("3Clubs",g.cols.get(1).cards.get(0).toString());
        assertEquals("4Clubs",g.cols.get(2).cards.get(0).toString());
        assertEquals("5Clubs",g.cols.get(3).cards.get(0).toString());
    }

    @Test
    public void testDealFour(){
        ApplicationController.gameModeNum = 0;
        Game g = new Game();
        g.dealFour();
        assertEquals(1,g.cols.get(0).size());
        assertEquals(1,g.cols.get(1).size());
        assertEquals(1,g.cols.get(2).size());
        assertEquals(1,g.cols.get(3).size());
    }

    @Test
    public void testRemoveFunction(){
        ApplicationController.gameModeNum = 0;
        Game g = new Game();
        g.customDeal(0,3,6,9);
        g.remove(2);
        assertEquals(0,g.cols.get(2).size());
    }

    @Test
    public void testMoveFunction(){
        ApplicationController.gameModeNum = 0;
        Game g = new Game();
        g.customDeal(0,3,6,9);
        g.remove(2);
        assertEquals(0,g.cols.get(2).size());
        g.move(0,2);
        assertEquals(0,g.cols.get(0).size());  //TEST HAS ERROR HERE. EXPECTS 0, ACTUALLY GETS 1 FOR SOME REASON
        assertEquals(1,g.cols.get(2).size());

        ApplicationController.gameModeNum = 1;
        Game e = new Game();
        e.customDeal(0,3,6,37);
        e.remove(2);
        assertEquals(0,e.cols.get(2).size());
        e.move(0,2);                 //can't move this card because it's not an ace
        assertEquals(1,e.cols.get(0).size());    //card was not moved
        assertEquals(0,e.cols.get(2).size());    //column did not get a new card
    }

    @Test
    public void testCanRemove(){
        ApplicationController.gameModeNum = 0;
        Game g = new Game();
        g.customDeal(0, 3, 6, 9);
        g.remove(0);
        assertEquals(1,g.canRemove);      //remove from column 0 successful
        g.remove(0);
        assertEquals(2,g.canRemove);      //remove from column 0 unsuccessful, column is empty
        g.remove(3);
        assertEquals(0,g.canRemove);      //remove from column 3 unsuccessful, no higher card of same suit

        Game h = new Game();
        h.customDeal(49, 3, 6, 0);
        h.remove(0);
        assertEquals(4,h.canRemove);      //remove from column 3 unsuccessful, it was an ace

        // set game mode to 2 and run test for remove again
        ApplicationController.gameModeNum = 2;
        Game e = new Game();
        e.customDeal(0, 3, 6, 9);
        e.remove(0);
        assertEquals(1,e.canRemove);      //remove from column 0 successful
        e.remove(0);
        assertEquals(2,e.canRemove);      //remove from column 0 unsuccessful, column is empty
        e.remove(3);
        assertEquals(0,e.canRemove);      //remove from column 3 unsuccessful, no higher card of same suit

        Game f = new Game();
        f.customDealJokers();
        f.remove(0);
        assertEquals(3, f.canRemove);     //remove from column 0 unsuccessful (joker in place)
        f.remove(1);
        assertEquals(3, f.canRemove);     //remove from column 1 unsuccessful (joker in place)
        f.remove(2);
        assertEquals(1, f.canRemove);     //remove from column 2 successful (higher number in play)
        f.remove(3);
        assertEquals(1, f.canRemove);     //remove from column 3 successful (joker on field)

        Game i = new Game();
        i.customDealJokersAndAces();
        i.remove(1);
        assertEquals(4, i.canRemove);
    }

    @Test
    public void testCanMove(){
        ApplicationController.gameModeNum = 0;
        Game g = new Game();
        g.customDeal(0, 3, 6, 9);
        g.remove(0);
        g.move(1, 0);
        assertEquals(1,g.canMove);         //move is successful to empty column
        g.move(1, 0);
        assertEquals(3,g.canMove);         //move is unsuccessful, column 1 is empty
        g.move(0, 2);
        assertEquals(0,g.canMove);         //move is unsuccessful, column 2 is not empty

        ApplicationController.gameModeNum = 1;     //aces only can be moved
        Game e = new Game();
        e.customDeal(0, 3, 6, 47);
        e.remove(0);
        e.move(1, 0);
        assertEquals(2,e.canMove);         //move is unsuccessful, no ace in column 1
        e.move(0, 1);
        assertEquals(3,e.canMove);         //move is unsuccessful, column 0 is empty
        e.move(3, 2);
        assertEquals(0,e.canMove);         //move is unsuccessful, column 2 is not empty
        e.move(3, 0);
        assertEquals(1,e.canMove);         //move is successful, ace moved from column 3 to 0
    }

    @Test
    public void testWinScenario(){
        ApplicationController.gameModeNum = 0;
        Game g = new Game();
        g.points = 47;
        g.customDeal(0, 3, 6, 9);
        g.remove(0); // this remove will be successful
        assertEquals(48,g.points); // points will be 48 at this point due to remove

        ApplicationController.gameModeNum = 2;
        Game e = new Game();
        e.points = 45;
        e.customDealJokers();
        e.remove(3); // this remove will be successful
        assertEquals(46,e.points); // points will be 46 at this point due to remove
    }
}
