package com.khalid.thmanyah

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.ui.Modifier
import com.khalid.core_ui.theme.KhalidThmanyahTheme
import com.khalid.thmanyah.graphs.RootGraph
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            KhalidThmanyahTheme {
                RootGraph(
                    modifier = Modifier.fillMaxSize()
                )
            }
        }
    }
}