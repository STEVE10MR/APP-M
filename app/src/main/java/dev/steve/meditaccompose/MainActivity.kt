package dev.steve.meditaccompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
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
import dev.steve.meditaccompose.presentation.LoginScreen
import dev.steve.meditaccompose.presentation.home.HomeScreen
import dev.steve.meditaccompose.presentation.login.LoginViewModel
import dev.steve.meditaccompose.presentation.registration.RegisterViewModel
import dev.steve.meditaccompose.presentation.registration.RegistrationScreen
import dev.steve.meditaccompose.ui.theme.AppColors
import dev.steve.meditaccompose.ui.theme.LoginJetpackComposeTheme
import dev.steve.meditaccompose.ui.theme.OFFICEBACK

@ExperimentalAnimationApi
@AndroidEntryPoint
class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LoginJetpackComposeTheme {


                val navController = rememberAnimatedNavController()
                BoxWithConstraints {
                    AnimatedNavHost(
                        navController = navController,
                        startDestination = Destinations.Login.route)
                    {
                        addLogin(navController)
                        addRegister(navController)
                        addHome(navController)
                    }
                }


            }
        }
    }
}

@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addLogin(navController:NavHostController)
{
    composable(route=Destinations.Login.route, enterTransition = {_,_->
        slideInHorizontally(initialOffsetX = {1000}, animationSpec = tween(500))
    },
    exitTransition = {_,_->
        slideOutHorizontally(targetOffsetX = {-1000}, animationSpec = tween(500))
    },
    popEnterTransition = {_,_->
        slideInHorizontally(initialOffsetX = {-1000}, animationSpec = tween(500))
    },
    popExitTransition = {_,_->
        slideOutHorizontally(targetOffsetX = {1000}, animationSpec = tween(500))
    })
    {
        val viewModel:LoginViewModel = hiltViewModel()


        if(viewModel.state.value.succesLogin)
        {
            LaunchedEffect(key1 = Unit)
            {
                navController.navigate(Destinations.Home.route)
                {
                    popUpTo(Destinations.Login.route)
                    {
                        inclusive = true
                    }
                }
            }
        }
        else
        {
            LoginScreen(
                state = viewModel.state.value,
                onLogin =viewModel::login,
                onNavigationToRegister =
                {
                    navController.navigate (Destinations.Register.route)
                },
                onDissmisDialog = viewModel::hideErrorDialog)
        }
    }
}
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addRegister(navController:NavHostController)
{
    composable(route=Destinations.Register.route, enterTransition = {_,_->
        slideInHorizontally(initialOffsetX = {1000}, animationSpec = tween(500))
    },
        exitTransition = {_,_->
            slideOutHorizontally(targetOffsetX = {-1000}, animationSpec = tween(500))
        },
        popEnterTransition = {_,_->
            slideInHorizontally(initialOffsetX = {-1000}, animationSpec = tween(500))
        },
        popExitTransition = {_,_->
            slideOutHorizontally(targetOffsetX = {1000}, animationSpec = tween(500))
        })
    {
        val viewModel:RegisterViewModel = hiltViewModel()

        RegistrationScreen(state = viewModel.state.value
        , onRegister = viewModel::register,
        onBack =
        {
            navController.popBackStack()
        },
        onDissmisDialog = viewModel::hideErrorDialog)
    }
}
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addHome(navController:NavHostController)
{
    composable(route=Destinations.Home.route)
    {
        HomeScreen("id","name")
    }
}







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











