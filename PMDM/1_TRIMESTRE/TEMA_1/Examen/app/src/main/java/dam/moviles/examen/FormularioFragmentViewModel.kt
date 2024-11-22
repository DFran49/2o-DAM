package dam.moviles.examen

import androidx.lifecycle.ViewModel

class FormularioFragmentViewModel : ViewModel() {
    var partidas: Int = 0;
    var victorias: Int = 0;
    var derrotas: Int = 0;
    var puntosJ: Int = 0;
    var puntosA: Int = 0;
    var cartaJ1: Int = 0;
    var cartaJ2: Int = 0;
    var cartaA1: Int = 0;
    var cartaA2: Int = 0;
    var numeros: List<Int> = listOf(1,2,3,4,5,6,7,10,11,12)

    fun generarCartasJugador() {
        cartaJ1 = numeros.random()
        cartaJ2 = numeros.random()
    }

    fun generarCartasAndroid() {
        cartaA1 = numeros.random()
        cartaA2 = numeros.random()
    }

    fun calcularPuntos() {
        puntosJ = cartaJ1 + cartaJ2
        puntosA = cartaA1 + cartaA2
    }

    fun calcularVictorias() : Boolean? {
        partidas++
        if (puntosA < puntosJ) {
            victorias++
            return true
        } else if (puntosA > puntosJ) {
            derrotas++
            return false
        } else {
            return null
        }
    }

    fun reiniciarCartas() {
        cartaA1 = 0
        cartaA2 = 0
        cartaJ1 = 0
        cartaJ2 = 0
    }
}