package es.us.dp1.chess.tournament.match;

import java.util.Optional;

import org.springframework.data.repository.CrudRepository;

public interface RatingRepository extends CrudRepository<Rating, Integer> {

    Optional<Rating> findById(int i);
    
}
