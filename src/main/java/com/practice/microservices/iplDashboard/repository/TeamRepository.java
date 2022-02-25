package com.practice.microservices.iplDashboard.repository;

import com.practice.microservices.iplDashboard.model.Team;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Component;

@Component
public interface TeamRepository extends CrudRepository<Team, Long> {

    Team findByTeamName(String teamName);
    
}
