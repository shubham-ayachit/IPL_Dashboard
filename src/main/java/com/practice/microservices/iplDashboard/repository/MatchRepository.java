package com.practice.microservices.iplDashboard.repository;

import java.time.LocalDate;
import java.util.List;

import com.practice.microservices.iplDashboard.model.Match;

import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;

public interface MatchRepository extends CrudRepository<Match, Long> {
    
    List<Match> findByTeam1OrTeam2OrderByDateDesc(String team1, String team2, Pageable pagable);
    
    @Query("select m from Match m where (m.team1 = :teamName or m.team2 = :teamName) AND m.date between :startDate and :endDate")
    List<Match> findByTeamNameForGivenDate(@Param("teamName") String teamName, @Param("startDate")LocalDate startDate, @Param("endDate")LocalDate endDate);

    default List<Match> findLatestMatches(String teamName, int count) {
        return findByTeam1OrTeam2OrderByDateDesc(teamName, teamName, Pageable.ofSize(count));
    }

}
