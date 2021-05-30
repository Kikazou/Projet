package com.example.projet.presentation.api

data class HockeyDetailResponse(
    val name: String,
    val types: List<HockeySlot>
)

data class HockeySlot(
    val slot: Int,
    val types: HockeyType
)

data class HockeyType(
    val name: String,
    val url: String
)