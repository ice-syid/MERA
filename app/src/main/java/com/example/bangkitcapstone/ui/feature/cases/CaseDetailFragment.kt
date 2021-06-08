package com.example.bangkitcapstone.ui.feature.cases

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.navArgs
import com.bumptech.glide.Glide
import com.example.bangkitcapstone.R
import com.example.bangkitcapstone.core.model.Case
import com.example.bangkitcapstone.databinding.FragmentCaseDetailBinding

class CaseDetailFragment : Fragment() {

    private lateinit var case: Case
    private lateinit var type: String
    private val args: CaseDetailFragmentArgs by navArgs()
    private var _binding: FragmentCaseDetailBinding? = null
    private val binding get() = _binding as FragmentCaseDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        case = args.case
        type = when (case.type) {
            "1" -> "Pemerkosaan"
            "2" -> "Perdagangan/Prostitusi"
            "3" -> "KDRT"
            "4" -> "Bully"
            "5" -> "Body Shaming"
            "6" -> "Rasisme"
            else -> "Violence"
        }
        (activity as AppCompatActivity?)?.supportActionBar?.title = type
    }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentCaseDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val image = when ((1..5).random()) {
            1 -> R.drawable.image_1
            2 -> R.drawable.image_2
            3 -> R.drawable.image_3
            else -> R.drawable.image_4
        }
        with(binding) {
            Glide.with(root).load(image).into(imageCase)
            tvName.text = case.name
            tvAddress.text = case.address
            tvGender.text = case.gender
            tvDate.text = case.date_birth
            tvType.text = type
            tvDescription.text = case.description
            tvDateCase.text = case.date_case
            tvStatus.text = case.status
        }
    }
}