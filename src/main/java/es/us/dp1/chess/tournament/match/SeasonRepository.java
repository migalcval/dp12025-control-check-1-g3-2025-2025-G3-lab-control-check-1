package es.us.dp1.chess.tournament.match;

import java.util.Comparator;
import java.util.List;
import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface SeasonRepository extends CrudRepository<Season, Integer> {

    default List<Rating> getStandings(Season season) {
        return season.getRatings().stream()
            .sorted(Comparator.comparing(Rating::getElo).reversed())
            .toList();
    }

    List<Season> findAll();

    Optional<Season> findById(Integer id);

    Season save(Season season);
}