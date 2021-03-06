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

    def play(){

        println "================================"
        println "MAIN: New Round - " + (parentGame.rounds.size() + 1)
        println "================================"

        //Player betting rounds
        playBettingRounds()

        //Detect winners
        detectWinners()

        //TO DO - Add winnings to winners!

        //Finish and play next round
        parentGame.nextRound(this)
    }

     def playBettingRounds(){
         List<BettingRound> bettingRounds = [new FirstRound(this),new FlopRound(this),new TurnCardRound(this), new RiverCardRound(this)]

         for(int roundNum = 0; roundNum< bettingRounds.size(); roundNum++){
             BettingRound currentRound = bettingRounds[roundNum]
             currentRound.dealCards()
             currentRound.beginBetting()

             //Finish round - and check if only 1 player remains
             if(completeBettingRound(currentRound)){
                 //Finish whole round
                 break
             }
         }
     }

    //Complete the betting round
    boolean completeBettingRound(BettingRound round){
        int bettingRoundPot = round.getPot()
        pot += bettingRoundPot

        println "Betting round pot: " + bettingRoundPot + ". Total: " + pot

        //Reset ALL players after betting round
        parentGame.players*.resetBetweenBettingRounds()

        return (roundPlayers.size()>1)?false:true
    }

    //Detect the round winner(s)
    def detectWinners(){

        if(roundPlayers.size() > 1){
            println "================================"

            //Detect hands...
            roundPlayers.each{ Player player ->
                player.detectHand()
                // println "MAIN: "+ player.name + " - All hand-results: " + player.hands
                println "MAIN: "+ player.name + " - Best hand: " + player.bestHand

            }

            println "================================"

            //Get winner
            winners = RoundWinnerDetector.detectWinners(roundPlayers)
        }
        else{
            winners =  [roundPlayers.first()]
        }

        println "MAIN: Winners: " + winners
    }

}
