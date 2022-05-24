package giniapps.blackjack.model

class Deck {
    private var cards: MutableList<Card> = mutableListOf()

    init {
        val ranks = listOf("A", "2", "3", "4", "5", "6", "7", "8", "9", "10", "J", "Q", "K")
        val suits = listOf("♥️", "♦️", "♠️", "♣️")

        for (rank in ranks) {
            for (suit in suits) {
                cards.add(Card(rank, suit))

            }
        }
    }

    fun shuffle() = cards.shuffle()

    fun dealCard(): Card {
        return cards.removeFirst()
    }
}