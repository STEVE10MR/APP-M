package dev.steve.meditaccompose.presentation.home.Detail

import android.os.Build
import android.util.Log
import android.view.RoundedCorner
import androidx.annotation.RequiresApi
import androidx.compose.animation.animateColorAsState
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.draw.drawBehind
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Pressed
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.gowtham.ratingbar.RatingBar
import dev.steve.meditaccompose.*
import dev.steve.meditaccompose.Data.categoria
import dev.steve.meditaccompose.Data.categoriaData
import dev.steve.meditaccompose.Data.comentari
import dev.steve.meditaccompose.Data.commentarios
import dev.steve.meditaccompose.R
import dev.steve.meditaccompose.presentation.component.RatingBar
import dev.steve.meditaccompose.presentation.home.Detail.ItemInfo.InfoState
import dev.steve.meditaccompose.ui.theme.AppColors
import dev.steve.meditaccompose.ui.theme.AppTextStyle
import dev.steve.meditaccompose.ui.theme.OFFICEBACK
import kotlinx.coroutines.launch
import java.util.*
import kotlin.reflect.KFunction1


@RequiresApi(Build.VERSION_CODES.S)
@Composable
fun infoScreen(init: (Id:Int)->Unit, state: InfoState,idSearch:Int,onBack:()->Unit)
{
    init(idSearch)

    var btnMore by remember { mutableStateOf(true) }
    var heightValueState by remember { mutableStateOf(true) }
    var rating = rememberSaveable{mutableStateOf(5.0)}

    val nombre =state.medico?.Nombre ?:"Error"
    val apellidos =state.medico?.apellido ?:"Error"
    val descripcion =state.medico?.descripcion ?:"Error"
    val imgURL =state.medico?.Img ?:""

    var listSample = mutableListOf<Int>(8,1,10,20,10)


    val ColorBackground by remember { mutableStateOf(AppColors.Light.Background) }

    Surface(modifier = Modifier, color = ColorBackground)
    {
        Column(modifier = Modifier
            .fillMaxSize()
            .padding(5.dp)
            .verticalScroll(rememberScrollState())) {
                ConstraintLayout{

                    val (profileRef,espRef,txtRef,txtMy,moreRef,ratingTextRef,ratingRef,comentTextRef,comentRef,backRef) = createRefs()

                    Surface(modifier = Modifier
                        .fillMaxWidth()
                        .constrainAs(profileRef)
                        {
                            start.linkTo(parent.start, margin = 10.dp)
                            end.linkTo(parent.end, margin = 10.dp)
                            top.linkTo(parent.top, margin = 10.dp)
                        },shape = RoundedCornerShape(24.dp)) {
                        ConstraintLayout(modifier = Modifier
                            .fillMaxWidth()
                            .background(color = Color.White)){
                            val (imageRef,nameRef,referenceRef,editRef) = createRefs()

                            // TODO Imagen del medico- Informacion
                            Surface(modifier = Modifier
                                .size(115.dp)
                                .border(0.dp, Color.Transparent, shape = CircleShape)
                                .constrainAs(imageRef) {
                                    start.linkTo(parent.start, margin = 10.dp)
                                    top.linkTo(parent.top, margin = 10.dp)
                                }) {

                                AsyncImage(modifier = Modifier.size(115.dp).padding(8.dp).clip(
                                    CircleShape),
                                    model = ImageRequest.
                                    Builder(LocalContext.current).
                                    data(imgURL).
                                    crossfade(3000).
                                    build(), contentDescription = null,
                                    contentScale = ContentScale.FillWidth)
                            }
                            // TODO Nombre del medico - Informacion
                            Text(text = "$nombre $apellidos",maxLines = 1,style = MaterialTheme.typography.body1.copy(
                                fontWeight = FontWeight.Bold,
                                color = MaterialTheme.colors.onSecondary,
                                textAlign = TextAlign.Center),modifier = Modifier.constrainAs(nameRef)
                            {
                                start.linkTo(imageRef.end, margin = 0.dp)
                                top.linkTo(imageRef.top, margin = 15.dp)
                            })


                            // TODO Ubicacion del medico - Informacion
                            Text(text = "Peru/Tacna",maxLines = 1,style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Medium,
                                color = MaterialTheme.colors.onSecondary,
                                textAlign = TextAlign.Center),modifier = Modifier.constrainAs(referenceRef)
                            {
                                start.linkTo(nameRef.start, margin = 5.dp)
                                top.linkTo(nameRef.bottom, margin = 15.dp)
                            })

                        }
                    }

                    /*
                    Button(
                        modifier = Modifier
                            .defaultMinSize(minHeight = 80.dp)
                            .fillMaxWidth()
                            .neu(
                                lightShadowColor = AppColors.lightShadow(),
                                darkShadowColor = AppColors.darkShadow(),
                                shadowElevation = 12.dp,
                                lightSource = LightSource.LEFT_TOP
                            ),
                        colors = ButtonDefaults.buttonColors(
                            backgroundColor = MaterialTheme.colors.background
                        ), onClick = {}
                    ) {
                        Text(
                            text = "Button", style = AppTextStyle.button()
                        )
                    }
                    */


                    // TODO Especialidad del medico - Informacion
                    Text(text = "Mi especialidad",maxLines = 1,style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSecondary),modifier = Modifier.constrainAs(txtRef)
                    {
                        start.linkTo(profileRef.start)
                        top.linkTo(profileRef.bottom, margin = 15.dp)
                    })
                    Surface(modifier = Modifier.padding(5.dp).constrainAs(espRef)
                    {
                        start.linkTo(profileRef.start, margin = 5.dp)
                        top.linkTo(profileRef.bottom, margin = 45.dp)
                    }, shape = RoundedCornerShape(12.dp)) {
                        //TODO mi especialidad
                        HashTagListCategoria(categoriaData.categoriaList)
                    }
                    // TODO Descripcion del medico - Informacion
                    Column(modifier = Modifier
                        .fillMaxWidth()
                        .background(Color.Transparent)
                        .constrainAs(txtMy)
                        {
                            start.linkTo(profileRef.start)
                            top.linkTo(espRef.bottom, margin = 15.dp)
                        }, verticalArrangement = Arrangement.spacedBy(5.dp)) {

                        Text(text = "Sobre mi",maxLines = 1,style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onSecondary))
                        Box(modifier = Modifier
                            .fillMaxWidth()
                            .padding(10.dp))
                        {
                            if(heightValueState)
                            {

                                Text(text = descripcion,style= MaterialTheme.typography.body2.copy(
                                    fontWeight = FontWeight.Normal,
                                    color = MaterialTheme.colors.onSecondary), maxLines = 2, textAlign = TextAlign.Justify)
                            }
                            else
                            {
                                Text(text = descripcion,style= MaterialTheme.typography.body2.copy(
                                    fontWeight = FontWeight.Normal,
                                    color = MaterialTheme.colors.onSecondary),textAlign = TextAlign.Justify)
                            }

                        }
                    }
                    TextButton(modifier = Modifier.constrainAs(moreRef)
                    {
                        start.linkTo(profileRef.start)
                        top.linkTo(txtMy.bottom, margin = 15.dp)
                    }, onClick = {

                        btnMore = !btnMore
                        heightValueState = !heightValueState

                    }) {
                        Text(text = if(btnMore) "Ver mas" else "Ver menos",style= TextStyle(color = Color.Blue, fontWeight = FontWeight.Bold, fontSize = 14.sp))
                    }

                    // TODO Calificacion del medico - Informacion
                    Text(text = "Calificacion ",maxLines = 1,style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSecondary),modifier = Modifier.constrainAs(ratingTextRef)
                    {
                        start.linkTo(profileRef.start)
                        top.linkTo(moreRef.bottom, margin = 15.dp)
                    })

                    Surface(modifier = Modifier
                        .fillMaxWidth()
                        .padding(2.dp)
                        .constrainAs(ratingRef)
                        {
                            top.linkTo(ratingTextRef.bottom, margin = 25.dp)
                        },shape = RoundedCornerShape(bottomEndPercent = 10, bottomStartPercent = 10, topStartPercent = 10, topEndPercent = 10)) {


                        RatingBar(rating = rating, sampleTotal = listSample)


                    }

                    // TODO Comentarios del medico - Informacion

                    Text(text = "Comentarios ",maxLines = 1,style = MaterialTheme.typography.body1.copy(
                        fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colors.onSecondary), modifier = Modifier.constrainAs(comentTextRef)
                    {
                        top.linkTo(ratingRef.bottom, margin = 15.dp)
                        start.linkTo(profileRef.start)
                    })


                    Surface(modifier = Modifier
                        .fillMaxWidth()
                        .height(500.dp)
                        .padding(10.dp)
                        .constrainAs(comentRef)
                        {
                            top.linkTo(comentTextRef.bottom, margin = 5.dp)
                        }, color = Color.Transparent) {
                        if(state.listComment != null)
                        {
                            LazyColumn(modifier = Modifier
                                .background(color = Color.Transparent), verticalArrangement = Arrangement.spacedBy(25.dp)){
                                items(state.listComment)
                                {
                                    comment(coment = it)
                                }
                            }
                        }
                        else
                        {
                            //TODO error al cargar
                        }
                    }

                    IconButton(modifier = Modifier
                        .background(Color.Transparent, shape = RoundedCornerShape(18.dp))
                        .size(42.dp)
                        .constrainAs(backRef)
                        {
                            top.linkTo(parent.top)
                            start.linkTo(parent.start, margin = 5.dp)
                        }
                        ,
                        onClick = { /*TODO*/
                            onBack()
                        }) {
                        Icon(
                            modifier = Modifier.size(32.dp),
                            imageVector = Icons.Default.ArrowBack,
                            tint = OFFICEBACK,
                            contentDescription = "Icons")
                    }
                }


        }
    }
}

