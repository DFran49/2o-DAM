package dam.moviles.pruebafilebase

import android.os.Bundle
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseUser
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import dam.moviles.pruebafilebase.databinding.ActivityMainBinding
import kotlinx.coroutines.tasks.await

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicializarBinding()
        inicializarBotonRegistrar()
        inicializarBotonLogIn()
        setContentView(binding.root)
    }

    fun trabajar() {
        /* Vamos a crear un alumno con id automático
            nombre: Hermenegildo Lanz Pérez
            edad: 32
            ciudad: Jaén
         */
        /*var h = Alumno(
            "Hermenegildo",
            "Lans Pérez",
            32,
            "Jaén"
        )
        Firebase.firestore
            .collection("alumnos")
            .document() // creamos nuevo documento con id automático
            .set(h)*/

        val documento = Firebase.firestore
            .collection("alumnos")
            .document()

        val id = documento.id

        var h = Alumno(
            id,
            "Hermenegildo",
            "Lans Pérez",
            32,
            "Jaén"
        )

        documento.set(h)
            .addOnSuccessListener {
                lanzarToast("alumno creado")
            }.addOnFailureListener { excepcion ->
                lanzarToast(excepcion.message.toString())
            }
    }

    fun trabajar2() {
        // vamos a consultar todos los alumnos
        val lista:List<Alumno> = Firebase.firestore
            .collection("alumnos")
            .get()
            .await()
            .toObjects(Alumno::class.java)
    }

    fun inicializarBotonLogIn() {
        binding.btnLogIn.setOnClickListener {
            Firebase.auth.signInWithEmailAndPassword(
                binding.txtMail.text.toString(),
                binding.txtPass.text.toString()
            ).addOnSuccessListener {
                val usuario = Firebase.auth.currentUser
                if (usuario != null && usuario.isEmailVerified) {
                    lanzarToast("usuario accede correctamente")
                    trabajar2()
                } else {
                    lanzarToast("el usuario debe verificar su cuenta")
                }
            }.addOnFailureListener { excepcion ->
                lanzarToast(excepcion.toString())
            }
        }
    }

    fun inicializarBotonRegistrar() {
        binding.btnRegistro.setOnClickListener {
            //creo un usuario en filebase
            Firebase.auth.createUserWithEmailAndPassword(
                binding.txtMail.text.toString(),
                binding.txtPass.text.toString()
            ).addOnSuccessListener {
                val usuario:FirebaseUser? = Firebase.auth.currentUser
                if (usuario != null) {
                    usuario.sendEmailVerification()
                        .addOnSuccessListener {
                            lanzarToast("usuaruo creado")
                        }.addOnFailureListener {
                            lanzarToast("no se mandó el correo de verificación")
                        }

                }
            }.addOnFailureListener { excepcion ->
                lanzarToast("Errir: ${excepcion.message}")
            }
        }
    }

    fun inicializarBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
    }

    fun lanzarToast(mensaje:String) {
        Toast.makeText(
            this,
            mensaje,
            Toast.LENGTH_LONG
        ).show()
    }
}