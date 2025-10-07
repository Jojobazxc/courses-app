package com.example.domain.repository

import com.example.domain.models.CoursesModel

interface CoursesRepository {
    suspend fun getCourses(): Result<CoursesModel>
}