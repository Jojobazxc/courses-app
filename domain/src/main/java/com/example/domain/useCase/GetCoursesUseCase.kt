package com.example.domain.useCase

import android.util.Log
import com.example.domain.models.CoursesModel
import com.example.domain.repository.CoursesRepository

class GetCoursesUseCase(
    private val repository: CoursesRepository
) {
    suspend operator fun invoke(): Result<CoursesModel> {
        Log.d("UseCase Request", "Requesting")
        return repository.getCourses()
    }
}