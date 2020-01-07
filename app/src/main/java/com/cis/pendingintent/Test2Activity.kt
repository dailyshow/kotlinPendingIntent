package com.cis.pendingintent

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_test2.*

class Test2Activity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_test2)

        val stringData = intent.getStringExtra("stringSend")
        val intData = intent.getIntExtra("intSend", 0)

        test2Tv.text = "${stringData}\n"
        test2Tv.append("${intData}")
    }
}
