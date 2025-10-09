package com.example.data.reposImpl

import android.util.Log
import com.example.data.localStorage.dao.FavouritesDao
import com.example.data.localStorage.entities.toDomainModel
import com.example.data.network.api.CoursesApi
import com.example.data.network.models.toEntity
import com.example.data.network.models.toModel
import com.example.domain.models.CourseModel
import com.example.domain.models.CoursesModel
import com.example.domain.repository.CoursesRepository
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import java.io.IOException
import javax.inject.Inject

class CoursesReposImpl @Inject constructor(
    private val coursesApi: CoursesApi,
    private val favouritesDao: FavouritesDao
) : CoursesRepository {
    override suspend fun getCourses(): Result<CoursesModel> {
        return try {
            val coursesResponse = coursesApi.getCourses()

            if (coursesResponse.isSuccessful && coursesResponse.body() != null) {
                Log.d("DATA Request", coursesResponse.body()!!.courses.toString())
                Result.success(coursesResponse.body()!!.toModel())
            } else {
                Log.d("DATA Request", "$coursesResponse")
                Result.failure(Throwable("Невозможно загрузить курсы"))
            }

        } catch (e: Exception) {
            Log.d("DATA Request", "${e.message}")
            Result.failure(Throwable(e.message))
        }
    }

    override suspend fun addCoursesToFavourites(courseModel: CourseModel) {
        try {
            favouritesDao.insert(courseModel.toEntity())
            Log.e("Room","Добавлен курс в избранное")
        } catch (e: Exception) {
            Log.e("Room", e.message.toString())
        }
    }

    override fun getFavouriteCourses(): Flow<List<CourseModel>> {
        return favouritesDao.getAllFavourites()
            .map { entities -> entities.map { it.toDomainModel() } }
            .catch { emit(emptyList()) }
    }

    override suspend fun deleteCourseFromFavourites(courseModel: CourseModel) {
        try {
            favouritesDao.delete(courseModel.toEntity())
        } catch (e: Exception) {
            Log.e("Room", e.message.toString())
        }
    }
}