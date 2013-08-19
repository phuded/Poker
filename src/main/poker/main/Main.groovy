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

    static main(args) {
        Game game = new Game()

        game.startGame()

      //  printGame(game)

    }

    static printGame(Game game){
        println game.player.hand
        println game.gameCards
        println game.deck.getRemainingCards() + " remaining cards."
    }


}
