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

    String toString(){
        cardValue.name + "-" + suit.name
    }
}
