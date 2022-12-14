package dev.steve.meditaccompose.presentation.registration

import androidx.annotation.StringRes

data class RegisterState (
    val successRegister:Boolean = false,
    val displayProgressBar:Boolean = false,
    @StringRes val errorMessage:Int?=null)
{

}