package com.kuldeep.whatsapp.presentations.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.kuldeep.whatsapp.presentations.callscreen.CallScreen
import com.kuldeep.whatsapp.presentations.communitiesscreen.CummunitiesScreen
import com.kuldeep.whatsapp.presentations.homescreen.HomeScreen
import com.kuldeep.whatsapp.presentations.splashscreen.SplashScreen
import com.kuldeep.whatsapp.presentations.updatescreen.UpdateScreen
import com.kuldeep.whatsapp.presentations.userregistrationscreen.AuthScreen
import com.kuldeep.whatsapp.presentations.welcomescreen.WelcomeScreen

@Composable
fun WhatsAppNavigationSystem() {

    val navController = rememberNavController()
     NavHost(startDestination = Routes.SplashScreen, navController = navController){
         composable<Routes.SplashScreen> {
             SplashScreen(navController)
         }
         composable<Routes.WelcomeScreen> {
             WelcomeScreen(navController)
         }
         composable<Routes.UserRegistrationScreen> {
             AuthScreen(navController)
         }
         composable<Routes.HomeScreen> {
             HomeScreen()
         }
         composable<Routes.UpdateScreen> {
             UpdateScreen()
         }
         composable<Routes.CommunitiesScreen> {
             CummunitiesScreen()
         }
         composable<Routes.CallsScreen> {
             CallScreen()
         }


     }
 }