package com.example.projet.presentation.list

sealed class HockeyModel

data class HockeySuccess(val hockeyList: List<Hockey>) : HockeyModel()
object HockeyLoader : HockeyModel()
object HockeyError : HockeyModel()