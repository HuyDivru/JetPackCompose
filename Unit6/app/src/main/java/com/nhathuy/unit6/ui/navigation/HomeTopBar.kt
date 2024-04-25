package com.nhathuy.unit6.ui.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import androidx.navigation.navigation
import com.nhathuy.unit6.ui.home.HomeDestination
import com.nhathuy.unit6.ui.home.HomeScreen
import com.nhathuy.unit6.ui.item.*

@Composable
fun HomeNavHost(
    navController:  NavHostController,
    modifier :Modifier = Modifier
){
    //composable giúp quản lý chuyển màn hình dễ hơn
    NavHost(navController = navController, startDestination = HomeDestination.route,modifier=modifier){
        composable(route=HomeDestination.route){
            HomeScreen(
                navigateToItemEntry = { navController.navigate(ItemEntryDestination.route) },
                navigateToItemUpdate = {navController.navigate("${ItemDetailsDestination.route}/${it}")}
            )
        }
        composable(route=ItemEntryDestination.route){
            ItemEntryScreen(
                navigateBack = {navController.popBackStack()},
                onNavigateUp = {navController.navigateUp()}
            )
        }
        composable(
            route=ItemDetailsDestination.routeWithArgs,
            arguments= listOf(navArgument(ItemDetailsDestination.itemIdArg){
                type=NavType.IntType
            })
        ){
            ItemDetailsScreen(
                navigateToEditItem = { navController.navigate("${ItemEditDestination.route}/$it") },
                navigateBack = { navController.navigateUp() })
        }

        composable(
            route=ItemEditDestination.routedWithArgs,
            arguments = listOf(navArgument(ItemEditDestination.itemArg){
                type=NavType.IntType
            })
        ){
            ItemEditScreen(
                navigateBack = { navController.popBackStack() },
                onNavigateUp = { navController.navigateUp() })
        }
    }
}