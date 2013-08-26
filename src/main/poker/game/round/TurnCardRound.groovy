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
class TurnCardRound extends BettingRound{

    def TurnCardRound(Round round){
        parentRound = round
    }

    @Override
    def dealCards() {
        Game parentGame = parentRound.parentGame

        // Remove turn card from deck and add to round cards
        Card turnCard = parentGame.deck.getCard()
        parentRound.roundCards.add(turnCard)

        //Add to each players hand
        parentGame.players.each { Player player ->
            //Add river card to player hand
            player.addGameCards(turnCard)
            // println "MAIN: Player: " + player.name + " hand after turn card: " + player.allCards
        }
    }
}
