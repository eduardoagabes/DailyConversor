package com.eduardoagabes.dailyconversor

import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.eduardoagabes.dailyconversor.databinding.ActivityResultBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.material.textfield.TextInputEditText

class ResultActivity : AppCompatActivity() {

    private lateinit var binding: ActivityResultBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        enableEdgeToEdge()
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        val tvResult = binding.tvResult
        val tieWeight = binding.tieWeigth
        val tieDistance = binding.tieDistance
        val tieVolume= binding.tieVolume

        val fromWeightUnitPos = binding.spinnerWeight1.selectedItemPosition
        val toWeightUnitPos = binding.spinnerWeight2.selectedItemPosition



        ArrayAdapter.createFromResource(
            this,
            R.array.weight_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerWeight1.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.weight_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerWeight2.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.distance_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerDistance1.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.distance_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerDistance2.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.volume_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerVolume1.adapter = adapter
        }

        ArrayAdapter.createFromResource(
            this,
            R.array.volume_array,
            android.R.layout.simple_spinner_item
        ).also { adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.spinnerVolume2.adapter = adapter
        }

        binding.btnConverter.setOnClickListener {
            val inputWeight = tieWeight.text.toString()
            val inputDistance = tieDistance.text.toString()
            val inputVolume = tieVolume.text.toString()


            if (inputWeight.isNotEmpty()) {
                val fromWeightUnit = binding.spinnerWeight1.selectedItem.toString()
                val toWeightUnit = binding.spinnerWeight2.selectedItem.toString()
                val weightValue = inputWeight.toDouble()
                val weightResult = convertWeight(weightValue, fromWeightUnit, toWeightUnit)
                tvResult.text = "$weightResult"
            } else {

            }

            if (inputDistance.isNotEmpty()) {
                val fromDistanceUnit = binding.spinnerDistance1.selectedItem.toString()
                val toDistanceUnit = binding.spinnerDistance2.selectedItem.toString()
                val distanceValue = inputDistance.toDouble()
                val distanceResult = convertDistance(distanceValue, fromDistanceUnit, toDistanceUnit)
                tvResult.text = "$distanceResult"
            }
            if (inputVolume.isNotEmpty()) {
                val fromVolumeUnit = binding.spinnerVolume1.selectedItem.toString()
                val toVolumeUnit = binding.spinnerVolume2.selectedItem.toString()
                val volumeValue = inputVolume.toDouble()
                val volumeResult = convertVolume(volumeValue, fromVolumeUnit, toVolumeUnit)
                tvResult.text = "$volumeResult"
            }

        }

        binding.btnClean.setOnClickListener {

            tieWeight.setText("")
            tieDistance.setText("")
            tieVolume.setText("")

            binding.spinnerWeight1.setSelection(0)
            binding.spinnerWeight2.setSelection(0)
            binding.spinnerDistance1.setSelection(0)
            binding.spinnerDistance2.setSelection(0)
            binding.spinnerVolume1.setSelection(0)
            binding.spinnerVolume2.setSelection(0)

            tvResult.text = ""
        }
    }

    private fun convertWeight(value: Double, fromUnit: String, toUnit: String): Double {
        val baseValue = when (fromUnit) {
            "Kilogramos" -> value
            "Gramos" -> value / 1000
            "Miligramos" -> value / 1_000_000
            "Toneladas" -> value * 1000
            "Libras" -> value / 2.20462
            else -> value
        }

        return when (toUnit) {
            "Kilogramos" -> baseValue
            "Gramos" -> baseValue * 1000
            "Miligramos" -> baseValue * 1_000_000
            "Toneladas" -> baseValue / 1000
            "Libras" -> baseValue * 2.20462
            else -> baseValue
        }
    }

    private fun convertDistance(value: Double, fromUnit: String, toUnit: String): Double {
        val baseValue = when (fromUnit) {
            "Metros" -> value
            "Centímetros" -> value / 100
            "Milímetros" -> value / 1000
            "Quilómetros" -> value * 1000
            "Milhas" -> value * 1609.34
            else -> value
        }

        return when (toUnit) {
            "Metros" -> baseValue
            "Centímetros" -> baseValue * 100
            "Milímetros" -> baseValue * 1000
            "Quilómetros" -> baseValue / 1000
            "Milhas" -> baseValue / 1609.34
            else -> baseValue
        }
    }

    private fun convertVolume(value: Double, fromUnit: String, toUnit: String): Double {
        val baseValue = when (fromUnit) {
            "Litros" -> value
            "Mililitros" -> value / 1000
            "Metros cúbicos" -> value * 1000
            "Centímetros cúbicos" -> value / 1000
            "Galões" -> value * 3.78541
            "Onças fluídas" -> value / 33.814
            "Barris" -> value * 158.987
            else -> value
        }

        return when (toUnit) {
            "Litros" -> baseValue
            "Mililitros" -> baseValue * 1000
            "Metros cúbicos" -> baseValue / 1000
            "Centímetros cúbicos" -> baseValue * 1000
            "Galones" -> baseValue / 3.78541
            else -> baseValue
        }
    }
}