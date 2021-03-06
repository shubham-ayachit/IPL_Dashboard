import { React } from 'react';
import { Link } from 'react-router-dom';

export const MatchSmallCard = ({teamName, match}) => {
  if(!match) return null;
  const otherTeam = teamName === match.team1 ? match.team2 : match.team1;
  const otherTeamRoute = `/teams/${otherTeam}`;
  return (
    <div className="MatchSmallCard">
      <h3>Vs. 
        <Link to={otherTeamRoute}>
        {otherTeam}
        </Link>
      </h3>
      <p>{match.date}</p>
      <p>{match.matchWinner} won by {match.resultMargin} {match.result}</p>
    </div>
  );
}