package com.payback.interviewapp.dashboard.ui.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize
import javax.annotation.concurrent.Immutable

@Parcelize
@Immutable
internal data class UiDashboardItem(
    val id: Int,
    val thumbnailUrl: String,
    val largeImageUrl: String,
    val username: String,
    val tags: List<String>,
    val likes: Int,
    val downloads: Int,
    val comments: Int,
) : Parcelable

internal val mockUiDashboardItem = UiDashboardItem(
    id = 195893,
    thumbnailUrl = "https://cdn.pixabay.com/photo/2022/12/26/13/50/flower-7679117_150.jpg",
    largeImageUrl = "https://pixabay.com/get/g46e786b5b1dfcc4fe3570a52a7edf117f0582c17772a233c47e96744fb780e1b38d8d7e03f20f4b22879ed06ca23fd997d6bc352475c53ed5e462a2c688eec97_1280.jpg",
    username = "Alfred_Grupstra",
    tags = "flower, nature, stamens".split(", "),
    likes = 111,
    downloads = 4729,
    comments = 19,
)