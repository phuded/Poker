package main.poker.game.round

import main.poker.card.Card
import main.poker.game.Game
import main.poker.game.Round
import main.poker.player.Player

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 26/08/13
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
class RiverCardRound extends BettingRound{

    def RiverCardRound(Round round){
        parentRound = round
    }

    @Override
    def dealCards() {
        Game parentGame = parentRound.parentGame

        // Remove river card from deck and add to round cards
        Card finalCard = parentGame.deck.getCard()
        parentRound.roundCards.add(finalCard)

        //Add to each players hand
        parentGame.players.each { Player player ->
            //Add river card to player hand
            player.addGameCards(finalCard)
            println "MAIN: " + player.name + " - After river card: " + player.allCards
        }
    }
}
