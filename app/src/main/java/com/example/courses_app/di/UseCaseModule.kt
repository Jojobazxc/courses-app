package com.example.courses_app.di

import com.example.domain.repository.CoursesRepository
import com.example.domain.useCase.GetCoursesUseCase
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

}