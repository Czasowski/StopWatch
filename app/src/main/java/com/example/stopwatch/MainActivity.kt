package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.view.View
import android.widget.TextView
import androidx.core.os.postDelayed

class MainActivity : AppCompatActivity() {

    private var seconds = 0
    private var running : Boolean = false

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        runTimer()
    }
    fun onClickStart(view: View) {
        running = true
    }

    fun onClickStop(view: View) {
    running = false
    }

    fun onClickReset(view: View) {
        running = false
        seconds = 0
    }
    private fun runTimer() {
        var timeView: TextView = findViewById(R.id.time_view)
val handler : Handler = Handler()
        handler.post(object : Runnable {
            override fun run() {
                var hours = seconds / 3600
                var minutes = (seconds % 3600) / 60
                var secs = seconds % 60
                var time: String = String.format("%d:%02d:%02d", hours, minutes, secs)
                timeView.text = time
                if (running) seconds++
                handler.postDelayed(this, 1000)
            }
        })
    }
}
