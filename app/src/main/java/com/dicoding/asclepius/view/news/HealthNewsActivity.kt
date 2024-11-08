package com.dicoding.asclepius.view.news

import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.NewsAdapter
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityHealthNewsBinding

class HealthNewsActivity : AppCompatActivity() {
    private lateinit var binding: ActivityHealthNewsBinding
    private val newsViewModel by viewModels<HealthNewsViewModel>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityHealthNewsBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.health_main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title = "Seputar Cancer"

        val newsAdapter = NewsAdapter()
        newsViewModel.fetchPosts()
        newsViewModel.news.observe(this) { articleList ->
            articleList?.let {
                newsAdapter.submitList(it)
            }
        }

        newsViewModel.isLoading.observe(this) { isLoading ->
            if (isLoading) {
                binding.progressBar.visibility = View.VISIBLE
            } else {
                binding.progressBar.visibility = View.GONE
            }
        }

        newsViewModel.error.observe(this){errorMsg ->
            errorMsg?.let {
                Toast.makeText(this, errorMsg, Toast.LENGTH_SHORT).show()
            }
        }

        binding.rvNews.layoutManager = LinearLayoutManager(this)
        binding.rvNews.adapter = newsAdapter

    }
}