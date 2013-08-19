package main.poker.util

import main.poker.card.Card
import main.poker.card.CardValue
import main.poker.card.Suit
import main.poker.hand.HandType
import main.poker.player.HandResult

/**
 * Created with IntelliJ IDEA.
 * User: matthew.carter
 * Date: 14/08/13
 * Time: 15:44
 * To change this template use File | Settings | File Templates.
 */
class HandDetector {

    static detect (List<Card> cards){

        //Sorting cards here?
        CardUtil.sortCards(cards)

        def results = []
        detectSameCard(cards,results)
        detectStraight(cards,results)
        detectFlush(cards,results)
        detectOthers(results)
        return results
    }

    static detectSameCard(List<Card> cards,List<HandResult> results){
        cards.each {
            CardValue currentCardValue = it.cardValue

            List<Card> foundCards = cards.findAll{
                it.cardValue == currentCardValue
            }

            switch (foundCards.size()){
                case 2: results << new HandResult(HandType.PAIR,foundCards)
                        break
                case 3: results << new HandResult(HandType.THREE_OF_A_KIND,foundCards)
                        break
                case 4: results << new HandResult(HandType.FOUR_OF_A_KIND,foundCards)
                        break
            }
        }

        //Get unique results since above logic will create multiple entries for each unique hand
        results = results.unique()

        List<HandResult> pairResults = results.findAll{
            it.handType == HandType.PAIR
        }

        if(pairResults.size() == 2){
            //TO DO: Decide if should remove
            //  results.removeAll(twoPairResults)
            results << new HandResult(HandType.TWO_PAIR,pairResults.collectMany{it.cards})
        }
    }

    static detectStraight(List <Card> cards, List<HandResult> results){
        boolean straight = false

        //Number of found cards
        def straightCards = [cards[0]]

        //First card value
        int cardValue = cards[0].cardValue.value

        for (int i = 1; i < cards.size(); i++){
            Card nextCard = cards[i]

            if(nextCard.cardValue.value == (cardValue + 1)){
                cardValue = nextCard.cardValue.value
                straightCards << nextCard

                if(straightCards.size() > 4){
                    straight = true
                }
            }
            else{
                straightCards = [nextCard]
                cardValue = nextCard.cardValue.value
            }
        }

        if(straight){
            int straightCardSize = straightCards.size()-1
            results << new HandResult(HandType.STRAIGHT,straightCards[(straightCardSize-4)..straightCardSize])
        }
    }

    static detectFlush(List <Card> cards,List<HandResult> results){

        for(Card card : cards){
            Suit currentCardSuit = card.suit

            List<Card> foundCards = cards.findAll{
                it.suit == currentCardSuit
            }

            if(foundCards.size() > 4){
                int lastCardIndex = foundCards.size() -1
                results << new HandResult(HandType.FLUSH, foundCards[(lastCardIndex-4)..lastCardIndex])
                break
            }
        }
    }


    /**
     * Look for full house & Straight Flush
     */
    static detectOthers(List<HandResult> results){
        detectFullHouse(results)
        detectStraightFlush(results)
    }

    static detectFullHouse(List<HandResult> results){
        List <HandResult> threes = results.findAll{
            it.handType == HandType.THREE_OF_A_KIND
        }

        List <HandResult> pairs = results.findAll{
            it.handType == HandType.PAIR
        }

        HandResult highestThree =  threes.isEmpty()?null:threes.last()
        HandResult highestPair = null

        //If no pairs are present
        if(pairs.isEmpty()){
              //If there are more than one 3 of a kinds
              if(threes.size() > 1){
                  //Use first one (lower valued)
                  highestPair = threes.first()
              }
        }
        else{
            highestPair = pairs.last()
        }

        if(highestThree && highestPair){
            def fullHouseCards = []
            fullHouseCards.addAll(highestPair.cards[0..1])
            fullHouseCards.addAll(highestThree.cards)
            results << new HandResult(HandType.FULLHOUSE,fullHouseCards)
        }
    }

    static detectStraightFlush(List<HandResult> results){

        HandResult flush = results.find{it.handType == HandType.FLUSH}
        HandResult straight = results.find{it.handType == HandType.STRAIGHT}

        if(flush && straight){
           if(flush.cards == straight.cards){
               results << new HandResult(HandType.STRAIGHT_FLUSH,straight.cards)

               if(straight.cards.last().cardValue == CardValue.ACE){
                   results << new HandResult(HandType.ROYAL_FLUSH, straight.cards)
               }
           }
        }
    }

}
