package com.example.cocci

import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.example.cocci.databinding.ActivityGameBinding

class Game : AppCompatActivity() {
    private lateinit var binding: ActivityGameBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        Holder(binding)


    }

    inner class Holder(binding: ActivityGameBinding) {
        private var state = 0

        private val mybinding = binding
        var gan = Play(10)
        private val tvGAN = binding.InputNumbers
        private val btnCanc = binding.Delete
        private val btnOK = binding.OK
        private val ivState = binding.Thonking
        private val tvAttempts = binding.Attempts
        private val btns = listOf(
                binding.one,
                binding.two,
                binding.three,
                binding.four,
                binding.five,
                binding.six,
                binding.seven,
                binding.eight,
                binding.nine,
                binding.zero
        )


        init {
            for (btn in btns) {
                btn.setOnClickListener(NumberClick())
            }
            btnCanc.setOnClickListener(ActionClick())
            btnOK.setOnClickListener(ActionClick())
            ivState.setOnClickListener {
                Toast.makeText(this@Game, "Thonking...", Toast.LENGTH_SHORT).show()
                startGame()
            }
            disableButtons()
        }

        private fun startGame() {

            gan = Play(10)
            tvGAN.setText("")
            gan.new()
            enableButtons()
        }

        private fun isEnabled(state: Boolean) {
            for (btn in btns) {
                btn.isEnabled = state
            }
            btnCanc.isEnabled = state
            btnOK.isEnabled = state
        }

        private fun disableButtons() {
            for (btn in btns) {
                btn.isEnabled = false
            }
            btnCanc.isEnabled = false
            btnOK.isEnabled = false
        }

        private fun enableButtons() {
            for (btn in btns) {
                btn.isEnabled = true
            }
            btnCanc.isEnabled = true
            btnOK.isEnabled = true
        }

        fun check(): Play.Answer {
            // forse try & catch?
            val text = mybinding.InputNumbers.text.toString()
            // Controllo su text

            val answer = gan.check(text.toInt())
            state = when(answer) {
                Play.Answer.YOULOOSE -> {gan.new()
                    R.drawable.no}
                Play.Answer.YOUWIN -> {gan.new()
                    R.drawable.win
                }
                Play.Answer.TOOSMALL -> R.drawable.too_small
                Play.Answer.TOOBIG -> R.drawable.too_big
            }
            ivState.setImageResource(state)
            return answer
        }

        inner class NumberClick : View.OnClickListener {
            override fun onClick(view: View?) {
                view as Button
                val txt = tvGAN.text.toString() + view.text.toString()
                tvGAN.setText(txt)
            }
        }

        inner class ActionClick : View.OnClickListener {
            override fun onClick(view: View?) {
                view as Button
                if(view.id == R.id.Delete)
                    tvGAN.setText("")   // Si potrebbe fare meglio
                if(view.id == R.id.OK) {
                    var c :Int = 0 //Control variable for null string number
                    if (tvGAN.text.toString() == ""){
                        if(c == 0) {
                            Toast.makeText(this@Game, "Please insert at least one number", Toast.LENGTH_SHORT).show()
                            ++c // I need this to avoid loop of the Toast.makeText...
                        }

                    }
                    else {
                        when (check()) {

                            Play.Answer.TOOBIG, Play.Answer.TOOSMALL -> tvAttempts.text = gan.attempts.toString()
                            Play.Answer.YOUWIN -> disableButtons()
                            Play.Answer.YOULOOSE -> disableButtons()
                        }
                        tvGAN.setText("")
                        tvAttempts.text = gan.attempts.toString()
                    }
                }
            }
        }
    }
}

