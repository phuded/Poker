package test.poker.util

import main.poker.card.Card
import main.poker.card.CardValue
import main.poker.card.Suit
import main.poker.util.HandDetector
import main.poker.hand.HandType

class HandDetectorTest extends GroovyTestCase {

    void testTwoPair() {

        def cards = []
        cards << new Card(cardValue: CardValue.TWO,suit: Suit.HEARTS)
        cards << new Card(cardValue: CardValue.THREE,suit: Suit.DIAMONDS)
        cards << new Card(cardValue: CardValue.TWO,suit: Suit.HEARTS)
        cards << new Card(cardValue: CardValue.THREE,suit: Suit.SPADES)
        cards << new Card(cardValue: CardValue.ACE,suit: Suit.CLUBS)

        println "testTwoPair cards: " + cards

        def results = HandDetector.detect(cards)

        println "testTwoPair results: " + results

        assert results.size() == 3 && results.findAll{it.handType == HandType.TWO_PAIR}.size()==1
    }

    void testBestTwoPair() {

        def cards = []
        cards << new Card(cardValue: CardValue.TWO,suit: Suit.HEARTS)
        cards << new Card(cardValue: CardValue.THREE,suit: Suit.DIAMONDS)
        cards << new Card(cardValue: CardValue.TWO,suit: Suit.HEARTS)
        cards << new Card(cardValue: CardValue.THREE,suit: Suit.SPADES)
        cards << new Card(cardValue: CardValue.ACE,suit: Suit.CLUBS)
        cards << new Card(cardValue: CardValue.ACE,suit: Suit.HEARTS)

        println "testBestTwoPair cards: " + cards

        def results = HandDetector.detect(cards)

        println "testBestTwoPair results: " + results

        assert results.size() == 4 && results.findAll{it.handType == HandType.TWO_PAIR}.size()==1
    }

    void testStraight() {

        def cards = []
        cards << new Card(cardValue: CardValue.TEN,suit: Suit.HEARTS)
        cards << new Card(cardValue: CardValue.JACK,suit: Suit.CLUBS)
        cards << new Card(cardValue: CardValue.TWO,suit: Suit.HEARTS)
        cards << new Card(cardValue: CardValue.QUEEN,suit: Suit.HEARTS)
        cards << new Card(cardValue: CardValue.KING,suit: Suit.DIAMONDS)
        cards << new Card(cardValue: CardValue.ACE,suit: Suit.DIAMONDS)
        cards << new Card(cardValue: CardValue.NINE,suit: Suit.HEARTS)

        println "testStraight cards: " + cards

        def results = HandDetector.detect(cards)

        println "testStraight results: " + results

        assert results.size() == 1 && results[0].handType == HandType.STRAIGHT
    }

    void testLowStraight() {

        def cards = []
        cards << new Card(cardValue: CardValue.ACE,suit: Suit.HEARTS)
        cards << new Card(cardValue: CardValue.TWO,suit: Suit.HEARTS)
        cards << new Card(cardValue: CardValue.KING,suit: Suit.CLUBS)
        cards << new Card(cardValue: CardValue.THREE,suit: Suit.HEARTS)
        cards << new Card(cardValue: CardValue.FOUR,suit: Suit.HEARTS)
        cards << new Card(cardValue: CardValue.FIVE,suit: Suit.DIAMONDS)
        cards << new Card(cardValue: CardValue.SEVEN,suit: Suit.DIAMONDS)

        println "testLowStraight cards: " + cards

        def results = HandDetector.detect(cards)

        println "testLowStraight results: " + results

        assert results.size() == 1 && results[0].handType == HandType.STRAIGHT
    }

    void testFlush() {

      def cards = []
      cards << new Card(cardValue: CardValue.EIGHT,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.THREE,suit: Suit.SPADES)
      cards << new Card(cardValue: CardValue.JACK,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.QUEEN,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.KING,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.TWO,suit: Suit.DIAMONDS)
      cards << new Card(cardValue: CardValue.ACE,suit: Suit.HEARTS)

      println "testFlush cards: " + cards

      def results = HandDetector.detect(cards)

      println "testFlush results: " + results

      assert results.size() == 1
    }

    void testFullHouse() {

      def cards = []
      cards << new Card(cardValue: CardValue.TWO,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.TWO,suit: Suit.DIAMONDS)
      cards << new Card(cardValue: CardValue.TWO,suit: Suit.SPADES)
      cards << new Card(cardValue: CardValue.THREE,suit: Suit.CLUBS)
      cards << new Card(cardValue: CardValue.FOUR,suit: Suit.SPADES)
      cards << new Card(cardValue: CardValue.FOUR,suit: Suit.CLUBS)
      cards << new Card(cardValue: CardValue.FOUR,suit: Suit.HEARTS)

      println "testFullHouse cards: " + cards

      def results = HandDetector.detect(cards)

      println "testFullHouse results: " + results

      assert results.size() == 3 && results.findAll{it.handType == HandType.FULLHOUSE}.size()==1
    }

    void testStraightFlush() {

      def cards = []
      cards << new Card(cardValue: CardValue.THREE,suit: Suit.DIAMONDS)
      cards << new Card(cardValue: CardValue.FOUR,suit: Suit.SPADES)
      cards << new Card(cardValue: CardValue.FIVE,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.SIX,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.SEVEN,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.EIGHT,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.NINE,suit: Suit.HEARTS)

      println "testStraightFlush cards: " + cards

      def results = HandDetector.detect(cards)

      println "testStraightFlush results: " + results

      assert results.size() == 3 && results.last().handType == HandType.STRAIGHT_FLUSH
    }

    void testRoyalFlush() {

      def cards = []
      cards << new Card(cardValue: CardValue.TWO,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.THREE,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.TEN,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.JACK,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.QUEEN,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.KING,suit: Suit.HEARTS)
      cards << new Card(cardValue: CardValue.ACE,suit: Suit.HEARTS)

      println "testRoyalFlush cards: " + cards

      def results = HandDetector.detect(cards)

      println "testRoyalFlush results: " + results

      assert results.size() == 4 && results.last().handType == HandType.ROYAL_FLUSH
    }
}