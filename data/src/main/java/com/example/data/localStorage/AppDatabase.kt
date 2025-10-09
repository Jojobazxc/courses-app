package com.example.data.localStorage

import androidx.room.Database
import androidx.room.RoomDatabase
import com.example.data.localStorage.dao.FavouritesDao
import com.example.data.localStorage.entities.FavouritesCoursesEntity

@Database(
    entities = [FavouritesCoursesEntity::class],
    version = 1
)
abstract class AppDatabase : RoomDatabase() {
    abstract fun favouritesDao(): FavouritesDao
}