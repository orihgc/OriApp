package com.example.scrolllistconflict

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ArrayAdapter
import android.widget.ListView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val listView = findViewById<ListView>(R.id.list_view)
        val mutableListOf = mutableListOf<String>()
        for (i in 1..10) {
            mutableListOf.add("这是数据$i")
        }
        listView.adapter = ArrayAdapter(this, R.layout.support_simple_spinner_dropdown_item, mutableListOf)
    }
}