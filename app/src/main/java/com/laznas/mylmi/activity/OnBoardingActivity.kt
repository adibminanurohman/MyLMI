package com.laznas.mylmi.activity

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.laznas.mylmi.R

class OnBoardingActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_onboarding)
    }

    fun navigateToBeranda(view: View) {
        val intent = Intent(this, BottomNavActivity::class.java)
        startActivity(intent)
    }
}