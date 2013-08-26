package main.poker.game.round

import main.poker.card.Card
import main.poker.game.Game
import main.poker.game.Round
import main.poker.player.Player

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 26/08/13
 * Time: 21:23
 * To change this template use File | Settings | File Templates.
 */
class FlopRound extends BettingRound{

    def FlopRound(Round round){
        parentRound = round
    }

    @Override
    def dealCards() {
        Game parentGame = parentRound.parentGame

        //Remove flop from deck and add the round cards
        List<Card> flop = parentGame.deck.getFlop()
        parentRound.roundCards.addAll(flop)

        //Add flop to each player's hands
        parentGame.players.each { Player player ->
            //Add flop to player hand
            player.addGameCards(flop)

            println "MAIN: " + player.name + " - Hand after flop: " + player.allCards
        }
    }
}
