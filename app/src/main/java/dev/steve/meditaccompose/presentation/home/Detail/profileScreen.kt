package dev.steve.meditaccompose.presentation.home.Detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gandiva.neumorphic.neu
import dev.steve.meditaccompose.R
import dev.steve.meditaccompose.defaultFlatNeuAttrs
import dev.steve.meditaccompose.presentation.component.RoundedButton
import dev.steve.meditaccompose.presentation.component.RoundedCustomButton
import dev.steve.meditaccompose.presentation.component.TransparentTextField
import dev.steve.meditaccompose.ui.theme.AppColors
import dev.steve.meditaccompose.ui.theme.OFFICEBACK


@Composable
fun profileScreen(){

    var nombre = remember{ mutableStateOf("")}
    var apellido = remember{ mutableStateOf("")}
    var descripcion by remember{ mutableStateOf("")}
    val focusManager = LocalFocusManager.current

    val ColorBackground by remember { mutableStateOf(AppColors.Light.Background) }

    Surface(modifier = Modifier.fillMaxSize(), color = ColorBackground)
    {

            ConstraintLayout(modifier = Modifier
                .fillMaxWidth().padding(5.dp)) {

                val (imageColumnRef) = createRefs()

                Box(modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(imageColumnRef) {
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                        top.linkTo(parent.top, margin = 10.dp)
                    }, contentAlignment = Alignment.Center)
                {
                    ConstraintLayout {
                        val (designImageRef,designtextRef) = createRefs()
                        Surface(modifier = Modifier
                            .fillMaxWidth().padding(5.dp).neu(defaultFlatNeuAttrs())
                            .constrainAs(designImageRef) { top.linkTo(parent.top) }, shape = RoundedCornerShape(24.dp)) {
                            ConstraintLayout() {
                                val (imageRef,editRef) = createRefs()
                                Surface(modifier = Modifier
                                    .width(165.dp)
                                    .height(165.dp)
                                    .border(0.dp, Color.White, shape = CircleShape)
                                    .background(color = Color.White)
                                    .constrainAs(imageRef)
                                    {
                                        top.linkTo(parent.top)
                                        end.linkTo(parent.end)
                                        start.linkTo(parent.start)
                                    }) {
                                    Image(painter = painterResource(id = R.drawable.img_1),
                                        contentDescription = "Hi",
                                        modifier = Modifier
                                            .width(150.dp)
                                            .height(150.dp)
                                            .padding(8.dp))
                                }

                                FloatingActionButton(
                                    modifier = Modifier
                                        .size(52.dp)
                                        .constrainAs(editRef)
                                        {
                                            bottom.linkTo(parent.bottom)
                                            end.linkTo(imageRef.end, margin = (12).dp)

                                        },
                                    backgroundColor = OFFICEBACK,
                                    onClick ={
                                        //TODO CAMBIAR IMAGEN
                                    }
                                ) {
                                    Icon(
                                        modifier = Modifier.size(32.dp),
                                        painter = painterResource(id = R.drawable.ic_baseline_edit_24),
                                        tint = Color.White,
                                        contentDescription = "Icons")
                                }
                            }
                        }

                        Column(Modifier
                            .fillMaxWidth()
                            .padding(5.dp)
                            .constrainAs(designtextRef) {
                                top.linkTo(designImageRef.bottom,
                                    margin = 10.dp)
                            }, verticalArrangement = Arrangement.spacedBy(5.dp), horizontalAlignment = Alignment.CenterHorizontally) {

                            Text(text = "Editar",style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onSecondary),modifier = Modifier)


                            Divider(modifier = Modifier.fillMaxWidth().height(2.dp).background(Color.Black).padding(top = 15.dp))

                            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(20.dp)) {
                                TransparentTextField(textFieldValue = nombre,
                                    textLabel = "Nombre",
                                    keyboardType = KeyboardType.Text,
                                    keyboardActions = KeyboardActions(onNext =
                                    {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }),
                                    imeAction = ImeAction.Next)

                                TransparentTextField(textFieldValue = nombre,
                                    textLabel = "Apellidos",
                                    keyboardType = KeyboardType.Text,
                                    keyboardActions = KeyboardActions(onNext =
                                    {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }),
                                    imeAction = ImeAction.Next)

                                TransparentTextField(textFieldValue = nombre,
                                    textLabel = "Descripcion",
                                    keyboardType = KeyboardType.Text,
                                    keyboardActions = KeyboardActions(onNext =
                                    {
                                        focusManager.moveFocus(FocusDirection.Down)
                                    }),
                                    imeAction = ImeAction.Next)

                                RoundedCustomButton(modifier = Modifier,"Editar",false,{},
                                    OFFICEBACK)
                            }
                        }





                    }

                }




            }
    }

}

/*
@Preview(showBackground = true, name = "Nuevox2Nombre", showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun DefaultPrevieSw() {
    profileScreen()
}
*/

