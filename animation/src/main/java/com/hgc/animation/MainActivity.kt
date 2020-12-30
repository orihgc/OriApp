package com.hgc.animation

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.hgc.animation.property_animation.PropertyAnimation

class MainActivity : AppCompatActivity(), View.OnClickListener {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        initViews()
    }

    private fun initViews() {
        val propertyButton = findViewById<Button>(R.id.property_button)
        propertyButton.setOnClickListener(this)
    }

    override fun onClick(v: View?) {
        when (v?.id) {
            R.id.property_button -> {
                startActivity(Intent(this, PropertyAnimation::class.java))
            }
        }
    }
}