package com.practice.viewapp.ui.area1

import ImageAdapter
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewpager2.widget.ViewPager2
import com.practice.viewapp.R
import com.practice.viewapp.databinding.FragmentArea1Binding
import java.util.Timer
import kotlin.concurrent.fixedRateTimer

// 영역 1 에 대한 화면
class FragmentArea1 : Fragment() {

    private var _binding: FragmentArea1Binding? = null
    private var timer: Timer? = null

    private val binding get() = _binding!!

    // 이미지 리스트
    private val images = listOf(
        R.drawable.img_5, // dummy 자연스러운 반복을 위한 더미 이미지를 양쪽에 추가
        R.drawable.img_1,
        R.drawable.img_2,
        R.drawable.img_3,
        R.drawable.img_4,
        R.drawable.img_5,
        R.drawable.img_1 // dummy
    )

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArea1Binding.inflate(inflater, container, false)
        val root: View = binding.root

        val viewPager: ViewPager2 = binding.viewPager
        viewPager.adapter = ImageAdapter(images)

        autoScroll(viewPager)

        return root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.viewPager.setCurrentItem(1, false)

        // 5번째 이미지에서 1번째 이미지로 이동시 자연스러운 페이징 동작을 위한 코드
        binding.viewPager.registerOnPageChangeCallback(object : ViewPager2.OnPageChangeCallback() {
            override fun onPageSelected(position: Int) {
                val handler = Handler(Looper.getMainLooper())
                val update = Runnable {
                    if (position == 0) {
                        binding.viewPager.setCurrentItem(images.size - 2, false)
                    } else if (position == images.size - 1) {
                        binding.viewPager.setCurrentItem(1, false)
                    }
                }

                handler.postDelayed(update, 300)
            }
        })
    }

    // 5초 자동 스크롤 함수
    private fun autoScroll(viewPager: ViewPager2) {
        val handler = Handler(Looper.getMainLooper())
        val update = Runnable {
            viewPager.setCurrentItem(viewPager.currentItem + 1, true)
        }

        timer = fixedRateTimer("timer", false, 5000L, 5000) {
            handler.post(update)
        }
    }

    // 영역 1 나갈때 타이머 종료 해줘야함
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null

        timer?.cancel()
        timer = null
    }
}