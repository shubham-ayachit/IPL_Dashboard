package com.practice.microservices.iplDashboard.repository;

import java.util.List;

import com.practice.microservices.iplDashboard.model.Match;

import org.springframework.data.domain.Pageable;
import org.springframework.data.repository.CrudRepository;

public interface MatchRepository extends CrudRepository<Match, Long> {
    
    List<Match> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pagable);

    default List<Match> findLatestMatches(String teamName, int count) {
        return findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, Pageable.ofSize(count));
    }

}
