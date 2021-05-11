package local.moerlisoft.bfwrechner

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {
    val fehlerText = "Div/Null!"
    var onCommaSet = false
    var onOperatorSet = false
    var onLetterLast = true
    var onGleichlast = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    fun onLetter(view: View) {
        if (txtOutput.text.toString() != "0")
            onLetterLast = true
        if (txtOutput.text.toString() == "0" || onGleichlast) {
            txtOutput.text = (view as Button).text
            onGleichlast = false
        } else
            txtOutput.append((view as Button).text)
    }

    fun onComma(view: View) {
        if (onCommaSet == false) {
            txtOutput.append(",")
            onCommaSet = true
            onLetterLast = false
        }
    }

    fun onCLR(view: View) {
        txtOutput.text = "0"
        onCommaSet = false
        onOperatorSet = false
        onLetterLast = true
        onGleichlast = false
    }

    fun onOperator(view: View) {
        if (!onOperatorSet and onLetterLast) {
            txtOutput.append((view as Button).text)
            onOperatorSet = true
            onLetterLast = false
            onCommaSet = false
            onGleichlast = false
        }
    }

    fun onGleich(view: View) {
        if (onOperatorSet and onLetterLast) {
            val value = txtOutput.text.toString().replace(",", ".");

            if (value.contains("÷")) {
                val valueSplit = value.split("÷")
                var zahl1 = valueSplit[0].toDouble()
                var zahl2 = valueSplit[1].toDouble()
                if (zahl2 == 0.0)
                    txtOutput.text = fehlerText
                else {
                    txtOutput.text = (zahl1 / zahl2).toString()
                }
            } else if (value.contains("*")) {
                val valueSplit = value.split("*")
                var zahl1 = valueSplit[0].toDouble()
                var zahl2 = valueSplit[1].toDouble()
                txtOutput.text = (zahl1 * zahl2).toString()
            } else if (value.contains("-")) {
                val valueSplit = value.split("-")
                var zahl1 = valueSplit[0].toDouble()
                var zahl2 = valueSplit[1].toDouble()
                txtOutput.text = (zahl1 - zahl2).toString()
            } else if (value.contains("+")) {
                val valueSplit = value.split("+")
                var zahl1 = valueSplit[0].toDouble()
                var zahl2 = valueSplit[1].toDouble()
                txtOutput.text = (zahl1 + zahl2).toString()
            }
        }
        onOperatorSet = false
        onGleichlast = true
    }
}