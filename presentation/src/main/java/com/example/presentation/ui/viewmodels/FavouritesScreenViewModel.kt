package com.example.presentation.ui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.domain.models.CourseModel
import com.example.domain.useCase.DeleteCourseFromFavouriteUseCase
import com.example.domain.useCase.GetFavouritesCourseUseCase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject

@HiltViewModel
class FavouritesScreenViewModel @Inject constructor(
    private val getFavouritesCourseUseCase: GetFavouritesCourseUseCase,
    private val deleteCourseFromFavouriteUseCase: DeleteCourseFromFavouriteUseCase,
) : ViewModel() {

    val favourites = getFavouritesCourseUseCase().stateIn(
        viewModelScope,
        SharingStarted.WhileSubscribed(5000),
        emptyList()
    )

    fun deleteFromFavourites(id: Int) {
        if (favourites.value.isNotEmpty()) {
            val course = favourites.value.find { it.id == id }
            if (course != null) {
                viewModelScope.launch {
                    withContext(Dispatchers.IO) {
                        deleteCourseFromFavouriteUseCase(course)
                    }
                }
            }
        }
    }

}