package com.example.productdetail

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import com.example.productdetail.product.activity.ProductActivity
import kotlinx.android.synthetic.main.custom_toolbar.*

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.window.statusBarColor = resources.getColor(R.color.white)
        invokeToProductActivity()
    }

    private fun invokeToProductActivity() {
        Intent(this, ProductActivity::class.java).run { startActivityForResult(this, 0) }
    }
}