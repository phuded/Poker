package main.poker.game.round

import main.poker.game.Round

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 26/08/13
 * Time: 21:21
 * To change this template use File | Settings | File Templates.
 */
abstract class BettingRound {

    Round parentRound

    abstract dealCards()
}
