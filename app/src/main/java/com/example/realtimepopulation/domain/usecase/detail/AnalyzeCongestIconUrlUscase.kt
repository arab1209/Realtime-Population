package com.example.realtimepopulation.domain.usecase.detail

import android.util.Log
import javax.inject.Inject

class AnalyzeCongestIconUrlUscase @Inject constructor() {
    operator fun invoke(flag: Int): String {
        return if (flag == 0) "https://data.seoul.go.kr/SeoulRtd/images/icon/ico_future.png" else "https://data.seoul.go.kr/SeoulRtd/images/icon/ico_past.png"
    }
}