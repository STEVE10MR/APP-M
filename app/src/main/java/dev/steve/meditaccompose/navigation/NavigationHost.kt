package dev.steve.meditaccompose.navigation

import android.os.Build
import androidx.annotation.RequiresApi
import androidx.compose.animation.ExperimentalAnimationApi
import androidx.compose.animation.core.tween
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.foundation.layout.BoxWithConstraints
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import com.google.accompanist.navigation.animation.AnimatedNavHost
import com.google.accompanist.navigation.animation.composable
import com.google.accompanist.navigation.animation.rememberAnimatedNavController
import dev.steve.meditaccompose.presentation.LoginScreen
import dev.steve.meditaccompose.presentation.home.Detail.ItemInfo.InfoViewModel
import dev.steve.meditaccompose.presentation.home.Detail.infoScreen
import dev.steve.meditaccompose.presentation.home.HomeScreen
import dev.steve.meditaccompose.presentation.home.HomeViewModel
import dev.steve.meditaccompose.presentation.login.LoginViewModel
import dev.steve.meditaccompose.presentation.registration.RegisterViewModel
import dev.steve.meditaccompose.presentation.registration.RegistrationScreen

@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalAnimationApi::class)
@Composable
fun NavigationHost(navController: NavHostController)
{
    //val navController = rememberAnimatedNavController()
    BoxWithConstraints {
        AnimatedNavHost(
            navController = navController,
            startDestination = Destinations.Login.route)
        {
            addLogin(navController)
            addRegister(navController)
            addHome(navController)
            addHomeDetailInfo(navController)
        }
    }
}





///////////


@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addLogin(navController: NavHostController)
{
    composable(route= Destinations.Login.route,
        enterTransition = { slideInHorizontally(initialOffsetX = {1000}, animationSpec = tween(500)) },
        exitTransition = { slideOutHorizontally(targetOffsetX = {-1000}, animationSpec = tween(500)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = {-1000}, animationSpec = tween(500)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = {1000}, animationSpec = tween(500)) }
    )
    {
        val viewModel: LoginViewModel = hiltViewModel()


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
fun NavGraphBuilder.addRegister(navController: NavHostController)
{
    composable(route= Destinations.Register.route,
        enterTransition = { slideInHorizontally(initialOffsetX = {1000}, animationSpec = tween(500)) },
        exitTransition = { slideOutHorizontally(targetOffsetX = {-1000}, animationSpec = tween(500)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = {-1000}, animationSpec = tween(500)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = {1000}, animationSpec = tween(500)) })
    {
        val viewModel: RegisterViewModel = hiltViewModel()

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
fun NavGraphBuilder.addHome(navController: NavHostController)
{

    composable(route= Destinations.Home.route,
        enterTransition = { slideInHorizontally(initialOffsetX = {1000}, animationSpec = tween(500)) },
        exitTransition = { slideOutHorizontally(targetOffsetX = {-1000}, animationSpec = tween(500)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = {-1000}, animationSpec = tween(500)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = {1000}, animationSpec = tween(500)) })
    {
        val viewModel: HomeViewModel = hiltViewModel()

        HomeScreen(
            state = viewModel.state.value,
            initList = viewModel::__init__, decreaseClick = viewModel::decreaseClick, increaseCleck =viewModel::increaseClick ,
            updateAdd = viewModel::updateAdd,
            onNavigationToHightlights = {navController.navigate(Destinations.homeDetailHightlights.route)},
            onNavigationToCategories = {navController.navigate(Destinations.homeDetailCategories.route)},
            onNavigationToLatest = {navController.navigate(Destinations.homeDetailLastest.route)},
            onNavigationToRecommend = {/*Implement IA */},
            onNavigationToReInfo={id->
                navController.navigate(Destinations.homeDetailInfo.route+"/$id")},
            onDissmisDialog = viewModel::hideErrorDialog)
    }
}
@RequiresApi(Build.VERSION_CODES.S)
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addHomeDetailInfo(navController: NavHostController)
{

    composable(route= Destinations.homeDetailInfo.route+"/{idSearch}", arguments = Destinations.homeDetailInfo.arguments,
        enterTransition = { slideInHorizontally(initialOffsetX = {1000}, animationSpec = tween(500)) },
        exitTransition = { slideOutHorizontally(targetOffsetX = {-1000}, animationSpec = tween(500)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = {-1000}, animationSpec = tween(500)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = {1000}, animationSpec = tween(500)) })
    { backStackEntry->
        val id = backStackEntry.arguments?.getString("idSearch")?:""
        val viewModel: InfoViewModel = hiltViewModel()


        infoScreen(init = viewModel::__init__, state = viewModel.state.value, idSearch = id.toInt(),onBack =
        {
            navController.popBackStack()
        })

    }


}
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addHomeDetailHightLlights(navController: NavHostController)
{

    composable(route= Destinations.Home.route,
        enterTransition = { slideInHorizontally(initialOffsetX = {1000}, animationSpec = tween(500)) },
        exitTransition = { slideOutHorizontally(targetOffsetX = {-1000}, animationSpec = tween(500)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = {-1000}, animationSpec = tween(500)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = {1000}, animationSpec = tween(500)) })
    {

    }
}
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addHomeDetailCategories(navController: NavHostController)
{

    composable(route= Destinations.Home.route,
        enterTransition = { slideInHorizontally(initialOffsetX = {1000}, animationSpec = tween(500)) },
        exitTransition = { slideOutHorizontally(targetOffsetX = {-1000}, animationSpec = tween(500)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = {-1000}, animationSpec = tween(500)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = {1000}, animationSpec = tween(500)) })
    {

    }
}
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addHomeDetailLatest(navController: NavHostController)
{

    composable(route= Destinations.Home.route,
        enterTransition = { slideInHorizontally(initialOffsetX = {1000}, animationSpec = tween(500)) },
        exitTransition = { slideOutHorizontally(targetOffsetX = {-1000}, animationSpec = tween(500)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = {-1000}, animationSpec = tween(500)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = {1000}, animationSpec = tween(500)) })
    {

    }
}
@OptIn(ExperimentalAnimationApi::class)
fun NavGraphBuilder.addHomeDetailRecommend(navController: NavHostController)
{

    composable(route= Destinations.Home.route,
        enterTransition = { slideInHorizontally(initialOffsetX = {1000}, animationSpec = tween(500)) },
        exitTransition = { slideOutHorizontally(targetOffsetX = {-1000}, animationSpec = tween(500)) },
        popEnterTransition = { slideInHorizontally(initialOffsetX = {-1000}, animationSpec = tween(500)) },
        popExitTransition = { slideOutHorizontally(targetOffsetX = {1000}, animationSpec = tween(500)) })
    {

    }
}