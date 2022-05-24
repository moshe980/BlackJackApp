package giniapps.blackjack.view

import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import giniapps.blackjack.model.GameStateEnum
import giniapps.blackjack.R
import giniapps.blackjack.controller.Game

class MainActivity : AppCompatActivity() {
    private val game = Game()

    private lateinit var newGameBtn: Button
    private lateinit var exitBtn: Button
    private lateinit var hitBtn: Button
    private lateinit var standBtn: Button
    private lateinit var playerHandTV: TextView
    private lateinit var dealerHandTV: TextView
    private lateinit var playerScoreTV: TextView
    private lateinit var dealerScoreTV: TextView
    private lateinit var dealerTV: TextView
    private lateinit var playerTV: TextView


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
        newGameBtn.setOnClickListener {
            game.start()
            displayInitGame()
            displayResult(game.getState())
        }

        hitBtn.setOnClickListener {
            if (game.isGamingInProgress()) {
                displayResult(game.hit())
                displayGame()

            }
        }

        standBtn.setOnClickListener {
            if (game.isGamingInProgress()) {
                displayResult(game.stand())
                displayGame()

            }
        }

        exitBtn.setOnClickListener {
            finish()
        }

    }

    private fun displayInitGame() {
        dealerTV.text = getString(R.string.initDealerTV)
        playerTV.text = getString(R.string.initPlayerTV)

        //Display first card of the dealer:
        dealerHandTV.text = game.getDealerCards().getCards().first().prettyString()

        //Display first card value of the dealer:
        dealerScoreTV.text = game.getDealerCards().getCards().first().getValue().toString()

        //Display the card and value of player:
        playerScoreTV.text = game.getPlayerValue().toString()
        playerHandTV.text = game.getPlayerCards().prettyString()

    }

    private fun displayResult(state:GameStateEnum) {
        when (state) {
            GameStateEnum.PLAYER_WIN -> {
                playerTV.text = getString(R.string.win)
            }
            GameStateEnum.DEALER_WIN -> {
                dealerTV.text = getString(R.string.win)
            }
            GameStateEnum.EVEN -> {
                playerTV.text = getString(R.string.even)
                dealerTV.text = getString(R.string.even)
            }
            GameStateEnum.NOT_YET -> {}
        }
    }

    private fun displayGame() {
        //Display the card and value of player:
        playerScoreTV.text = game.getPlayerValue().toString()
        playerHandTV.text = game.getPlayerCards().prettyString()

        //Display the card and value of dealer:
        dealerScoreTV.text = game.getDealerValue().toString()
        dealerHandTV.text = game.getDealerCards().prettyString()

    }

    private fun initViews() {
        newGameBtn = findViewById(R.id.newGameBtn)
        exitBtn = findViewById(R.id.exitBtn)
        hitBtn = findViewById(R.id.hitBtn)
        standBtn = findViewById(R.id.standBtn)
        playerHandTV = findViewById(R.id.playerHandTV)
        dealerHandTV = findViewById(R.id.dealerHandTV)
        playerScoreTV = findViewById(R.id.playerScoreTV)
        dealerScoreTV = findViewById(R.id.dealerScoreTV)
        dealerTV = findViewById(R.id.dealerTV)
        playerTV = findViewById(R.id.playerTV)
    }

}
