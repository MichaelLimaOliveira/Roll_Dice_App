package com.example.myapplication

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Canvas
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.drawscope.DrawScope
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import com.example.myapplication.ui.theme.MyApplicationTheme
import kotlin.random.Random

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                SecondScreen()
            }
        }
    }
}

fun getRandomNumber(): Int {
    return Random.nextInt(1, 7) // Gera um número aleatório de 1 a 6 (inclusive)
}

fun DrawScope.circle(offset: (Float) -> Offset) {
    val radius = Dp(20f).value;
    drawCircle(
        Color.Black,
        radius = radius,
        center = offset(radius)
    )
}

fun DrawScope.center() {
    circle {
        Offset((size.width / 2f) + (it / 2f), (size.height / 2f) + (it / 2f) );
    }
}

fun DrawScope.topRight() {
    circle {
        Offset( size.width - it, it * 2f)
    }
}

fun DrawScope.topLeft() {
    circle {
        Offset( it * 2f, it * 2f)
    }
}

fun DrawScope.bottomLeft() {
    circle {
        Offset( it * 2f, size.height - it )
    }
}

fun DrawScope.bottomRight() {
    circle {
        Offset( size.width - it , size.height - it )
    }
}

fun DrawScope.centerLeft() {
    circle {
        Offset(it * 2f , (size.height /2f) + (it / 2f))
    }
}

fun DrawScope.centerRight() {
    circle {
        Offset( size.width - it, (size.height / 2f) + (it / 2f))
    }
}

fun DrawScope.bullet(faceNumber: Int) {
    when(faceNumber) {
        1 -> {
            center()
        }
        2 -> {
            topRight()
            bottomLeft()
        }
        3 -> {
            center()
            topRight()
            bottomLeft()
        }
        4 -> {
            bottomLeft()
            bottomRight()
            topLeft()
            topRight()
        }
        5 -> {
            bottomLeft()
            bottomRight()
            center()
            topLeft()
            topRight()
        }
        6 -> {
            centerRight()
            centerLeft()
            topLeft()
            topRight()
            bottomLeft()
            bottomRight()
        }
    }
}

@Composable
fun Dice(faceNumber: Int, modifier: Modifier) {
    Canvas(modifier = modifier.size(96.dp, 96.dp)) {
        drawRoundRect(
            Color.Green,
            cornerRadius = CornerRadius(20f, 20f),
            topLeft = Offset(10f, 10f),
            size = size
        )
        bullet(faceNumber = faceNumber);
    }
}

@Composable
fun SecondScreen() {
    var randomNumber by remember { mutableStateOf(getRandomNumber()) }
    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Black)
    ) {
        Dice(randomNumber, Modifier.align(Alignment.Center))

        Button(
            onClick = {
                randomNumber = getRandomNumber()
            },
            modifier = Modifier
                .align(Alignment.Center)
                .offset(y = (100).dp)
        ) {

            Text(text = "Roll Dice")
        }
    }
}

@Preview
@Composable
fun FirstScreenPreview() {
   SecondScreen()
}
