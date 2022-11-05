package dev.steve.meditaccompose.presentation.home

import android.annotation.SuppressLint
import android.util.Log
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gandiva.neumorphic.neu
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import dev.steve.meditaccompose.Data.*
import dev.steve.meditaccompose.R
import dev.steve.meditaccompose.defaultFlatNeuAttrs
import dev.steve.meditaccompose.ui.theme.OFFICEBACK
import java.util.*
import kotlin.concurrent.schedule


@SuppressLint("UnrememberedMutableState")
@Composable
fun HomeScreen(state: HomeState,initList:()->Unit,updateAdd:()->Unit,increaseCleck:()->Unit,decreaseClick:()->Unit,onNavigationToHightlights:(IdSearch:String)->Unit,onNavigationToCategories:(IdSearch:String)->Unit,onNavigationToLatest:(IdSearch:String)->Unit,onNavigationToRecommend:(IdSearch:String)->Unit,onNavigationToReInfo:(IdSearch:String)->Unit,onDissmisDialog:()->Unit)
{
    initList()

    Surface(modifier = Modifier.fillMaxSize().background(Color.Transparent)) {

    //Scaffold(modifier = Modifier.fillMaxSize(), backgroundColor = Color.Transparent) {PaddingValues ->
        Column(Modifier
            .padding(5.dp)
            .verticalScroll(rememberScrollState())) {
            Spacer(modifier = Modifier.height(20.dp))
            //////////////////////////////////////
            //TODO Anuncio
            //////////////////////////////////////

            var visible by remember{ mutableStateOf(true) }

            ConstraintLayout(modifier = Modifier
                .fillMaxWidth()
                .padding(5.dp)){

                val (btnleftRef,btnRightRef,contentRef) = createRefs()

                Surface(modifier = Modifier
                    .fillMaxWidth()
                    .constrainAs(contentRef)
                    {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)

                    }) {
                    this@Column.AnimatedVisibility(
                        visible = visible,
                        enter = fadeIn(
                            // Overwrites the initial value of alpha to 0.4f for fade in, 0 by default
                            initialAlpha = 0.4f
                        ),
                        exit = fadeOut(
                            // Overwrites the default animation with tween
                            animationSpec = tween(durationMillis = 250)
                        )
                    ) {

                        //https://prod-ripcut-delivery.disney-plus.net/v1/variant/star/36E34B32E2AA84C4E2B8DEE59F978AC8815CD8A1BDD33E32B4A66E230C2547DD/scale?width=1200&aspectRatio=1.78&format=jpeg
                        //anunciosItems(state.listAdd!!.get(0))
                        if( state.Add!= null){
                           anunciosItems(objectAnuncios = state.Add)
                        }
                    }

                }
                IconButton(modifier = Modifier
                    .background(Color.Transparent, shape = RoundedCornerShape(18.dp))
                    .size(42.dp)
                    .constrainAs(btnRightRef)
                    {
                        bottom.linkTo(contentRef.bottom)
                        top.linkTo(contentRef.top)
                        end.linkTo(contentRef.end, margin = 5.dp)
                    }
                    ,
                    onClick = {
                        increaseCleck()
                        updateAdd()
                    }) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Default.ArrowForward,
                        tint = Color.White,
                        contentDescription = "Icons")
                }
                IconButton(modifier = Modifier
                    .background(Color.Transparent, shape = RoundedCornerShape(18.dp))
                    .size(42.dp)
                    .constrainAs(btnleftRef)
                    {
                        bottom.linkTo(contentRef.bottom)
                        top.linkTo(contentRef.top)
                        start.linkTo(contentRef.start, margin = 5.dp)
                    }
                    ,
                    onClick = { /*TODO*/
                        decreaseClick()
                        updateAdd()
                    }) {
                    Icon(
                        modifier = Modifier.size(32.dp),
                        imageVector = Icons.Default.ArrowBack,
                        tint = Color.White,
                        contentDescription = "Icons")
                }

                /*
                FloatingActionButton(
                    modifier = Modifier
                        .size(52.dp)
                        .constrainAs(btnRightRef)
                        {
                            bottom.linkTo(contentRef.bottom)
                            top.linkTo(contentRef.top)
                            end.linkTo(contentRef.end, margin = 5.dp)
                        }
                    ,
                    backgroundColor = OFFICEBACK,
                    onClick ={
                        //TODO ver mas
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(42.dp),
                        imageVector = Icons.Default.ArrowForward,
                        tint = Color.White,
                        contentDescription = "Icons")
                }

                 */
            }
            //////////////////////////////////////
            //TODO Fin
            //////////////////////////////////////
            Spacer(modifier = Modifier.height(10.dp))


            //////////////////////////////////////
            //TODO Featured
            //////////////////////////////////////
            Column(Modifier.fillMaxWidth()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Destacados", style = MaterialTheme.typography.h5,color = OFFICEBACK,fontWeight = FontWeight.Bold)

                }
                Spacer(modifier = Modifier.height(15.dp))
                if (state.listHightlights != null) {


                    LazyRow(modifier = Modifier.height(160.dp)
                        , contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp))
                    {
                        items(state.listHightlights)
                        {
                            featuredItems(objectMedico = it,onNavigationToHightlights=onNavigationToHightlights, onNavigationToInfo = onNavigationToReInfo)
                        }
                    }
                }
                else
                {
                    //TODO Error cuando no carga la lista
                }
            }
            //////////////////////////////////////
            //TODO FIN
            //////////////////////////////////////
            Spacer(modifier = Modifier.height(10.dp))
            //////////////////////////////////////
            //TODO Categories
            //////////////////////////////////////
            Column(Modifier.fillMaxWidth()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Categorias", style = MaterialTheme.typography.h5,color = OFFICEBACK,fontWeight = FontWeight.Bold)
                    TextButton(onClick = {}) {
                        Text(text = "Mas", style = MaterialTheme.typography.body2,color = OFFICEBACK,fontWeight = FontWeight.Normal)
                    }
                }
                Column(Modifier.padding(horizontal = 16.dp)) {
                    state.listCategories?.let { HashTagListCategoria(hashTags = it.subList(0,5)) }
                }
            }
            //////////////////////////////////////
            //TODO FIN
            //////////////////////////////////////
            Spacer(modifier = Modifier.height(10.dp))
            //////////////////////////////////////
            //TODO physicians
            //////////////////////////////////////
            Column(Modifier.fillMaxWidth()) {
                Row(
                    Modifier
                        .fillMaxWidth()
                        .padding(horizontal = 16.dp),
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween
                ) {
                    Text(text = "Ultimos", style = MaterialTheme.typography.h5,color = OFFICEBACK,fontWeight = FontWeight.Bold)
                    TextButton(onClick = {}) {
                        Text(text = "Mas", style = MaterialTheme.typography.body2,color = OFFICEBACK,fontWeight = FontWeight.Normal)
                    }
                }

                if (state.listLatest != null) {

                    LazyRow(
                        contentPadding = PaddingValues(horizontal = 16.dp),
                        horizontalArrangement = Arrangement.spacedBy(16.dp)
                    )
                    {

                        itemsIndexed(state.listLatest)
                        {index,item ->
                            if(index <= 2)
                            {
                                productItems(objectMedico = item,onNavigationToLatest=onNavigationToLatest, onNavigationToInfo = onNavigationToReInfo)
                            }
                            if(index == 3)
                            {
                                Box(modifier = Modifier
                                    .fillMaxSize()
                                    .height(200.dp)
                                    .background(Color.Transparent), contentAlignment = Alignment.Center) {
                                    FloatingActionButton(
                                        modifier = Modifier
                                            .size(52.dp)
                                        ,
                                        backgroundColor = OFFICEBACK,
                                        onClick ={
                                            //TODO ver mas
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

                    }
                }
                else
                {
                    //TODO Error cuando no carga la lista
                }
            }
            //////////////////////////////////////
            //TODO FIN
            //////////////////////////////////////
        }
    }
}


@Composable
fun featuredItems(objectMedico:Medico,onNavigationToHightlights: (IdSearch: String) -> Unit,onNavigationToInfo: (IdSearch: String) -> Unit)
{

    val title:String ="${objectMedico.Nombre} , ${objectMedico.apellido}"
    val subtitle:String ="${objectMedico.descripcion}"
    val header:String ="${objectMedico.especialidad}"
    val imageSync:Painter = painterResource(id = objectMedico.Img)

    Card(Modifier
        .fillMaxSize()
        .neu(defaultFlatNeuAttrs()),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = OFFICEBACK,
        elevation = 0.dp) {
        Row{
            Column(Modifier
                .padding(horizontal = 16.dp)
                .width(150.dp),
                verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {
                Text(text = title, maxLines = 2,style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold, color = Color.White, fontSize = 12.sp))

                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.White))

                Text(text = subtitle, maxLines = 5,style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Normal, color = Color.White,fontSize = 10.sp))


                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(Color.White))

                Button(modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = Color.White
                    ), shape = RoundedCornerShape(20.dp), onClick = {
                        onNavigationToHightlights(objectMedico.id.toString())
                    }) {
                    Text(
                        text = "Solicitar Cita", style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Medium,
                            color = OFFICEBACK,
                            textAlign = TextAlign.Center, fontSize = 12.sp)
                    )
                }

            }
            Surface(color = Color.Transparent){
                ConstraintLayout{
                    val (imageRef,infoRef) = createRefs()

                    Image(
                        painter = imageSync,
                        contentDescription = "",
                        modifier = Modifier
                            .fillMaxHeight()
                            .width(150.dp)
                            .constrainAs(imageRef)
                            {
                                top.linkTo(parent.top)
                                end.linkTo(parent.end)
                            },
                        alignment = Alignment.CenterEnd,
                        contentScale = ContentScale.Crop
                    )

                    FloatingActionButton(
                        modifier = Modifier
                            .size(40.dp)
                            .constrainAs(infoRef)
                            {
                                top.linkTo(imageRef.top, margin = 2.dp)
                                end.linkTo(parent.end, margin = 2.dp)

                            },
                        backgroundColor = OFFICEBACK,
                        onClick ={
                            //TODO
                            onNavigationToInfo(objectMedico.id.toString())

                        }
                    ) {
                        Icon(
                            modifier = Modifier.size(42.dp),
                            painter = painterResource(id = R.drawable.ic_baseline_info_24),
                            tint = Color.White,
                            contentDescription = "Icons")
                    }
                }
            }
        }

    }
}

