package se.fransman.smartaim

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import initKoin
import org.koin.android.ext.koin.androidContext

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        enableEdgeToEdge()
        super.onCreate(savedInstanceState)

        initKoin(
            appDeclaration = { androidContext(this@MainActivity) },
        )

        setContent {
            App()
        }
    }
}