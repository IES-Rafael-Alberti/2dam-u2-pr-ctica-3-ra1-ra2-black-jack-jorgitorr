package com.jorgearceruiz97.black_jack_jorgitorr.clases

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.jorgearceruiz97.black_jack_jorgitorr.model.Routes
import com.jorgearceruiz97.black_jack_jorgitorr.ui.theme.Black_jack_jorgitorrTheme
import menu
import menu2
import menuInicio

class MainActivity : ComponentActivity() {
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
                        composable(Routes.menuInicio.routes){ menuInicio(navController) }
                        composable(Routes.dosJugadores.routes){menu(navController)}
                        composable(Routes.unJugador.routes){menu2(navController)}
                    }
                }
            }
        }
    }
}



