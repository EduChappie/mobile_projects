package com.example.dadosgame

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.TextView
import org.w3c.dom.Text
import java.util.Random

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {

        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //pegar as principais variáveis
        val btR = findViewById<Button>(R.id.buttonRoll);
        val d1 = findViewById<TextView>(R.id.textResultD1);
        val d2 = findViewById<TextView>(R.id.textResultD2);
        val result = findViewById<TextView>(R.id.textResultFinal);

        btR.setOnClickListener{
            rollingDice(d1, d2, result);
        }
    }
}

private fun rollingDice(d1: TextView, d2: TextView, result: TextView) {
    val rd1 = Random();
    val rd2 = Random();
    val nd1 = rd1.nextInt(12)+1;
    val nd2 = rd2.nextInt(12)+1;
    val status: String;

    Log.i("dices", "$nd1");
    Log.i("dices", "$nd2");


    if (nd1+nd2 == 7 || nd1+nd2 == 11) {
        status = "You win"
    } else if (nd1+nd2 >= 4 && nd1+nd2 <= 10) {
        status = "you got some points - ${nd1+nd2}"
    } else {
        status = "you Crap!"
    }

    updateScreen(status, nd1, nd2, d1, d2, result);
}

private fun updateScreen(status: String, f1: Int, f2: Int, d1: TextView, d2: TextView, result: TextView) {
    d1.text = "d1: $f1";
    d2.text = "d2: $f2";

    result.text = status
}