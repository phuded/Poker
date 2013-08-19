package main.poker.util

import main.poker.card.Card
/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 16/08/13
 * Time: 18:31
 * To change this template use File | Settings | File Templates.
 */
class CardUtil {

    static sortCards(List<Card> cards){
        cards.sort {
            it.cardValue.value
        }
    }
}
