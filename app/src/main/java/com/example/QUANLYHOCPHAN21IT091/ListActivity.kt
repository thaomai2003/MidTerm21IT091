package com.example.QUANLYHOCPHAN21IT091

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.thigk21it270.R
import kotlinx.android.synthetic.main.activity_list.*

class ListActivity : AppCompatActivity() {
//    lateinit var adapter: SimpleCursorAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_list)
        btnquaylai.setOnClickListener {
            val i2 = Intent(this,MainActivity::class.java)
            startActivity(i2)

        }
//        lvFull.visibility = View.VISIBLE
//        adapter.notifyDataSetChanged()
    }
}