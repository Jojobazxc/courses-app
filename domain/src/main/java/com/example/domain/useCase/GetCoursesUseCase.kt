package com.example.domain.useCase

import com.example.domain.models.CoursesModel
import com.example.domain.repository.CoursesRepository

class GetCoursesUseCase(
    private val repository: CoursesRepository
) {
    suspend operator fun invoke(): Result<CoursesModel> {
        return repository.getCourses()
    }
}