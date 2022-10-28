package dev.steve.meditaccompose.presentation.home

import android.graphics.Bitmap
import android.graphics.BitmapFactory
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.tween
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.outlined.FavoriteBorder
import androidx.compose.material.icons.outlined.Notifications
import androidx.compose.material.icons.rounded.Search
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.BlurredEdgeTreatment
import androidx.compose.ui.draw.blur
import androidx.compose.ui.draw.clip
import androidx.compose.ui.focus.FocusDirection
import androidx.compose.ui.geometry.*
import androidx.compose.ui.graphics.*
import androidx.compose.ui.graphics.drawscope.clipPath
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.ImeAction
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextDirection.Companion.Content
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import coil.compose.AsyncImage
import coil.request.ImageRequest
import com.gandiva.neumorphic.neu
import com.gandiva.neumorphic.shape.Flat
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import dev.steve.meditaccompose.Data.*
import dev.steve.meditaccompose.R
import dev.steve.meditaccompose.defaultFlatNeuAttrs
import dev.steve.meditaccompose.presentation.component.TransparentTextField
import dev.steve.meditaccompose.presentation.component.TransparentTextFieldSearch
import dev.steve.meditaccompose.presentation.home.Detail.itemScreen
import dev.steve.meditaccompose.ui.theme.OFFICEBACK


val listAnuncios = mutableListOf<anuncios>()


@Composable
fun HomeScreen(email:String,PASSWORD:String)
{
    listAnuncios.add(anuncios(1,"Prueba","Descripcion","https://ahoradoctor.cl/wp-content/uploads/elementor/thumbs/diverse-medical-team-of-doctors-looking-at-camera-while-holding-clipboard-and-medical-files-pfudi7nfdxq12hpwg808p9bxss5kvpaj16v1vldbls.jpg"))
    listAnuncios.add(anuncios(2,"Prueba","Descripcion","https://images.ecestaticos.com/Bosqgwi-bRoiiu2s89AjrlamxUE=/0x70:1716x1040/1338x751/filters:fill(white):format(jpg)/f.elconfidencial.com%2Foriginal%2F8db%2F8b6%2Faa5%2F8db8b6aa54b585253e15f79a68447aeb.jpg"))

    //val search_value = rememberSaveable{ mutableStateOf("") }
    val focusManager = LocalFocusManager.current

    Scaffold(

        /*topBar = {

        //Search App Bar

        Row(modifier = Modifier
            .padding(16.dp)
            .height(68.dp)
            , verticalAlignment = Alignment.CenterVertically, horizontalArrangement = Arrangement.SpaceAround) {


            TransparentTextFieldSearch(textFieldValue = search_value,
                textLabel = "Search",
                keyboardType = KeyboardType.Text,
                keyboardActions = KeyboardActions(
                    onNext = {
                        focusManager.moveFocus(FocusDirection.Down)
                    }
                ),
                imeAction = ImeAction.Next
            )
        }
    }*/ modifier = Modifier.fillMaxSize(), backgroundColor = Color.Transparent) {PaddingValues ->
        
        Content(paddingValues = PaddingValues)
    }
}

@Composable
fun Content(paddingValues: PaddingValues)
{
    Column(
        Modifier
            .padding(paddingValues)
            .verticalScroll(rememberScrollState())) {
        Spacer(modifier = Modifier.height(20.dp))
        InitSection()
        Spacer(modifier = Modifier.height(10.dp))
        FeaturedSection()
        Spacer(modifier = Modifier.height(10.dp))
        CategorySection()
        Spacer(modifier = Modifier.height(10.dp))
        ProductSection()

    }
}

@Composable
fun InitSection() {
    var visible by remember{ mutableStateOf(true) }

    ConstraintLayout(modifier = Modifier.fillMaxWidth()){

        val (btnleftRef,btnRightRef,contentRef) = createRefs()


        Surface(modifier = Modifier
            .fillMaxWidth()
            .constrainAs(contentRef)
            {
                top.linkTo(parent.top)
                start.linkTo(parent.start)
                end.linkTo(parent.end)

            }) {
            AnimatedVisibility(
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
                anunciosItems(listAnuncios.get(0))
            }
        }
        IconButton(modifier = Modifier.background(OFFICEBACK, shape = RoundedCornerShape(18.dp))
            .size(42.dp)
            .constrainAs(btnRightRef)
            {
                bottom.linkTo(contentRef.bottom)
                top.linkTo(contentRef.top)
                end.linkTo(contentRef.end, margin = 5.dp)
            }
            ,
            onClick = {
                /*
                anuncioObj.value = listAnuncios.first()

                 */
            }) {
            Icon(
                modifier = Modifier.size(32.dp),
                imageVector = Icons.Default.ArrowForward,
                tint = Color.White,
                contentDescription = "Icons")
        }
        IconButton(modifier = Modifier.background(OFFICEBACK, shape = RoundedCornerShape(18.dp))
            .size(42.dp)
            .constrainAs(btnleftRef)
            {
                bottom.linkTo(contentRef.bottom)
                top.linkTo(contentRef.top)
                start.linkTo(contentRef.start, margin = 5.dp)
            }
            ,
            onClick = { /*TODO*/
                /*
                anuncioObj.value = listAnuncios.first()

                 */
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


}

@Composable
fun FeaturedSection() {
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
        Featured()
    }
}
@Composable
fun CategorySection() {
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
        Categories()
    }
}
@Composable
fun ProductSection() {
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
        listProduct()
    }
}

@Composable
fun Featured()
{
    LazyRow(modifier = Modifier.height(160.dp)
    , contentPadding = PaddingValues(horizontal = 16.dp),
    horizontalArrangement = Arrangement.spacedBy(16.dp))
    {

        items(medicoData.medicosList)
        {
            featuredItems(objectMedico = it,backgroundColor = OFFICEBACK)
        }
    }

}






