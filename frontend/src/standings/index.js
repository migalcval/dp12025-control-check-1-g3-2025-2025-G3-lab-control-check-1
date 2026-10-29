import React, { useEffect, useState } from "react";
import { Table } from "reactstrap";

export default function StandingsListing(props) {

    const [standings, setStandings] = useState([]);
    useEffect(() => {
        fetch(`/api/v1/seasons/${props.seasonId}/standings`)
            .then((response) => response.json())
            .then((data) => setStandings(data));
    }, [props.seasonId]);

    if (standings.length === 0) {
        return <h1>Loading...</h1>;
    }

    return (
        <Table>
            <thead>
                <tr>
                    <th>Player</th>
                    <th>Elo</th>
                    <th>Wins</th>
                    <th>Losses</th>
                    <th>Draws</th>
                    <th>%Win</th>
                </tr>
            </thead>
            <tbody>
                {standings.map((standing) => (
                    <tr key={standing.player.username}>
                        <td>{standing.player.username}</td>
                        <td>{standing.elo}</td>
                        <td>{standing.wins}</td>
                        <td>{standing.losses}</td>
                        <td>{standing.draws}</td>
                        <td>{standing.winRate}</td>
                    </tr>
                ))}
            </tbody>
        </Table>
    );
}