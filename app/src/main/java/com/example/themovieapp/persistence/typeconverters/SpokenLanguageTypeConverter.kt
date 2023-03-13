package com.example.themovieapp.persistence.typeconverters

import androidx.room.TypeConverter
import com.example.themovieapp.data.vos.SpokenLanguageVO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.util.Objects

class SpokenLanguageTypeConverter {
    @TypeConverter
    fun toString(spokenLanguages: List<SpokenLanguageVO>?): String{
        return Gson().toJson(spokenLanguages)
    }

    @TypeConverter
    fun toSpokenLanguages(spokenLanguagesJsonString: String): List<SpokenLanguageVO>?{
        val spokenLanguagesListType = object : TypeToken<List<SpokenLanguageVO>?>(){}.type
        return Gson().fromJson(spokenLanguagesJsonString, spokenLanguagesListType)
    }
}