@Composable
fun listProduct()
{
    LazyRow(
        contentPadding = PaddingValues(horizontal = 16.dp),
        horizontalArrangement = Arrangement.spacedBy(16.dp)
    )
    {
        itemsIndexed(medicoData.medicosList)
        {index,item ->
            if(index <= 2)
            {
                productItems(objectMedico = item)
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

@Composable
fun featuredItems(objectMedico:Medico,backgroundColor:Color=OFFICEBACK)
{

    val title:String ="${objectMedico.Nombre} , ${objectMedico.apellido}"
    val subtitle:String ="${objectMedico.descripcion}"
    val header:String ="${objectMedico.especialidad}"
    val imageSync:Painter = painterResource(id = objectMedico.Img)

    Card(
        Modifier
            .fillMaxSize()
            .neu(defaultFlatNeuAttrs()),
        shape = RoundedCornerShape(8.dp),
        backgroundColor = OFFICEBACK,
        elevation = 0.dp) {
        Row{
            Column(
                Modifier
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
                    ), shape = RoundedCornerShape(20.dp), onClick = {}) {
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
    Card(modifier = Modifier.fillMaxWidth()) {
        ConstraintLayout{
            val (imageRef,contrastRef,textRef) = createRefs()


            //TODO IMAGES LOCALES
            /*
            Image(painter = painterResource(id = R.drawable.anuncio02),
                contentDescription = "anuncio",
                contentScale =ContentScale.FillWidth,
                modifier = Modifier
                    .fillMaxSize()
                    .height(250.dp)
                    .blur(
                        radiusX = 0.dp,
                        radiusY = 0.dp,
                        edgeTreatment = BlurredEdgeTreatment.Rectangle
                    )
                    .clip(RoundedCornerShape(8.dp))
                    .constrainAs(imageRef)
                    {
                        top.linkTo(parent.top)
                        start.linkTo(parent.start)
                        end.linkTo(parent.end)
                    })
            */


            //TODO IMAGENES SLIDER
            AsyncImage(
                model = ImageRequest.
                Builder(LocalContext.current).
                data("https://images.ecestaticos.com/caMPtwBa-yzC8f6a_wJnYNEA7os=/0x98:598x435/972x547/filters:fill(white):format(jpg)/f.elconfidencial.com%2Foriginal%2F3d8%2F385%2Fc5b%2F3d8385c5b0e594d05018da3ab7986479.jpg").
                crossfade(3000).
                build(), contentDescription = null,
                contentScale = ContentScale.FillWidth,modifier = Modifier
                    .fillMaxSize()
                    .height(250.dp)
                    .blur(
                        radiusX = 0.dp,
                        radiusY = 0.dp,
                        edgeTreatment = BlurredEdgeTreatment.Rectangle
                    )
                    .clip(RoundedCornerShape(8.dp))
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
                    bottom.linkTo(imageRef.bottom, margin = 10.dp)
                    start.linkTo(imageRef.start)
                    end.linkTo(imageRef.end)
                }
                .padding(15.dp)) {
                //TODO

                Text(text = objectAnuncios.Nombre, maxLines = 2,style = MaterialTheme.typography.h4.copy(
                    fontWeight = FontWeight.Bold, color = Color.Black))
                
                Spacer(modifier = Modifier.height(6.dp))

                Text(text = objectAnuncios.Descripcion, maxLines = 2,style = MaterialTheme.typography.body2.copy(
                    fontWeight = FontWeight.Normal, color = Color.Black, fontSize = 13.sp))
            }




        }
    }
}

@Composable
fun productItems(objectMedico:Medico)
{

    val title:String ="${objectMedico.Nombre} , ${objectMedico.apellido}"
    val discount:Int =objectMedico.desc
    val price:String ="${objectMedico.precio}"
    val imageSync:Painter = painterResource(id = objectMedico.Img)


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
                    .padding(horizontal = 8.dp)
            ) {

                Text(text = title, maxLines = 2,style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold, color = Color.Black, fontSize = 12.sp))
                Spacer(modifier = Modifier.height(3.dp))
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(OFFICEBACK))
                Spacer(modifier = Modifier.height(5.dp))
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
                Spacer(modifier = Modifier.height(5.dp))
                Divider(modifier = Modifier
                    .fillMaxWidth()
                    .height(2.dp)
                    .background(OFFICEBACK))


                Spacer(modifier = Modifier.height(3.dp))
                Button(modifier = Modifier.fillMaxWidth(),
                    colors = ButtonDefaults.buttonColors(
                        backgroundColor = OFFICEBACK
                    ), shape = RoundedCornerShape(20.dp), onClick = {}) {
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

/*
@Composable
fun categoryButton(
    categoria: categoria,
    backgroundColor: Color
)
{
    val text =categoria.Nombre
    val icon:Painter = painterResource(id = categoria.ImgId)

    Column(
        Modifier
            .width(72.dp)
            .clickable { }
    ) {
        Box(
            Modifier
                .size(72.dp)
                .background(
                    color = backgroundColor,
                    shape = RoundedCornerShape(12.dp)
                )
                .padding(18.dp)
        ) {
            Image(painter = icon, contentDescription = "", modifier = Modifier.fillMaxSize())
        }
        Text(text = text, modifier = Modifier.fillMaxWidth(), textAlign = TextAlign.Center, fontSize = 12.sp)
        
    }
}
*/

@Composable
fun Categories()
{
    Column(Modifier.padding(horizontal = 16.dp)) {
        HashTagListCategoria(hashTags = (categoriaData.categoriaList).subList(0,5))
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


@Preview()
@Composable
fun DefaultPreviewHome() {
    HomeScreen("","")
}


