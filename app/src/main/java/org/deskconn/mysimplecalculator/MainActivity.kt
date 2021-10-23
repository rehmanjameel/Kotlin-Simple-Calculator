package org.deskconn.mysimplecalculator

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.ImageView
import android.widget.TextView
import android.widget.Toast
import net.objecthunter.exp4j.ExpressionBuilder

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val placeholder = findViewById<TextView>(R.id.placeHolder)
        val answer = findViewById<TextView>(R.id.answer)

        val actionEquals = findViewById<TextView>(R.id.actionEquals)
        val num0 = findViewById<TextView>(R.id.num0)
        val num1 = findViewById<TextView>(R.id.num1)
        val num2 = findViewById<TextView>(R.id.num2)
        val num3 = findViewById<TextView>(R.id.num3)
        val num4 = findViewById<TextView>(R.id.num4)
        val num5 = findViewById<TextView>(R.id.num5)
        val num6 = findViewById<TextView>(R.id.num6)
        val num7 = findViewById<TextView>(R.id.num7)
        val num8 = findViewById<TextView>(R.id.num8)
        val num9 = findViewById<TextView>(R.id.num9)
        val clear = findViewById<TextView>(R.id.clear)
        val numDot = findViewById<TextView>(R.id.numDot)
        val startBracket = findViewById<TextView>(R.id.startBracket)
        val closeBracket = findViewById<TextView>(R.id.closeBracket)
        val actionDivide = findViewById<TextView>(R.id.actionDivide)
        val actionMultiply = findViewById<TextView>(R.id.actionMultiply)
        val actionMinus = findViewById<TextView>(R.id.actionMinus)
        val actionAdd = findViewById<TextView>(R.id.actionAdd)
        val actionBack = findViewById<ImageView>(R.id.actionBack)
        //Numbers
        num0.setOnClickListener { appendVal("0", false) }
        num1.setOnClickListener { appendVal("1", false) }
        num2.setOnClickListener { appendVal("2", false) }
        num3.setOnClickListener { appendVal("3", false) }
        num4.setOnClickListener { appendVal("4", false) }
        num5.setOnClickListener { appendVal("5", false) }
        num6.setOnClickListener { appendVal("6", false) }
        num7.setOnClickListener { appendVal("7", false) }
        num8.setOnClickListener { appendVal("8", false) }
        num9.setOnClickListener { appendVal("9", false) }
        numDot.setOnClickListener { appendVal(".", false) }
        //Operators
        clear.setOnClickListener { appendVal("", true) }
        startBracket.setOnClickListener { appendVal(" ( ", false) }
        closeBracket.setOnClickListener { appendVal(" ) ", false) }
        actionDivide.setOnClickListener { appendVal(" / ", false) }
        actionMultiply.setOnClickListener { appendVal(" * ", false) }
        actionMinus.setOnClickListener { appendVal(" - ", false) }
        actionAdd.setOnClickListener { appendVal(" + ", false) }

        actionBack.setOnClickListener {
            val expression = placeholder.text.toString()
            if (expression.isNotEmpty()) {
                placeholder.text = expression.substring(0, expression.length - 1)
            }
        }

        actionBack.setOnLongClickListener {
            placeholder.text = ""
            answer.text = ""
            true
        }

        actionEquals.setOnClickListener {
            try {
                val expression = ExpressionBuilder(placeholder.text.toString()).build()
                val result = expression.evaluate()
                val longResult = result.toLong()
                if (result == longResult.toDouble()) {
                    Toast.makeText(this, "Double", Toast.LENGTH_SHORT).show()
                    answer.text = longResult.toString()
                } else
                    answer.text = result.toString()

            } catch (e: Exception) {
                Toast.makeText(this, e.message, Toast.LENGTH_SHORT).show();

                Log.d("EXCEPTION", "Message: ${e.message}")
            }

        }


    }

    fun appendVal(string: String, isClear: Boolean) {
        val placeholder = findViewById<TextView>(R.id.placeHolder)
        val answer = findViewById<TextView>(R.id.answer)
        if (isClear) {
            placeholder.text = ""
            answer.text = ""
//            placeholder.append(string)
        } else {
            placeholder.append(string)
        }
    }
}