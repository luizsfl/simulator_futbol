package pedroluiz.projeto.simulator_futbol.ui

import android.animation.Animator
import android.animation.AnimatorListenerAdapter
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.service.autofill.FieldClassification
import android.util.Log
import android.widget.LinearLayout
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import pedroluiz.projeto.simulator_futbol.R
import pedroluiz.projeto.simulator_futbol.data.MatchesAPI
import pedroluiz.projeto.simulator_futbol.databinding.ActivityMainBinding
import pedroluiz.projeto.simulator_futbol.domain.Match
import pedroluiz.projeto.simulator_futbol.ui.adapter.MatchesAdapter
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.*
import kotlin.random.Random

class MainActivity : AppCompatActivity() {
    private lateinit var matchesApi: MatchesAPI
    private lateinit var binding: ActivityMainBinding
    private lateinit var matchesAdapter: MatchesAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupHttpClient()
        setupMatchList()
        setupMatchRefresh()
        setupFloatActionButon()

    }

    private fun setupHttpClient() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://luizsfl.github.io/matches-simulator-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        matchesApi = retrofit.create(MatchesAPI::class.java)

    }

    private fun setupFloatActionButon() {
       binding.fabSimulate.setOnClickListener {
           var random: Random = Random

           for (i in 0..matchesAdapter.itemCount-1){
               var mat = matchesAdapter.matches.get(i)
               mat.hometeam.score = random.nextInt(mat.hometeam.star+1)
               mat.awayTeam.score = random.nextInt(mat.awayTeam.star+1)
               matchesAdapter.notifyItemChanged(i)
           }
       }
    }

    private fun setupMatchRefresh() {
        binding.srfMatchs.setOnRefreshListener(this::findMatchesFromApi);

    }

    fun setupMatchList(){

        binding.rcvMatchs.setHasFixedSize(true)
        binding.rcvMatchs.layoutManager = LinearLayoutManager(this)
        matchesAdapter = MatchesAdapter(Collections.emptyList())
        binding.rcvMatchs.adapter = matchesAdapter

        findMatchesFromApi()

    }

    private fun showErrorMessage() {
        Snackbar.make(binding.fabSimulate, R.string.error_api,Snackbar.LENGTH_LONG).show()
    }

    private fun findMatchesFromApi(){
        binding.srfMatchs.isRefreshing = true

        matchesApi.Matches().enqueue(object : Callback<List<Match>> {

            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful){
                    var matches: List<Match>? = response.body()
                     matchesAdapter = MatchesAdapter(matches)
                    binding.rcvMatchs.adapter = matchesAdapter

                }else{
                    showErrorMessage()

                }

                binding.srfMatchs.isRefreshing = false

            }


            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                showErrorMessage()
                binding.srfMatchs.isRefreshing = false

            }

        })
    }
}