package pedroluiz.projeto.simulator_futbol.ui

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

class MainActivity : AppCompatActivity() {
    private lateinit var matchesApi: MatchesAPI
    private lateinit var binding: ActivityMainBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupHttpClient()
        setupMatchList()
       // setupMatchRefresh()
        //setupFloatActionButon()

    }

    private fun setupHttpClient() {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://luizsfl.github.io/matches-simulator-api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()

        matchesApi = retrofit.create(MatchesAPI::class.java)

    }

    private fun setupFloatActionButon() {
       // TODO("Not yet implemented")
    }

    private fun setupMatchRefresh() {
      //  TODO("Not yet implemented")
    }

    fun setupMatchList(){

        binding.rcvMatchs.setHasFixedSize(true)
        binding.rcvMatchs.layoutManager = LinearLayoutManager(this)

        matchesApi.Matches().enqueue(object : Callback<List<Match>> {

            override fun onResponse(call: Call<List<Match>>, response: Response<List<Match>>) {
                if (response.isSuccessful){
                   var matches: List<Match>? = response.body()
                    var matchesAdapter = MatchesAdapter(matches)
                    binding.rcvMatchs.adapter = matchesAdapter

                }else{
                    showErrorMessage()
                }
            }


            override fun onFailure(call: Call<List<Match>>, t: Throwable) {
                showErrorMessage()
            }

        })
    }

    private fun showErrorMessage() {
        Snackbar.make(binding.fabSimulate, R.string.error_api,Snackbar.LENGTH_LONG).show()
    }
}