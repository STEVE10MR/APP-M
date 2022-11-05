package dev.steve.meditaccompose.presentation.home.editAccount.optionsAccount

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import dev.steve.meditaccompose.presentation.component.commentText
import dev.steve.meditaccompose.presentation.home.Detail.texClick
import dev.steve.meditaccompose.ui.theme.AppColors

@Composable
fun optionsAccount() {



    val nombreRemb = rememberSaveable{ mutableStateOf("")}
    val apellidoRemb = rememberSaveable{ mutableStateOf("")}
    val descripcionRemb = rememberSaveable{ mutableStateOf("")}
    val enlaceWebRemb = rememberSaveable{ mutableStateOf("")}
    val enlaceTwitterRemb = rememberSaveable{ mutableStateOf("")}
    val enlaceFacebookRemb = rememberSaveable{ mutableStateOf("")}
    val enlaceLinkedinRemb = rememberSaveable{ mutableStateOf("")}
    val enlaceYoutuveRemb = rememberSaveable{ mutableStateOf("")}

    val numberStateView = rememberSaveable{ mutableStateOf(0)}
    val focusManager = LocalFocusManager.current


    var ColorText by remember { mutableStateOf(Color.Transparent) }
    var focusState by remember { mutableStateOf(true) }

    val withborder:Int = 1
    val colorBorder = Color.Gray




    if(focusState)
    {
        ColorText = Color.LightGray
    }

    Surface(modifier = Modifier.fillMaxSize(), color = AppColors.Light.Background)
    {
        Column(Modifier
            .fillMaxWidth()
            .padding(5.dp).verticalScroll(rememberScrollState()), verticalArrangement = Arrangement.spacedBy(15.dp), horizontalAlignment = Alignment.Start) {

            Column(modifier = Modifier.fillMaxWidth(), horizontalAlignment = Alignment.Start,verticalArrangement = Arrangement.spacedBy(2.dp)) {

                texClick(text = "Mi Perfil", style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.onSecondary), modifier = Modifier, { numberStateView.value = 0},false, colorBackground = when(numberStateView.value){
                        0 -> Color.LightGray
                        else -> Color.Transparent
                    })
                texClick(text = "Seguridad de la cuenta", style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.onSecondary), modifier = Modifier, {numberStateView.value = 1 },false,colorBackground = when(numberStateView.value){
                        1 -> Color.LightGray
                        else -> Color.Transparent
                    })
                texClick(text = "Metodos de pago", style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.onSecondary), modifier = Modifier, { numberStateView.value = 2},false,colorBackground = when(numberStateView.value){
                        2 -> Color.LightGray
                        else -> Color.Transparent
                    })
                texClick(text = "Localizar", style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                    textAlign = TextAlign.Start,
                    color = MaterialTheme.colors.onSecondary), modifier = Modifier, { numberStateView.value = 3},false,colorBackground = when(numberStateView.value){
                        3 -> Color.LightGray
                        else -> Color.Transparent
                    })

            }
            Column(modifier = Modifier
                .fillMaxWidth()
                .border((withborder).dp, colorBorder), verticalArrangement = Arrangement.spacedBy(0.dp)) {

                when(numberStateView.value)
                {
                    0 -> viewEditAccount(nombre = nombreRemb,apellido=apellidoRemb,descripcion=descripcionRemb, enlaceWeb = enlaceWebRemb, enlaceYoutuve = enlaceYoutuveRemb, enlaceLinkedin = enlaceLinkedinRemb, enlaceTwitter = enlaceTwitterRemb,enlaceFacebook=enlaceFacebookRemb,withborder = withborder,colorBorder=colorBorder)
                    else -> errorView()
                }

            }
        }
    }
}

@Composable
fun errorView()
{
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Hubo un Error Inesperado", style = MaterialTheme.typography.h4.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSecondary,
            textAlign = TextAlign.Center), modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))
    }
}

