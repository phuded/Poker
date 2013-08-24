package main.poker.player

import main.poker.card.Card
import main.poker.hand.HandType

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 14/08/13
 * Time: 16:15
 * To change this template use File | Settings | File Templates.
 */
class HandResult {
    HandType handType
    List<Card> cards
    //Cards not included in hand
    List<Card> secondaryCards

    def HandResult(HandType handType, List<Card> cards){
        this.handType = handType
        this.cards = cards
        this.secondaryCards = []
    }

    @Override
    String toString(){
        def result = handType.name + " - " + cards + "("+secondaryCards+")"
    }

    @Override
    boolean equals(HandResult nextHandResult){
        if(this.handType == nextHandResult.handType && this.cards == nextHandResult.cards){
            return true
        }
        return false
    }

    @Override
    int hashCode(){
       return (this.handType.hashCode() + this.cards.hashCode())
    }
}
