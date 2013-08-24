package main.poker.util

import main.poker.player.Player
import main.poker.hand.Hand
import main.poker.hand.HandType

/**
 * Created with IntelliJ IDEA.
 * User: matt
 * Date: 24/08/2013
 * Time: 11:49
 * To change this template use File | Settings | File Templates.
 */
class RoundWinnerDetector {

    static detectWinner(List<Player> players){
        //Find best hand type
        HandType bestHandType = players.max{it.bestHand.handType}.bestHand.handType

        println "MAIN: Best hand: " + bestHandType

        //Get all players with that hand type
        List<Player> winningPlayers = players.findAll{it.bestHand.handType == bestHandType}

        println "MAIN: Winning Players: " + winningPlayers

        // If one player -> theya are the winner
        if(winningPlayers.size() == 1){
            return [winner: winningPlayers[0], isDraw:false]
        }
        else{
            //Complex LOGIC - TO DO
        }

    }
}
