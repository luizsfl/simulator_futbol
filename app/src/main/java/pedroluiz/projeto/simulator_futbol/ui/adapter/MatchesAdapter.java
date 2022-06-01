package pedroluiz.projeto.simulator_futbol.ui.adapter;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;

import java.util.List;

import pedroluiz.projeto.simulator_futbol.databinding.MatchItemBinding;
import pedroluiz.projeto.simulator_futbol.domain.Match;

public class MatchesAdapter extends RecyclerView.Adapter<MatchesAdapter.ViewHolder>{

    private List<Match> matches;

    public List<Match> getMatches() {
        return matches;
    }

    public MatchesAdapter(List<Match> matches) {
        this.matches = matches;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
            MatchItemBinding binding = MatchItemBinding.inflate(LayoutInflater.from(parent.getContext()),parent,false);
        return new ViewHolder(binding);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
       Context context = holder.itemView.getContext();
        Match match = matches.get(position);
        //adapta os dados da partida recuperada api
        holder.binding.tvHomeTeamName.setText(match.getHometeam().getName());
        holder.binding.tvAwayTeamName.setText(match.getAwayTeam().getName());

        //bandeiras
        Glide.with(context).load(match.getHometeam().getImage()).circleCrop().into(holder.binding.ivHomeTeam);
        Glide.with(context).load(match.getAwayTeam().getImage()).circleCrop().into(holder.binding.ivAwayTeam);

        //score
        if(match.getHometeam().getScore()!= null) {
            holder.binding.tvHomeTeamScore.setText(match.getHometeam().getScore().toString());
        }

        if(match.getAwayTeam().getScore()!= null) {

            holder.binding.tvAwayTeamScore.setText(match.getAwayTeam().getScore().toString());
        }



    }

    @Override
    public int getItemCount() {
        return matches.size();
    }

    public static class ViewHolder extends RecyclerView.ViewHolder {
        private final MatchItemBinding binding;
        public ViewHolder(MatchItemBinding binding) {
            super(binding.getRoot());
            this.binding = binding;
        }
    }

}
