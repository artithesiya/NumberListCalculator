package com.example.numberlistcalculator

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.numberlistcalculator.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        // Initialize ViewBinding
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Set up the Calculate button click listener
        binding.calculateButton.setOnClickListener {
            // Parse the inputs from the EditText fields
            val list1 = parseInput(binding.editText1.text.toString())
            val list2 = parseInput(binding.editText2.text.toString())
            val list3 = parseInput(binding.editText3.text.toString())

            // Calculate intersection
            val intersection = list1.intersect(list2).intersect(list3).toList()

            // Calculate union
            val union = (list1 + list2 + list3).toSet().toList()

            // Find the highest number
            val highestNumber = (list1 + list2 + list3).maxOrNull()

            // Count the union numbers
            val unionCount = union.size

            // Display the results
            binding.resultTextView.text = """
                Intersection: ${intersection.joinToString(", ")}
                Union: ${union.sorted().joinToString(", ")}
                Highest Number: $highestNumber
                Union Count: $unionCount
            """.trimIndent()
        }
    }

    // Function to parse input strings into lists of integers
    private fun parseInput(input: String): List<Int> {
        return input.split(",")
            .mapNotNull { it.trim().toIntOrNull() }
    }
}
