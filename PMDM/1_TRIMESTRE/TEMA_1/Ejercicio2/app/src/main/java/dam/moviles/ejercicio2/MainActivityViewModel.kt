package dam.moviles.ejercicio2

import androidx.lifecycle.ViewModel
import kotlin.random.Random

class MainActivityViewModel : ViewModel() {
    var vidas:Int = 4
    var numero:Int = Random.nextInt(0,10)

}