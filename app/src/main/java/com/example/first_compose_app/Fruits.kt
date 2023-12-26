package com.example.first_compose_app

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilledTonalButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedButton
import androidx.compose.material3.RadioButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontFamily
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import java.time.format.TextStyle

val FruitItems = listOf(
    Items("Mango",100,"p",R.drawable.mango),
    Items("Guava",50,"q",R.drawable.guava),
    Items("Pineapple",60,"r",R.drawable.pineapple),
    Items("Strawberry",45,"s", R.drawable.strawberry),
    Items("Melon",78,"t",R.drawable.melon),
    Items("Banana",69,"u",R.drawable.banana)
)

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Fruits(
    onCancel:()->Unit,
    onNext:(String)->Unit,
    onBack:()->Unit,
    cart:String
) {
    var cart by rememberSaveable {
        mutableStateOf(cart)
    }
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Choose Fruits", textAlign = TextAlign.Center) },
                colors=topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = { onBack() }) {
                        Icon(Icons.Default.ArrowBack,"back_button", tint = Color.White)
                    }
                }
            )
        },
        bottomBar={
            Row(
                modifier= Modifier
                    .fillMaxWidth()
                    .height(80.dp),
                horizontalArrangement = Arrangement.SpaceAround
            ){
                OutlinedButton(onClick = { onCancel() }) {
                    Text("Cancel", fontSize = 20.sp)
                }
                FilledTonalButton(onClick = { onNext(cart) }) {
                    Text("Next", fontSize = 20.sp)
                }
            }
        }
    ){
        Column(
            modifier=Modifier.padding(it)
        ){
            LazyColumn{
                item{
                    Row(
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Text("Fruit Name", fontSize = 20.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Serif)
                        Text("Price/Kg", fontSize = 20.sp , fontStyle = FontStyle.Italic, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Serif)
                    }
                }
                items(FruitItems){item->
                    var state by rememberSaveable { mutableStateOf(false) }
                    Row(
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ){
                        Box(
                            modifier=Modifier.weight(0.7f)
                        ){
                            Row{
                                RadioButton(
                                    selected = state,
                                    onClick = {
                                        state=!state
                                        if(state){
                                            cart="${cart}${item.id}"
                                        }else if(cart.contains(item.id)){
                                            cart = cart.replace(item.id,"")
                                        }
                                    }
                                )
                                Text(item.name, fontSize = 20.sp,modifier=Modifier.padding(top=7.dp))
                                Spacer(modifier=Modifier.width(7.dp))
                                Image(
                                    painter = painterResource(id = item.image),
                                    contentDescription = null,
                                    modifier = Modifier.clip(CircleShape).height(60.dp)
                                )
                            }

                        }
                        Box(
                            modifier=Modifier.weight(0.3f)
                        ){
                            Text("$${item.price}", fontSize = 20.sp,modifier=Modifier.padding(top=7.dp))
                        }
                    }
                }
//                item{
//                    Text("cart is $cart")
//                }
            }
        }
    }
}


@Preview
@Composable
fun PreviewFruits(){
    Fruits({},{},{},"")
}
