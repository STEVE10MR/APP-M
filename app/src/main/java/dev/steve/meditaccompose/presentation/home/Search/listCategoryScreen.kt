package dev.steve.meditaccompose.presentation.home.Search

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode
import com.gowtham.ratingbar.RatingBar
import com.gowtham.ratingbar.RatingBarConfig
import dev.steve.meditaccompose.Data.Medico
import dev.steve.meditaccompose.Data.categoria
import dev.steve.meditaccompose.Data.categoriaData
import dev.steve.meditaccompose.Data.medicoData
import dev.steve.meditaccompose.R
import dev.steve.meditaccompose.presentation.home.Detail.HashTagListCategoria
import dev.steve.meditaccompose.presentation.home.productItems
import dev.steve.meditaccompose.ui.theme.OFFICEBACK
import java.lang.Float


@Composable
fun listCategoryScreen()
{
    val list:List<Medico> = medicoData.medicosList
    FlowRow(
        modifier = Modifier.padding(2.dp),
        mainAxisAlignment = MainAxisAlignment.Center,
        mainAxisSize = SizeMode.Wrap,
        crossAxisSpacing = 1.dp,
        mainAxisSpacing = 25.dp
    ) {
        list.forEach { medico ->
            productItems(medico,{},{})
        }
    }
}


@Preview(showBackground = true, name = "listEdit", showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun DefaultPrevieSw() {
    listCategoryScreen()
}