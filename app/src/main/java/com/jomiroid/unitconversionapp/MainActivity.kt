package com.jomiroid.unitconversionapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.PersistableBundle
import androidx.core.widget.addTextChangedListener
import com.jomiroid.unitconversionapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding : ActivityMainBinding
    private var cmToM = true

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)

        val outputTextView = binding.outputTextView
        val outputUnitTextView = binding.outputUnitTextView
        val inputEditTextView = binding.inputEditText
        val inputUnitTextView = binding.inputUnitTextView
        val swapImageButton = binding.swapImageButton

        var inputNumber : Int = 0

        inputEditTextView.addTextChangedListener { text ->
            inputNumber = if(text.isNullOrEmpty()) {
                0
            } else {
                text.toString().toInt()
            }

            if (cmToM) {
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                outputTextView.text = inputNumber.times(100).toString()
            }
        }

        var outputNumber = inputNumber.times(0.01)
        outputTextView.text = outputNumber.toString()

        swapImageButton.setOnClickListener {
            cmToM = cmToM.not()
            if (cmToM) {
                inputUnitTextView.text = "cm"
                outputUnitTextView.text = "m"
                outputTextView.text = inputNumber.times(0.01).toString()
            } else {
                inputUnitTextView.text = "m"
                outputUnitTextView.text = "cm"
                outputTextView.text = inputNumber.times(100).toString()
            }
        }
    }

    override fun onSaveInstanceState(outState: Bundle, outPersistentState: PersistableBundle) {
        outState.putBoolean("cmToM", cmToM)
        super.onSaveInstanceState(outState, outPersistentState)
    }

    override fun onRestoreInstanceState(
        savedInstanceState: Bundle?,
        persistentState: PersistableBundle?
    ) {
        cmToM = savedInstanceState?.getBoolean("cmToM") ?: true
        binding.inputUnitTextView.text = if (cmToM) "cm" else "m"
        binding.outputUnitTextView.text = if (cmToM) "m" else "cm"
        super.onRestoreInstanceState(savedInstanceState, persistentState)
    }
}
