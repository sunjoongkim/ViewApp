package com.practice.viewapp.ui.area5

import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.VideoView
import androidx.fragment.app.Fragment
import com.practice.viewapp.R
import com.practice.viewapp.databinding.FragmentArea5Binding

// 영역 5 에 대한 화면
class FragmentArea5 : Fragment() {

    private var _binding: FragmentArea5Binding? = null

    private val binding get() = _binding!!

    private lateinit var videoView: VideoView


    private val videoUrls = listOf(
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/BigBuckBunny.mp4",
        "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"
    )

    private val rawVideoIds = listOf(
        R.raw.video_1,
        R.raw.video_2
    )

    private var currentIndex = 0

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArea5Binding.inflate(inflater, container, false)
        val root: View = binding.root
        videoView = binding.videoView

        currentIndex = 0

        // 화면 진입시 video uri 0으로 설정
        videoView.setVideoURI(getVideoUri(currentIndex))

        // videoView가 영상 실행할 준비가 되면 start
        videoView.setOnPreparedListener {
            videoView.start()
        }

        // 영상 실행이 끝나면 다음 영상 재생
        videoView.setOnCompletionListener {
            if (currentIndex == 3) {
                currentIndex = 0
            } else {
                currentIndex++
            }
            videoView.setVideoURI(getVideoUri(currentIndex))
        }

        return root
    }

    private fun getVideoUri(index: Int) : Uri {
        return if (index < videoUrls.size) {
            Uri.parse(videoUrls[index])
        } else {
            Uri.parse("android.resource://${requireContext().packageName}/${rawVideoIds[index - videoUrls.size]}")
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}