package main.poker.game.round

import main.poker.game.Game
import main.poker.game.Round
import main.poker.player.Player

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 26/08/13
 * Time: 21:33
 * To change this template use File | Settings | File Templates.
 */
class FirstRound extends BettingRound{

    def FirstRound(Round round){
        parentRound = round
    }

    @Override
    def dealCards() {
        Game parentGame = parentRound.parentGame

        //Deal 2 cards to each player
        parentGame.players.each { Player player ->
            //Player gets two cards
            dealInitialCardsToPlayer(player)
        }
    }

    // Deal 1st two cards to player
    def dealInitialCardsToPlayer(Player player){
        player.receiveCard(parentRound.parentGame.deck.getCard())
        player.receiveCard(parentRound.parentGame.deck.getCard())
    }
}
