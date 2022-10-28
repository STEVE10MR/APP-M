package dev.steve.meditaccompose.presentation

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.ClickableText
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
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
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.steve.meditaccompose.R
import dev.steve.meditaccompose.presentation.component.EventDialog
import dev.steve.meditaccompose.presentation.component.RoundedButton
import dev.steve.meditaccompose.presentation.component.TransparentTextField
import dev.steve.meditaccompose.presentation.login.LoginState


@Composable
fun LoginScreen(state: LoginState
                ,onLogin:(String,String)->Unit
                ,onNavigationToRegister:()->Unit
                ,onDissmisDialog:()->Unit)
{
    val email_value = rememberSaveable{ mutableStateOf("")}
    val password_value = rememberSaveable{ mutableStateOf("")}
    var password_Visible by remember{ mutableStateOf(false) }

    val focusManager = LocalFocusManager.current


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(MaterialTheme.colors.background))
    {
        Image(
            painter = painterResource(id = R.drawable.logo_01),
            contentDescription = "Login Image",
            contentScale = ContentScale.Inside
        )

        Box(modifier = Modifier.fillMaxSize(),
            contentAlignment = Alignment.BottomCenter)
        {
            ConstraintLayout{

                val (surface,fab) = createRefs()

                Surface(
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(490.dp)
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
                    Column(
                        modifier = Modifier
                            .fillMaxSize()
                            .padding(20.dp),
                        verticalArrangement = Arrangement.SpaceEvenly) {


                        Text(text = "Ingresar Cuenta",
                            style = MaterialTheme.typography.h4.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colors.primary)
                        )
                        Column(modifier = Modifier
                            .fillMaxWidth()
                            .padding(horizontal = 16.dp),
                            horizontalAlignment = Alignment.CenterHorizontally,
                            verticalArrangement = Arrangement.spacedBy(8.dp)) {
                            
                            TransparentTextField(textFieldValue = email_value,
                                textLabel = "Email",
                                keyboardType = KeyboardType.Email,
                                keyboardActions = KeyboardActions(onNext =
                                {
                                    focusManager.moveFocus(FocusDirection.Down)
                                }),
                                imeAction = ImeAction.Next)

                            TransparentTextField(textFieldValue = password_value,
                                textLabel = "Contraseña",
                                keyboardType = KeyboardType.Password,
                                keyboardActions = KeyboardActions(onDone = {
                                    focusManager.clearFocus()

                                    onLogin(email_value.value,password_value.value)
                                }),
                                imeAction = ImeAction.Done,
                                trailingIcon = { IconButton(onClick = {
                                    password_Visible = !password_Visible})
                                {
                                    Icon(
                                        modifier = Modifier.size(24.dp),
                                        imageVector = if(password_Visible){
                                        Icons.Default.Visibility}
                                        else
                                        {
                                            Icons.Default.VisibilityOff
                                        },
                                        tint = MaterialTheme.colors.primary,
                                        contentDescription = "Icons")
                                }
                                }, visualTransformation =  if(password_Visible){
                                    VisualTransformation.None}
                                    else
                                    {PasswordVisualTransformation() })

                            Spacer(modifier = Modifier.height(16.dp))

                            Text(text = "Olviste tu Contraseña",style = MaterialTheme.typography.body1,
                            textAlign = TextAlign.Center, modifier = Modifier.fillMaxWidth())
                            Column() {
                                
                            }
                        }

                        Column(modifier = Modifier.fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally,
                        verticalArrangement = Arrangement.spacedBy(8.dp)) {

                            Spacer(modifier = Modifier.height(4.dp))
                            RoundedButton(text = "Ingresar",
                                displayProgressBar = state.displayProgressBar,
                                onClick = {
                                        onLogin(email_value.value,password_value.value)
                                     }
                            )

                            Spacer(modifier = Modifier.height(16.dp))

                            ClickableText(
                                text = buildAnnotatedString {
                                    append("No tienes una Cuenta? ")
                                    withStyle(
                                        style = SpanStyle(color = MaterialTheme.colors.primary, fontWeight = FontWeight.Bold)
                                    )
                                    {
                                        append("Crear Cuenta")
                                    }
                                })
                            {
                                onNavigationToRegister()
                            }
                                
                            
                        }
                    }
                }
                FloatingActionButton(
                    modifier = Modifier
                        .size(52.dp)
                        .constrainAs(fab)
                        {
                            top.linkTo(surface.top, margin = (18).dp)
                            end.linkTo(surface.end, margin = (12).dp)

                        },
                    backgroundColor = MaterialTheme.colors.primary,
                    onClick ={
                        onNavigationToRegister()
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(42.dp),
                        imageVector = Icons.Default.ArrowForward,
                        tint = Color.White,
                        contentDescription = "Icons")
                }
            }

        }
    }

    if(state.errorMessage != null)
    {
        EventDialog(errorMessage = state.errorMessage,onDismiss=onDissmisDialog)
    }

}




