package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
@Service
public class SeasonService {

    SeasonRepository repository;

    @Autowired 
    public SeasonService(Optional<SeasonRepository> repository) {
        this.repository = repository.isPresent()?repository.get():null;
    }

    @Transactional(readOnly = true)
    public List<Rating> getStandings(Season s) {
        return repository.getStandings(s);
    }

    @Transactional(readOnly = true)
    public List<Season> getAll() {
        return repository.findAll();
    }

    @Transactional(readOnly = true)
    public Season getById(Integer i) {
        return repository.findById(i).orElse(null);
    }

    @Transactional
    public Season save(Season season) {
        return repository.save(season);
    }
    
}
