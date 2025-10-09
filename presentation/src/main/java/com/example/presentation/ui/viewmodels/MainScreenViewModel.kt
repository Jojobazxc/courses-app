package com.example.presentation.ui.viewmodels

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CourseModel
import com.example.domain.models.CoursesModel
import com.example.domain.useCase.AddCourseToFavouriteUseCase
import com.example.domain.useCase.DeleteCourseFromFavouriteUseCase
import com.example.domain.useCase.GetCoursesUseCase
import com.example.presentation.ui.data.CourseItem
import com.example.presentation.ui.data.toDomainModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import java.text.SimpleDateFormat
import java.time.LocalDate
import java.time.format.DateTimeFormatter
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class MainScreenViewModel @Inject constructor(
    private val getCoursesUseCase: GetCoursesUseCase,
    private val addCourseToFavouriteUseCase: AddCourseToFavouriteUseCase,
    private val deleteCourseFromFavouriteUseCase: DeleteCourseFromFavouriteUseCase,
) : ViewModel() {


    private val _courses = MutableStateFlow<List<CourseItem>>(emptyList())
    var courses = _courses.asStateFlow()

    init {
        getCourses()
    }

    private fun getCourses() {
        viewModelScope.launch(Dispatchers.Main) {

            withContext(Dispatchers.IO) {
                getCoursesUseCase()
            }.onSuccess { result ->
                val presentationData = mapDomainToPresentation(result)
                _courses.value = presentationData.map {
                    it.copy(startDate = formatData(it.startDate), price = "${it.price} ₽")
                }
                Log.d("UI Data", "${_courses.value}")
                addInitialFavouritesIfNeeded()
            }.onFailure { exception ->
                _courses.value = emptyList()
            }
        }
    }

    private fun addInitialFavouritesIfNeeded() {
        val initialFavourites = _courses.value.filter { it.isLiked }
        viewModelScope.launch(Dispatchers.IO) {
            initialFavourites.forEach {
                addCourseToFavouriteUseCase(it.toDomainModel())
            }
        }
    }

    fun addFavourites(id: Int, isLiked: Boolean) {
        if (_courses.value.isNotEmpty()) {
            _courses.value = _courses.value.map {
                if (it.id == id) it.copy(isLiked = isLiked) else it
            }
            val course = _courses.value.find { it.id == id }

            if (course != null) {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        if (!isLiked) {
                            deleteCourseFromFavouriteUseCase(
                                course.toDomainModel()
                            )
                            Log.e("Room", "Удаление")
                        } else {
                            addCourseToFavouriteUseCase(
                                course.toDomainModel()
                            )
                            Log.e("Room", "Добавлен курс в избранное")
                        }
                    }
                }
            }

        }

    }

    fun sortCoursesByData() {
        _courses.value = _courses.value.sortedByDescending {
            parseDate(it.publishDate)
        }
    }

    private fun parseDate(date: String): LocalDate {
        val inputFormat = DateTimeFormatter.ofPattern("yyyy-DD-mm")
        return LocalDate.parse(date, inputFormat)
    }

    private fun formatData(date: String): String {
        return try {
            val inputFormat = SimpleDateFormat("yyyy-MM-dd", Locale.getDefault())
            val outputFormat = SimpleDateFormat("d MMMM yyyy", Locale("ru"))
            outputFormat.format(inputFormat.parse(date)!!)
        } catch (e: NullPointerException) {
            date
        }

    }

    private fun mapDomainToPresentation(domain: CoursesModel): List<CourseItem> =
        domain.courses.map {
            CourseItem(
                id = it.id,
                title = it.title,
                description = it.description,
                price = it.price,
                rating = it.rating,
                startDate = it.startDate,
                isLiked = it.isLiked,
                publishDate = it.publishDate
            )
        }

}