import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import com.jorgearceruiz97.black_jack_jorgitorr.R
import com.jorgearceruiz97.black_jack_jorgitorr.model.Routes
import com.jorgearceruiz97.black_jack_jorgitorr.ui.theme.Black_jack_jorgitorrTheme


@Composable
fun menuInicio(navController: NavHostController){
    val imageModifier = Modifier
        .size(900.dp)
        .border(BorderStroke(1.dp, Color.Black))
        .background(Color.Yellow)

    Image(painter = painterResource(id = R.drawable.fondo), contentDescription = "",
        contentScale = ContentScale.Crop,
        modifier = imageModifier)
    Row(modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
        verticalAlignment = Alignment.CenterVertically){
        Text(text="BlackJack")
        Button(onClick = { navController.navigate(Routes.dosJugadores.routes) }) {
            Text(text = "2 PLAYERS")
        }
        Button(onClick = { navController.navigate(Routes.unJugador.routes)}) {
            Text(text = "PLAYER VS PC")
        }
    }
}
