package dev.steve.meditaccompose.presentation.home

import androidx.annotation.StringRes
import dev.steve.meditaccompose.Data.Medico
import dev.steve.meditaccompose.Data.anuncios
import dev.steve.meditaccompose.Data.categoria


data class HomeState(
    val typeUser:Int = 0,
    val listCategories: List<categoria>?=null,
    val listRecommend: List<Medico>?=null,
    val listLatest: List<Medico>?=null,
    val listHightlights: List<Medico>?=null,
    val listAdd: List<anuncios>?=null,
    val Add: anuncios?=null,
    val displayAdd:Boolean = false,
    @StringRes val errorMessage: Int?=null)