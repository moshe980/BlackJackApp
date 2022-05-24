package giniapps.blackjack.controller

import giniapps.blackjack.model.GameStateEnum
import giniapps.blackjack.model.Deck
import giniapps.blackjack.model.Hand

class Game {
    private var myDeck: Deck = Deck()
    private var playerHand: Hand = Hand()
    private var dealerHand: Hand = Hand()
    private var gameState: GameStateEnum = GameStateEnum.NOT_YET
    private val blackJackValue = 21


    fun hit():GameStateEnum {
        //Draw card from the deck to hand's player:
        playerHand.addCard(myDeck.dealCard())

        //Check if player win or lose
        if (getPlayerValue() > blackJackValue) {
            return  GameStateEnum.DEALER_WIN
        } else if (getPlayerValue() == blackJackValue) {
            return GameStateEnum.PLAYER_WIN
        }

        return GameStateEnum.NOT_YET


    }

    fun stand():GameStateEnum {
        //Dealer keep drawing cards tile hand's value at least 17
        while (getDealerValue() < 17) {
            dealerHand.addCard(myDeck.dealCard())
        }
        //Check game state :
        if (dealerHand.getValue() <= blackJackValue) {
            gameState = if (dealerHand.getValue() > playerHand.getValue()) {
                return GameStateEnum.DEALER_WIN

            } else if (dealerHand.getValue() < playerHand.getValue()) {
                return GameStateEnum.PLAYER_WIN
            } else GameStateEnum.EVEN

        } else if (dealerHand.getValue() > blackJackValue) {
            return  GameStateEnum.PLAYER_WIN

        }

        return GameStateEnum.NOT_YET
    }

    private fun checkHandIsBlackJack(hand: Hand): Boolean {
        return hand.getValue() == 21
    }

    fun start() = restart()
    fun restart():GameStateEnum {
        //Rebuild the deck:
        myDeck = Deck()

        //Rebuild the hands of player and dealer:
        playerHand = Hand()
        dealerHand = Hand()

        //Shuffle the deck:
        myDeck.shuffle()

        //Start the game:
        gameState = GameStateEnum.NOT_YET

        //Init the hands by drawing two cards each:
        repeat(2) {
            hit()//Player draw card
            dealerHand.addCard(myDeck.dealCard())//Dealer draw card

        }
        //Check if player got BlackJack
        if (checkHandIsBlackJack(playerHand)) {
            gameState = GameStateEnum.PLAYER_WIN
        }
        return gameState
    }

    fun getPlayerCards(): Hand = playerHand


    fun getDealerCards(): Hand = dealerHand


    fun getPlayerValue(): Int = playerHand.getValue()


    fun getDealerValue(): Int = dealerHand.getValue()

    fun getState(): GameStateEnum = gameState

    fun isGamingInProgress()=getState()==GameStateEnum.NOT_YET
}