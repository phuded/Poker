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
    List<Player> players
    List<Round> rounds

    //TEMP -> TO REMOVE
    int tempRoundLimit = 2;

    Game(List<String> playerNames, int startingPlayerFunds){
        this.players = []
        this.rounds = []

        //Create players
        createPlayers(playerNames,startingPlayerFunds)
    }

    def createPlayers(List<String> playerNames,int startingPlayerFunds){
       for (String name: playerNames){
           players << new Player(name,startingPlayerFunds)
       }
    }

    def startGame(){
        println "MAIN: Starting new game with: "+ players

        Round round = new Round(this)
        round.playRound()
    }

    // Player next round
    def nextRound(Round finishedRound){

        rounds << finishedRound

        if(rounds.size() < tempRoundLimit){
            //Reset players cards and hands
            players*.reset()

            //New round and play
            Round round = new Round(this)
            round.playRound()
        }
    }
}
