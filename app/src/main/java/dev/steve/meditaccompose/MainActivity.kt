package dev.steve.meditaccompose

import android.annotation.SuppressLint
import android.os.Build
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.material.Scaffold
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.gandiva.neumorphic.LightSource
import com.gandiva.neumorphic.NeuAttrs
import com.gandiva.neumorphic.shape.CornerShape
import com.gandiva.neumorphic.shape.Flat
import com.gandiva.neumorphic.shape.Pressed
import com.gandiva.neumorphic.shape.RoundedCorner
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dagger.hilt.android.AndroidEntryPoint
import dev.steve.meditaccompose.navigation.Destinations
import dev.steve.meditaccompose.navigation.Destinations.*
import dev.steve.meditaccompose.navigation.NavigationHost
import dev.steve.meditaccompose.presentation.LoginScreen
import dev.steve.meditaccompose.presentation.component.BottomNavigationBar
import dev.steve.meditaccompose.presentation.component.SearchBar
import dev.steve.meditaccompose.presentation.component.TitleBar
import dev.steve.meditaccompose.presentation.home.Detail.ItemInfo.InfoViewModel
import dev.steve.meditaccompose.presentation.home.Detail.infoScreen
import dev.steve.meditaccompose.presentation.home.HomeScreen
import dev.steve.meditaccompose.presentation.home.HomeViewModel
import dev.steve.meditaccompose.presentation.login.LoginViewModel
import dev.steve.meditaccompose.presentation.registration.RegisterViewModel
import dev.steve.meditaccompose.presentation.registration.RegistrationScreen
import dev.steve.meditaccompose.ui.theme.AppColors
import dev.steve.meditaccompose.ui.theme.LoginJetpackComposeTheme

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    @RequiresApi(Build.VERSION_CODES.S)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginJetpackComposeTheme {
                MainScreen()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun MainScreen()
{
    val navController = rememberAnimatedNavController()
    val navigationItems = listOf(Home, Search, Appointment,Featured,Account)


    val title by remember{mutableStateOf("Inicio de Sesion")}
    val focusedBar by remember{mutableStateOf(true)}
    Scaffold(
        /*
        topBar = {TitleBar(title,true,{}, modifier = Modifier)},
        bottomBar = { BottomNavigationBar(navController = navController, items = navigationItems)}
        */
        ){

        NavigationHost(navController)
    }
}




//Design Button Pressed Elevation DD

@Composable
fun DefaultSpacer() = Spacer(modifier = Modifier.size(8.dp))

val defaultWidgetPadding = 16.dp
val defaultElevation = 5.dp
val defaultCornerShape: CornerShape = RoundedCorner(12.dp)

@Composable
fun defaultPressedNetAttrs() = NeuAttrs(
    lightShadowColor = AppColors.lightShadow(),
    darkShadowColor = AppColors.darkShadow(),
    shadowElevation = defaultElevation,
    lightSource = LightSource.RIGHT_BOTTOM,
    shape = Pressed(defaultCornerShape),
)

@Composable
fun defaultFlatNeuAttrs() = NeuAttrs(
    lightShadowColor = AppColors.lightShadow(),
    darkShadowColor = AppColors.darkShadow(),
    shadowElevation = defaultElevation,
    lightSource = LightSource.RIGHT_BOTTOM,
    shape = Flat(defaultCornerShape)
)











