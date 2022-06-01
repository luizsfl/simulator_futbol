package pedroluiz.projeto.simulator_futbol.data;

import java.util.List;

import pedroluiz.projeto.simulator_futbol.domain.Match;
import retrofit2.Call;
import retrofit2.http.GET;

public interface MatchesAPI {

    @GET("matches.json")
    Call<List<Match>> Matches();


}
