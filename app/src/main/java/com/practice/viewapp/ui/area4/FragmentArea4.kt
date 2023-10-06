package com.practice.viewapp.ui.area4

import BottomSheetView
import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.practice.viewapp.R
import com.practice.viewapp.data.Holiday
import com.practice.viewapp.databinding.FragmentArea4Binding
import com.practice.viewapp.service.RestApiService
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

// 영역 3 에 대한 화면
class FragmentArea4 : Fragment() {

    private var _binding: FragmentArea4Binding? = null

    private val binding get() = _binding!!

    private val retrofit = Retrofit.Builder()
        .baseUrl("https://date.nager.at/")
        .addConverterFactory(GsonConverterFactory.create())
        .build()

    private val restApiService = retrofit.create(RestApiService::class.java)

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        _binding = FragmentArea4Binding.inflate(inflater, container, false)
        val root: View = binding.root

        // 확인 버튼 누르면 사용자가 입력한 year, locale 를 사용해 api 호출
        binding.okButton.setOnClickListener {
            restApiService.getHolidays(binding.editYear.text.toString(), binding.editLocale.text.toString())
                .enqueue(object: Callback<List<Holiday>> {
                    override fun onResponse(
                        call: Call<List<Holiday>>,
                        response: Response<List<Holiday>>
                    ) {
                        if(response.isSuccessful.not()){
                            binding.horizontalRecyclerView.adapter = null
                            return
                        }

                        response.body()?.let{ holidayList ->
                            Log.d("OK", it.toString())

                            // 성공적으로 api 호출이 완료되면 holiday list를 RecyclerView 에 넣어서 보여준다.
                            binding.horizontalRecyclerView.adapter = MyAdapter(holidayList)

                        } ?: run {
                            Log.d("NG", "body is null")
                        }
                    }

                    override fun onFailure(call: Call<List<Holiday>>, t: Throwable) {
                        Log.e("ERROR", t.toString())
                        //Toast.makeText(this@FragmentArea4, t.toString(), Toast.LENGTH_LONG).show()
                    }

                })

            val imm = requireActivity().getSystemService(Context.INPUT_METHOD_SERVICE) as? InputMethodManager
            imm?.hideSoftInputFromWindow(it.windowToken, 0)
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


    private class MyAdapter(private val dataList: List<Holiday>) :
        RecyclerView.Adapter<MyAdapter.ViewHolder>() {

        class ViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
            val textViewLocalName: TextView = itemView.findViewById(R.id.textLocalName)
            val textViewName: TextView = itemView.findViewById(R.id.textName)
        }

        override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
            val view = LayoutInflater.from(parent.context)
                .inflate(R.layout.holiday_item, parent, false)
            return ViewHolder(view)
        }

        override fun onBindViewHolder(holder: ViewHolder, position: Int) {
            val data = dataList[position]
            holder.textViewLocalName.text = data.localName
            holder.textViewName.text = data.name
        }

        override fun getItemCount() = dataList.size
    }
}