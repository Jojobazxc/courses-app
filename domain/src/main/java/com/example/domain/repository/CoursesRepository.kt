package com.example.domain.repository

import com.example.domain.models.CourseModel
import com.example.domain.models.CoursesModel
import kotlinx.coroutines.flow.Flow

interface CoursesRepository {
    suspend fun getCourses(): Result<CoursesModel>
    suspend fun addCoursesToFavourites(courseModel: CourseModel)
    fun getFavouriteCourses(): Flow<List<CourseModel>>
    suspend fun deleteCourseFromFavourites(courseModel: CourseModel)
}