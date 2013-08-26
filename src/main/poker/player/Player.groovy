package main.poker.player

import main.poker.card.Card
import main.poker.hand.Hand
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

    // First 2 plus all parentGame cards (up to 7)
    List<Card> allCards = []
    List<Hand> hands
    Hand bestHand

    //Funds
    int funds

    def Player(String name, int startingFunds){
        this.name = name
        this.funds = startingFunds
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
        hands = HandDetector.detect(allCards)
        bestHand = hands.last()
    }

    @Override
    String toString(){
       this.name
    }

    def reset(){
        this.initialCards = []
        this.allCards = []
        this.hands = null
        this.bestHand = null
    }

}
