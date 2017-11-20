package controllers;

import models.Game;
import ninja.Context;
import ninja.Result;
import ninja.Results;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Random;
import javax.swing.ImageIcon;
import javax.swing.JDialog;
import javax.swing.JOptionPane;

import com.google.inject.Singleton;
import ninja.params.PathParam;

@Singleton
public class ApplicationController {
    public static int gameModeNum;
    public Result index() {
        return Results.html().template("views/AcesUp/startPage.html");
    }
    public Result mainIndex() {
        Game.updateGameMode();
        return Results.html().template("views/AcesUp/AcesUp.flt.html");
    }

    public static Game newGame;

    public Result gameGet(){
        newGame = new Game();
        newGame.dealFour();

        return Results.json().render(newGame);
    }

    public Result grabGame(){
        return Results.json().render(newGame);
    }


    public Result dealPost(Context context, Game g) {
        if(context.getRequestPath().contains("deal")){
            g.dealFour();
        }
        return Results.json().render(g);
    }

    public Result removeCard(Context context, @PathParam("column") int colNumber, Game g){
        g.remove(colNumber);
        return Results.json().render(g);
    }

    public Result moveCard(Context context, @PathParam("columnFrom") int colFrom, @PathParam("columnTo") int colTo, Game g){
        g.move(colFrom,colTo);
        return Results.json().render(g);
    }

    public Result gameMode(Context context, @PathParam("modeNumber") int gameModeNumber, Game g){
        gameModeNum = gameModeNumber;
        return Results.json().render(g);
    }
}
