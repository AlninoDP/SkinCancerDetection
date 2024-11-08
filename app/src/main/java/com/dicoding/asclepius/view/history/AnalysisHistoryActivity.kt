package com.dicoding.asclepius.view.history

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.LinearLayoutManager
import com.dicoding.asclepius.AnalysisHistoryAdapter
import com.dicoding.asclepius.R
import com.dicoding.asclepius.databinding.ActivityAnalysisHistoryBinding
import kotlinx.coroutines.launch

class AnalysisHistoryActivity : AppCompatActivity() {
    private lateinit var binding: ActivityAnalysisHistoryBinding
    private lateinit var historyViewModel: AnalysisHistoryViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityAnalysisHistoryBinding.inflate(layoutInflater)
        setContentView(binding.root)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        supportActionBar?.title = "Riwayat Analisis"
        historyViewModel = AnalysisHistoryViewModel(applicationContext)



        binding.emptyLayoutState.visibility = View.VISIBLE
        historyViewModel.getAllHistory().observe(this) { it->

            if (it.isNullOrEmpty()) {
                showEmptyState()
            } else {
                val adapter = AnalysisHistoryAdapter{
                    deleteHistory(it.imageUri.toString(),this)
                }
                binding.emptyLayoutState.visibility = View.GONE
                adapter.submitList(it)
                binding.rvHistory.layoutManager = LinearLayoutManager(this)
                binding.rvHistory.adapter = adapter
            }
        }
    }

    private fun deleteHistory (imageUri:String,context: Context){
        showDeleteConfirmationDialog(context, onConfirm = {
            lifecycleScope.launch {
                historyViewModel.deleteHistoryByImageUri(imageUri)
            }
        })

    }
   private fun showDeleteConfirmationDialog(context: Context, onConfirm: () -> Unit) {
        // Build the AlertDialog
        val builder = AlertDialog.Builder(context)
        builder.setTitle("Confirm Deletion")
        builder.setMessage("Are you sure you want to delete this item?")

        // Set up the positive button to confirm deletion
        builder.setPositiveButton("Yes") { dialog, _ ->
            onConfirm() // Execute the delete action
            dialog.dismiss() // Close the dialog
        }

        // Set up the negative button to cancel
        builder.setNegativeButton("No") { dialog, _ ->
            dialog.dismiss() // Just close the dialog
        }

        // Create and show the AlertDialog
        builder.create().show()
    }

    private fun showEmptyState() {
        binding.emptyLayoutState.visibility = View.VISIBLE
    }
}