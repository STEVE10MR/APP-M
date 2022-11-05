package dev.steve.meditaccompose.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.rounded.AccountCircle
import androidx.compose.material.icons.rounded.Favorite
import androidx.compose.material.icons.rounded.Notifications
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NamedNavArgument
import androidx.navigation.NavType
import androidx.navigation.navArgument


sealed class Destinations(
    val route: String,
    val title: String? = "",
    val arguments: List<NamedNavArgument>,
    var icon: ImageVector? = null
){
    object Login: Destinations("login","",emptyList())
    object Register: Destinations("register","", emptyList())
    object Home: Destinations("home","Home",emptyList(),Icons.Filled.Home)
    object Account: Destinations("account","Cuenta",emptyList(),Icons.Rounded.AccountCircle)
    object Search: Destinations("search","Buscar",emptyList(),Icons.Filled.Search)
    object Featured: Destinations("featured","Destacados",emptyList(),Icons.Rounded.Favorite)
    object Appointment: Destinations("appointment","Mis Citas",emptyList(),Icons.Rounded.Notifications)
    object homeDetailLastest: Destinations("homeDetailLastest","",listOf(
        navArgument(name = "idSearch"){ type = NavType.StringType }
    ))
    object homeDetailCategories: Destinations("homeDetailCategories","",listOf(
        navArgument(name = "idSearch"){ type = NavType.StringType }
    ))
    object homeDetailHightlights: Destinations("homeDetailHightlights","",listOf(
        navArgument(name = "idSearch"){ type = NavType.StringType }
    ))
    object homeDetailRecommend: Destinations("homeDetailRecommend","",listOf(
        navArgument(name = "idSearch"){ type = NavType.StringType }
    ))
    object homeDetailInfo: Destinations("homeDetailInfo","",listOf(
        navArgument(name = "idSearch"){ type = NavType.StringType }
    ))
}
