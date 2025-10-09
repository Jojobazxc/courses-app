package com.example.presentation.ui.data

import com.example.domain.models.CourseModel

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

fun CourseModel.toCourseItem() = CourseItem(
    id = this.id,
    title = this.title,
    description = this.description,
    price = this.price,
    rating = this.rating,
    startDate = this.startDate,
    isLiked = this.isLiked,
    publishDate = this.publishDate
)

fun CourseItem.toDomainModel(): CourseModel = CourseModel(
    id = id,
    title = title,
    description = description,
    price = price,
    rating = rating,
    startDate = startDate,
    isLiked = isLiked,
    publishDate = publishDate
)