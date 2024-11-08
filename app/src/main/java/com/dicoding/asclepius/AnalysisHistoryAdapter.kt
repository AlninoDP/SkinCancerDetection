package com.dicoding.asclepius

import android.annotation.SuppressLint
import android.net.Uri
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.dicoding.asclepius.data.local.entity.AnalysisHistoryEntity
import com.dicoding.asclepius.databinding.HistoryItemBinding

class AnalysisHistoryAdapter(private val onDeleteClick: (AnalysisHistoryEntity) -> Unit) :
    ListAdapter<AnalysisHistoryEntity, AnalysisHistoryAdapter.AnalysisHistoryViewHolder>(
        HistoryDiffCallback()
    ) {
    class AnalysisHistoryViewHolder(val binding: HistoryItemBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(analysisHistoryEntity: AnalysisHistoryEntity) {
            binding.historyImage.setImageURI(Uri.parse(analysisHistoryEntity.imageUri))
            binding.tvHistoryResult.text = binding.root.context.getString(
                R.string.result_information,
                analysisHistoryEntity.label,
                analysisHistoryEntity.score
            )
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AnalysisHistoryViewHolder {
        val binding = HistoryItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return AnalysisHistoryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: AnalysisHistoryViewHolder, position: Int) {
        val item = getItem(position)
        holder.bind(item)

        val deleteButton = holder.binding.deleteImage

        deleteButton.setOnClickListener {
            onDeleteClick(item)
        }

    }

    class HistoryDiffCallback : DiffUtil.ItemCallback<AnalysisHistoryEntity>() {
        override fun areItemsTheSame(
            oldItem: AnalysisHistoryEntity,
            newItem: AnalysisHistoryEntity
        ) =
            oldItem.id == newItem.id

        @SuppressLint("DiffUtilEquals")
        override fun areContentsTheSame(
            oldItem: AnalysisHistoryEntity,
            newItem: AnalysisHistoryEntity
        ): Boolean {
            return oldItem == newItem
        }

    }
}