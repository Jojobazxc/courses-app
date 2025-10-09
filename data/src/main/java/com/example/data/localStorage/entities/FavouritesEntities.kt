package com.example.data.localStorage.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.domain.models.CourseModel
import com.example.domain.models.CoursesModel

@Entity
data class FavouritesCoursesEntity(
    @PrimaryKey val id: Int,
    val title: String,
    val text: String,
    val price: String,
    val rate: String,
    val startDate: String,
    val haseLike: Boolean,
    val publishDate: String,
)

fun FavouritesCoursesEntity.toDomainModel(): CourseModel =
    CourseModel(
        id = id,
        title = title,
        description = text,
        price = price,
        rating = rate,
        startDate = startDate,
        isLiked = haseLike,
        publishDate = publishDate
    )