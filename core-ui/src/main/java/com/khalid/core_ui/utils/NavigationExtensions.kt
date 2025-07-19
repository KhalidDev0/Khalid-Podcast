package com.khalid.core_ui.utils

import androidx.navigation.NavHostController

fun <T : Any> NavHostController.navigateSingleTop(route: T) {
    navigate(route) {
        launchSingleTop = true
    }
}