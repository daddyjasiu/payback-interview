package com.payback.interviewapp.details.ui.mapper

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Parcelize
@Immutable
internal data class UiDashboardItem (
    val url: String,
    val username: String,
    val tags: List<String>
) : Parcelable