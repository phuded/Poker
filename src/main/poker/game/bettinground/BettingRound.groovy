package main.poker.game.bettinground

import main.poker.game.round.Round
import main.poker.player.Player

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 26/08/13
 * Time: 21:21
 * To change this template use File | Settings | File Templates.
 */
abstract class BettingRound {

    Round parentRound
    def currentBet
    boolean firstCycle

    abstract dealCards()

    def beginBetting(){

        //Do this while all the players are not check or not folded
        while(checkPlayers(parentRound.roundPlayers)){

            parentRound.roundPlayers.each{ Player player ->

                //Must be first
                if(currentBet == 0){
                    print player.name + " - Place Bet: "
                    BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
                    int betAmount = br.readLine().toInteger()

                    //Make bet and set new current bet
                    player.makeBet(betAmount.toInteger())
                    currentBet += betAmount.toInteger()
                }
                //Next player
                else{

                    if(currentBet == player.amountBet){
                        //Nothing
                    }
                    else{
                        //Give player options..

                        print player.name + " - fold (f), call (c) or specify amount to raise (Current bet: " + currentBet + "):"
                        BufferedReader br = new BufferedReader(new InputStreamReader(System.in))
                        def betAmount = br.readLine()

                        if(betAmount == "f"){
                            player.hasFolded = true
                        }
                        else if(betAmount == "c"){
                            player.makeBet(currentBet)
                        }
                        else{
                            //Add raise to current bet
                            currentBet += betAmount.toInteger()
                            //Player bets difference
                            player.makeBet(currentBet)

                        }
                    }

                }

            }

        }
    }

    // TO DO - REMOVE PLAYERS IF FOLDED
    def checkPlayers(List<Player> players){
        if(firstCycle){
            firstCycle = false
            return true
        }

        // Do not keep betting
        boolean continueBetting = false
        players.each{ Player player ->
            if(!player.hasFolded && player.amountBet != currentBet){
                //Must continue betting
                continueBetting = true
            }
        }

        return continueBetting
    }



    def getPot(){
        int roundPot = 0
        parentRound.roundPlayers.each{Player player ->
            roundPot += player.amountBet
        }

        return roundPot
    }
}
