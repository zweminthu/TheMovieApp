package com.example.themovieapp.network.responses

import com.example.themovieapp.data.vos.ActorVO
import com.google.gson.annotations.SerializedName

data class ActorsResponse(
    @SerializedName("results")
    val results : List<ActorVO>?
)
