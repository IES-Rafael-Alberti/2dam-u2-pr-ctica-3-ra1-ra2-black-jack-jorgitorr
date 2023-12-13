package com.jorgearceruiz97.black_jack_jorgitorr

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.ui.Modifier
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.data.Routes
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.ui.BlackJackScreen
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.ui.BlackJackViewModel
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.ui.HighestCardScreen
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.ui.HighestCardViewModel
import com.jorgearceruiz97.black_jack_jorgitorr.ui.theme.Black_jack_jorgitorrTheme
import com.jorgearceruiz97.black_jack_jorgitorr.cardgames.ui.menuInicio

class MainActivity : ComponentActivity() {
    private val highestCardViewModel: HighestCardViewModel by viewModels()
    private val blackJackViewModel: BlackJackViewModel by viewModels()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Black_jack_jorgitorrTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    val navController = rememberNavController()
                    NavHost(navController = navController,startDestination = Routes.menuInicio.routes){
                        //menu inicio
                        composable(Routes.menuInicio.routes){ menuInicio(navController) }
                        //HighestCard
                        composable(Routes.highestCard.routes){ HighestCardScreen(
                            navController = navController,
                            highestCardViewModel = highestCardViewModel
                        )}
                        //blackJack 2 Jugadores
                        composable(Routes.blackJack2Players.routes){ BlackJackScreen(
                            navController = navController,
                            blackjackviewmodel = blackJackViewModel
                        )}
                    }
                }
            }
        }
    }
}



