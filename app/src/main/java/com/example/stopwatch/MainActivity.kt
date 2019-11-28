package com.example.stopwatch

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.PersistableBundle
import android.view.View
import android.widget.TextView
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    private var seconds = 0
    private var running : Boolean = false
    private var wasRunning : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState !=null) {
            seconds = savedInstanceState.getInt("seconds")
            running = savedInstanceState.getBoolean("running")
            wasRunning = savedInstanceState.getBoolean("wasRunning")
        }
        runTimer()
    }
    override fun onSaveInstanceState(saveItems: Bundle) {
        super.onSaveInstanceState(saveItems)
        saveItems.putInt("seconds", seconds)
        saveItems.putBoolean("running", running)
        saveItems.putBoolean("wasRunning", wasRunning)
    }

    fun onClickStart(view:View) {
        running = true
    }

    fun onClickStop(view:View) {
    running = false
    }

    fun onClickReset(view:View) {
        running = false
        seconds = 0
    }

    private fun runTimer() {
        //val timeView: TextView = findViewById(R.id.time_view)
val hand = Handler()
        hand.post(object : Runnable {
            override fun run() {
                val hours = seconds / 3600
                val minutes = (seconds % 3600) / 60
                val secs = seconds % 60
                val time = String.format("%d:%02d:%02d", hours, minutes, secs)
                time_view.text = time
                //timeView.text = time
                if (running) seconds++
                hand.postDelayed(this, 1000)
            }
        })
    }

    override fun onPause() {
        super.onPause()
        wasRunning = running
    running = false
    }

    override fun onResume() {
        super.onResume()
        if(wasRunning) {
            running = true
        }
    }
}
