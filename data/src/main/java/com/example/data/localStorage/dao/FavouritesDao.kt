package com.example.data.localStorage.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.example.data.localStorage.entities.FavouritesCoursesEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface FavouritesDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(course: FavouritesCoursesEntity)

    @Delete
    suspend fun delete(course: FavouritesCoursesEntity)

    @Query("SELECT * FROM FavouritesCoursesEntity")
    fun getAllFavourites(): Flow<List<FavouritesCoursesEntity>>

}