package com.example.bangkitcapstone.core.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.bangkitcapstone.R
import com.example.bangkitcapstone.core.model.Case
import com.example.bangkitcapstone.databinding.ItemListCaseBinding
import com.example.bangkitcapstone.ui.feature.report.ReportFragmentDirections

class ReportCaseAdapter(private val caseList: ArrayList<Case>) :
    RecyclerView.Adapter<ReportCaseAdapter.ListViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListViewHolder =
        ListViewHolder(
            LayoutInflater.from(parent.context).inflate(R.layout.item_list_case, parent, false)
        )

    override fun onBindViewHolder(holder: ListViewHolder, position: Int) {
        val case = caseList[position]
        holder.bind(case)
    }

    override fun getItemCount(): Int = caseList.size

    inner class ListViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        private val binding = ItemListCaseBinding.bind(itemView)

        fun bind(case: Case) {
            itemView.setOnClickListener {
                val action = ReportFragmentDirections.actionReportFragmentToCaseDetailFragment(case)
                itemView.findNavController().navigate(action)
            }

            val image = when ((1..5).random()) {
                1 -> R.drawable.image_1
                2 -> R.drawable.image_2
                3 -> R.drawable.image_3
                else -> R.drawable.image_4
            }
            with(binding) {
                Glide.with(itemView.context)
                    .load(image)
                    .into(imgCase)
                tvName.text = case.name
                tvType.text = when (case.type) {
                    "1" -> "Pemerkosaan"
                    "2" -> "Perdagangan/Prostitusi"
                    "3" -> "KDRT"
                    "4" -> "Bully"
                    "5" -> "Body Shaming"
                    "6" -> "Rasisme"
                    else -> "Violence"
                }
                tvDescription.text = case.description
            }
        }
    }
}