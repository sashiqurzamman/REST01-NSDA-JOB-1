package com.example.nsdajobone

import android.content.Intent
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ProductActivity : AppCompatActivity() {
    private lateinit var productViewModel: ProductViewModel
    private lateinit var productAdapter: ProductAdapter
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_product)

        productViewModel = ViewModelProvider(this).get(ProductViewModel::class.java)
        productAdapter = ProductAdapter(emptyList())

        val refreshBtn: FloatingActionButton = findViewById(R.id.refreshBtn)

        refreshBtn.setOnClickListener {
            startActivity(Intent(this@ProductActivity, ProductActivity::class.java))
            finish()
        }

        val recyclerView: RecyclerView = findViewById(R.id.productRV)
        recyclerView.layoutManager = LinearLayoutManager(this)
        recyclerView.adapter = productAdapter

        productViewModel.products.observe(this) { products ->
            products?.let {
                productAdapter = ProductAdapter(it)
                recyclerView.adapter = productAdapter
            }
        }

    }
}