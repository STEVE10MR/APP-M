package dev.steve.meditaccompose.presentation.component

import androidx.compose.material.BottomNavigation
import androidx.compose.material.BottomNavigationItem
import androidx.compose.material.Icon
import androidx.compose.runtime.Composable
import androidx.compose.runtime.currentComposer
import androidx.compose.runtime.getValue
import androidx.navigation.NavController
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import dev.steve.meditaccompose.navigation.Destinations

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
                    })
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