package giniapps.blackjack.model

import java.lang.StringBuilder

class Hand {
    private var cards: MutableSet<Card> = mutableSetOf()
    fun addCard(card: Card) {
        cards.add(card)
    }

    fun getValue(): Int {
        var sum = cards.fold(0) { acc, card -> acc + card.getValue() }
        val aceList = cards.filter { isAce(it) }

        var aceCounter = aceList.size
        while (aceCounter > 0 && sum > 21) {
            sum -= 10//Ace=1
            aceCounter--
        }

        return sum
    }

    fun prettyString(): String {
        val str = StringBuilder()
        cards.forEach { card -> str.append("${card.prettyString()}\n") }

        return str.toString()

    }

    fun getCards(): MutableSet<Card> = cards

    private fun isAce(card: Card): Boolean = card.rank == "A"

}