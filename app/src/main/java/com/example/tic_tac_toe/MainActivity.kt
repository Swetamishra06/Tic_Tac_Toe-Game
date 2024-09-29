package com.example.tic_tac_toe

import android.os.Bundle
import android.view.View
import android.os.Handler
import android.widget.Button
import android.widget.Toast
import android.media.MediaPlayer
import androidx.appcompat.app.AppCompatActivity
import android.widget.TextView
import androidx.activity.enableEdgeToEdge

class MainActivity : AppCompatActivity() {
    lateinit var mediaPlayer1: MediaPlayer
    lateinit var mediaPlayer2: MediaPlayer

    lateinit var btn1: Button
    lateinit var btn2: Button
    lateinit var btn3: Button
    lateinit var btn4: Button
    lateinit var btn5: Button
    lateinit var btn6: Button
    lateinit var btn7: Button
    lateinit var btn8: Button
    lateinit var btn9: Button
    lateinit var replay: Button
    lateinit var message :TextView

    var flag = 0
    var count = 0

    lateinit var b1: String
    lateinit var b2: String
    lateinit var b3: String
    lateinit var b4: String
    lateinit var b5: String
    lateinit var b6: String
    lateinit var b7: String
    lateinit var b8: String
    lateinit var b9: String

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        setContentView(R.layout.activity_main)
        initialize()
        message = findViewById<TextView>(R.id.editTextText)
        //initialize the media player
        mediaPlayer1= android.media.MediaPlayer.create(this,R.raw.click)
        mediaPlayer2 = android.media.MediaPlayer.create(this,R.raw.cracker)
    }

    private fun initialize() {
        btn1 = findViewById(R.id.button1)
        btn2 = findViewById(R.id.button2)
        btn3 = findViewById(R.id.button3)
        btn4 = findViewById(R.id.button4)
        btn5 = findViewById(R.id.button5)
        btn6 = findViewById(R.id.button6)
        btn7 = findViewById(R.id.button7)
        btn8 = findViewById(R.id.button8)
        btn9 = findViewById(R.id.button9)

        replay = findViewById(R.id.replay)

        // Replay button action
        replay.setOnClickListener {
            message.text =""
            newchance()
        }
    }

    fun Check(view: View) {
        val btnCurrent = view as Button

        if (btnCurrent.text.isEmpty()) {
              mediaPlayer1.start()//play the sound
            count++
            // Toggle the turns
            if (flag == 0) {
                btnCurrent.text = "X"
                flag = 1
            } else {
                btnCurrent.text = "O"
                flag = 0
            }
            btnCurrent.isEnabled = false // Disable the button after it's clicked

            // Check for a winner if more than 4 moves are made
            if (count > 4) {
                b1 = btn1.text.toString()
                b2 = btn2.text.toString()
                b3 = btn3.text.toString()
                b4 = btn4.text.toString()
                b5 = btn5.text.toString()
                b6 = btn6.text.toString()
                b7 = btn7.text.toString()
                b8 = btn8.text.toString()
                b9 = btn9.text.toString()

                // Check rows, columns, and diagonals
                when {
                    (b1 == b2 && b2 == b3 && b1 != "") -> announceWinner(b1)
                    (b4 == b5 && b5 == b6 && b4 != "") -> announceWinner(b4)
                    (b7 == b8 && b8 == b9 && b7 != "") -> announceWinner(b7)
                    (b1 == b4 && b4 == b7 && b1 != "") -> announceWinner(b1)
                    (b2 == b5 && b5 == b8 && b2 != "") -> announceWinner(b2)
                    (b3 == b6 && b6 == b9 && b3 != "") -> announceWinner(b3)
                    (b1 == b5 && b5 == b9 && b1 != "") -> announceWinner(b1)
                    (b3 == b5 && b5 == b7 && b3 != "") -> announceWinner(b3)
                    if(count == 9) -> {
                        message.text = "It's a draw! "
                        Handler().postDelayed({
                            message.text = "" },2000)
                        newchance()

                    }
                }
            }
        }
    }

    private fun announceWinner(winner: String) {

        mediaPlayer2.start()
        message.text = "Winner is: $winner 🎉 🎊 "
        Handler().postDelayed({
            message.text = ""
        },2000)

        newchance()
    }

    fun newchance() {
        btn1.text = ""
        btn2.text = ""
        btn3.text = ""
        btn4.text = ""
        btn5.text = ""
        btn6.text = ""
        btn7.text = ""
        btn8.text = ""
        btn9.text = ""
        // Enable all buttons again for the new game
        btn1.isEnabled = true
        btn2.isEnabled = true
        btn3.isEnabled = true
        btn4.isEnabled = true
        btn5.isEnabled = true
        btn6.isEnabled = true
        btn7.isEnabled = true
        btn8.isEnabled = true
        btn9.isEnabled = true

        flag = 0
        count = 0
    }
}
