package com.payback.interviewapp.dashboard.data.model

import com.google.gson.annotations.SerializedName

internal data class PixabayHit(
    @SerializedName("id")
    val id: Int,
    @SerializedName("pageURL")
    val pageURL: String,
    @SerializedName("type")
    val type: String,
    @SerializedName("tags")
    val tags: String,
    @SerializedName("previewURL")
    val previewURL: String,
    @SerializedName("previewWidth")
    val previewWidth: Int,
    @SerializedName("previewHeight")
    val previewHeight: Int,
    @SerializedName("webformatURL")
    val webformatURL: String,
    @SerializedName("webformatWidth")
    val webformatWidth: Int,
    @SerializedName("webformatHeight")
    val webformatHeight: Int,
    @SerializedName("largeImageURL")
    val largeImageURL: String,
    @SerializedName("fullHDURL")
    val fullHDURL: String?,
    @SerializedName("imageURL")
    val imageURL: String,
    @SerializedName("imageWidth")
    val imageWidth: Int,
    @SerializedName("imageHeight")
    val imageHeight: Int,
    @SerializedName("imageSize")
    val imageSize: Int,
    @SerializedName("views")
    val views: Int,
    @SerializedName("downloads")
    val downloads: Int,
    @SerializedName("likes")
    val likes: Int,
    @SerializedName("comments")
    val comments: Int,
    @SerializedName("user_id")
    val userId: Int,
    @SerializedName("user")
    val user: String,
    @SerializedName("userImageURL")
    val userImageURL: String,
)

internal val mockPixabayHit = PixabayHit(
    id = 123,
    pageURL = "https://example.com/page",
    type = "photo",
    tags = "nature, water",
    previewURL = "https://example.com/preview.jpg",
    previewWidth = 150,
    previewHeight = 100,
    webformatURL = "https://example.com/webformat.jpg",
    webformatWidth = 600,
    webformatHeight = 400,
    largeImageURL = "https://example.com/large.jpg",
    fullHDURL = "https://example.com/fullhd.jpg",
    imageURL = "https://example.com/image.jpg",
    imageWidth = 1920,
    imageHeight = 1080,
    imageSize = 1048576,
    views = 1000,
    downloads = 200,
    likes = 50,
    comments = 10,
    userId = 1,
    user = "test_user",
    userImageURL = "https://example.com/user.jpg"
)
