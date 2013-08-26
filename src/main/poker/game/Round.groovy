package main.poker.game

import main.poker.card.Card
import main.poker.game.round.BettingRound
import main.poker.game.round.FirstRound
import main.poker.game.round.FlopRound
import main.poker.game.round.RiverCardRound
import main.poker.game.round.TurnCardRound
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

        //Initial deals and betting
        dealCardsToPlayers()

        //Add other cards
        dealFlop()

        dealTurnCard()

        dealRiverCard()

        //Detect winners
        detectWinners()

        //Finish and play next round
        parentGame.nextRound(this)
    }

    //Deal player cards
    def dealCardsToPlayers(){
        FirstRound firstRound = new FirstRound(this)
        firstRound.dealCards()
    }

    //Deal flop
    def dealFlop(){
        BettingRound flopRound = new FlopRound(this)
        flopRound.dealCards()

        println "================================"
    }

    //Deal river parentGame card
    def dealTurnCard(){
        TurnCardRound turnCardRound = new TurnCardRound(this)
        turnCardRound.dealCards()
    }

    //Deal final parentGame card
    def dealRiverCard(){
        RiverCardRound riverCardRound = new RiverCardRound(this)
        riverCardRound.dealCards()
    }

    def detectWinners(){
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
    }

}
