package dev.steve.meditaccompose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavType
import androidx.navigation.compose.NamedNavArgument
import androidx.navigation.compose.navArgument

sealed class Destinations(
    val route: String,
    val arguments: List<NamedNavArgument>,
    var icon: ImageVector? = null
){

    object Login: Destinations("login", emptyList(), Icons.Filled.Home)
    object Register: Destinations("register", emptyList())
    object Home: Destinations(
        "home",
        listOf(
            navArgument("id"){ type = NavType.StringType },
            navArgument("name"){ type = NavType.StringType }
        )
    )

}
