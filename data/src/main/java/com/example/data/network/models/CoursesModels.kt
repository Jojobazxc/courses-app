package com.example.data.network.models

import com.example.data.localStorage.entities.FavouritesCoursesEntity
import com.example.domain.models.CourseModel
import com.example.domain.models.CoursesModel
import com.google.gson.annotations.SerializedName

data class CoursesResponse(
    @SerializedName("courses") val courses: List<Course>
)

data class Course(
    @SerializedName("id") val id: Int,
    @SerializedName("title") val title: String,
    @SerializedName("text") val description: String,
    @SerializedName("price") val price: String,
    @SerializedName("rate") val rating: String,
    @SerializedName("startDate") val startDate: String,
    @SerializedName("hasLike") val isLiked: Boolean,
    @SerializedName("publishDate") val publishDate: String
)

fun CourseModel.toEntity(): FavouritesCoursesEntity = FavouritesCoursesEntity(
    id = id,
    title = title,
    text = title,
    price = price,
    rate = rating,
    startDate = startDate,
    haseLike = isLiked,
    publishDate = publishDate
)

fun CoursesResponse.toModel(): CoursesModel =
    CoursesModel(
        courses = courses.map { it.toModel() }
    )

fun Course.toModel(): CourseModel =
    CourseModel(
        id = id,
        title = title,
        description = description,
        price = price,
        rating = rating,
        startDate = startDate,
        isLiked = isLiked,
        publishDate = publishDate
    )