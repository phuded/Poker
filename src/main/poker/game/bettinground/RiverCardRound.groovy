package main.poker.game.bettinground

import main.poker.card.Card
import main.poker.game.round.Round
import main.poker.player.Player

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 26/08/13
 * Time: 21:41
 * To change this template use File | Settings | File Templates.
 */
class RiverCardRound extends BettingRound{

    RiverCardRound(Round round){
        super(round)
    }

    @Override
    def dealCards() {
        // Remove river card from deck and add to round cards
        Card finalCard = parentRound.parentGame.deck.getCard()
        parentRound.roundCards.add(finalCard)

        //Add to each players hand
        parentRound.roundPlayers.each { Player player ->
            //Add river card to player hand
            player.addGameCards(finalCard)
            println "MAIN: " + player.name + " - After river card: " + player.allCards
        }
    }
}
