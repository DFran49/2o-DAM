package dam.moviles.t1_proyecto02

import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.Spinner
import android.widget.TextView
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
    }

    fun inicializarAtributos(){
        spiCursos = findViewById(R.id.spiCurso)
        btnSeleccionarCurso = findViewById(R.id.btnSeleccionarCurso)
        btnEnviar = findViewById(R.id.btnEnviar)
        txtObservaciones = findViewById(R.id.txtObservaciones)
        txtAsignaturas = findViewById(R.id.txtAsignatura)

        btnSeleccionarCurso.setOnClickListener{
            val curso:String = spiCursos.selectedItem.toString()
            val asignaturas:List<String> = this.getAsignaturas(curso)
            txtAsignaturas.text = asignaturas.toString()
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
}