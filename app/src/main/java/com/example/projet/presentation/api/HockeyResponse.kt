package com.example.projet.presentation.api

import com.example.projet.presentation.list.Hockey

class HockeyResponse (
    val count: Int,
    val next: String,
    val results: List<Hockey>
)