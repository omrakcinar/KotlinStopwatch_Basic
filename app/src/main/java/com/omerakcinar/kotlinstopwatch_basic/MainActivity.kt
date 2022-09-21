package com.omerakcinar.kotlinstopwatch_basic

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.SystemClock
import android.view.View
import android.widget.Chronometer
import com.omerakcinar.kotlinstopwatch_basic.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    private var isResumable = false
    private var currentTime: Long = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        val view = binding.root
        setContentView(view)
        binding.stopButton.isEnabled = false
        binding.resetButton.isEnabled = false
    }

    fun startOrResumeWatch(view: View) {
        if (!isResumable) {
            val chronometer: Chronometer = binding.chronometerView
            chronometer.base = SystemClock.elapsedRealtime()
            chronometer.start()
            isResumable = true
            binding.stopButton.isEnabled = true
            binding.startButton.isEnabled = false
        } else {
            val chronometer: Chronometer = binding.chronometerView
            chronometer.base = SystemClock.elapsedRealtime() - currentTime
            chronometer.start()
            binding.stopButton.isEnabled = true
            binding.startButton.isEnabled = false
        }
    }

    fun stopWatch(view: View) {
        val chronometer: Chronometer = binding.chronometerView
        chronometer.stop()
        currentTime = SystemClock.elapsedRealtime() - chronometer.base
        binding.startButton.isEnabled = true
        binding.resetButton.isEnabled = true
    }

    fun resetWatch(view: View) {
        val chronometer: Chronometer = binding.chronometerView
        chronometer.base = SystemClock.elapsedRealtime()
        currentTime = 0
        binding.resetButton.isEnabled = false
        binding.stopButton.isEnabled = false
        binding.startButton.isEnabled = true
    }


}