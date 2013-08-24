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
    List<Card> initialCards = []

    // First 2 plus all game cards (up to 7)
    List<Card> allCards = []
    List<HandResult> handResults
    HandResult bestHandResult

    def Player(String name){
        this.name = name
    }

    //Get dealt card
    def receiveCard(Card card){
        initialCards.push(card)
        allCards.push(card)
    }

    // Reference card from the round
    def addGameCards(Card card){
        allCards.add(card)
    }

    def addGameCards(List <Card> cards){
        allCards.addAll(cards)
    }

    def detectHand(){
        handResults = HandDetector.detect(allCards)
        bestHandResult = handResults.last()
    }

    @Override
    String toString(){
       this.name
    }

}
