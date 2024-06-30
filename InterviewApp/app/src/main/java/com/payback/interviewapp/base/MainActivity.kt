package com.payback.interviewapp.base

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.navigation.NavHostController
import com.payback.interviewapp.base.navigation.AppNavHost
import com.payback.interviewapp.base.theme.InterviewAppTheme
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @Inject
    lateinit var navController: NavHostController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            InterviewAppTheme {
                AppNavHost(navController = navController)
            }
        }
    }
}
