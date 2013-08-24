package main.poker.game

import main.poker.card.Card
import main.poker.player.Player
import main.poker.util.RoundWinnerDetector

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

    //Winner
    Player winner

    //Draw
    boolean isDraw

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
            dealCardsToPlayer(player)
        }

        //Add other cards
        dealFlop()
        dealRiver()
        dealFinal()

        //Detect hands...
        players.each{ Player player ->
            player.detectHand()
           // println "MAIN: "+ player.name + " - All hand-results: " + player.hands
            println "MAIN: "+ player.name + " - Best hand-result: " + player.bestHand
        }

        println "================================"

        //Get winner
        def winner = RoundWinnerDetector.detectWinner(players)

    }

    // Deal 1st two cards to player
    def dealCardsToPlayer(Player player){
        player.receiveCard(deck.getCard())
        player.receiveCard(deck.getCard())
    }

    //Deal flop
    def dealFlop(){

        List<Card> flop = deck.getFlop()
        gameCards.addAll(flop)

        players.each { Player player ->
            //Add flop to player hand
            player.addGameCards(flop)

            println "MAIN: " + player.name + " - Hand after flop: " + player.allCards
            println "================================"
        }
    }

    //Deal river game card
    def dealRiver(){
        Card river = deck.getCard()
        gameCards.add(river)

        players.each { Player player ->
            //Add river card to player hand
            player.addGameCards(river)

           // println "MAIN: Player: " + player.name + " hand after river: " + player.allCards
        }
    }

    //Deal final game card
    def dealFinal(){
        Card finalCard = deck.getCard()
        gameCards.add(finalCard)

        players.each { Player player ->
            //Add final card to player hand
            player.addGameCards(finalCard)

            println "MAIN: " + player.name + " - After All cards: " + player.allCards
            println "================================"
        }
    }
}
