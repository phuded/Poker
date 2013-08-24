package main.poker.game


import main.poker.player.Player
import main.poker.util.HandDetector

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 13/08/13
 * Time: 18:10
 * To change this template use File | Settings | File Templates.
 */
class Game {
    Deck deck
    List<Player> players
    List<Round> rounds

    Game(){
        players = []
        rounds = []
        //Create players and new deck
        players << new Player ("Matt")
        players << new Player ("Cathy")
        deck = new Deck()
    }

    def startGame(){
        println "MAIN: Starting game with: "+ players

        Round round = new Round(players, deck)
        round.playRound()
        rounds << round
    }
}
