package com.machina.spotify_clone.model

class ParentModel<T>(
    val title : String = "",
    val childern: List<T>
)