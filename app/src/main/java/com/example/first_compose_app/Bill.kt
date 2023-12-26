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
import androidx.compose.material3.Divider
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

//function to get item from its id
fun getItemById(id: String): Items? {
    val combinedList = VegetableItems + FruitItems
    return combinedList.find { it.id == id }
}

//if we go back from bill page to fruit section, it should remove present fruits from cart and again should ask from user
fun prev(cart:String):String{
    var cartNew=cart
    FruitItems.forEach{
        if(cartNew.contains(it.id)){
            cartNew=cartNew.replace(it.id,"")
        }
    }
    return cartNew
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Bill(
    onCancel:()->Unit,
    onSubmit:()->Unit,
    onBack:(String)->Unit,
    cart:String
) {
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Bill", textAlign = TextAlign.Center) },
                colors=topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primary,
                    titleContentColor = MaterialTheme.colorScheme.onPrimary
                ),
                navigationIcon = {
                    IconButton(onClick = { onBack(prev(cart))}) {
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
                FilledTonalButton(onClick = { onSubmit() }) {
                    Text("Submit", fontSize = 20.sp)
                }
            }
        }
    ){
        Column(
            modifier=Modifier.padding(it)
        ){
            LazyColumn{
                var amount =0
                item{
                    Row(
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(vertical = 5.dp),
                        horizontalArrangement = Arrangement.SpaceAround
                    ){
                        Text("Items Summary", fontSize = 20.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Serif)
                    }
                }
                items(cart.length){index->
                    val item : Items? = getItemById("${cart[index]}")
                    Row(
                        modifier= Modifier
                            .fillMaxWidth()
                            .padding(10.dp)
                    ){
                        Box(
                            modifier=Modifier.weight(0.7f)
                        ){
                            Row{
                                Text("${item!!.name}", fontSize = 20.sp,modifier=Modifier.padding(top=7.dp))
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
                            Text("$${item!!.price}", fontSize = 20.sp,modifier=Modifier.padding(top=7.dp))
                            amount+=item.price
                        }
                    }
                }
                item{
                    Divider(color = Color.Green, thickness = 2.dp)
                    Row(
                        modifier = Modifier.fillMaxWidth().padding(vertical = 10.dp),
                        horizontalArrangement = Arrangement.Center
                    ){
                        Text("Total Bill: $${amount}", fontSize = 20.sp, fontStyle = FontStyle.Italic, fontWeight = FontWeight.ExtraBold, fontFamily = FontFamily.Serif)
                    }
                    Divider(color = Color.Green, thickness = 2.dp)

                }
            }
        }
    }
}

@Preview
@Composable
fun Pre(){
    Bill({},{},{},"ap")
}


