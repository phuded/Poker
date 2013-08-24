package main.poker.game

import main.poker.card.Card
import main.poker.player.Player

/**
 * Created with IntelliJ IDEA.
 * User: matt
 * Date: 24/08/2013
 * Time: 02:20
 * To change this template use File | Settings | File Templates.
 */
class Round {

    Deck deck
    List<Player> players

    // Up for 5 cards
    List<Card> gameCards

    def Round(List<Player> players, deck){
        //Add deck and shuffle
        this.deck = deck
        this.deck.shuffle()

        //Add players
        this.players = players

        //Prepare game cards
        gameCards = []
    }

    def playRound(){

        println "MAIN: New round"
        println "================================"

        players.each { Player player ->
            //Player gets two cards
            player.addCardToHand(deck.dealCard())
            player.addCardToHand(deck.dealCard())

        }

        //Add other cards
        getFlop()
        getRiver()
        getFinal()

        //Detect hands...
        players.each{ Player player ->
            player.detectHand()
            println "MAIN: "+ player.name + " - All hand-results:" + player.handResults
            println "MAIN: "+ player.name + " - Best hand-result:" + player.bestHandResult
            println "================================"
        }
    }

    /**
     * Gets the Flop
     * @return
     */
    def getFlop(){

        List<Card> flop = deck.dealFlop()
        gameCards.addAll(flop)

        players.each { Player player ->
            //Add flop to player hand
            player.addGameCards(flop)

            println "MAIN: " + player.name + " - Hand after flop: " + player.wholeHand
            println "================================"
        }
    }

    def getRiver(){
        Card river = deck.dealCard()
        gameCards.add(river)

        players.each { Player player ->
            //Add river card to player hand
            player.addGameCards(river)

           // println "MAIN: Player: " + player.name + " hand after river: " + player.wholeHand
        }
    }

    def getFinal(){
        Card finalCard = deck.dealCard()
        gameCards.add(finalCard)

        players.each { Player player ->
            //Add final card to player hand
            player.addGameCards(finalCard)

            println "MAIN: " + player.name + " -  After All cards: " + player.wholeHand
            println "================================"
        }
    }
}
