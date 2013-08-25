package test.poker.util

import main.poker.card.Card
import main.poker.card.CardValue
import main.poker.card.Suit
import main.poker.player.Player
import main.poker.util.RoundWinnerDetector

/**
 * Created with IntelliJ IDEA.
 * User: matt
 * Date: 25/08/2013
 * Time: 12:39
 * To change this template use File | Settings | File Templates.
 */
class RoundWinnerDetectorTest extends GroovyTestCase {

    void testSamePairsWithSameKickers() {

        List<Card> gameCards = []
        gameCards << new Card(CardValue.TWO,Suit.HEARTS)
        gameCards << new Card(CardValue.TWO,Suit.DIAMONDS)
        gameCards << new Card(CardValue.ACE,Suit.HEARTS)
        gameCards << new Card(CardValue.KING,Suit.DIAMONDS)
        gameCards << new Card(CardValue.QUEEN,Suit.DIAMONDS)

        Player player1 = new Player("Player 1")
        player1.receiveCard(new Card(CardValue.FIVE,Suit.CLUBS))
        player1.receiveCard(new Card(CardValue.THREE,Suit.SPADES))

        player1.addGameCards(gameCards)

        Player player2 = new Player("Player 2")
        player2.receiveCard(new Card(CardValue.SIX,Suit.CLUBS))
        player2.receiveCard(new Card(CardValue.EIGHT,Suit.SPADES))

        player2.addGameCards(gameCards)

        List<Player> players = [player1,player2]

        players.each{ Player player ->
            player.detectHand()
            println "testSamePairsWithSameKickers: "+ player.name + " - Best hand-result: " + player.bestHand
        }

        List <Player> winners = RoundWinnerDetector.detectWinners(players)
        println "testSamePairsWithSameKickers: Winners: " + winners

        assert winners.size() == 2
        println "================================"
    }

    void testSamePairsWithDifferentKickers() {

        List<Card> gameCards = []
        gameCards << new Card(CardValue.TWO,Suit.HEARTS)
        gameCards << new Card(CardValue.TWO,Suit.DIAMONDS)
        gameCards << new Card(CardValue.TEN,Suit.HEARTS)
        gameCards << new Card(CardValue.KING,Suit.DIAMONDS)
        gameCards << new Card(CardValue.QUEEN,Suit.DIAMONDS)

        Player player1 = new Player("Player 1")
        player1.receiveCard(new Card(CardValue.ACE,Suit.CLUBS))
        player1.receiveCard(new Card(CardValue.THREE,Suit.SPADES))

        player1.addGameCards(gameCards)

        Player player2 = new Player("Player 2")
        player2.receiveCard(new Card(CardValue.SIX,Suit.CLUBS))
        player2.receiveCard(new Card(CardValue.EIGHT,Suit.SPADES))

        player2.addGameCards(gameCards)

        List<Player> players = [player1,player2]

        players.each{ Player player ->
            player.detectHand()
            println "testSamePairsWithDifferentKickers: "+ player.name + " - Best hand-result: " + player.bestHand
        }

        List <Player> winners = RoundWinnerDetector.detectWinners(players)
        println "testSamePairsWithDifferentKickers: Winners: " + winners

        assert winners.size() == 1 && winners.first().name == "Player 1"
        println "================================"
    }

    void testDifferentThreesInFullHouse() {

        List<Card> gameCards = []
        gameCards << new Card(CardValue.TWO,Suit.HEARTS)
        gameCards << new Card(CardValue.TWO,Suit.DIAMONDS)
        gameCards << new Card(CardValue.SIX,Suit.HEARTS)
        gameCards << new Card(CardValue.FIVE,Suit.DIAMONDS)
        gameCards << new Card(CardValue.FOUR,Suit.DIAMONDS)

        Player player1 = new Player("Player 1")
        player1.receiveCard(new Card(CardValue.FOUR,Suit.CLUBS))
        player1.receiveCard(new Card(CardValue.FOUR,Suit.SPADES))

        player1.addGameCards(gameCards)

        Player player2 = new Player("Player 2")
        player2.receiveCard(new Card(CardValue.SIX,Suit.CLUBS))
        player2.receiveCard(new Card(CardValue.SIX,Suit.SPADES))

        player2.addGameCards(gameCards)

        List<Player> players = [player1,player2]

        players.each{ Player player ->
            player.detectHand()
            println "testDifferentThreesInFullHouse: "+ player.name + " - Best hand-result: " + player.bestHand
        }

        List <Player> winners = RoundWinnerDetector.detectWinners(players)
        println "testDifferentThreesInFullHouse: Winners: " + winners

        assert winners.size() == 1 && winners.first().name == "Player 2"
        println "================================"
    }

    void testDifferentThreesInFullHouse2() {

        List<Card> gameCards = []
        gameCards << new Card(CardValue.TWO,Suit.HEARTS)
        gameCards << new Card(CardValue.TWO,Suit.DIAMONDS)
        gameCards << new Card(CardValue.TWO,Suit.HEARTS)
        gameCards << new Card(CardValue.FIVE,Suit.DIAMONDS)
        gameCards << new Card(CardValue.FIVE,Suit.DIAMONDS)

        Player player1 = new Player("Player 1")
        player1.receiveCard(new Card(CardValue.FIVE,Suit.CLUBS))
        player1.receiveCard(new Card(CardValue.FOUR,Suit.SPADES))

        player1.addGameCards(gameCards)

        Player player2 = new Player("Player 2")
        player2.receiveCard(new Card(CardValue.SEVEN,Suit.CLUBS))
        player2.receiveCard(new Card(CardValue.SIX,Suit.SPADES))

        player2.addGameCards(gameCards)

        List<Player> players = [player1,player2]

        players.each{ Player player ->
            player.detectHand()
            println "testDifferentThreesInFullHouse2: "+ player.name + " - Best hand-result: " + player.bestHand
        }

        List <Player> winners = RoundWinnerDetector.detectWinners(players)
        println "testDifferentThreesInFullHouse2: Winners: " + winners

        assert winners.size() == 1 && winners.first().name == "Player 1"
        println "================================"
    }

    void testDifferentPairsInFullHouse() {

        List<Card> gameCards = []
        gameCards << new Card(CardValue.TWO,Suit.HEARTS)
        gameCards << new Card(CardValue.TWO,Suit.DIAMONDS)
        gameCards << new Card(CardValue.TWO,Suit.HEARTS)
        gameCards << new Card(CardValue.FIVE,Suit.DIAMONDS)
        gameCards << new Card(CardValue.SIX,Suit.DIAMONDS)

        Player player1 = new Player("Player 1")
        player1.receiveCard(new Card(CardValue.SIX,Suit.CLUBS))
        player1.receiveCard(new Card(CardValue.FOUR,Suit.SPADES))

        player1.addGameCards(gameCards)

        Player player2 = new Player("Player 2")
        player2.receiveCard(new Card(CardValue.FIVE,Suit.CLUBS))
        player2.receiveCard(new Card(CardValue.NINE,Suit.SPADES))

        player2.addGameCards(gameCards)

        List<Player> players = [player1,player2]

        players.each{ Player player ->
            player.detectHand()
            println "testDifferentPairsInFullHouse: "+ player.name + " - Best hand-result: " + player.bestHand
        }

        List <Player> winners = RoundWinnerDetector.detectWinners(players)
        println "testDifferentPairsInFullHouse: Winners: " + winners

        assert winners.size() == 1 && winners.first().name == "Player 1"
        println "================================"
    }

}
