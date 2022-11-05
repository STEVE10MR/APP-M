package dev.steve.meditaccompose.presentation.component

import android.content.res.Resources.Theme
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.compose.ui.graphics.Color
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.steve.meditaccompose.navigation.Destinations
import dev.steve.meditaccompose.ui.theme.AppColors

@Composable
fun BottomNavigationBar(
    navController: NavHostController,
    items:List<Destinations>)
{
    val currentRoute = currentRoute(navController)

    BottomNavigation{
        items.forEach{ screen ->

            if(screen.icon != null)
            {
                BottomNavigationItem(
                    icon = {Icon(imageVector = screen.icon!!,contentDescription=screen.route)},
                    selected = currentRoute ==screen.route,
                    onClick = {
                        navController.navigate(screen.route)
                        {
                            popUpTo(navController.graph.findStartDestination().id)
                            {
                                saveState = true
                            }
                            launchSingleTop = true
                        }
                    }, label = { Text(text = screen.route)}
                    ,alwaysShowLabel = true
                    , selectedContentColor = Color.White
                    , unselectedContentColor = Color.LightGray)

            }
        }
    }
}

@Composable
private fun currentRoute(navController: NavHostController):String?
{
    val navBackStackEntry by navController.currentBackStackEntryAsState()
    return navBackStackEntry?.destination?.route
}