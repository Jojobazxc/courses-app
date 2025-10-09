package com.example.presentation.ui.data

data class CourseItem(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rating: String,
    var startDate: String,
    val publishDate: String,
    val isLiked: Boolean,
)