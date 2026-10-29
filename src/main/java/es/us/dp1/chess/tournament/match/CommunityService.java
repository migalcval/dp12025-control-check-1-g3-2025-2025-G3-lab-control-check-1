package es.us.dp1.chess.tournament.match;

import java.util.List;
import java.util.Objects;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import es.us.dp1.chess.tournament.exceptions.MalformedCommunityException;

@Service
public class CommunityService {
    CommunityRepository repo;

    @Autowired
    public CommunityService(CommunityRepository repo) {
        this.repo = repo;
    }


    @Transactional(readOnly = true)
    public Optional<Community> getCommunityById(Integer id) {
        return repo.findById(id);
    }

    @Transactional (rollbackFor = MalformedCommunityException.class)
    public Community save(Community m) throws MalformedCommunityException {
        if (m.getMembers().size() < 2) {
            throw new MalformedCommunityException("A community must have at least 2 members.");
        }
        if (m.getMembers().size() > m.maxPlayers) {
            throw new MalformedCommunityException("A community must have at most " + m.maxPlayers + " members.");
        }
        for (var member : m.getMembers()) {
            boolean memberHasMatch = m.getSeasons().stream()
                .flatMap(season -> season.getMatches().stream())
                .anyMatch(match -> Objects.equals(member, match.getWhitePlayer())
                    || Objects.equals(member, match.getBlackPlayer()));
            if (!memberHasMatch) {
                throw new MalformedCommunityException("Every community member must participate in at least one match.");
            }
        }
        return repo.save(m);
    }
    
    @Transactional(readOnly = true)
    public List<Community> getCommunities() {
        return repo.findAll();
    }    
    }
