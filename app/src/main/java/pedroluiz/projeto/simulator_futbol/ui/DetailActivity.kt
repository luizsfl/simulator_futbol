package pedroluiz.projeto.simulator_futbol.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.bumptech.glide.Glide
import pedroluiz.projeto.simulator_futbol.databinding.ActivityDetailBinding
import pedroluiz.projeto.simulator_futbol.databinding.ActivityMainBinding
import pedroluiz.projeto.simulator_futbol.domain.Match

class DetailActivity : AppCompatActivity() {
    object Extras{
        const val MATCH = "EXTRA_MATCH"
    }
    private lateinit var binding: ActivityDetailBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setSupportActionBar(binding.toolbar)
        supportActionBar?.setDisplayHomeAsUpEnabled(true)

        loadMatchFromExtra()
    }

    private fun loadMatchFromExtra() {

        intent?.extras?.getParcelable<Match>(Extras.MATCH)?.let {
            Glide.with(this).load(it.place.image).into(binding.ivPlace)
            Glide.with(this).load(it.hometeam.image).into(binding.ivHomeTeam)
           Glide.with(this).load(it.awayTeam.image).into(binding.ivAwayTeam)
            supportActionBar?.title = it.description

            binding.tvHomeTeamName.text = it.hometeam.name
            binding.tvAwayTeamName.text = it.awayTeam.name

            val scoreHome = if(it.hometeam.score!=null) it.hometeam.score.toString() else ""
            val scoreAway = if(it.awayTeam.score!=null) it.hometeam.score.toString() else ""

            binding.tvHomeTeamScore.text = scoreHome
            binding.tvAwayTeamScore.text = scoreAway



        }
    }
}