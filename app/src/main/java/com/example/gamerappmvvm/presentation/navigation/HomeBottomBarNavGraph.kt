package com.example.gamerappmvvm.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.gamerappmvvm.presentation.screens.my_posts.MyPostsScreen
import com.example.gamerappmvvm.presentation.screens.posts.PostsScreen
import com.example.gamerappmvvm.presentation.screens.profile.ProfileScreen

@Composable
fun HomeBottomBarNavGraph(navController: NavHostController) {

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.Posts.route
    ) {

        composable(route = HomeBottomBarScreen.Posts.route) {
            PostsScreen(navController)
        }

        composable(route = HomeBottomBarScreen.MyPosts.route) {
            MyPostsScreen(navController)
        }

        composable(route = HomeBottomBarScreen.Profile.route) {
            ProfileScreen(navController)
        }

        detailsNavGraph(navController = navController)

    }

}


sealed class HomeBottomBarScreen(
    val route: String,
    var title: String,
    val icon: ImageVector
) {

    object Posts : HomeBottomBarScreen(
        route = "posts",
        title = "Posts",
        icon = Icons.Default.List
    )

    object MyPosts : HomeBottomBarScreen(
        route = "my_posts",
        title = "Mis Posts",
        icon = Icons.Outlined.List
    )

    object Profile : HomeBottomBarScreen(
        route = "profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )
}