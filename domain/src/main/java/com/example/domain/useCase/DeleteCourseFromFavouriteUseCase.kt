package com.example.domain.useCase

import com.example.domain.models.CourseModel
import com.example.domain.repository.CoursesRepository

class DeleteCourseFromFavouriteUseCase (
    private val repository: CoursesRepository
) {
    suspend operator fun invoke(courseModel: CourseModel) {
        repository.deleteCourseFromFavourites(courseModel)
    }
}