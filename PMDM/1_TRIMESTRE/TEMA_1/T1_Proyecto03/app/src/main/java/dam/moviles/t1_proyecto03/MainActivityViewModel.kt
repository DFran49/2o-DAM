package dam.moviles.t1_proyecto03

import androidx.lifecycle.ViewModel

class MainActivityViewModel : ViewModel(){
    var base:Long = 0
    var situacion:Situacion = Situacion.PARADO
    var tiempoTranscurrido:Long = 0


}