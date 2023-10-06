package com.practice.viewapp.ui.area2

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.webkit.WebView
import android.webkit.WebViewClient
import androidx.fragment.app.Fragment
import com.practice.viewapp.databinding.FragmentArea2Binding

// 영역 2 에 대한 화면
class FragmentArea2 : Fragment() {

    private var _binding: FragmentArea2Binding? = null

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArea2Binding.inflate(inflater, container, false)
        val root: View = binding.root

        // web 화면을 보여줄 view를 선언
        val webView: WebView = binding.webView
        webView.webViewClient = WebViewClient()

        // 확인 버튼을 누르면 webView에 url 호출해 보여줌
        binding.goButton.setOnClickListener {
            val url = binding.urlEditText.text.toString()
            if (url.isNotEmpty()) {
                webView.loadUrl(url)

                val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
                imm?.hideSoftInputFromWindow(it.windowToken, 0)
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}