@Composable
fun anunciosItems(objectAnuncios:anuncios)
{
    var imgUrl = objectAnuncios.ImgURL
    var nombre = objectAnuncios.Nombre
    var descripcion = objectAnuncios.Descripcion

    Card(modifier = Modifier
        .fillMaxWidth()
        .clip(RoundedCornerShape(12.dp))) {
        ConstraintLayout{
            val (imageRef,contrastRef,textRef) = createRefs()
            //TODO IMAGENES SLIDER
            AsyncImage(
                model = ImageRequest.
                Builder(LocalContext.current).
                data(imgUrl).
                crossfade(3000).
                build(), contentDescription = null,
                contentScale = ContentScale.FillWidth,modifier = Modifier
                    .fillMaxSize()
                    .height(250.dp)
                    .clip(RoundedCornerShape(12.dp))
                    .constrainAs(imageRef)
                    {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })

            Column(modifier = Modifier
                .fillMaxWidth()
                .height(100.dp)
                .constrainAs(textRef)
                {
                    top.linkTo(imageRef.bottom, margin = 2.dp)
                    start.linkTo(imageRef.start)
                    end.linkTo(imageRef.end)
                }
                .padding(15.dp)) {
                //TODO

                Text(text = nombre, maxLines = 2,style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold, color = Color.Black))

                Spacer(modifier = Modifier.height(6.dp))

                Text(text = descripcion, maxLines = 2,style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Normal, color = Color.Black, fontSize = 13.sp))
            }




        }
    }


}

