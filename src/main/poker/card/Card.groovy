package main.poker.card

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 12/08/13
 * Time: 15:18
 * To change this template use File | Settings | File Templates.
 */
class Card {

    Suit suit;
    CardValue cardValue;

    def Card(CardValue cardValue,Suit suit){
        this.cardValue = cardValue
        this.suit = suit
    }

    String toString(){
        cardValue.name + "-" + suit.name
    }
}
