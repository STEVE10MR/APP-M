package dev.steve.meditaccompose.presentation.registration

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.Visibility
import androidx.compose.material.icons.filled.VisibilityOff
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.steve.meditaccompose.R
import dev.steve.meditaccompose.presentation.component.EventDialog
import dev.steve.meditaccompose.presentation.component.RoundedButton
import dev.steve.meditaccompose.presentation.component.TransparentTextField

@Composable
fun RegistrationScreen(state: RegisterState
                       ,onRegister:(String,String,String,String,String)->Unit
                       ,onBack:()->Unit
                       ,onDissmisDialog:()->Unit)
{

    val focusManager = LocalFocusManager.current

    val emailValue = remember{ mutableStateOf("")}
    val lastnameValue = remember{ mutableStateOf("")}
    val firstnameValue = remember{ mutableStateOf("")}
    val password_value = rememberSaveable{ mutableStateOf("")}
    val confirm_password_value = rememberSaveable{ mutableStateOf("")}
    var password_Visible_01 by remember{ mutableStateOf(false) }
    var password_Visible_02 by remember{ mutableStateOf(false) }
    Box(modifier = Modifier
        .fillMaxSize()
        .background(MaterialTheme.colors.background))
    {

        Image(
            painter = painterResource(id = R.drawable.logo_02),
            contentDescription = "Login Image",
            contentScale = ContentScale.Fit
        )


        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter)
        {
            ConstraintLayout{

                val (surface,fab) = createRefs()

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(650.dp)
                        .constrainAs(surface)
                        {
                            bottom.linkTo(parent.bottom)
                        },
                    color = Color.White,
                    shape = RoundedCornerShape(
                        topStartPercent = 10,
                        topEndPercent = 10
                    )
                ) {

                    Column(modifier = Modifier
                        .fillMaxSize()
                        .padding(16.dp)
                        .verticalScroll(rememberScrollState())) {

                        Row(verticalAlignment = Alignment.CenterVertically) {
                            IconButton(onClick ={
                                onBack()
                            }) {
                                Icon(modifier = Modifier.size(42.dp),
                                    imageVector = Icons.Default.ArrowBack,
                                    tint = MaterialTheme.colors.primary,
                                    contentDescription = "Icons")
                            }
                            Text(text = "Crear Cuenta",style = MaterialTheme.typography.h6.copy(MaterialTheme.colors.primary))
                        }
                        Spacer(modifier = Modifier.height(32.dp))
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)
                        )
                        {
                            TransparentTextField(textFieldValue = firstnameValue,
                                textLabel = "Nombres",
                                keyboardType = KeyboardType.Text,
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                imeAction = ImeAction.Next)
                            TransparentTextField(textFieldValue = lastnameValue,
                                textLabel = "Apellidos",
                                keyboardType = KeyboardType.Text,
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                imeAction = ImeAction.Next)
                            TransparentTextField(textFieldValue = emailValue,
                                textLabel = "Email",
                                keyboardType = KeyboardType.Text,
                                keyboardActions = KeyboardActions(
                                    onNext = {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }
                                ),
                                imeAction = ImeAction.Next)

                            TransparentTextField(textFieldValue = password_value,
                                textLabel = "Contraseña",
                                keyboardType = KeyboardType.Password,
                                keyboardActions = KeyboardActions(onDone = {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }),
                                imeAction = ImeAction.Done,
                                trailingIcon = { IconButton(onClick = {
                                    password_Visible_01 = !password_Visible_01})
                                {
                                    Icon(
                                        modifier = Modifier.size(24.dp),
                                        imageVector = if(password_Visible_01){
                                            Icons.Default.Visibility}
                                        else
                                        {
                                            Icons.Default.VisibilityOff
                                        },
                                        tint = MaterialTheme.colors.primary,
                                        contentDescription = "Icons")
                                }
                                }, visualTransformation =  if(password_Visible_01){
                                    VisualTransformation.None}
                                else
                                {
                                    PasswordVisualTransformation() })

                            TransparentTextField(textFieldValue = confirm_password_value,
                                textLabel = "Confirmar Contraseña",
                                keyboardType = KeyboardType.Password,
                                keyboardActions = KeyboardActions(onDone = {
                                    focusManager.clearFocus()
                                }),
                                imeAction = ImeAction.Done,
                                trailingIcon = { IconButton(onClick = {
                                    password_Visible_02 = !password_Visible_02})
                                {
                                    Icon(
                                        modifier = Modifier.size(24.dp),
                                        imageVector = if(password_Visible_02){
                                            Icons.Default.Visibility}
                                        else
                                        {
                                            Icons.Default.VisibilityOff
                                        },
                                        tint = MaterialTheme.colors.primary,
                                        contentDescription = "Icons")
                                }
                                }, visualTransformation =  if(password_Visible_02){
                                    VisualTransformation.None}
                                else
                                {
                                    PasswordVisualTransformation() })

                            Spacer(modifier = Modifier.height(16.dp))


                            RoundedButton(text = "Registrar", displayProgressBar = false, onClick = {
                                onRegister(
                                    emailValue.value,
                                    firstnameValue.value,
                                    lastnameValue.value,
                                    password_value.value,
                                    confirm_password_value.value)
                            })

                            Spacer(modifier = Modifier.height(16.dp))

                            ClickableText(
                                text = buildAnnotatedString {
                                    append("Ya tienes una Cuenta? ")
                                    withStyle(
                                        style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)
                                    )
                                    {
                                        append("Ingresar")
                                    }
                                },
                                onClick =
                                {
                                    onBack()
                                })

                        }


                    }

                    if(state.errorMessage !=null)
                    {
                        EventDialog(errorMessage = state.errorMessage, onDismiss = onDissmisDialog)
                    }

                }
            }

        }



    }
}

