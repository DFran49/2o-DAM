package dam.moviles.proyecto07

import android.os.Bundle
import android.view.Menu
import android.view.MenuInflater
import android.view.MenuItem
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.MenuProvider
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.navigation.findNavController
import androidx.navigation.ui.onNavDestinationSelected
import dam.moviles.proyecto07.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    lateinit var binding:ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        inicializarBinding()
        configurarToolbar()
        setContentView(binding.root)
    }

    private fun configurarToolbar() {
        setSupportActionBar(binding.materialToolbar)
        addMenuProvider( object : MenuProvider{
            override fun onCreateMenu(menu: Menu, menuInflater: MenuInflater) {
                menuInflater.inflate(R.menu.menu_barra_tareas,menu)
            }

            override fun onMenuItemSelected(menuItem: MenuItem): Boolean {
                val navController = findNavController(R.id.frmNavHostFragment)
                menuItem.onNavDestinationSelected(navController)
                return true
            }
        })
    }

    private fun inicializarBinding() {
        binding = ActivityMainBinding.inflate(layoutInflater)
    }
}