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
        //Sorting cards here
        PokerUtil.sortCards(cards)

        List<HandResult> results = []
        detectSameCard(cards,results)

        //Look for straights
        detectStraight(cards,results)
        //Look for low straights
        detectAceLowStraight(cards,results)

        detectFlush(cards,results)
        detectFullHouse(results)
        detectStraightFlush(results)

        //Get high cards
        if(results.isEmpty()){
           results << new HandResult(HandType.HIGH_CARD,cards[-5..-1])
        }

        //Sort results
        PokerUtil.sortHandResults(results)

        //Get secondary cards
        getSecondaryCards(results.last(),cards)

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

        //Get all pairs
        List<HandResult> pairResults = results.findAll{
            it.handType == HandType.PAIR
        }

        //If more than 1 pair -> two pair
        if(pairResults.size() > 1){
            List<Card> twoPairCards = pairResults.collectMany{it.cards}
            //Limit to best 2 pair
            results << new HandResult(HandType.TWO_PAIR,twoPairCards[-4..-1])
        }
    }

    /*
        TO DO: Support Ace as low in straight
     */
    static detectStraight(List <Card> cards, List<HandResult> results){
        boolean straight = false

        //Number of found cards
        def straightCards = [cards[0]]

        //First card value
        int cardValue = cards[0].cardValue.value

        for (int i = 1; i < cards.size(); i++){
            Card nextCard = cards[i]

            if(nextCard.cardValue.value == (cardValue + 1)){
                //Add next card to list of straight cards
                straightCards << nextCard

                //Straight found
                if(straightCards.size() > 4){
                    straight = true
                }
            }
            else{
                //If straight found and next card not included in straight - finish
                if(straight){
                    break
                }
                //Reset found cards
                straightCards = [nextCard]
            }

            //Get next card value
            cardValue = nextCard.cardValue.value
        }

       if(straight){
           //Add straight
           HandResult straightHandResult =  new HandResult(HandType.STRAIGHT,straightCards[-5..-1])
           //Set secondary cards as all straight cards - for use in straight flush detection
           straightHandResult.secondaryCards = straightCards
           results << straightHandResult
       }
    }

    static detectAceLowStraight(List<Card> cards, List<HandResult> results){
        if(!results.find{it.handType == HandType.STRAIGHT}){
            //Reorder cards
            PokerUtil.convertAce(cards,true)
            //Look for straights
            detectStraight(cards,results)
            //Reorder cards back
            PokerUtil.convertAce(cards,false)
        }
    }

    static detectFlush(List <Card> cards,List<HandResult> results){

        for(Card card : cards){
            Suit currentCardSuit = card.suit

            List<Card> foundCards = cards.findAll{
                it.suit == currentCardSuit
            }

            if(foundCards.size() > 4){
                results << new HandResult(HandType.FLUSH, foundCards[-5..-1])
                break
            }
        }
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
        //Check if have a straight and flush to begin with
        HandResult flush = results.find{it.handType == HandType.FLUSH}
        HandResult straight = results.find{it.handType == HandType.STRAIGHT}

        if(flush && straight){
            boolean isStraightFlush = false
            Suit flushSuit = flush.cards.last().suit
            List<Card> straightFlushCards = []

            //Loop through ALL straight cards (not just best 5)
            straight.secondaryCards.each{ Card card ->
                //If they have the flush suit - add
                if(card.suit == flushSuit){
                    straightFlushCards << card

                    //Have a straight flush
                    if(straightFlushCards.size() > 4){
                        isStraightFlush = true
                    }
                }
                else{
                    //If no straight flush -> reset
                    if(!isStraightFlush){
                        straightFlushCards = []
                    }

                }
            }

            if(isStraightFlush){
                //Get best 5 straight flush cards
                straightFlushCards = straightFlushCards[-5..-1]
                results << new HandResult(HandType.STRAIGHT_FLUSH,straightFlushCards)

                //Check for royal flush
                if(straightFlushCards.last().cardValue == CardValue.ACE){
                    results << new HandResult(HandType.ROYAL_FLUSH, straightFlushCards)
                }
            }
        }
    }

    static getSecondaryCards(HandResult bestResult, List<Card> cards){
        int cardsToFill = 5 - bestResult.cards.size()
        if(cardsToFill > 0){
            //Get best remaining cards
            List <Card> bestRemainingCards = cards.findAll{
                !bestResult.cards.contains(it)
            }
            //Add as secondary cards
            bestResult.secondaryCards.addAll(bestRemainingCards[-cardsToFill..-1])
        }
    }

}
