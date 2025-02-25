package com.example.kaknibyd
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import java.text.DecimalFormat
import net.objecthunter.exp4j.ExpressionBuilder
import com.example.kaknibyd.databinding.ActivityMainBinding // Убедитесь, что сгенерированный класс соответствует имени вашего файла разметки

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Инициализируем binding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Настраиваем обработчики кликов
        binding.buttonClear.setOnClickListener {
            binding.input.text = " "
            binding.output.text = " "
        }
        binding.buttonBracketLeft.setOnClickListener {
            addToInputText("(")
        }
        binding.buttonBracketRight.setOnClickListener { // исправил на buttonBracketRight
            addToInputText(")")
        }
        binding.button0.setOnClickListener { addToInputText("0") }
        binding.button1.setOnClickListener { addToInputText("1") }
        binding.button2.setOnClickListener { addToInputText("2") }
        binding.button3.setOnClickListener { addToInputText("3") }
        binding.button4.setOnClickListener { addToInputText("4") }
        binding.button5.setOnClickListener { addToInputText("5") }
        binding.button6.setOnClickListener { addToInputText("6") }
        binding.button7.setOnClickListener { addToInputText("7") }
        binding.button8.setOnClickListener { addToInputText("8") }
        binding.button9.setOnClickListener { addToInputText("9") }
        binding.buttonDot.setOnClickListener { addToInputText(".") }
        binding.buttonDivision.setOnClickListener { addToInputText("÷") }
        binding.buttonMultiply.setOnClickListener { addToInputText("x") }
        binding.buttonSubtraction.setOnClickListener { addToInputText("-") }
        binding.buttonAddition.setOnClickListener { addToInputText("+") }
        binding.buttonEquals.setOnClickListener { showResult() }
        binding.buttonPercent.setOnClickListener { addToInputText("%") }
    }

    private fun addToInputText(value: String) {
        binding.input.append(value)
    }

    private fun getInputExpression(): String {
        return binding.input.text.toString()
    }

    private fun showResult() {
        try {
            val expression = getInputExpression().replace("%", "/100")
            val result = ExpressionBuilder(expression).build().evaluate()
            binding.output.text = DecimalFormat("0.######").format(result as Double)
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.neon_green))
        } catch (e: Exception) {
            binding.output.text = "Ошибка"
            binding.output.setTextColor(ContextCompat.getColor(this, R.color.red))
        }
    }
}