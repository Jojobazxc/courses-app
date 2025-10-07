package com.example.domain.models

data class CoursesModel(
    val courses: List<CourseModel>
)

data class CourseModel(
    val id: Int,
    val title: String,
    val description: String,
    val price: String,
    val rating: String,
    val startDate: String,
    val isLiked: Boolean,
    val publishDate: String
)