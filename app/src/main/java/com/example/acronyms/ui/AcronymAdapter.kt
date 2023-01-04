package com.example.acronyms.ui

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.acronyms.R
import com.example.acronyms.data.model.Lf
import com.example.acronyms.databinding.AcronymItemBinding

class AcronymAdapter : RecyclerView.Adapter<AcronymAdapter.ViewHolder>() {

    private val acronyms = mutableListOf<Lf>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            DataBindingUtil.inflate(
                LayoutInflater.from(parent.context),
                R.layout.acronym_item,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return acronyms.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(acronyms[position])
    }

    fun submitList(acronym: List<Lf>) {
        this.acronyms.clear()
        this.acronyms.addAll(acronym)
        notifyDataSetChanged()
    }

    class ViewHolder(
        private val binding: AcronymItemBinding
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(acronym: Lf) {
            binding.acronym = acronym
        }
    }
}