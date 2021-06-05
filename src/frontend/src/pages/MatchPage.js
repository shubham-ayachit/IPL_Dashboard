import { React, useEffect, useState } from 'react';
import { useParams } from 'react-router-dom';
import { MatchDetailCard } from '../components/MatchDetailCard';
import { MatchSmallCard } from '../components/MatchSmallCard';

export const MatchPage = () => {

    const [match, setMatch] = useState([]);
    const { teamName, year } = useParams();

    useEffect(
        () => {
            const fetchMatches = async() => {
                const response = await fetch(`http://localhost:8080/team/${teamName}/matches?year=${year}`);
                const data = await response.json();
                console.log(data);
                setMatch(data);
                
            };

            fetchMatches();
        }, [teamName, year]
    );

  return (
    <div className="MatchPage">
      <h1>Match Detailes</h1>
      <h3>{teamName}</h3>
      {match.map(match => <MatchSmallCard teamName = {teamName} match={match} />)}
    </div>
  );
}