package dev.steve.meditaccompose.presentation.home.Detail.ItemInfo

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.steve.meditaccompose.Data.*
import dev.steve.meditaccompose.R
import dev.steve.meditaccompose.presentation.home.HomeState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch



class InfoViewModel: ViewModel() {

    val state: MutableState<InfoState> = mutableStateOf(InfoState())

    fun __init__(Id:Int)
    {

        val objectMedico = searchUser(Id)!!


        val errorMessage = if (objectMedico==null)
        {
            R.string.error_search
        } else null

        errorMessage?.let {
            state.value = state.value.copy(errorMessage = it)
            return
        }

        viewModelScope.launch {
            state.value = state.value.copy(medico = objectMedico)
            state.value = state.value.copy(listComment  = searchListComment(objectMedico.id))
            state.value = state.value.copy(listCategories  = searchListCategories(objectMedico.id))}
    }
    private fun searchUser(Id:Int): Medico? {
        for(user in medicoData.medicosList)
        {
            if (user.id == Id)
            {
                return user
            }
        }
        return null
    }
    private fun searchListComment(Id: Int): List<comentari> {
        return commentarios.commentariosList
    }
    private fun searchListCategories(Id: Int): List<categoria> {
        return categoriaData.categoriaList
    }
    fun hideErrorDialog()
    {
        state.value = state.value.copy(
            errorMessage = null
        )
    }
}