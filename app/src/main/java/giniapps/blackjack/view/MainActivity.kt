package giniapps.blackjack.view

import android.animation.ValueAnimator
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.helper.widget.Layer
import giniapps.blackjack.R
import giniapps.blackjack.controller.Game
import giniapps.blackjack.databinding.ActivityMainBinding
import giniapps.blackjack.model.GameStateEnum

class MainActivity : AppCompatActivity() {
    private val game = Game()
    private var isGamingInProgress: Boolean = false

    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.newGameBtn.setOnClickListener {
            game.start()
            displayInitGame()
            displayResult(game.getState())
            isGamingInProgress = true
        }

        binding.hitBtn.setOnClickListener {
            if (isGamingInProgress) {
                displayResult(game.hit())
                displayGame()

            }
        }

        binding.standBtn.setOnClickListener {
            if (isGamingInProgress) {
                displayResult(game.stand())
                displayGame()
                isGamingInProgress = false

            }
        }

        binding.exitBtn.setOnClickListener {
            finish()
        }

    }

    private fun displayInitGame() {
        binding.dealerTV.text = getString(R.string.initDealerTV)
        binding.playerTV.text = getString(R.string.initPlayerTV)

        //Display first card of the dealer:
        binding.dealerHandTV.text = game.getDealerCards().getCards().first().prettyString()

        //Display first card value of the dealer:
        binding.dealerScoreTV.text = game.getDealerCards().getCards().first().getValue().toString()

        //Display the card and value of player:
        binding.playerScoreTV.text = game.getPlayerValue().toString()
        binding.playerHandTV.text = game.getPlayerCards().prettyString()

    }

    private fun displayResult(state: GameStateEnum) {
        when (state) {
            GameStateEnum.PLAYER_WIN -> {
                binding.playerTV.text = getString(R.string.win)
                rotate(binding.playerLayer)
            }
            GameStateEnum.DEALER_WIN -> {
                binding.dealerTV.text = getString(R.string.win)
                rotate(binding.dealerLayer)
            }
            GameStateEnum.EVEN -> {
                binding.playerTV.text = getString(R.string.even)
                binding.dealerTV.text = getString(R.string.even)
            }
            GameStateEnum.NOT_YET -> {}
        }
    }

    private fun rotate(layer: Layer) {
        ValueAnimator.ofFloat(0F, 360F)
            .apply {
                addUpdateListener { animator ->
                    layer.rotation = animator.animatedValue as Float
                }
                duration = 1000 // in ms = 2 sec
                start()
            }
    }

    private fun displayGame() {
        //Display the card and value of player:
        binding.playerScoreTV.text = game.getPlayerValue().toString()
        binding.playerHandTV.text = game.getPlayerCards().prettyString()

        //Display the card and value of dealer:
        binding.dealerScoreTV.text = game.getDealerValue().toString()
        binding.dealerHandTV.text = game.getDealerCards().prettyString()

    }


}
