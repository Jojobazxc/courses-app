package com.example.domain.useCase

import com.example.domain.models.CourseModel
import com.example.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow

class GetFavouritesCourseUseCase(
    private val repository: CoursesRepository
) {
    operator fun invoke(): Flow<List<CourseModel>> {
        return repository.getFavouriteCourses()
    }
}