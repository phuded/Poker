package main.poker.main

import main.poker.game.Game

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 12/08/13
 * Time: 15:28
 * To change this template use File | Settings | File Templates.
 */
class Main {
    static final def playerNames = ["Matt","Cathy","Ella","Becky"]
    static final int startingPlayerFunds = 1000;

    static main(args) {
        Game game = new Game(playerNames,startingPlayerFunds)
        println "================================"
        game.startGame()
    }

}
