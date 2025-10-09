package com.example.data.modules

import android.content.Context
import androidx.room.Room
import com.example.data.localStorage.AppDatabase
import com.example.data.localStorage.dao.FavouritesDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object RoomModule {

    @Provides
    @Singleton
    fun provideDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(
            context = context,
            klass = AppDatabase::class.java,
            "app_database"
        ).build()

    @Provides
    fun provideFavouritesDao(db: AppDatabase): FavouritesDao = db.favouritesDao()

}