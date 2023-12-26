package com.example.first_compose_app

import androidx.compose.runtime.Composable
import androidx.navigation.NavArgument
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument

@Composable
fun Nav() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = PAGE.START.name){
        composable(PAGE.START.name){
            Start{
                navController.navigate(PAGE.VEGETABLES.name)
            }
        }
        composable(PAGE.VEGETABLES.name){
            Vegetables(
                onBack = { navController.navigate(PAGE.START.name)},
                onCancel = { navController.navigate(PAGE.START.name)},
                onNext = {
                    navController.navigate("PAGE.FRUITS.name?cart=${it}")
                }
            )
        }
        composable(
            "PAGE.FRUITS.name?cart={cart}",
            arguments= listOf(
                navArgument("cart"){
                    type= NavType.StringType
                    defaultValue=""
                }
            )
        ){
            val cartItems = it.arguments?.getString("cart") ?: ""
            Fruits(
                onBack = { navController.navigate(PAGE.VEGETABLES.name)},
                onCancel = { navController.navigate(PAGE.START.name)},
                onNext = {cart->
                    navController.navigate("PAGE.BILL.name?cart=${cart}")},
                cart = cartItems
            )
        }

        composable(
            "PAGE.BILL.name?cart={cart}",
            arguments = listOf(
                navArgument("cart"){
                    type= NavType.StringType
                    defaultValue=""
                }
            )
        ){
            val cart =it.arguments?.getString("cart") ?: ""
            Bill(
                onBack = {cart->
                    navController.navigate("PAGE.FRUITS.name?cart=${cart}")
                         },
                onCancel = { navController.navigate(PAGE.START.name)},
                onSubmit = {navController.navigate(PAGE.START.name)},
                cart=cart
            )
        }
    }
}