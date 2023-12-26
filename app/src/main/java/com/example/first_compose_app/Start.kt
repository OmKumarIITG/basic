package com.example.first_compose_app

import android.graphics.Color.parseColor
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ShoppingCart
import androidx.compose.material.icons.outlined.ShoppingCart
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonColors
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExtendedFloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Brush
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun Start(
    onClick: ()->Unit
) {
    Box(
        modifier=Modifier.fillMaxSize().background(
            Brush.verticalGradient(
                listOf(Color.White,Color.Red),
                startY = 1100f,
            )
        )
    ) {
        Column(
            modifier = Modifier
                .fillMaxSize(),
            verticalArrangement = Arrangement.Bottom,
            horizontalAlignment = Alignment.CenterHorizontally
        ) {
            Box(
                modifier = Modifier.fillMaxWidth(0.5f).fillMaxHeight(0.5f)
            ) {
                Image(
                    painter = painterResource(id = R.drawable.shopping),
                    contentDescription = "shopping_img",
                    modifier = Modifier.fillMaxSize(),
                    contentScale = ContentScale.FillWidth
                )
            }
            Button(
                onClick = { onClick() },
                colors = ButtonDefaults.buttonColors(
                    Color.Transparent
                ),
                contentPadding = PaddingValues(),
                modifier = Modifier.padding(vertical = 150.dp),
                border = BorderStroke(1.dp,Color(parseColor("#D43B3B")))
            ) {
                Box(
                    modifier = Modifier
                        .background(
                            Brush.horizontalGradient(
                                colors = listOf(
                                    Color.Red,
                                    Color.Yellow
                                )
                            )
                        )
                        .padding(horizontal = 30.dp, vertical = 8.dp),
                    contentAlignment = Alignment.Center
                ) {
                    Row {
                        Icon(Icons.Outlined.ShoppingCart, "Cart")
                        Spacer(modifier = Modifier.width(15.dp))
                        Text("Start Shopping", color = Color.White)
                    }
                }
            }
        }
    }
}

@Preview
@Composable
fun Start(){
    Start({})
}