package dev.steve.meditaccompose.presentation.home.Detail.ItemInfo

import androidx.annotation.StringRes
import androidx.navigation.NamedNavArgument
import dev.steve.meditaccompose.Data.Medico
import dev.steve.meditaccompose.Data.categoria
import dev.steve.meditaccompose.Data.comentari


data class InfoState (
    var medico: Medico?=null,
    val listCategories: List<categoria>?=null,
    val listComment: List<comentari>?=null,
    @StringRes val errorMessage : Int?=null)

