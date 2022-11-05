package dev.steve.meditaccompose.presentation.component

import androidx.compose.compiler.plugins.kotlin.ComposeFqNames.remember
import androidx.compose.foundation.layout.*
import androidx.compose.material.LinearProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Devices
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.constraintlayout.compose.ConstraintLayout
import com.gowtham.ratingbar.RatingBar

@Composable
fun RatingBar(rating:MutableState<Double>,sampleTotal:MutableList<Int>)
{

    var sampleTotalN:Int = 0
    for (sample in sampleTotal) sampleTotalN +=sample


    Row(modifier = Modifier
        .fillMaxWidth()
        .padding(5.dp)) {
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.spacedBy(30.dp)) {
            Text(text = rating.value.toString(), style = TextStyle(fontSize = 50.sp, fontWeight = FontWeight.Bold))
            RatingBar(value = (if (rating.value >= Math.floor(rating.value)+0.4) Math.floor(rating.value)+0.5 else Math.floor(rating.value)).toFloat(),
                onValueChange = {},
                onRatingChanged = {})
        }
        Spacer(modifier = Modifier.width(10.dp))
        Column(horizontalAlignment = Alignment.CenterHorizontally, verticalArrangement = Arrangement.SpaceBetween) {

            for (numberIterator in 5 downTo 1){

                val sampleN:Int = sampleTotal[numberIterator-1]

                val calculatePercentSample: Double = ((sampleN*100)/sampleTotalN).toDouble()



                val color = when (numberIterator) {
                    1 -> Color(242,67,32)
                    2 -> Color(242,120,32)
                    3 -> Color(242,196,32)
                    4 -> Color(170,199,44)
                    5 -> Color(100,199,44)
                    else -> {Color.Black}
                }
                PogressBar(number = numberIterator, percent = calculatePercentSample/100,color=color)
            }
        }
    }
}

@Composable
fun PogressBar(number:Int,percent:Double,color: Color)
{
    ConstraintLayout() {
        val (textRef,progressbarRef) = createRefs()
        Text(modifier = Modifier
            .constrainAs(textRef) { start.linkTo(parent.start) },
            text = number.toString(), style = TextStyle(fontSize = 18.sp, fontWeight = FontWeight.Normal))

        LinearProgressIndicator(progress = percent.toFloat(), color = color, modifier = Modifier
            .fillMaxWidth()
            .height(16.dp)
            .constrainAs(progressbarRef)
            {
                start.linkTo(textRef.end, margin = 6.dp)
                top.linkTo(textRef.top, margin = 5.dp)
            })

    }
}
