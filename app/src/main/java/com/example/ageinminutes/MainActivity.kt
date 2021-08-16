package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.RequiresApi
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btn = findViewById<Button>(R.id.button1) as Button
        btn.setOnClickListener { view -> clickDatePicker(view) }

    }
    fun clickDatePicker(view: View){

        val selectedDate = findViewById<TextView>(R.id.selectedDate) as TextView
        val selectedDate2 = findViewById<TextView>(R.id.firstHiddenTextView) as TextView

        val ageInMins = findViewById<TextView>(R.id.secondHiddenTextView) as TextView
        val ageInMins2 = findViewById<TextView>(R.id.ageInMinutes) as TextView

        val myCalendar = Calendar.getInstance()
        val year = myCalendar.get(Calendar.YEAR)
        val month = myCalendar.get(Calendar.MONTH)
        val day = myCalendar.get(Calendar.DAY_OF_MONTH)

        val dpd = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->

            val sdf = SimpleDateFormat("yyyy-MM-dd", Locale.ENGLISH)

            selectedDate.text = "Selected date"
            selectedDate2.text = "$selectedYear-${selectedMonth + 1}-$selectedDayOfMonth"

            val theDate = sdf.parse("$selectedYear-${selectedMonth + 1}-$selectedDayOfMonth")
            val timeInMinutes = theDate!!.time / (60 * 1000) // returns in milliseconds, multiplying by 60 * 1000 to get minutes
            val currentDateInMinutes = (sdf.parse(sdf.format(System.currentTimeMillis())))!!.time / (60 * 1000)
            val difference = currentDateInMinutes - timeInMinutes

            ageInMins.text = difference.toString()
            ageInMins2.text = "Age in minutes"

        }
            , year, month, day)

        dpd.datePicker.setMaxDate(Date().time - 86400000)
        dpd.show()
    }
}