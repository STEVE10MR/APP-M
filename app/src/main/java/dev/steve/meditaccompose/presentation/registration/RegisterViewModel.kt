package dev.steve.meditaccompose.presentation.registration

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dev.steve.meditaccompose.R
import dev.steve.meditaccompose.presentation.login.LoginState
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class RegisterViewModel: ViewModel() {
    val state: MutableState<RegisterState> = mutableStateOf(RegisterState())

    fun register(email:String,firtsname:String,lastname:String,password: String,confirmpassword:String)
    {
        val errorMessage = if(email.isBlank() || password.isBlank() || firtsname.isBlank() || lastname.isBlank() || password.isBlank() || confirmpassword.isBlank())
        {
            R.string.error_input_empty
        }else if(!Patterns.EMAIL_ADDRESS.matcher(email).matches())
        {
            R.string.error_not_a_valid_email
        }else if(password != confirmpassword)
        {
            R.string.error_incorrectly_repeated_password
        } else null

        errorMessage?.let {
            state.value = state.value.copy(errorMessage = it)
            return
        }

        viewModelScope.launch {
            state.value = state.value.copy(displayProgressBar = true)
            delay(3000)
            state.value = state.value.copy(displayProgressBar = false)

        }
    }

    fun hideErrorDialog()
    {
        state.value = state.value.copy(
            errorMessage = null
        )
    }

}