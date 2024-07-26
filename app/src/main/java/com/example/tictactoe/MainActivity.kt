package com.example.tictactoe

import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.example.tictactoe.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private var player = true // true = X, false = O
    private var board = Array(3) { arrayOfNulls<String>(3) }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val buttons = arrayOf(
            binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9
        )

        for (button in buttons) {
            button.setOnClickListener { onButtonClick(button) }
        }

        binding.resetButton.setOnClickListener { resetGame() }
    }

    private fun onButtonClick(view: View) {
        if (view !is Button) return

        if (view.text.isNotEmpty()) return

        val symbol = if (player) "X" else "O"
        view.text = symbol

        val tag = view.tag.toString().toInt()
        val row = (tag - 1) / 3
        val col = (tag - 1) % 3
        board[row][col] = symbol

        if (checkForWin()) {
            disableButtons()
            return
        }

        player = !player
    }

    private fun checkForWin(): Boolean {
        for (i in 0..2) {
            // Check rows and columns
            if ((board[i][0] == board[i][1] && board[i][1] == board[i][2] && board[i][0] != null) ||
                (board[0][i] == board[1][i] && board[1][i] == board[2][i] && board[0][i] != null)
            ) {
                return true
            }
        }

        // Check diagonals
        if ((board[0][0] == board[1][1] && board[1][1] == board[2][2] && board[0][0] != null) ||
            (board[0][2] == board[1][1] && board[1][1] == board[2][0] && board[0][2] != null)
        ) {
            return true
        }

        return false
    }

    private fun disableButtons() {
        val buttons = arrayOf(
            binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9
        )

        for (button in buttons) {
            button.isEnabled = false
        }
    }

    private fun resetGame() {
        val buttons = arrayOf(
            binding.button1, binding.button2, binding.button3,
            binding.button4, binding.button5, binding.button6,
            binding.button7, binding.button8, binding.button9
        )

        for (button in buttons) {
            button.text = ""
            button.isEnabled = true
        }

        board = Array(3) { arrayOfNulls<String>(3) }
        player = true
    }
}
