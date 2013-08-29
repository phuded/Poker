package main.poker.game.round

import main.poker.card.Card
import main.poker.card.Deck
import main.poker.game.Game
import main.poker.game.bettinground.BettingRound
import main.poker.game.bettinground.FirstRound
import main.poker.game.bettinground.FlopRound
import main.poker.game.bettinground.RiverCardRound
import main.poker.game.bettinground.TurnCardRound
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

    //Players in round
    List<Player> roundPlayers

    //Winners
    List<Player> winners

    //Pot
    int pot

    def Round(Game game){
        //Link to parent parentGame
        parentGame = game

        //Create/replace deck and shuffle
        parentGame.deck = new Deck()
        parentGame.deck.shuffle()

        //Add players to round
        roundPlayers = []
        roundPlayers.addAll(game.players)

        //Prepare round cards
        roundCards = []
    }

    def playRound(){

        println "================================"
        println "MAIN: New Round - " + (parentGame.rounds.size() + 1)
        println "================================"

        //Initial deals and betting
        dealCardsToPlayers()

        //Add other cards
        dealFlop()

        //Turn Card
        dealTurnCard()

        //River card
        dealRiverCard()

        //Detect winners
        detectWinners()

        //TO DO - Add winnings to winners!

        //Finish and play next round
        parentGame.nextRound(this)
    }

    //Deal player cards
    def dealCardsToPlayers(){
        FirstRound firstRound = new FirstRound(this)
        firstRound.dealCards()
        firstRound.beginBetting()

        //Get pot
        pot += firstRound.getPot()
        println "POT:" + pot

        //Reset
        roundPlayers*.resetBetweenBettingRounds()
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
            //TO DO: Check if folded - should be removed?
            //if(!player.hasFolded) {
                player.detectHand()
                // println "MAIN: "+ player.name + " - All hand-results: " + player.hands
                println "MAIN: "+ player.name + " - Best hand: " + player.bestHand
          //  }
        }

        println "================================"

        //Get winner
        winners = RoundWinnerDetector.detectWinners(parentGame.players)

        println "MAIN: Winners: " + winners
    }

}
