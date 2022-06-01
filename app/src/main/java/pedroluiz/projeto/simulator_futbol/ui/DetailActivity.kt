package pedroluiz.projeto.simulator_futbol.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import pedroluiz.projeto.simulator_futbol.databinding.ActivityDetailBinding
import pedroluiz.projeto.simulator_futbol.databinding.ActivityMainBinding

class DetailActivity : AppCompatActivity() {
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)


    }
}