@Composable
fun comment(coment:comentari)
{
    val nombre = coment.Nombre
    val mensaje = coment.Mensaje
    val fecha = coment.Fecha
    val puntaje = coment.Puntaje
    val image = painterResource(id = R.drawable.img_1)
    var showMessageBoolean = rememberSaveable{ mutableStateOf(false) }

    Card(modifier = Modifier
        .padding(start = 5.dp, top = 5.dp)
        .neu(defaultFlatNeuAttrs())
        .fillMaxWidth(), backgroundColor = Color.White) {

            ConstraintLayout{
                val (imageRef,nameTxtRef,messageTxtRef,dateTxtRef,pointTxtRef) = createRefs()

                Box(modifier = Modifier
                    .width(65.dp)
                    .height(65.dp)
                    .background(Color.White)
                    .border(0.dp, Color.White, shape = CircleShape)
                    .constrainAs(imageRef)
                    {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start, margin = 15.dp)
                        bottom.linkTo(parent.bottom)
                    }, contentAlignment = Alignment.Center) {
                    Image(painter = image, contentDescription = "Description Image", modifier = Modifier
                        .fillMaxSize()
                        .padding(2.dp))
                }


                Surface(modifier = Modifier
                    .padding(start = 5.dp, end = 5.dp, top = 15.dp, bottom = 15.dp)
                    .neu(
                        defaultFlatNeuAttrs())
                    .constrainAs(nameTxtRef)
                    {
                        top.linkTo(parent.top, margin = 5.dp)
                        start.linkTo(imageRef.end, margin = 5.dp)
                    },
                    shape = RoundedCornerShape(24.dp))

                {
                    Text(text = "$nombre",maxLines = 1,
                        style = MaterialTheme.typography.body2.copy(
                            fontWeight = FontWeight.Bold,
                            color = MaterialTheme.colors.onSecondary), modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 5.dp, bottom = 5.dp))
                }


                Box(modifier = Modifier
                    .width(250.dp)
                    .clickable
                    {
                        showMessageBoolean.value = !showMessageBoolean.value
                    }
                    .constrainAs(messageTxtRef)
                    {
                        top.linkTo(nameTxtRef.bottom, margin = 2.dp)
                        start.linkTo(imageRef.end, margin = 15.dp)
                    })
                {

                    if(showMessageBoolean.value)
                    {
                        Text(text = "$mensaje",
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colors.onSecondary), modifier = Modifier.padding(end = 25.dp, bottom = 25.dp), textAlign = TextAlign.Justify)
                    }
                    else
                    {
                        Text(text = "$mensaje",
                            style = MaterialTheme.typography.body2.copy(
                                fontWeight = FontWeight.Normal,
                                color = MaterialTheme.colors.onSecondary), modifier = Modifier.padding(end = 25.dp, bottom = 25.dp), maxLines = 2, textAlign = TextAlign.Justify)
                    }
                }
                /*
                Text(text = "$mensaje",
                    style= TextStyle(color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Normal, fontSize = 10.sp),
                    modifier = Modifier
                        .width(IntrinsicSize.Max)
                        .padding(5.dp)
                        .constrainAs(messageTxtRef)
                        {
                            top.linkTo(nameTxtRef.bottom, margin = 2.dp)
                            start.linkTo(nameTxtRef.start)
                        })

                 */

                Text(text = "$fecha",
                    style= TextStyle(color = MaterialTheme.colors.onSurface,
                        fontWeight = FontWeight.Normal, fontSize = 10.sp),
                    modifier = Modifier.constrainAs(dateTxtRef)
                    {
                        top.linkTo(parent.top, margin = 5.dp)
                        end.linkTo(parent.end, margin = 5.dp)
                    })


                Box(modifier = Modifier
                    .size(28.dp)
                    .clip(CircleShape)
                    .background(OFFICEBACK)
                    .constrainAs(pointTxtRef)
                    {
                        end.linkTo(parent.end, margin = 2.dp)
                        bottom.linkTo(parent.bottom, margin = 0.dp)
                    }, contentAlignment = Alignment.Center,)
                {
                    Text(text = "$puntaje",
                        style= TextStyle(color = MaterialTheme.colors.onSurface,
                            fontWeight = FontWeight.Bold, fontSize = 10.sp, textAlign = TextAlign.Center))
                }


        }

    }
}

@Composable
fun HashTagListCategoria(hashTags: List<categoria>) {
    FlowRow(
        modifier = Modifier.padding(8.dp),
        mainAxisAlignment = MainAxisAlignment.Start,
        mainAxisSize = SizeMode.Expand,
        crossAxisSpacing = 12.dp,
        mainAxisSpacing = 8.dp
    ) {
        hashTags.forEach { hashTag ->
            Text(
                text = hashTag.Nombre,
                modifier = Modifier
                    .neu(defaultFlatNeuAttrs())
                    .background(
                        color = OFFICEBACK,
                        shape = RoundedCornerShape(24.dp)
                    ).padding(8.dp),
                overflow = TextOverflow.Ellipsis,
                maxLines = 1,
                style = MaterialTheme.typography.h6.copy(
                    fontWeight = FontWeight.Medium,
                    color = MaterialTheme.colors.surface,
                    textAlign = TextAlign.Center,
                    fontSize = 12.sp)
            )
        }
    }
}

/*
@RequiresApi(Build.VERSION_CODES.S)
@Preview(showBackground = true, name = "NuevoNombre", showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun DefaultPreviewItem() {
    itemScreen()
}

 */







