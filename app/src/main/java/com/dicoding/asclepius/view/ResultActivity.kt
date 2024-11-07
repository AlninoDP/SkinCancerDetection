package com.dicoding.asclepius.view

import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityResultBinding

class ResultActivity : AppCompatActivity() {
    private lateinit var binding: ActivityResultBinding
    private var currentImageUri: Uri? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityResultBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Show Image
        val imageString = intent.getStringExtra(EXTRA_IMAGE_URI)
        val imageUri = Uri.parse(imageString)
        currentImageUri = imageUri
        currentImageUri?.let {
            showImage()
        }

        // Show Result
        val label = intent.getStringExtra(EXTRA_LABEL)
        val score = intent.getStringExtra(EXTRA_SCORE)
        showResult(label ?: "Default Label", score ?: "0")


    }

    private fun showImage() {
        currentImageUri?.let {
            Log.d(TAG, "Show Image: $it")
            binding.resultImage.setImageURI(it)
        }
    }

    private fun showResult(label: String, score: String) {
        binding.resultText.text = getString(R.string.result_information, label, score)
    }

    companion object {
        private const val TAG = "ResultActivity"
        const val EXTRA_IMAGE_URI = "extra_image_uri"
        const val EXTRA_LABEL = "extra_label"
        const val EXTRA_SCORE = "extra_score"
    }
}