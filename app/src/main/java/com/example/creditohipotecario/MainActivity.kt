package com.example.creditohipotecario

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)


        val etPropertyValue: EditText = findViewById(R.id.et_property_value)
        val etLoanAmount: EditText = findViewById(R.id.et_loan_amount)
        val etTerm: EditText = findViewById(R.id.et_term)
        val etInterestRate: EditText = findViewById(R.id.et_interest_rate)
        val btnSimulate: Button = findViewById(R.id.btn_simulate)


        btnSimulate.setOnClickListener {

            val propertyValue = etPropertyValue.text.toString().toDoubleOrNull()
            val loanAmount = etLoanAmount.text.toString().toDoubleOrNull()
            val term = etTerm.text.toString().toIntOrNull()
            val interestRate = etInterestRate.text.toString().toDoubleOrNull()


            if (propertyValue != null && loanAmount != null && term != null && interestRate != null) {

                val totalPayment = simulateMortgage(propertyValue, loanAmount, term, interestRate)


                Toast.makeText(this, "Total a pagar: $totalPayment", Toast.LENGTH_LONG).show()
            } else {

                Toast.makeText(this, "Por favor llena todos los campos correctamente", Toast.LENGTH_SHORT).show()
            }
        }
    }


    private fun simulateMortgage(propertyValue: Double, loanAmount: Double, term: Int, interestRate: Double): Double {

        val monthlyInterestRate = interestRate / 100 / 12
        val numberOfPayments = term * 12
        val monthlyPayment = (loanAmount * monthlyInterestRate) / (1 - Math.pow(1 + monthlyInterestRate, -numberOfPayments.toDouble()))


        return monthlyPayment * numberOfPayments
    }
}
