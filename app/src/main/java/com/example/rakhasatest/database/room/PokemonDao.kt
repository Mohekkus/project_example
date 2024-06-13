package com.example.rakhasatest.database.room

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query


@Dao
interface PokemonDao {
    @Query("SELECT * FROM PokemonEntity")
    fun getAll(): List<PokemonEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insert(entity: PokemonEntity)

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    fun insertAll(entities: List<PokemonEntity>)

    @Query("SELECT COUNT(*) FROM PokemonEntity")
    fun count(): Int

    @Delete
    fun delete(entity: PokemonEntity)


}