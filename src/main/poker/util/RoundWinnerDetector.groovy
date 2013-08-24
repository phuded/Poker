package main.poker.util

import main.poker.card.Card
import main.poker.player.Player
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
            getBetterHand(winningPlayers)
        }

    }


    static getBetterHand(List<Player> winningPlayers){
        println "MAIN: Winner: " + isBetterHand(winningPlayers[0],winningPlayers[1])

    }


    static isBetterHand(Player playerA, Player playerB){

        int numberOfCards = playerA.bestHand.cards.size()

        int cardComparison = compareCards(playerA.bestHand.cards,playerB.bestHand.cards)

        if(cardComparison == 1){
            return playerA
        }
        else if(cardComparison == 2){
            return playerB
        }
        else{
            if(numberOfCards < 5){
                int secondaryCardComparison = compareCards(playerA.bestHand.secondaryCards,playerB.bestHand.secondaryCards)

                if(secondaryCardComparison == 1){
                    return playerA
                }
                else if(secondaryCardComparison == 2){
                    return playerB
                }
                else{
                    return "DRAW"
                }
            }
            else{
                return "DRAW"
            }
        }
    }

    static compareCards(List<Card> cardsA, List<Card> cardsB){
        int numberOfCards = cardsA.size()
        int lastCardIndex = numberOfCards -1

        for(lastCardIndex;lastCardIndex>=0;lastCardIndex--){
            Card cardA = cardsA[lastCardIndex]
            Card cardB = cardsB[lastCardIndex]

            if(cardA.cardValue.value>cardB.cardValue.value){
                //println "SEE - " +cardA.cardValue + " is better than " + cardB.cardValue
                return 1
            }
            else if(cardB.cardValue.value > cardA.cardValue.value){
                //println "SEE - " +cardB.cardValue + " is better than " + cardA.cardValue
                return 2
            }
        }

        return 0
    }


}
