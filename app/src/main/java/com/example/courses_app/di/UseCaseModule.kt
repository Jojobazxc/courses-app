package com.example.courses_app.di

import com.example.domain.repository.CoursesRepository
import com.example.domain.useCase.AddCourseToFavouriteUseCase
import com.example.domain.useCase.DeleteCourseFromFavouriteUseCase
import com.example.domain.useCase.GetCoursesUseCase
import com.example.domain.useCase.GetFavouritesCourseUseCase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
object UseCaseModule {

    @Provides
    fun provideGetCoursesUseCase(
        repository: CoursesRepository
    ): GetCoursesUseCase = GetCoursesUseCase(repository)

    @Provides
    fun provideDeleteCourseFromFavouriteUseCase(
        repository: CoursesRepository
    ): DeleteCourseFromFavouriteUseCase = DeleteCourseFromFavouriteUseCase(repository)

    @Provides
    fun provideAddCourseToFavouriteUseCase(
        repository: CoursesRepository
    ): AddCourseToFavouriteUseCase = AddCourseToFavouriteUseCase(repository)

    @Provides
    fun provideGetFavouritesCourseUseCase(
        repository: CoursesRepository
    ): GetFavouritesCourseUseCase = GetFavouritesCourseUseCase(repository)


}