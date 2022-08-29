package com.navi.github_app.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.ViewModelProvider
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.navi.github_app.R
import com.navi.github_app.databinding.FragmentFilterBottomSheetFragmentBinding
import com.navi.github_app.ui.homescreen.viewmodel.HomeScreenViewModel

class FilterBottomSheetFragment() : BottomSheetDialogFragment() {
    private lateinit var binding: FragmentFilterBottomSheetFragmentBinding
    private val viewModel: HomeScreenViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentFilterBottomSheetFragmentBinding.inflate(layoutInflater,container,false)
        binding.filterApplyButton.setOnClickListener {
            Log.d("TEST", getPrStatus())
            viewModel.setStatus(getPrStatus())
            dismiss()
        }
        return binding.root
    }

    private fun getPrStatus(): String {
        val showOpenPr = binding.prStatusOpenCheckbox.isChecked
        val showClosedPr = binding.prStatusClosedCheckbox.isChecked
        if(showOpenPr && showClosedPr){
            return "all"
        }
        if(showOpenPr){
            return "open"
        }
        if(showClosedPr){
            return "closed"
        }
        return "all"
    }
}