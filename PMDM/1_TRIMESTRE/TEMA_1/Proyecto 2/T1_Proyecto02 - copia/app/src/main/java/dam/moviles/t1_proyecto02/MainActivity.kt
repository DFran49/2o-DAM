package dam.moviles.t1_proyecto02

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

class MainActivity : AppCompatActivity() {

    private lateinit var spiCursos: Spinner
    private lateinit var btnSeleccionarCurso: Button
    private lateinit var btnEnviar:Button
    private lateinit var txtObservaciones:EditText
    private lateinit var txtAsignaturas:TextView

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        this.inicializarAtributos()
        this.inicializarBotones()
    }

    fun inicializarAtributos(){
        spiCursos = findViewById(R.id.spiCurso)
        btnSeleccionarCurso = findViewById(R.id.btnSeleccionarCurso)
        btnEnviar = findViewById(R.id.btnEnviar)
        txtObservaciones = findViewById(R.id.txtObservaciones)
        txtAsignaturas = findViewById(R.id.txtAsignatura)
    }

    fun inicializarBotones() {
        btnSeleccionarCurso.setOnClickListener{
            val curso:String = spiCursos.selectedItem.toString()
            txtAsignaturas.text = getListaAsignaturasBonica(this.getAsignaturas(curso))
        }

        btnEnviar.setOnClickListener() {
            val texto:String = txtObservaciones.text.toString()
            Toast.makeText(
                this, //Se verá en la activity que estamos programando
                getString(R.string.mensajeToast,texto), //texto que se verá
                Toast.LENGTH_LONG //duración
            ).show()
        }
    }

    fun getAsignaturas(curso:String):List<String>{
        return when(curso){
            "1º DAM" -> listOf(getString(R.string.programacion),"entornos","marcas","base de datos","sistemas")
            "2º DAM" -> listOf("móviles","interfaces","acceso a datos","servicios y procesos","sistemas de gestión empresarial")
            else -> throw Exception("curso no admitido")
        }
    }
    /*fun getAsignaturas(curso:String):List<String> =
        when(curso){
            "1º DAM" -> listOf(getString(R.string.programacion),"entornos","marcas","base de datos","sistemas")
            "2º DAM" -> listOf("móviles","interfaces","acceso a datos","servicios y procesos","sistemas de gestión empresarial")
            else -> throw Exception("curso no admitido")
        }*/

    fun getListaAsignaturasBonica(lista:List<String>):String =
        lista
            .map { dato -> " - ${dato} \n" }
            .joinToString("")

    /*fun getListaAsignaturasBonica(lista:List<String>):String{
        var texto:String = ""
        for (asignatura in lista){
            //texto += " - " + asignatura + "\n" //COMO EN JAVA
            texto += " - $asignatura \n" //EN KOTLIN
        }

        return texto
    }*/



}