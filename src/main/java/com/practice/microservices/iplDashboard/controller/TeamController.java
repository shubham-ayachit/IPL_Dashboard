package com.practice.microservices.iplDashboard.controller;

import java.time.LocalDate;
import java.util.List;

import com.practice.microservices.iplDashboard.model.Match;
import com.practice.microservices.iplDashboard.model.Team;
import com.practice.microservices.iplDashboard.repository.MatchRepository;
import com.practice.microservices.iplDashboard.repository.TeamRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
public class TeamController {

    @Autowired
    private TeamRepository teamRepository;
    
    @Autowired
    private MatchRepository matchRepository;

    @GetMapping("/team/{teamName}")
    public Team getTeam(@PathVariable String teamName) {
        Team team = this.teamRepository.findByTeamName(teamName);
        team.setMatches(matchRepository.findLatestMatches(teamName, 4));
        return team;
    }

    @GetMapping("/team/{teamName}/matches")
    public List<Match> getMatchesForTeam(@PathVariable String teamName, @RequestParam int year) {
        LocalDate startDate = LocalDate.of(year, 1 , 1);
        LocalDate endDate = LocalDate.of(year+1, 1 , 1);
        List<Match> findByTeam1OrTeam2AndDateContaining = this.matchRepository.findByTeamNameForGivenDate(teamName, startDate, endDate);
        return findByTeam1OrTeam2AndDateContaining;
    }


}