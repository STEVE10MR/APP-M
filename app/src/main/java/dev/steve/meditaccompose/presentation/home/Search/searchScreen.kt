package dev.steve.meditaccompose.presentation.home.Search

import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import dev.steve.meditaccompose.Data.categoria
import dev.steve.meditaccompose.Data.categoriaData
import dev.steve.meditaccompose.presentation.home.Detail.HashTagListCategoria


@Composable
fun searchScreen()
{
    val list:List<categoria> = categoriaData.categoriaList
    HashTagListCategoria(list)
}


@Preview(showBackground = true, name = "listEdit", showSystemUi = true, device = Devices.DEFAULT)
@Composable
fun DefaultPrevie() {
    searchScreen()
}