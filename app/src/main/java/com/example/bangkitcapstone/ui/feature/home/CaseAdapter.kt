package com.example.bangkitcapstone.ui.feature.home

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bangkitcapstone.R
import com.example.bangkitcapstone.databinding.ItemListCaseBinding

class CaseAdapter : RecyclerView.Adapter<CaseAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_case, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        holder.bind()
    }

    override fun getItemCount(): Int = 5

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListCaseBinding.bind(itemView)

        fun bind() {
            with(binding) {
                Glide.with(itemView.context)
                    .load("https://static.wikia.nocookie.net/solo-leveling/images/5/59/Jin-Woo_Profile.png/revision/latest/scale-to-width-down/340?cb=20200208070835")
                    .into(imgCase)
                tvTitle.text = "Bullying "
                tvDescription.text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit. Curabitur mattis."
                tvStatus.text = "Accepted"
            }
        }
    }
}