package pedroluiz.projeto.simulator_futbol.domain

data class Match(
    val description : String,
    val place : Place,
    val hometeam :Team,
    val awayTeam: Team
)