@Composable
fun viewEditAccount(nombre:MutableState<String>, apellido: MutableState<String>, descripcion: MutableState<String>, enlaceWeb: MutableState<String>, enlaceYoutuve: MutableState<String>, enlaceLinkedin: MutableState<String>, enlaceTwitter: MutableState<String>, enlaceFacebook: MutableState<String>, withborder:Int, colorBorder:Color)
{
    val counterMaxLength = 240
    Column(modifier = Modifier.fillMaxWidth()) {
        Text(text = "Editar perfil", style = MaterialTheme.typography.h4.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSecondary,
            textAlign = TextAlign.Center), modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))

        commentText("A;ade informacion sobre ti", position = TextAlign.Center)
    }
    Divider(modifier = Modifier
        .fillMaxWidth()
        .height((withborder).dp)
        .background(colorBorder))
    Column(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp), verticalArrangement = Arrangement.spacedBy(10.dp)) {
        //////////// TODO Info basica
        Text(text = "Informacion basica :", style = MaterialTheme.typography.body1.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSecondary), modifier = Modifier.fillMaxWidth(), maxLines = 1)
        TextField(value = nombre.value, onValueChange = {nombre.value = it},modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black),colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ), maxLines = 1)

        TextField(value = apellido.value, onValueChange = {apellido.value = it},modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black),colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ), maxLines = 1)


        commentText("A;ada una descripcion")

        Column {
            TextField(value = descripcion.value, onValueChange = {
                if((descripcion.value).length < counterMaxLength ) descripcion.value = it },modifier = Modifier
                .fillMaxWidth()
                .height((150).dp)
                .border(1.dp, Color.Black),colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ), maxLines = 7)
            Text(
                text = "${descripcion.value.length}/$counterMaxLength",//3
                color = MaterialTheme.colors.onSurface.copy(alpha = ContentAlpha.medium),
                style = MaterialTheme.typography.caption,
                modifier = Modifier
                    .padding(start = 16.dp)
                    .align(Alignment.End) //4
            )
        }
        //////////////
        Divider(modifier = Modifier
            .fillMaxWidth()
            .height((withborder).dp)
            .background(colorBorder)
            .padding(start = 2.dp, end = 2.dp))
        //////////// TODO Enlaces

        Text(text = "Enlaces :", style = MaterialTheme.typography.body1.copy(
            fontWeight = FontWeight.Bold,
            color = MaterialTheme.colors.onSecondary), modifier = Modifier.fillMaxWidth(), maxLines = 1)
        TextField(value = enlaceWeb.value, onValueChange = {enlaceWeb.value = it},modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black),colors = TextFieldDefaults.textFieldColors(
            backgroundColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent,
            focusedIndicatorColor = Color.Transparent
        ), maxLines = 1,placeholder = { Text("https(s)://..(p.ej. https:eldorado.gg)") })



        // TODO TWITTER
        Row(modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            .height(50.dp), verticalAlignment = Alignment.CenterVertically){
            Text(text = "http://twitter.com/", style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onSecondary), modifier = Modifier.padding(start = 4.dp, end = 4.dp), maxLines = 1)
            //////////////
            Divider(modifier = Modifier
                .fillMaxHeight()
                .width((withborder).dp)
                .background(Color.Black))
            ////////////
            TextField(value = enlaceTwitter.value, onValueChange = {enlaceTwitter.value = it},modifier = Modifier
                .fillMaxWidth(),colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ), maxLines = 1)
        }

        Text(text = "A;ada una descripcion", style = MaterialTheme.typography.body2.copy(
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            textAlign = TextAlign.Start), modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))



        // TODO FACEBGOOK
        Row(modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            .height(50.dp), verticalAlignment = Alignment.CenterVertically){
            Text(text = "http://facebook.com/", style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onSecondary), modifier = Modifier.padding(start = 4.dp, end = 4.dp), maxLines = 1)
            //////////////
            Divider(modifier = Modifier
                .fillMaxHeight()
                .width((withborder).dp)
                .background(Color.Black))
            ////////////
            TextField(value = enlaceFacebook.value, onValueChange = {enlaceFacebook.value = it},modifier = Modifier
                .fillMaxWidth(),colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ), maxLines = 1)
        }

        Text(text = "A;ada una descripcion", style = MaterialTheme.typography.body2.copy(
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            textAlign = TextAlign.Start), modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))


        // TODO YOUTUVE
        Row(modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            .height(50.dp), verticalAlignment = Alignment.CenterVertically){
            Text(text = "http://youtuve.com/", style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onSecondary), modifier = Modifier.padding(start = 4.dp, end = 4.dp), maxLines = 1)
            //////////////
            Divider(modifier = Modifier
                .fillMaxHeight()
                .width((withborder).dp)
                .background(Color.Black))
            ////////////
            TextField(value = enlaceYoutuve.value, onValueChange = {enlaceYoutuve.value = it},modifier = Modifier
                .fillMaxWidth(),colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ), maxLines = 1)
        }

        Text(text = "A;ada una descripcion", style = MaterialTheme.typography.body2.copy(
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            textAlign = TextAlign.Start), modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))


        // TODO LINKEDIN
        Row(modifier = Modifier
            .fillMaxWidth()
            .border(1.dp, Color.Black)
            .height(50.dp), verticalAlignment = Alignment.CenterVertically){
            Text(text = "http://linkedin.com/", style = MaterialTheme.typography.body1.copy(
                fontWeight = FontWeight.Normal,
                color = MaterialTheme.colors.onSecondary), modifier = Modifier.padding(start = 4.dp, end = 4.dp), maxLines = 1)
            //////////////
            Divider(modifier = Modifier
                .fillMaxHeight()
                .width((withborder).dp)
                .background(Color.Black))
            ////////////
            TextField(value = enlaceLinkedin.value, onValueChange = {enlaceLinkedin.value = it},modifier = Modifier
                .fillMaxWidth(),colors = TextFieldDefaults.textFieldColors(
                backgroundColor = Color.Transparent,
                unfocusedIndicatorColor = Color.Transparent,
                focusedIndicatorColor = Color.Transparent
            ), maxLines = 1)
        }

        Text(text = "A;ada una descripcion", style = MaterialTheme.typography.body2.copy(
            fontWeight = FontWeight.Bold,
            color = Color.Gray,
            textAlign = TextAlign.Start), modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp))


        // TODO BUTTOn
        Button(onClick = { /*TODO*/ },colors =ButtonDefaults.buttonColors(backgroundColor = Color.Black)) {
            Text(text = "Guardar", color = Color.White)
        }
    }
}

@Preview(showBackground = true, name = "OptionAccount", showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun DefaultPrevie() {
    optionsAccount()
}

