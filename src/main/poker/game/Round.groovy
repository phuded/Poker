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
    //Parent parentGame
    Game parentGame

    // Up for 5 cards
    List<Card> roundCards

    //Winners
    List<Player> winners

    def Round(Game game){
        //Link to parent parentGame
        parentGame = game

        //Create/replace deck and shuffle
        parentGame.deck = new Deck()
        parentGame.deck.shuffle()

        //Prepare parentGame cards
        roundCards = []
    }

    def playRound(){

        println "================================"
        println "MAIN: New round - " + (parentGame.rounds.size() + 1)
        println "================================"

        parentGame.players.each { Player player ->
            //Player gets two cards
            dealCardsToPlayer(player)
        }

        //Add other cards
        dealFlop()

        println "================================"

        dealRiver()
        dealFinal()

        println "================================"

        //Detect hands...
        parentGame.players.each{ Player player ->
            player.detectHand()
           // println "MAIN: "+ player.name + " - All hand-results: " + player.hands
            println "MAIN: "+ player.name + " - Best hand: " + player.bestHand
        }

        println "================================"

        //Get winner
        winners = RoundWinnerDetector.detectWinners(parentGame.players)

        println "MAIN: Winners: " + winners

        //Finish and play next round
        parentGame.nextRound(this)
    }

    // Deal 1st two cards to player
    def dealCardsToPlayer(Player player){
        player.receiveCard(parentGame.deck.getCard())
        player.receiveCard(parentGame.deck.getCard())
    }

    //Deal flop
    def dealFlop(){

        List<Card> flop = parentGame.deck.getFlop()
        roundCards.addAll(flop)

        parentGame.players.each { Player player ->
            //Add flop to player hand
            player.addGameCards(flop)

            println "MAIN: " + player.name + " - Hand after flop: " + player.allCards
        }
    }

    //Deal river parentGame card
    def dealRiver(){
        Card river = parentGame.deck.getCard()
        roundCards.add(river)

        parentGame.players.each { Player player ->
            //Add river card to player hand
            player.addGameCards(river)

           // println "MAIN: Player: " + player.name + " hand after river: " + player.allCards
        }
    }

    //Deal final parentGame card
    def dealFinal(){
        Card finalCard = parentGame.deck.getCard()
        roundCards.add(finalCard)

        parentGame.players.each { Player player ->
            //Add final card to player hand
            player.addGameCards(finalCard)

            println "MAIN: " + player.name + " - After All cards: " + player.allCards
        }
    }
}
