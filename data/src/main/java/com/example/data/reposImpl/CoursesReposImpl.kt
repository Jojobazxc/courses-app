package com.example.data.reposImpl

import com.example.data.network.api.CoursesApi
import com.example.data.network.models.toModel
import com.example.domain.models.CoursesModel
import com.example.domain.repository.CoursesRepository
import javax.inject.Inject

class CoursesReposImpl @Inject constructor(
    private val coursesApi: CoursesApi
) : CoursesRepository {
    override suspend fun getCourses(): Result<CoursesModel> {
        return try {
            val coursesResponse = coursesApi.getCourses()

            if (coursesResponse.isSuccessful && coursesResponse.body() != null) {
                Result.success(coursesResponse.body()!!.toModel())
            } else {
                Result.failure(Throwable("Невозможно загрузить курсы"))
            }

        } catch (e: Exception) {
            Result.failure(Throwable(e.message))
        }
    }
}