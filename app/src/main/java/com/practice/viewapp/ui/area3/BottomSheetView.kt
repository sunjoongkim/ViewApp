import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import com.google.android.material.bottomsheet.BottomSheetDialogFragment
import com.practice.viewapp.R

// 영역 3 의 하단 알림창 화면
class BottomSheetView : BottomSheetDialogFragment() {

    var buttonText: String? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.bottom_sheet_view, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        // 선택한 버튼에 대한 textView
        val textView: TextView = view.findViewById(R.id.textView)
        textView.text = "$buttonText 선택"
    }
}
