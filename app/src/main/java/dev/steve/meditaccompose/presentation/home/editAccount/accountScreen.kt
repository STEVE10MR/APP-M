package dev.steve.meditaccompose.presentation.home.Detail

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import dev.steve.meditaccompose.R
import dev.steve.meditaccompose.ui.theme.AppColors
import dev.steve.meditaccompose.ui.theme.OFFICEBACK


@Composable
fun accountScreen(){

    var title = remember{ mutableStateOf("Jhonatan Stveve mamani Ramos")}
    var apellido = remember{ mutableStateOf("")}
    var descripcion by remember{ mutableStateOf("")}
    val focusManager = LocalFocusManager.current

    val ColorBackground by remember { mutableStateOf(AppColors.Light.Background) }

    Surface(modifier = Modifier.fillMaxSize(), color = ColorBackground)
    {
        ConstraintLayout {
            val (designImageRef,designtextRef) = createRefs()

            Surface(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .constrainAs(designImageRef) { top.linkTo(parent.top) }, shape = RoundedCornerShape(24.dp)) {
                ConstraintLayout() {
                    val (imageRef,editRef,titleRef,viewTypeRef) = createRefs()
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
                            .size(42.dp)
                            .constrainAs(editRef)
                            {
                                end.linkTo(imageRef.end, margin = 12.dp)
                                top.linkTo(imageRef.bottom, margin = -35.dp)
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

                    Text(text = title.value,style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Center,
                        color = MaterialTheme.colors.onSecondary), modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(titleRef) {
                            top.linkTo(imageRef.bottom, margin = 15.dp)
                            start.linkTo(imageRef.start, margin = 15.dp)
                            end.linkTo(imageRef.end, margin = 15.dp)
                        })
                    Button(modifier = Modifier
                        .constrainAs(viewTypeRef)
                        {
                            top.linkTo(titleRef.bottom, margin = 5.dp)
                            start.linkTo(imageRef.start)
                            end.linkTo(imageRef.end)

                        },
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = OFFICEBACK
                        ), shape = RoundedCornerShape(20.dp), onClick = {}) {
                        Text(
                            text = "Cambiar de vista", style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Medium,
                                color = Color.White,
                                textAlign = TextAlign.Center, fontSize = 12.sp)
                        )
                    }
                }
            }

            Column(Modifier
                .fillMaxWidth()
                .padding(5.dp)
                .constrainAs(designtextRef) {
                    top.linkTo(designImageRef.bottom,
                        margin = 10.dp)
                }, verticalArrangement = Arrangement.spacedBy(15.dp), horizontalAlignment = Alignment.Start) {
                Text(text = "Configuracion de la cuenta",style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSecondary),modifier = Modifier)
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start,verticalArrangement = Arrangement.spacedBy(2.dp)) {

                    texClick(text = "Seguridad de la cuenta", style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onSecondary), modifier = Modifier,{})
                    texClick(text = "Recordatorio de Citas", style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onSecondary), modifier = Modifier,{})


                }
                Text(text = "Ayuda y Asistencia",style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                    color = MaterialTheme.colors.onSecondary),modifier = Modifier)
                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start) {

                    texClick(text = "Acerca de Meditac", style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onSecondary), modifier = Modifier,{})
                }

                Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(45.dp)) {

                    texClick(text = "Cerrar Sesion", style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        textAlign = TextAlign.Start,
                        color = MaterialTheme.colors.onSecondary), modifier = Modifier,{}, showIcon = false)
                    Text(text = "Meditac 0.0.1",style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSecondary),modifier = Modifier)
                }

            }



        }


    }

}

@Composable
fun texClick(
    text: String, style: TextStyle, modifier: Modifier, onClickText:()->Unit,
    showIcon:Boolean=true,
    colorBackground: Color =Color.Transparent)
{
    Box(modifier = Modifier
        .fillMaxWidth().background(colorBackground).padding(2.dp)
        .clickable { onClickText() }, contentAlignment = Alignment.CenterStart)
    {
        ConstraintLayout(modifier=Modifier) {
            val (textRef,iconRef)= createRefs()
            Text(text = text,style = style,modifier = modifier.constrainAs(textRef)
            {
                start.linkTo(parent.end)
            })
            if(showIcon) {
                Icon(
                    modifier = Modifier.size(24.dp).constrainAs(iconRef)
                    {
                        start.linkTo(textRef.end, margin = 15.dp)
                        top.linkTo(textRef.top, margin = 0.dp)
                    },
                    imageVector = Icons.Default.ArrowForward,
                    tint = Color.Black,
                    contentDescription = "Icons")
            }
        }
    }
}

@Composable
fun texClickColor(text: String, style: TextStyle, modifier: Modifier, onClickText:()->Unit)
{
    Box(modifier = Modifier
        .fillMaxWidth().background(Color.Transparent)
        .clickable { onClickText() }, contentAlignment = Alignment.CenterStart)
    {
        ConstraintLayout(modifier=Modifier) {
            val (textRef,iconRef)= createRefs()
            Text(text = text,style = style,modifier = modifier.constrainAs(textRef)
            {
                start.linkTo(parent.end)
            })
        }
    }
}

/*
@Preview(showBackground = true, name = "Nuevox2Nombre", showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun DefaultPrevieSw() {
    accountScreen()
}

 */