@Composable
fun productItems(objectMedico:Medico,onNavigationToLatest: (IdSearch: String) -> Unit,onNavigationToInfo: (IdSearch: String) -> Unit)
{
    val title:String ="${objectMedico.Nombre} , ${objectMedico.apellido}"
    val discount:Int =objectMedico.desc
    val price:String ="${objectMedico.precio}"
    val imageSync:Painter = painterResource(id = objectMedico.Img)
    var rating = rememberSaveable{mutableStateOf(2.5)}
    Card(
        Modifier
            .width(160.dp)
    ) {
        Column(
            Modifier
                .padding(bottom = 32.dp)
        ) {

            ConstraintLayout{
                val (imageRef,infoRef) = createRefs()

                Image(
                    painter = imageSync, contentDescription = "",
                    modifier = Modifier
                        .fillMaxWidth()
                        .aspectRatio(1f)
                        .constrainAs(imageRef)
                        {
                            top.linkTo(parent.top)
                            end.linkTo(parent.end)
                        },
                    contentScale = ContentScale.Fit
                )

                FloatingActionButton(
                    modifier = Modifier
                        .size(32.dp)
                        .constrainAs(infoRef)
                        {
                            top.linkTo(imageRef.top, margin = 2.dp)
                            start.linkTo(parent.start, margin = 2.dp)

                        },
                    backgroundColor = Color.White,
                    onClick ={
                        //TODO
                        onNavigationToInfo(objectMedico.id.toString())
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(42.dp),
                        painter = painterResource(id = R.drawable.ic_baseline_info_24),
                        tint = OFFICEBACK,
                        contentDescription = "Icons")
                }
            }

            Column(
                Modifier
                    .fillMaxWidth()
                    .padding(horizontal = 8.dp), verticalArrangement = Arrangement.spacedBy(5.dp)
            ) {

                Text(text = title, maxLines = 2,style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 12.sp))
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(OFFICEBACK))
                LazyRow{
                    //TODO especialidad de cada medico
                    var textObj = "  "
                    itemsIndexed(categoriaData.categoriaList)
                    { index,item->

                        if(categoriaData.categoriaList.size > index+1) textObj="  &"

                        Text(text = item.Nombre + textObj, maxLines = 1,style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Normal, color = Color.Black, fontSize = 10.sp))
                        Spacer(modifier = Modifier.width(5.dp))

                    }

                }
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(OFFICEBACK))
                RatingBar(value = (if (rating.value >= Math.floor(rating.value) + 0.4) Math.floor(
                    rating.value) + 0.5 else Math.floor(rating.value)).toFloat(),
                    onValueChange = {},
                    onRatingChanged = {}, config = RatingBarConfig().activeColor(Color(90,211,173)))
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(OFFICEBACK))
                Button(modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = OFFICEBACK
                    ), shape = RoundedCornerShape(20.dp), onClick = {
                        onNavigationToLatest(objectMedico.id.toString())
                    }) {
                    Text(
                        text = "Solicitar Cita", style = MaterialTheme.typography.body1.copy(
                            fontWeight = FontWeight.Medium,
                            color = Color.White,
                            textAlign = TextAlign.Center, fontSize = 12.sp)
                    )
                }
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
                    )
                    .padding(8.dp),
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


