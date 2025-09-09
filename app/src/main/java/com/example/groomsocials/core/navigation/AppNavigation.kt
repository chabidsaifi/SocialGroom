package com.example.groomsocials.core.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.groomsocials.features.splash.SplashScreen
import com.example.groomsocials.features.onboarding.OnboardingScreen
import com.example.groomsocials.features.auth.AuthScreen
import com.example.groomsocials.features.profile.setup.ProfileSetupScreen
import com.example.groomsocials.features.main.MainScreen

@Composable
fun AppNavigation(
    navController: NavHostController,
    startDestination: String = Screen.Splash.route
) {
    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        // Splash Screen
        composable(Screen.Splash.route) {
            SplashScreen(
                onNavigateToOnboarding = {
                    navController.navigate(Screen.Onboarding.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                },
                onNavigateToMain = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.Splash.route) { inclusive = true }
                    }
                }
            )
        }
        
        // Onboarding Flow
        composable(Screen.Onboarding.route) {
            OnboardingScreen(
                onNavigateToAuth = {
                    navController.navigate(Screen.Auth.route) {
                        popUpTo(Screen.Onboarding.route) { inclusive = true }
                    }
                }
            )
        }
        
        // Authentication
        composable(Screen.Auth.route) {
            AuthScreen(
                onNavigateToProfileSetup = {
                    navController.navigate(Screen.ProfileSetup.route) {
                        popUpTo(Screen.Auth.route) { inclusive = true }
                    }
                }
            )
        }
        
        // Profile Setup
        composable(Screen.ProfileSetup.route) {
            ProfileSetupScreen(
                onNavigateToMain = {
                    navController.navigate(Screen.Main.route) {
                        popUpTo(Screen.ProfileSetup.route) { inclusive = true }
                    }
                }
            )
        }
        
        // Main App
        composable(Screen.Main.route) {
            MainScreen()
        }
    }
}

// Screen definitions
sealed class Screen(val route: String) {
    object Splash : Screen("splash")
    object Onboarding : Screen("onboarding")
    object Auth : Screen("auth")
    object ProfileSetup : Screen("profile_setup")
    object Main : Screen("main")
    
    // Main App Tabs
    object Home : Screen("home")
    object Explore : Screen("explore")
    object Create : Screen("create")
    object Messages : Screen("messages")
    object Profile : Screen("profile")
    
    // Feature Modules
    object Marketplace : Screen("marketplace")
    object Food : Screen("food")
    object Events : Screen("events")
    object Communities : Screen("communities")
    object Notifications : Screen("notifications")
    object Wallet : Screen("wallet")
}

// Deep Link Patterns
object DeepLinks {
    const val POST = "groomsocials://post/{postId}"
    const val USER = "groomsocials://user/{userId}"
    const val STORY = "groomsocials://story/{storyId}"
    const val REEL = "groomsocials://reel/{reelId}"
    const val CHAT = "groomsocials://chat/{chatId}"
    const val EVENT = "groomsocials://event/{eventId}"
    const val PRODUCT = "groomsocials://product/{productId}"
}

