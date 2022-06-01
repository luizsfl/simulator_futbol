package pedroluiz.projeto.simulator_futbol

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pedroluiz.projeto.simulator_futbol.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupMatchList()
        setupMatchRefresh()
        setupFloatActionButon()

    }

    private fun setupFloatActionButon() {
       // TODO("Not yet implemented")
    }

    private fun setupMatchRefresh() {
      //  TODO("Not yet implemented")
    }

    fun setupMatchList(){
        //TODO LISTAR AS PARTIDAS CONSUMINDO NOSSA API

    }
}