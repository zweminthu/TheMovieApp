package com.example.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.themovieapp.data.vos.GenreVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class GenreListTypeConverter {
    @TypeConverter
    fun toString(genres: List<GenreVO>?): String{
        return Gson().toJson(genres)
    }

    @TypeConverter
    fun toGenres(genresJsonString: String): List<GenreVO>?{
        val genresListType = object: TypeToken<List<GenreVO>?>() {}.type
        return Gson().fromJson(genresJsonString, genresListType)
    }
}