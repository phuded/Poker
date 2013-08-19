package main.poker.player

import main.poker.card.Card
import main.poker.util.HandDetector
import main.poker.util.CardUtil

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 13/08/13
 * Time: 18:11
 * To change this template use File | Settings | File Templates.
 */
class Player {
    String name
    List<Card> hand = []
    List<Card> wholeHand = []
    List<HandResult> handResults

    def addCardToHand(Card card){
        hand.push(card)
        wholeHand.push(card)
    }

    def addGameCards(List <Card> cards){
        wholeHand.addAll(cards)

        // SORTING CARDS HERE???
        CardUtil.sortCards(wholeHand)
    }

    def detectHand(){
        handResults = HandDetector.detect(wholeHand)
    }
}
