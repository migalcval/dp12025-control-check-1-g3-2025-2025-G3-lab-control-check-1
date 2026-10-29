package es.us.dp1.chess.tournament.match;


import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import es.us.dp1.chess.tournament.user.UserService;

@RestController
@RequestMapping ("/api/v1/seasons") 
public class SeasonController {

    UserService userService;
    SeasonService seasonService;


    public SeasonController(SeasonService service,UserService userService){
        this.seasonService=service;
        this.userService=userService;    
    }

    @GetMapping 
    public ResponseEntity<List<Season>> getAllSeasons() {
        List<Season> seasons = seasonService.getAll();
        return ResponseEntity.ok(seasons);
    }

    @GetMapping("/{id}/standings")
    public ResponseEntity<List<Rating>> getStandingsById(@PathVariable Integer id, Authentication authentication) {
        if (authentication == null || authentication.getAuthorities().stream()
                .noneMatch(authority -> "PLAYER".equals(authority.getAuthority()))) {
            return ResponseEntity.status(403).build();
        }
        Season season = seasonService.getById(id);
        if (season == null) {
            return ResponseEntity.notFound().build();
        }
        List<Rating> standings = seasonService.getStandings(season);
        return ResponseEntity.ok(standings);
    }

}