package es.us.dp1.chess.tournament.match;

import es.us.dp1.chess.tournament.model.BaseEntity;
import es.us.dp1.chess.tournament.user.User;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter
@Setter
public class Rating extends BaseEntity {
    
    @NotNull 
    @Min (100)
    Integer elo;

    @NotNull 
    @Min(0)
    Integer wins;

    @NotNull 
    @Min(0)
    Integer losses;

    @NotNull 
    @Min(0)
    Integer draws;
    
    @Transient
    public Double getWinRate() {
        int totalGames = wins + losses;
        if (totalGames == 0) {
            return 0.0;
        }
        return (double) wins / totalGames;
    }

    @ManyToOne
    @NotNull
    User player;
}
