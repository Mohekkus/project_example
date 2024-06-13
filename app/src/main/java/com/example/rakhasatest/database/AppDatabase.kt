package com.example.rakhasatest.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.rakhasatest.database.converter.SpritesTypeConverter
import com.example.rakhasatest.database.room.PokemonDao
import com.example.rakhasatest.database.room.PokemonEntity

@Database(entities = [PokemonEntity::class], version = 1)
@TypeConverters(value = [SpritesTypeConverter::class])
abstract class AppDatabase: RoomDatabase() {
    abstract fun pokemonDao(): PokemonDao
}

object Database {
    fun getInstance(context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "pokemon.db"
        )
            .build()
    }
}