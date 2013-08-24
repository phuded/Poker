package main.poker.player

import main.poker.card.Card
import main.poker.util.HandDetector

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 13/08/13
 * Time: 18:11
 * To change this template use File | Settings | File Templates.
 */
class Player {
    String name
    // First 2 cards
    List<Card> initialHand = []

    // First 2 plus all game cards (up to 7)
    List<Card> wholeHand = []
    List<HandResult> handResults
    HandResult bestHandResult

    def Player(String name){
        this.name = name
    }

    def addCardToHand(Card card){
        initialHand.push(card)
        wholeHand.push(card)
    }

    def addGameCards(Card card){
        wholeHand.add(card)
    }

    def addGameCards(List <Card> cards){
        wholeHand.addAll(cards)
    }

    def detectHand(){
        handResults = HandDetector.detect(wholeHand)
        bestHandResult = handResults.last()
    }

    @Override
    String toString(){
       this.name
    }

}
