package dev.steve.meditaccompose.presentation.home

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.steve.meditaccompose.Data.anuncioData
import dev.steve.meditaccompose.Data.anuncios
import dev.steve.meditaccompose.Data.categoriaData
import dev.steve.meditaccompose.Data.medicoData
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class HomeViewModel: ViewModel() {
    var numberAdd:Int=0
    val state: MutableState<HomeState> = mutableStateOf(HomeState())
    fun __init__()
    {
        reloadList()
    }
    private fun reloadList()
    {
        viewModelScope.launch {
            state.value = state.value.copy(listCategories = categoriaData.categoriaList)
            state.value = state.value.copy(listHightlights = medicoData.medicosList)
            state.value = state.value.copy(listLatest  = medicoData.medicosList)
            state.value = state.value.copy(listRecommend  = medicoData.medicosList)
            state.value = state.value.copy(listAdd = anuncioData.anuncioList)
            state.value = state.value.copy(Add = anuncioData.anuncioList.get(numberAdd))
        }
    }
    fun hideErrorDialog()
    {
        state.value = state.value.copy(
            errorMessage = null
        )
    }
    fun updateAdd(){
        viewModelScope.launch {
            delay(1000)
            state.value = state.value.copy(Add = state.value.listAdd!!.get(numberAdd))
        }
    }
    fun increaseClick()
    {
        if(numberAdd < state.value.listAdd!!.size-1)
        {
            numberAdd += 1
        }
        else
        {
            numberAdd = 0
        }
        Log.w("INFO + INCREMENT : ",numberAdd.toString())
    }
    fun decreaseClick()
    {
        if(numberAdd > 0)
        {
            numberAdd -= 1
        }
        else
        {
            numberAdd = state.value.listAdd!!.size-1
        }
        Log.w("INFO - INCREMENT : ",numberAdd.toString())
    }
}