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
    Player player
    def gameCards = []

    Game(){
        player = new Player (name: "Matt")
        deck = new Deck()
    }

    def startGame(){
        deck.shuffle()

        //Player gets to cards
        player.addCardToHand(deck.dealCard())
        player.addCardToHand(deck.dealCard())

        //Get the flop
        getFlop()

        //Add game cards to player hand
        player.addGameCards(gameCards)

        println "MAIN: Player whole-hand: " + player.wholeHand

        player.detectHand()

        println "MAIN: Player hand-results:" + player.handResults

    }

    /**
     * Gets the Flop
     * @return
     */
    def getFlop(){
        gameCards.addAll(deck.dealFlop())
    }
}
