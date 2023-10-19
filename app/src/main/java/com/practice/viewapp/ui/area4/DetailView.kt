package com.practice.viewapp.ui.area4

import android.util.Log
import android.view.View
import androidx.core.view.isVisible
import com.bumptech.glide.Glide
import com.practice.viewapp.data.Holiday
import com.practice.viewapp.databinding.HolidayDetailBinding

class DetailView(private val binding: HolidayDetailBinding, private val showBottomSheet: (String) -> Unit) {

    private val DEFAULT_URL = "https://cdn.pixabay.com/photo/2021/08/03/07/03/orange-6518675_960_720.jpg"

    fun isVisible(): Boolean {
        return binding.root.isVisible
    }
    fun visible(visible: Boolean, info: Holiday? = null) {
        binding.root.visibility = if (visible) View.VISIBLE else View.INVISIBLE

        if (visible) {
            Log.e("@@@@@", "========> info : ${info?.date}, ${info?.name}, ${info?.localName}, ${info?.url}")
            if (info == null) return

            binding.textDate.text = info?.date
            binding.textLocalName.text = info?.localName
            binding.textName.text = info?.name
            // name 클릭시 하단 팝업 생성
            binding.textName.setOnClickListener {
                showBottomSheet(info?.name!!)
            }
            // holiday info의 url 이 값이 없으면 DEFAULT_URL, 있으면 해당 url의 이미지를 보여줌
            Glide.with(binding.root)
                .load(if (info.url == null) DEFAULT_URL else info?.url)
                .into(binding.imageView);
        }
    }
}