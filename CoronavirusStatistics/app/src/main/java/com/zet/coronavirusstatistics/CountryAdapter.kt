package com.zet.coronavirusstatistics

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.AsyncListDiffer
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.zet.coronavirusstatistics.databinding.ItemBinding
import com.zet.coronavirusstatistics.entity.CountyDto

class CountryAdapter : RecyclerView.Adapter<CountryAdapter.RvViewHolder>() {
    inner class RvViewHolder(val binding: ItemBinding) : RecyclerView.ViewHolder(binding.root)

    private val differCallBack = object : DiffUtil.ItemCallback<CountyDto>() {
        override fun areItemsTheSame(
            oldItem: CountyDto,
            newItem: CountyDto
        ): Boolean = oldItem.country == newItem.country

        override fun areContentsTheSame(
            oldItem: CountyDto,
            newItem: CountyDto
        ): Boolean = oldItem == newItem
    }

    val differ = AsyncListDiffer(this, differCallBack)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RvViewHolder {
        val binding = ItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return RvViewHolder(binding)
    }

    override fun onBindViewHolder(holder: RvViewHolder, position: Int) {
        val item = differ.currentList[position]
        holder.binding.apply {
            text.text = item.country

            root.setOnClickListener {
                onItemClickListener?.invoke(item)
            }
        }
    }

    override fun getItemCount(): Int {
        return differ.currentList.size
    }

    private var onItemClickListener: ((CountyDto) -> Unit)? = null

    fun setOnItemClickListener(listener: (CountyDto) -> Unit) {
        onItemClickListener = listener
    }
}