package com.practice.viewapp.ui.area3

import BottomSheetView
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import com.practice.viewapp.databinding.FragmentArea3Binding

// 영역 3 에 대한 화면
class FragmentArea3 : Fragment() {

    private var _binding: FragmentArea3Binding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArea3Binding.inflate(inflater, container, false)
        val root: View = binding.root

        // 버튼 1, 2, 3 을 클릭할때 하단 알림창 띄우며 text 전달
        binding.button1.setOnClickListener { showBottomSheet(binding.button1.text.toString()) }
        binding.button2.setOnClickListener { showBottomSheet(binding.button2.text.toString()) }
        binding.button3.setOnClickListener { showBottomSheet(binding.button3.text.toString()) }

        return root
    }

    // 하단 알림창 띄우는 함수
    private fun showBottomSheet(buttonText: String) {
        val bottomSheetFragment = BottomSheetView()
        bottomSheetFragment.buttonText = buttonText
        bottomSheetFragment.show(requireFragmentManager(), bottomSheetFragment.tag)
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}