package es.us.dp1.chess.tournament.match;


import java.time.LocalDate;
import java.util.List;

import es.us.dp1.chess.tournament.model.BaseEntity;
import jakarta.persistence.Entity;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Transient;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.Getter;
import lombok.Setter;

@Entity 
@Getter
@Setter
public class Season extends BaseEntity {

    @NotNull 
    @NotBlank 
    @Size (min = 3, max = 50)
    String name;

    @NotNull
    LocalDate startDate;

    @NotNull
    LocalDate endDate;

    @OneToMany
    List<ChessMatch> matches;

    @OneToMany
    List<Rating> ratings;
}
