package com.hgc.handlerleak

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Looper
import android.widget.Button
import android.widget.Toast

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val button = findViewById<Button>(R.id.go)
        button.setOnClickListener {
//            val intent = Intent(this, NoLeakActivity::class.java)
//            startActivity(intent)

            Thread {
                if (Looper.myLooper() == null) {
                    Looper.prepare()
                }
                Toast.makeText(this@MainActivity, "test", Toast.LENGTH_SHORT).show()
                Looper.loop()

            }.start()
        }
    }
}