package co.tiagoaguiar.ganheinamega

import android.content.Context
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import org.w3c.dom.Text
import java.util.Random

class MainActivity : AppCompatActivity() {

    private lateinit var prefs: SharedPreferences

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val editText: EditText = findViewById(R.id.edit_number);
        val txtResult: TextView = findViewById(R.id.txt_result);
        val btnGenerate: Button = findViewById(R.id.btn_generate);

        prefs = getSharedPreferences("db", Context.MODE_PRIVATE);
        val result = prefs.getString("resultold", null);
        result?.let {
            txtResult.text = "últimos números: $result";
        }

        btnGenerate.setOnClickListener {
            val text = editText.text.toString();
            numberGenerate(text, txtResult);
        }
    }

    private fun numberGenerate(text: String, txtResult: TextView) {

        /*
        * Muito recomendavel para validação é usar um "return"
        * ao invés de usar "else" em um "if"
        * */

        // validação de vazio
        if (text.isEmpty()) {
            Toast.makeText(this, "Não é válido valor vazio", Toast.LENGTH_LONG).show()
            return
        }

        // transformando em int
        val qtd = text.toInt();

        // validação de escopo
        if (qtd < 6 || qtd > 15) {
            Toast.makeText(this, "Informe um valor entre 6 e 15", Toast.LENGTH_LONG).show()
            return
        }

        // mexendo devidamente no código
        val random = Random()
        val numbers = mutableSetOf<Int>()

        while (true) {
            val number = random.nextInt(60)
            numbers.add(number + 1);

            if (numbers.size == qtd) {
                break;
            }
        }

        txtResult.text = numbers.joinToString(" - ")
        val editor = prefs.edit()
        editor.putString("resultold", txtResult.text.toString());
        editor.apply()
        //alternativa de editor
        /*
            prefs.edit().apply {
                .putString("resultold", "texto salvo");
                .editor.apply();
            }
        * */

        //editor.commit() de forma sincrona
        // bloquear a interface e retorna um boolean

        //editor.apply() de forma assincrona
        // salvar sem bloquear nada, mas não notifica nada

        for (x in numbers) {
            Log.i("Sorte", "numº: $x");
        }
    }
    //txtResult.text = "Ola, mundo 2"

    //opção nº3
    // função mais simples, o clique ativa uma função
    /*btnGenerate.setOnClickListener {
        Log.i("info", "clicado")
    }

    // opção nº²variavel que seja do tipo ClickEventListener
    // btnGenerate.setOnClickListener(buttonOnClickListener);*/

    // opção nº²variavel que seja do tipo ClickEventListener
    /*val buttonOnClickListener = object : View.OnClickListener{
        // onClick: é o próprio SDK do android
        override fun onClick(v:View?) {
            Log.i("Teste", "botão clicado")
        }
    }

    // opção nº¹: XML
    fun generate(view: View) {
        Log.i("Teste", "botão clicado")
    }*/
}