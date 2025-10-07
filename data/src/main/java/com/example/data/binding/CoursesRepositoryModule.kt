package com.example.data.binding

import com.example.data.reposImpl.CoursesReposImpl
import com.example.domain.repository.CoursesRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class CoursesRepositoryModule {
    @Binds
    abstract fun bindCoursesRepository(
        impl: CoursesReposImpl
    ): CoursesRepository
}