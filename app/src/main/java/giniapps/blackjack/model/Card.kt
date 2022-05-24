package giniapps.blackjack.model

data class Card(val rank: String, val suit: String) {
    fun getValue(): Int {
        return when (rank) {
            "A" -> 11
            "J", "Q", "K" -> 10
            else -> rank.toInt()
        }

    }

    fun prettyString(): String = "[$rank,$suit]"

}


