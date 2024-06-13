package com.example.rakhasatest.database.converter

import androidx.room.ProvidedTypeConverter
import androidx.room.TypeConverter
import com.example.rakhasatest.app.model.PokemonDetailResponse
import com.example.rakhasatest.app.model.Sprites
import com.google.gson.Gson

class SpritesTypeConverter {
    @TypeConverter
    fun convertSpritesToString(response: Sprites): String {
        return Gson().toJson(response)
    }

    @TypeConverter
    fun convertStringToSprites(string: String): Sprites {
        return Gson().fromJson(string, Sprites::class.java)
    }
}