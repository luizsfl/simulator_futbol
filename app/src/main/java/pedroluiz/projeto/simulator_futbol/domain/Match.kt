package pedroluiz.projeto.simulator_futbol.domain

import com.google.gson.annotations.SerializedName

data class Match(
    @SerializedName("descricao")
    val description : String,
    @SerializedName("local")
    val place : Place,
    @SerializedName("mandante")
    val hometeam :Team,
    @SerializedName("visitante")
    val awayTeam: Team
)
