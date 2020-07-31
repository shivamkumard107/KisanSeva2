package com.uttarakhand.kisanseva2.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.uttarakhand.kisanseva2.R

class QualityTestResultActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_quality_test_result)
        supportActionBar!!.title = getString(R.string.crop_quality)

    }
}