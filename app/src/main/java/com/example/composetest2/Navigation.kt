package com.example.composetest2

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.navArgument
import androidx.navigation.compose.rememberNavController
import com.example.composetest2.ui.theme.MainScreen


@Composable
fun Navigation() {
    val navController = rememberNavController()
    NavHost(navController = navController, startDestination = Screen.AutogenerateNickname.route) {
        composable(route = Screen.MainScreen.route) {
            MainScreen(navController = navController)
        }
        composable(route = Screen.CreateNickNameScreen.route) {
            CreateNickNameView()
        }
        composable(route = Screen.CategoryScreen.route) {
            CategoryView()
        }
        composable(route = Screen.AutogenerateNickname.route) {
            AutogenerateNickname()
        }
//        composable(
//            route = Screen.SecondScreen.route + "?name={name}",
//            arguments = listOf(
//                navArgument("name") {
//                    type = NavType.StringType
//                    defaultValue = "DefaultVal"
//                    nullable = true
//                }
//            )
//        ) { entry ->
//            SecondScreen(name = entry.arguments?.getString("name"))
//        }
    }
}




