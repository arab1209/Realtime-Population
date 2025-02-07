package com.example.realtimepopulation.domain.usecase.main

import com.example.realtimepopulation.ui.util.Screen
import javax.inject.Inject

class GetScreenNavRouteUseCase @Inject constructor() {
    operator fun invoke(route: String?): Int {
        return when (route) {
            Screen.Home.route -> 0
            Screen.Map.route -> 1
            else -> 0
        }
    }
}