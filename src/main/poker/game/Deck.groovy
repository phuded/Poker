package main.poker.game

import main.poker.card.Card
import main.poker.card.CardValue
import main.poker.card.Suit

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 13/08/13
 * Time: 18:00
 * To change this template use File | Settings | File Templates.
 */
class Deck {
    def cards = []

    def Deck(){
        makeDeck()
    }

    Card dealCard(){
        return cards.pop()
    }

    def dealFlop(){
        def flop = []
        flop << cards.pop()
        flop << cards.pop()
        flop << cards.pop()

        return flop
    }

    def makeDeck(){
        for(Suit suit: Suit){
            for(CardValue cardValue: CardValue){
                cards << new Card(cardValue,suit)
            }
        }
    }

    def shuffle(){
        Collections.shuffle(cards)
    }

    int getRemainingCards(){
       cards.size()
    }
}
