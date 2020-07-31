package com.uttarakhand.kisanseva2.activities

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.uttarakhand.kisanseva2.R
import kotlinx.android.synthetic.main.activity_quality_test_result.*

class QualityTestResultActivity : AppCompatActivity() {
    var category: String? = null
    private val qualities = arrayOf<String?>("Elite", "Premium", "Classic")

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quality_test_result)
        supportActionBar!!.title = getString(R.string.crop_quality)
        category = intent.extras!!.getString("category", getString(R.string.wheat))
        tvName.text = "Category: $category"
        tvRatio.text = "Ratio: ${getRandomNumber(80, 90)}/100"
//        tvQuality.text = "Quality of the $category: ${qualities[getRandomNumber(0, 3)]}"
        tvQuality.text = "Quality of the $category: Premium"
    }

    fun getRandomNumber(min: Int, max: Int): Int {
        return (Math.random() * (max - min) + min).toInt()
    }
}