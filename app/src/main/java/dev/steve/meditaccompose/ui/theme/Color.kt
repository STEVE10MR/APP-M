package dev.steve.meditaccompose.ui.theme

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color

/*
    BLUE LIGHT PALETTE
 */
val Blue500 = Color(0xFF2196f3)  // Primary
val BLUE800 = Color(0xFF0277BD)  // PrimaryVariant
val CYAN500 = Color(0xFF00bcd4)  // Secondary
val CYAN700 = Color(0xff008ba3)  // SecondaryVariant
val LIGHTBLUE50 = Color(0xffe1f5fe)  // Background
val LIGHTBLUE100 = Color(0xffb3e5fc)  // Surface
val RED600 = Color(0xffe53935)  //Error

/*
    BLUE DARK PALETTE
 */
val BLUE900 = Color(0xFF0d47a1)  // Primary
val BLUE950 = Color(0xFF002171)  // PrimaryVariant
val CYAN900 = Color(0xFF006064)  // Secondary
val CYAN800 = Color(0xff428e92)  // SecondaryVariant
val BLUEGREY900 = Color(0xff000a12)  // Background
val BLUEGREY800 = Color(0xff263238)  // Surface
val RED800 = Color(0xffba000d)  //Error


/*
    SOCIAL MEDIA COLORS
 */
val FACEBOOKCOLOR = Color(0xFF4267B2)
val GMAILCOLOR = Color(0xFFDB4437)
val OFFICE = Color(211,237,251,255)
val OFFICEBACK = Color(37, 150, 190)


object AppColors {
    val Purple200 = Color(0xFFBB86FC)
    val Purple500 = Color(0xFF6200EE)

    object Light {
        val Background = Color(0xFFFFFFFF)
        val LightShadow = Color(0xFFFFFFFF)
        val DarkShadow = Color(0xFFA8B5C7)
        val TextColor = Color.Black
    }

    object Dark {
        val Background = Color(0xFF303234)
        val LightShadow = Color(0x66494949)
        val DarkShadow = Color(0x66000000)
        val TextColor = Color.White
    }

    @Composable
    fun lightShadow() = if (MaterialTheme.colors.isLight) Light.LightShadow else Dark.LightShadow

    @Composable
    fun darkShadow() = if (MaterialTheme.colors.isLight) Light.DarkShadow else Dark.DarkShadow
}